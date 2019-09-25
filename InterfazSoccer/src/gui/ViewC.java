package gui;

import java.awt.Graphics;

import controller.ControllerClient;
import model.Game;

public interface ViewC {
	   final String SEND = "SEND";
	    
	    public void enableSend();
	    public void disableSend();
	    public void addMessage(String msg);
	    public void initGame();
	    public void paintBall(Graphics gra, Game gam, int i, int j);
	    public void paintPlayer(Graphics gra,Game gam, int i, int j);
	    public void paintComponents(Graphics gra);
	    public int hightGoals();
	    public void initComponents();
	    public void setController(ControllerClient control);
	    public void makeVisible();
	    public void inicialize();
	    public void inGame();
}