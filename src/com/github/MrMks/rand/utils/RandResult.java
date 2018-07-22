package com.github.MrMks.rand.utils;

public class RandResult {
	private boolean m;
    private Object r;

    public RandResult(boolean m, Object r) {
        this.m = m;
        this.r = r;
    }

    public boolean find() {
        return m;
    }

    public String asString() {
        return (String) r;
    }

    public String[] asArray() {
        return (String[]) r;
    }
}
