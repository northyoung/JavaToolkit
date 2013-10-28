/**
 * 自定义事件demo
 */
package com.xulingjue.event;

public class ComputeEvent extends java.util.EventObject{

	public static final int STEPOVER = 1; // 申明事件标志，单步计算完成
	public static final int CALOVER = 2; // 计算完成标志
	public static final int CALCANCEL = 3; // 计算取消标志
	public static final int TYPEERROR = -1;
	
	public ComputeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	

}
