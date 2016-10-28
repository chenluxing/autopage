package com.lxc.autopage.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;

/**
 * 节点Leader选举
 * Created by chenlx
 * on 2016/10/28.
 */
public abstract class LeaderSelectorClient extends LeaderSelectorListenerAdapter implements Closeable {

    // 选举节点
    private final String path;
    // 选举器
    private final LeaderSelector leaderSelector;

    public LeaderSelectorClient(CuratorFramework client, String path){
        this.path = path;   // 设置选举节点
        this.leaderSelector = new LeaderSelector(client, path, this);   // 构造选举器
        // 设置当前listener有机会重新获得LeaderShip; 如果不设置，放弃了的listener是不会再变成leader
        this.leaderSelector.autoRequeue();
    }

    abstract void doWork();

    /**
     *
     * @param curatorFramework
     * @throws Exception
     */
    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        doWork();
    }

    /**
     * 开始参与选举
     * @throws IOException
     */
    public void start() throws IOException {
        leaderSelector.start();
    }

    /**
     * 结束选举
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        this.leaderSelector.close();
    }
}
