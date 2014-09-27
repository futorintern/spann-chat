package com.spann.datametica.controller;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spann.datametica.core.UserDao;
import com.spann.datametica.models.User;

@Controller
public class ChatController {

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView chat(@RequestParam(value = "email") String email) throws InterruptedException, KeeperException, IOException {
		User user = new User();
		user.setEmail(email);
		user.setTopic(email);
		user.setName(email);
		user.setStatus("available");
		
		UserDao userDao = new UserDao();
		userDao.loginUser(user);
		
		ModelAndView mv = new ModelAndView("chat");
		mv.addObject("email",email);
		return mv;
	}
}
