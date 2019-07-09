package com.it.test;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * Author:曾志鹏
 * Date:2019/7/3
 * Time:20:19
 */
public class testWatch {

    @Test
    public void getDate() throws IOException, KeeperException, InterruptedException {

        String hostPaort ="192.168.52.130:2181";

        ZooKeeper zooKeeper = new ZooKeeper(hostPaort, 1000, null);

        Stat exists = zooKeeper.exists("/nodes", new MyWatch());

        Thread.sleep(8000);

    }
}
