package com.spann.zookeeper;

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
	public ActiveKeyValueStore(ZooKeeper zk) {
		this.zk = zk;
	}
	
	public void write(String path, String value) throws InterruptedException,
			KeeperException {
		try {

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

	public String read(String path, Watcher watcher)
			throws InterruptedException, KeeperException {
		List<String> childrens = zk.getChildren(path, watcher);
		return childrens.toString();
//		byte[] data = zk.getData(path, watcher, null/* stat */);
//		return new String(data, CHARSET);
	}
	
}