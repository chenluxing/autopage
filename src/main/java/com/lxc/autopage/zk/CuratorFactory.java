package com.lxc.autopage.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.Assert;

/**
 * Created by chenlx
 * on 2016/10/28.
 */
public class CuratorFactory {

    private static CuratorFramework client;

    synchronized public static CuratorFramework getClient() {
        synchronized(client) {
            if (client == null) {
                String zkHost = "";
                RetryPolicy rp = new ExponentialBackoffRetry(1000, 3);
                CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(zkHost);
                builder.connectionTimeoutMs(5000);
                builder.sessionTimeoutMs(5000);
                builder.retryPolicy(rp);
                client = builder.build();
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
}
