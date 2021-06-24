package com.cn;


public class Test {

	public static void main(String[] args) {
		Object a = new Integer[]{1,2,3,4,5,6,8,9};
        if(a instanceof String[]){
            System.out.println("ss");
        }
        if(a.getClass().isArray()){
            System.out.println("yy");
        }
	}

}
