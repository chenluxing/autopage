package com.lxc.autopage.base.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by admin on 2016/8/23.
 */
public class ClassUtils {

    public static List<Class<?>> getClasses(String packageName){

        List<Class<?>> classes = new ArrayList<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名称，并进行替换
        String packageDirName = packageName.replace('.','/');
        // 定义一个枚举的集合，进行循环来处理目录下的文件
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();

                String protocol = url.getProtocol();
                if ("file".equals(protocol)){
                    String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                    // 以文件的形式扫描整个包下的文件，并添加到集合中
                    findAddAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if("jar".equals(protocol)){
                    // 如果是jar包文件
                    JarFile jar;
                    try {
                        jar = ((JarURLConnection)url.openConnection()).getJarFile();
                        // 从此jar包得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 循环遍历
                        while (entries.hasMoreElements()){
                            // 获取jar里面的一个实体， 可以是目录或一些jar包里面的其它文件 如META-INF文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以“/”开头的
                            if (name.charAt(0) == '/'){
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)){
                                int idx = name.lastIndexOf('/');
                                // 如果是以“/”结尾 是一个包
                                if (idx != -1){
                                    // 获取包名，把“/”替换成“.”
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                //如果可以迭代下去，并且是一个包
                                if ((idx != -1) || recursive){
                                    // 如果是一个.class文件 并且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()){
                                        // 去掉后面的“.class”获取真正的类型
                                        String className = name.substring(packageName.length() + 1, name.length()-6 );
                                        try {
                                            classes.add(Class.forName(packageName + '.' + className));
                                        }catch (ClassNotFoundException e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return classes;
    }

    public static void findAddAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){
        // 获取此包的目录，建立一个File
        File dir = new File(packagePath);
        // 如果文件不存在或者也不是目录，直接返回
        if (!dir.exists() || !dir.isDirectory()){
            return;
        }
        // 如果存在，就获取包下面的所有文件，包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles){
            if (file.isDirectory()){
                findAddAddClassesInPackageByFile(packageName+"."+file.getName(), file.getAbsolutePath(), recursive, classes);
            }else{
                // 如果是java类文件，去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try{
                    classes.add(Class.forName(packageName+ "." + className));
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void  main(String[] args){
        ClassUtils.getClasses("com.lxc.autopage");
    }

}
