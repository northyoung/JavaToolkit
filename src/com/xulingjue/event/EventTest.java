package com.xulingjue.event;

public class EventTest {
	public static void main(String [] agrs){
		Compute compute = new Compute();
		
		compute.addComputeListener(new ComputeEventListener(){

			@Override
			public void complate(ComputeEvent ce) {
				// TODO Auto-generated method stub
				System.out.println("hi, compute is complate!");
			}

			@Override
			public void propertyEventy(ComputeEvent ce) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		compute.start();
		
	}
}
