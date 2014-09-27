package com.spann.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MyWatcher implements Watcher {
	
	String znode;
	public MyWatcher(String znode) {
		this.znode = znode;
	}

	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            // We are are being told that the state of the
            // connection has changed
            switch (event.getState()) {
	            case SyncConnected:
	                // In this particular example we don't need to do anything
	                // here - watches are automatically re-registered with 
	                // server and any watches triggered while the client was 
	                // disconnected will be delivered (in order of course)
	            	System.out.println("sync connected");
	                break;
	            case Expired:
	            	System.out.println("dead state");
	                // It's all over
	                break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                // Something has changed on the node, let's find out
            	System.out.println("inside else");
            }
        }
	}

}
