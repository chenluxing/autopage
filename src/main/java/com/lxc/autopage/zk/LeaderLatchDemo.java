package com.lxc.autopage.zk;

import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * Created by chenlx
 * on 2016/10/31.
 */
public class LeaderLatchDemo {

    public static  void main(String[] args) {
        try {
            LeaderLatch leader = new LeaderLatch(CuratorFactory.getClient(), "/leader");
            LeaderLatchListener listener = new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    // 成为leader， do some thing
                }

                @Override
                public void notLeader() {

                }
            };
            leader.addListener(listener);

            leader.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
