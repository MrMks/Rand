package com.github.MrMks.rand.group;

import com.github.MrMks.rand.RandExecutor;
import com.github.MrMks.rand.utils.NumberResult;

import java.util.LinkedHashMap;
import java.util.Set;

public abstract class Group {
	static private LinkedHashMap<String,Group> list = new LinkedHashMap<>();
	static public Group getGroup(String groupName){
	    return list.get(groupName);
    }

    static public boolean createGroup(String name, int returnType, int groupType, double a, double b, int doAction){
	    Group group = groupType == 1 ? new SameCroup(returnType,a,b) : new DifferentGroup(returnType,a,b);
	    if (list.containsKey(name)){
	        Group old = list.get(name);
	        if (old.min != group.min || old.max != group.max || old.groupType != groupType || old.returnType != returnType){
	            if (doAction == 0) {
	            	return false;
				} else {
	                list.remove(name);
	                list.put(name,group);
	                return true;
                }
            } else if(old.min == group.min && old.max == group.max){
	            if(doAction == 1) old.action();
                return true;
            }
        } else {
	        list.put(name,group);
	        return true;
        }
        return false;
    }

    static public Set<String> getAllGroupsName(){
	    return list.keySet();
    }
    
    //

	private double min,max;
	private int returnType;
	private int groupType;
	boolean flag = false;
	public Group(int returnType,double a,double b,int groupType) {
		min = Math.min(a,b);
		max = Math.max(a,b);
		this.returnType = returnType;
		this.groupType = groupType;
	}
	
	public abstract String getResult();
	public abstract void action();

	double getMax(){return max;}
	double getMin(){return min;}
	NumberResult getNumberResult(){
		return RandExecutor.getResult(min,max,flag ? 0 : returnType);
	}
}
