package com.it.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Author:曾志鹏
 * Date:2019/7/3
 * Time:20:16
 */
public class MyWatch implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("根节点发生人了变化："+watchedEvent.getPath());
        System.out.println("发生了什么变化:"+watchedEvent.getType());
    }
}
