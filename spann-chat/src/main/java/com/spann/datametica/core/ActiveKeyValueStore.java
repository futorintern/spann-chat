package com.spann.datametica.core;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class ActiveKeyValueStore {

	private static final Charset CHARSET = Charset.forName("UTF-8");
	ZooKeeper zk;
	String baseTopic;
	
	public ActiveKeyValueStore(ZooKeeper zk) {
		this.baseTopic = Config.getConfig().baseZNode;
		this.zk = zk;
	}
	
	public void write(String path, String value) throws InterruptedException,
			KeeperException {
		try {

			path = baseTopic+path;
			Stat stat = zk.exists(path, false);
			if (stat == null) {
				zk.create(path, value.getBytes(CHARSET), Ids.OPEN_ACL_UNSAFE,
						CreateMode.PERSISTENT);
			} else {
				zk.setData(path, value.getBytes(CHARSET), -1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String read(String path)
			throws InterruptedException, KeeperException {
		path = baseTopic+path;
		List<String> childrens = zk.getChildren(path, Config.getConfig().myWatcher);
		return childrens.toString();
//		byte[] data = zk.getData(path, watcher, null/* stat */);
//		return new String(data, CHARSET);
	}
	
	public List<String> getChildren() throws KeeperException, InterruptedException {
		List<String> children = zk.getChildren(baseTopic, Config.getConfig().myWatcher);
		return children;
	}
	
}