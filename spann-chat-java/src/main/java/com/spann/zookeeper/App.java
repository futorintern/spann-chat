package com.spann.zookeeper;

import org.apache.zookeeper.ZooKeeper;

public class App {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		
		try {
			String znode = "/hello";
			
			MyWatcher watcher = new MyWatcher(znode);
			ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, watcher);
			
			ActiveKeyValueStore akvs = new ActiveKeyValueStore(zk);
			//akvs.write(znode, "value");
			
			System.out.println(akvs.read(znode, watcher));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
