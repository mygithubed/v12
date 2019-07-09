package com.it.test;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * Author:曾志鹏
 * Date:2019/7/3
 * Time:17:49
 */
public class Test_api {

    @Test
    public void tests() throws IOException, KeeperException, InterruptedException {
        String hostPort = "192.168.52.130:2181";
        ZooKeeper zooKeeper = new ZooKeeper(hostPort, 1000, null);

        System.out.println(zooKeeper);

    }

    @Test
    public  void createZNode() throws KeeperException, InterruptedException, IOException {
        String hostPort = "192.168.52.130:2181";
        ZooKeeper zooKeeper = new ZooKeeper(hostPort, 1000, null);
        //创建节点
        String znode = zooKeeper.create("/test", "999".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(znode);
    }

    @Test
    public void getZnode() throws IOException, KeeperException, InterruptedException {
        String hostPort = "192.168.52.130:2181";
        ZooKeeper zooKeeper = new ZooKeeper(hostPort, 1000, null);

        Stat exists = zooKeeper.exists("/test", null);
        System.out.println(exists);
    }

    @Test
    public  void getData() throws IOException, KeeperException, InterruptedException {
        String hostPaort ="192.168.52.130:2181";
        ZooKeeper zooKeeper = new ZooKeeper(hostPaort, 1000, null);
        byte[] data = zooKeeper.getData("/test", false, null);

        System.out.println(data);

    }
}
