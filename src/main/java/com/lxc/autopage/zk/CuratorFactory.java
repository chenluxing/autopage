package com.lxc.autopage.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;

import java.util.Properties;

/**
 * Created by chenlx
 * on 2016/10/28.
 */
public class CuratorFactory {

    static String ZK_HOST = "zkHost";
    static String CONNECTION_TIMEOUT = "connectionTimeout";
    static String SESSION_TIMEOUT = "sessionTimeout";

    private static CuratorFramework client;

    synchronized public static CuratorFramework getClient() {
        synchronized(client) {
            if (client == null) {
                Properties zkProp = getZkProperties();
                String zkHost = zkProp.getProperty(ZK_HOST);
                if (StringUtils.isNotEmpty(zkHost)) {
                    int connectionTimeout = 5000;
                    int sessionTimeout = 5000;
                    // 读取connection超时时间
                    if (StringUtils.isNotEmpty(zkProp.getProperty(CONNECTION_TIMEOUT))) {
                        connectionTimeout = Integer.valueOf(zkProp.getProperty(CONNECTION_TIMEOUT));
                    }
                    // 读取session超时时间
                    if (StringUtils.isNotEmpty(zkProp.getProperty(SESSION_TIMEOUT))) {
                        sessionTimeout = Integer.valueOf(zkProp.getProperty(SESSION_TIMEOUT));
                    }
                    // 设置重试策略
                    // 提供了3个重试策略：LinearRetry：水平式重试，重试间隔时间相同
                    // ExponentialRetry:递增式重试，每一次重试时间是前一次的2倍
                    // NoRetry：不重试，可用来配合前面两个
                    RetryPolicy rp = new ExponentialBackoffRetry(1000, 3);
                    CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkHost);
                    builder.connectionTimeoutMs(connectionTimeout);
                    builder.sessionTimeoutMs(sessionTimeout);
                    builder.retryPolicy(rp);
                    client = builder.build();
                }
                else {
                    throw new IllegalArgumentException("zookeeper host is not null !");
                }
            }
            return client;
        }
    }

    /**
     * 校验节点存在
     * @param path
     * @return
     */
    public static boolean checkPath(String path) {
        try{
            client.start();
            Stat stat = client.checkExists().forPath(path);
            if (stat != null){
                return true;
            }
        } catch (Exception e) {
            CloseableUtils.closeQuietly(client);
        }
        return false;
    }

    /**
     * 创建节点
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public static void createPath(String path, byte[] data) throws Exception {
        if (StringUtils.isNotEmpty(path)) {
            try {
                getClient().start();
                if (checkPath(path)){
                    getClient().create().forPath(path, data);
                } else {
                    getClient().setData().forPath(path, data);
                }
            } finally {
                CloseableUtils.closeQuietly(client);
            }
        }
    }

    synchronized private static Properties getZkProperties(){
        return null;
    }
}
