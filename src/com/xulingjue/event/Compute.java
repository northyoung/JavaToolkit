package com.xulingjue.event;

public class Compute extends ComputeEventAdapter{
	private int i=100;
	private int result;
	
	public void start(){
		for(;i>0;i--){
			result += i;
			if(result>1000)
				this.notifyComlateEvent();
		}
	}
}
