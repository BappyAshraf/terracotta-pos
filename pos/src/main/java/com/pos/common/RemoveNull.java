package com.pos.common;

public class RemoveNull {

	public String nullRemove(String a){
		
		
		if(a == null){
			a = "";
		}else if(a == "null"){
			a = "";
		}
		
		return a;
	}
}
