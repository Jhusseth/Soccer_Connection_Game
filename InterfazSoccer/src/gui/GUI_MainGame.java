package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.Options_Panel;
import controller.ControllerClient;
import controller.ControllerGame;
import model.Client;
import model.Game;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GUI_MainGame extends JFrame implements KeyListener , MouseListener,ViewC{
	
	/**
	 * 
	 */
	
	
	private Options_Panel op;
	private JPanel panel;
	
	private ControllerGame game;
	WindowListener exitListener;
	private int score1;
	private int score2;
	private static final long serialVersionUID = 1L;
	ControllerClient control;
	private JTextField clientInformation;
	private JButton aceptBtn;
	private static Client client;
	private JLabel infoLabel;

	public int getPuntaje2() {
		return score2;
	}


	public void setPuntaje2(int puntaje2) {
		this.score2 = puntaje2;
	}
	
	public int getPuntaje1() {
		return score1;
	}


	public void setPuntaje1(int puntaje1) {
		this.score1 = puntaje1;
	}


	public GUI_MainGame() {
		initComponents();

//	addKeyListener(new KeyAdapter() {
//
//
//	@Override
//	public void keyTyped(KeyEvent key) {
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//	System.out.println(e.getKeyCode());
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//	
//	}
//	});
	}
	@Override
	public void initComponents() {
		this.setSize(860,487);
		this.setTitle("SFCB                                                                                  <<<<<< SOCCER FULL HD 4k GAME >>>>>>>");
		this.op = new Options_Panel(this);
		addMouseListener(this);
		addKeyListener(this);
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		infoLabel = new JLabel("Ingresar el nick");
		aceptBtn = new JButton("Nick");
		aceptBtn.setSize(50, 50);
        clientInformation=new JTextField();
        clientInformation.setSize(2,2);
        
        aceptBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.sendMessage(clientInformation.getText());
			}
		});
       panel.add(infoLabel,BorderLayout.NORTH);
       panel.add(clientInformation,BorderLayout.CENTER);
       panel.add(aceptBtn,BorderLayout.SOUTH);
       add(panel,BorderLayout.CENTER);
//		this.init();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setFocusable(true);
	}
	
	@Override
	public void inGame(){
		
	
	
		panel.removeAll();
		panel.setBackground(SystemColor.window);
		panel.setLayout(new BorderLayout());
		ImageIcon mg = new ImageIcon("data/Field.png");
		JLabel field = new JLabel(mg);
		
		panel.add(field, BorderLayout.CENTER);
		panel.add(op,BorderLayout.SOUTH);
		
		add(panel, BorderLayout.CENTER);
//		getContentPane().add(op, BorderLayout.SOUTH);
		
		score1 =0;
		score2=0;
		
		game = new ControllerGame(this);
		setResizable(false);
		makeVisible();
	}
	
	
	public Options_Panel getOp() {
		return op;
	}


	public void setOp(Options_Panel op) {
		this.op = op;
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}


//	public static void main(String[] args){
//		
//		GUI_MainGame window = new GUI_MainGame();
//		window.setVisible(true);
//		
//	}
	@Override
	public void paintBall(Graphics g,Game game, int x, int y){
		Graphics2D g2 = (Graphics2D) g;	
		g2.drawImage(game.getBall().getImg().getImage(),x, y,20,20, this);
	}
	
	@Override
	public void paintPlayer(Graphics g,Game game,int x, int y){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(game.getPlayer().getAvatar().getImage(), x, y,50,75, this);
		
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;	
		g2.drawImage(game.getGame().getBall().getImg().getImage(),game.getGame().getBall().getPosX(),game.getGame().getBall().getPosY(),20,20, this);
		
		g2.drawImage(game.getGame().getPlayer().getAvatar().getImage(),game.getGame().getPlayer().getPosX(),game.getGame().getPlayer().getPosY(),50,75, this);
		
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Verdana",Font.BOLD,14 ));
		g2.drawString("Player1: "+score1, 54, 47);
		g2.drawString("Player2: "+score2, 725, 47);
		
		if(hightGoals()==5){
			JOptionPane.showMessageDialog(null, "Match Finished");
			game.getGame().getPlayer().setWinner(true);
			game.getGame().getBall().setStopped(true);
			
			game.setGame(new Game());
			setPuntaje2(0);
			setPuntaje1(0);
		}
		
	}

	@Override
	public void initGame() {
		game.initGame();
	}
	
	public void test(){
		game.prueba();
	}


	@Override
	public void keyPressed(KeyEvent key) {
//		System.out.println(key.getKeyCode());
		
		int kick =3;
		if(key.getKeyCode() == 10){
			initGame();
		}
		else if(key.getKeyCode()==38){
			game.move();
			game.getGame().getPlayer().setAddres(game.getGame().getPlayer().UP);
			kick =game.getGame().getPlayer().UP;
//			repaint();
		}
		else if(key.getKeyCode()==40){
			game.move();
			game.getGame().getPlayer().setAddres(game.getGame().getPlayer().DOWN);
			kick =game.getGame().getPlayer().DOWN;
//			repaint();
		}
		else if(key.getKeyCode()==37){
			game.move();
			game.getGame().getPlayer().setAddres(game.getGame().getPlayer().LEFT);
			kick =game.getGame().getPlayer().LEFT;
//			repaint();
		}
		else if(key.getKeyCode()==39){
			game.move();
			game.getGame().getPlayer().setAddres(game.getGame().getPlayer().RIGHT);
			kick =game.getGame().getPlayer().RIGHT;
//			repaint();
		}
		
		else if(key.getKeyCode()==32){
			game.shot(kick);
//			repaint();
			
		}
		else if(key.getKeyCode()==82){
			paintComponents(this.getGraphics());
		}
		
		else if(key.getKeyCode()==27){
			System.exit(0);
		}
		
		
//		
	}
	@Override
	public int hightGoals(){
		
		int bgy =game.getGame().getBall().getPosY();
		
		
		if(game.getGame().getBall().getPosX()<=7){
			if(186<bgy && bgy<317){
				
				score2+=1;
				game.getGame().goal();
				game.getGame().getBall().setAddres(game.getGame().getBall().RIGHT);
			}
		}

		
		if(game.getGame().getBall().getPosX()>=840){
			if(186<bgy && bgy<317){
				score1+=1;
				game.getGame().goal();
				game.getGame().getBall().setAddres(game.getGame().getBall().LEFT);
			}
		}
		
		
		
		if(score1>score2){
			return score1;
		}
		else{
			return score2;
		}
	}


	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + " " + e.getY());
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void enableSend() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void disableSend() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addMessage(String msg) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void setController(ControllerClient control) {
		this.control = control;
		
	}


	@Override
	  public void makeVisible() {
        setVisible(true);
   }
	


	@Override
	public void inicialize() {
		// TODO Auto-generated method stub
		
	}






}
