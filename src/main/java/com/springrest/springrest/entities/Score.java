package com.springrest.springrest.entities;

public class Score {
	int FirstInn ;
	int SecInn ;
	
	public void set(int a , int b) {
		this.FirstInn = a;
		this.SecInn = b;
	}
	
	public int getFI() {
		return FirstInn;
	}
	
	public int getSI() {
		return SecInn;
	}
	
}
