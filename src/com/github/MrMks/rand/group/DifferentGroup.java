package com.github.MrMks.rand.group;

import com.github.MrMks.rand.utils.NumberResult;

import java.util.ArrayList;

public class DifferentGroup extends Group{

    //byte 0 refer to int and 1 refer to double;

    DifferentGroup(int type, double a, double b){
        super(type,a,b,0);
        double c = Math.ceil(getMin());
        double d = Math.floor(getMax());
        this.length = d - c + 1;
        }

    private double length;
    private ArrayList<Number> arrayList = new ArrayList<>();
	@Override
	public String getResult() {
	    NumberResult result;
	    do{
	        flag = false;
	        if(arrayList.size() >= length){
	            flag = true;
	            result = getNumberResult();
	            break;
            }
	    	result = getNumberResult();
        } while (arrayList.contains(result.getNumber()));
	    if(!flag) arrayList.add(result.getNumber());
	    return result.toString();
	}

    @Override
    public void action() {
	    arrayList.clear();
    }

}
