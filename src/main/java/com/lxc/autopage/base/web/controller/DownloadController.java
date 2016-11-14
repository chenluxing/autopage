package com.lxc.autopage.base.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 文件下载
 * Created by chenlx
 * on 2016/10/24.
 */
@Controller("/")
public class DownloadController extends BaseController {

    /**
     * 下载到图片空间
     */
    @RequestMapping(value = "/download")
    public void download(String filePath, String fileName, Integer isDelete, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(filePath)) {
            File file = new File(filePath);
            if (file != null) {
                ServletOutputStream out = null;
                InputStream is = null;
                try {
                    response.reset();
                    out = response.getOutputStream();
                    //乱码处理
                    String userAgent = request.getHeader("User-Agent");
                    byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
                    fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
                    response.setHeader("Connection", "close");

                    is = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                            if (1 == isDelete){
                                file.delete();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
