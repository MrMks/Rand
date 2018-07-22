package com.github.MrMks.rand.utils;

public class NumberResult {
	private Number n;
	private int type;

	public NumberResult(Number n, int type){
		this.n= n;
		this.type = type;
	}
	
	public String toString() {
		return type == 1 ? String.valueOf(n.longValue()) : String.valueOf(n.doubleValue());
	}
	
	public Number getNumber() {
		return n;
	}

}
