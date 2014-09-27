package com.spann.datametica.core;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

public class Config {
	
	public ZooKeeper zk;
	public MyWatcher myWatcher;
	public String baseZNode;
	
	private ActiveKeyValueStore activeKeyValueStore;
	
	private static Config c;
	
	private Config() {
		baseZNode = "/status";
		myWatcher = new MyWatcher(baseZNode);
	}
	
	public ZooKeeper getZooKeeperInstance() throws IOException {
		if(zk == null) {
			zk = new ZooKeeper("localhost:2181", 3000, myWatcher);
		}
		return zk;
	}
	
	public ActiveKeyValueStore getActiveKeyValueStore() throws IOException {
		if(activeKeyValueStore == null) {
			activeKeyValueStore = new ActiveKeyValueStore(getZooKeeperInstance());
		}
		return activeKeyValueStore;
	}
	
	public static Config getConfig() {
		if(c == null) {
			c = new Config();
		}
		return c;
	}

}
