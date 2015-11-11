package com.aturhelp.common;

public final class Random {

	private static Random random = new Random();
	
	public static Random getInstance() {
		return random;
	}
	
	public synchronized Long nextId() {
		
		long currentTime = System.currentTimeMillis();
		return currentTime;
	}
	
}
