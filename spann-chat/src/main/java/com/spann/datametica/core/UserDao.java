package com.spann.datametica.core;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;

import com.spann.datametica.models.User;

public class UserDao {

	ActiveKeyValueStore store;
	
	public UserDao() throws IOException {
		store = Config.getConfig().getActiveKeyValueStore();
	}
	
	public void loginUser(User user) throws InterruptedException, KeeperException {
		store.write("/"+user.getEmail(), "{'name':'"+user.getName()+"','email':'"+user.getEmail()+
				"','topic':'"+user.getTopic()+"','status':'"+user.getStatus()+"'}");
	}
	
	public List<String> getAllUsers() throws KeeperException, InterruptedException {
		List<String> childrens = store.getChildren();
		return childrens;
	}
}
