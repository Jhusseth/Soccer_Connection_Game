package controller;

import gui.ViewC;

public class TimeR extends Thread {
	private int time;
	private ViewC vista;
	public TimeR(ViewC vista2) {
		this.vista = vista2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
	
			// TODO Auto-generated method stub
			for(int i = 0; i<45;i++) {
				try {
					Thread.sleep(1000);
					time += 1;
					vista.setTime(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		
	}
	
}

