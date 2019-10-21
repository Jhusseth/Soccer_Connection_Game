package GUI_Server_Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Client;
import controller.ControllerClient;
import server.Player;

public class Client_GUI extends JFrame implements ViewC, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Field_Panel field;
	private ControllerClient cl;
	
	private Player py;
	private Player py2;
	private JTextField clientInformation;
	private JButton aceptBtn;
	private JLabel infoLabel;
	private JPanel panel;
	
	private int score1;
	private int score2;
	private int time;
	
	public Client_GUI() {
		
		addWindowListener(new java.awt.event.WindowAdapter()
		{
		public void windowClosing(java.awt.event.WindowEvent evt)
		{
		cl.disconnectClient();
		}
		});
		initComponents();
		
		field = new Field_Panel(this);
		setFocusable(true);
		addKeyListener(this);
	}
	
	@Override
	public void initComponents() {
		setSize(30,113);
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
				py = new Player(clientInformation.getText(), 333,253 );
				clientInformation.setEditable(false);
				aceptBtn.setEnabled(false);
				setSize(100,113);
				cl.sendMessage(clientInformation.getText());
				clientInformation.setText("ESPERANDO OTRO JUGADOR...");
			}
		});
       panel.add(infoLabel,BorderLayout.NORTH);
       panel.add(clientInformation,BorderLayout.CENTER);
       panel.add(aceptBtn,BorderLayout.SOUTH);
       add(panel,BorderLayout.CENTER);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setFocusable(true);
	}
	
	public void playGame() {
		remove(panel);
		score1 =0;
		score2=0;
		setSize(860,487);
		setTitle("::: Client :::");
		setLayout(new BorderLayout());
		add(field,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		cl.setPlayer(py);
	}
	
	 @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana",Font.BOLD,10 ));
		g.drawString(cl.getPlayer().getName(), cl.getPlayer().getPosX(), cl.getPlayer().getPosY());
		g.drawString(cl.getPlayer2().getName(), cl.getPlayer2().getPosX(),cl.getPlayer2().getPosY());
		g.drawImage(cl.getBall().getImg().getImage(),cl.getBall().getPosX(),cl.getBall().getPosY(),20,20, this);
		g.drawImage(cl.getPlayer().getAvatar().getImage(),cl.getPlayer().getPosX(),cl.getPlayer().getPosY(),50,75, this);
		g.drawImage(cl.getPlayer2().getAvatar().getImage(),cl.getPlayer2().getPosX(),cl.getPlayer2().getPosY(),50,75, this);
		comprobate();
	}
	
	@Override
	public void setController(ControllerClient control) {
		cl = control;
	}
	
	
	public int getScore1() {
		return score1;
	}

	public void setScore1(int score1) {
		this.score1 = score1;
	}

	public int getScore2() {
		return score2;
	}

	public void setScore2(int score2) {
		this.score2 = score2;
	}

	public void showInformation() {
		remove(field);
		panel.removeAll();
		add(panel,BorderLayout.CENTER);
		infoLabel = new JLabel("Informacion del juego");
		String msg = "Puntaje de jugador 1 ( "+cl.getPlayer().getName() + " )"+ cl.getPlayer().getGoal() + "\n" +"Puntaje de jugador 2 ( "+cl.getPlayer2().getName() + " )"+ cl.getPlayer2().getGoal() + "\n"+ "Tiempo jugado: "+ getTime();
		JTextArea ar = new JTextArea(msg);
		panel.add(infoLabel,BorderLayout.NORTH);
		panel.add(ar,BorderLayout.CENTER);
		
	}
	
	
	public void comprobate() {
		if(hightGoals()==1||getTime() == 45){
			JOptionPane.showMessageDialog(null, "Match Finished");
			cl.getPlayer().setWinner(true);
			cl.getBall().setStopped(true);
			removeAll();
			showInformation();			
		}
		cl.catchBall();
	}
	
	public int getTime() {
		return time;
	}	
	
	public Player getPy() {
		return py;
	}


	public void setPy(Player py) {
		this.py = py;
	}

	
	public static void main(String[] args) {		
		ViewC view = new Client_GUI();
		Client model = new Client();
		ControllerClient control = new ControllerClient(view,model);		
		view.setController(control);
		model.setController(control);
		control.initializeActivity();
	}

	@Override
	public void makeVisible() {
		setVisible(true);
	}
	
	@Override
	public void playerMovement(int t, int p) {
		int kick =3;
		
		 if(t==38 && p == 1){
			kick =py.UP;
			py.setAddres(kick);
			py.move(cl.width(), cl.height());
			repaint();
			cl.setPlayer(py);
		}
		else if(t==40 && p == 1){
			kick =py.DOWN;
			py.setAddres(kick);
			py.move(cl.width(), cl.height());
			repaint();
			cl.setPlayer(py);
		}
		else if(t==37 && p == 1){
			kick =py.LEFT;
			py.setAddres(kick);
			py.move(cl.width(), cl.height());
			repaint();
			cl.setPlayer(py);
		}
		else if(t==39 && p == 1){
			kick =py.RIGHT;
			py.setAddres(kick);
			py.move(cl.width(), cl.height());
			repaint();
			cl.setPlayer(py);
		}
		
		else if(t==32){
			cl.shot(kick);
			repaint();
			
		}
		else if(t==82){
			repaint();
		}
		
		else if(t==27){
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		
		if(key.getKeyCode()==38){
			cl.sendGameInformation("38");	
		}
		if(key.getKeyCode()==40){
			cl.sendGameInformation("40");
			
		}
		if(key.getKeyCode()==37){
			cl.sendGameInformation("37");
		}
		if(key.getKeyCode()==39){
			cl.sendGameInformation("39");
		
		}
		if(key.getKeyCode()==32){
			cl.sendGameInformation("32");
		}

		if(key.getKeyCode()==82){
//			field.paintMatch(this.getGraphics(),cl);
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passMessage(String msg) {
		// TODO Auto-generated method stub
		cl.passMessage(msg);
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return field;
	}
	
	public int hightGoals(){
		
		int bgy =cl.getBall().getPosY();
		
		
		if(cl.getBall().getPosX()<=7){
			if(186<bgy && bgy<317){
				
				score2+=1;
				cl.goal();
				cl.getBall().setAddres(cl.getBall().RIGHT);
			}
		}

		
		if(cl.getBall().getPosX()>=840){
			if(186<bgy && bgy<317){
				score1+=1;
				cl.goal();
				cl.getBall().setAddres(cl.getBall().LEFT);
			}
		}
		
		
		
		if(score1>score2){
			return score1;
		}
		else{
			return score2;
		}
	}
	
	
	
}
