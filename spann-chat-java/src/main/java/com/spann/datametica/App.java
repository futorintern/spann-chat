package com.spann.datametica;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Producer p = new Producer("test-topic");
		p.start();
		
		Consumer c = new Consumer("test-topic");
		c.start();
	}

}
