
package modeloTCP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client_GUI extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DX = 1;
	private static final int DY = 1;

	private Field_Panel field;
	private Item player1;
	private Item player;
	private Item balon;
	private String time;
	private boolean goal;
	
	public int score1;
	public int score2;
	private ImageIcon pl;
	private ImageIcon pl1;
	private ImageIcon bl;
	private String name;
	private boolean have1;
	private boolean have2;
	
	private Panel_Report pr;

	public Client_GUI() {
		pl=new ImageIcon("data/player2.gif");
		bl=new ImageIcon("data/ball.png");
		time="00:00";
		player1 = new Item(2, new Point(525, 185), "Cliente 2",pl);
		balon = new Item(0, new Point(427, 240), "Ball",bl);
		score1=0;
		have1=false;
		have2=false;
		goal =false;
		init();
		field = new Field_Panel();
		pr = new Panel_Report(this);
		setLayout(new BorderLayout());
		add(field, BorderLayout.CENTER);
		setSize(860, 487);
		setLocationRelativeTo(null);
		setFocusable(true);
		addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		new ThreadClient(this).start();
		setResizable(false);
	}

	public void init() {
		name = JOptionPane.showInputDialog("Name of Cliente:");
		setTitle(":: Cliente :: " + name + " ::");
		pl1=new ImageIcon("data/player1.gif");
		player = new Item(1, new Point(300, 185), name,pl1);
		score2=0;
		setVisible(true);
		repaint();
	}

	public static void main(String[] args) {
		Client_GUI window = new Client_GUI();
		window.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent key) {
		
		Point point = getPlayer();

		if (key.getKeyCode() == 27) {
			System.exit(0);
		}
		if (key.getKeyCode() == 38) {
			setPlayer(new Point(point.x, point.y - DY));
		}
		if (key.getKeyCode() == 40) {
			setPlayer(new Point(point.x, point.y + DY));
		}
		if (key.getKeyCode() == 37) {
			setPlayer(new Point(point.x-DX, point.y));
		}
		if (key.getKeyCode() == 39) {
			setPlayer(new Point(point.x+DX, point.y));

		}
		if (key.getKeyCode() == 32) {
		}

		if (key.getKeyCode() == 82) {
			repaint();
		}
		repaint();
		
		
	}
	
	public Point getPlayer1() {
		return player1.getPos();
	}

	public void setPlayer1(Point point) {
		this.player1.setPos(point);
	}

	public Point getPlayer() {
		return player.getPos();
	}

	public void setPlayer(Point point) {
		this.player.setPos(point);
	}

	public Point getBalon() {
		return balon.getPos();
	}

	public void setBalon(Point point) {
		this.balon.setPos(point);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public void update() {
		repaint();
		revalidate();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana",Font.BOLD,10 ));
		g.drawString(player.getName(), player.getPos().x, player.getPos().y);
		g.drawImage(player.getImage().getImage(),player.getPos().x, player.getPos().y,50,75, null);
		
		g.drawString(player1.getName(), player1.getPos().x, player1.getPos().y);
		g.drawImage(player1.getImage().getImage(),player1.getPos().x, player1.getPos().y,50,75, null);
		
		g.setFont(new Font("Verdana",Font.BOLD,25 ));
		g.setColor(Color.BLACK);

		g.drawString(time, (this.getWidth()/2)-36, 57);
		g.drawString(score1 + "", 52, 57);
		g.drawString(score2 + "", 792, 57);

		g.drawImage(balon.getImage().getImage(),balon.getPos().x, balon.getPos().y,20,20, null);
		
		test();
	}
	
	public void catchBall(int id) {
		System.out.println("catch");
		if(id==player1.getId()) {
			balon.setPos(new Point(player1.getPos().x-17,player1.getPos().y+53));
		}
		else if(id == player.getId()) {
			balon.setPos(new Point(player.getPos().x+33,player.getPos().y+53));
		}
	}
	
	
	public String checkGoal() {
		String goals ="no gol";
		if(balon.getPos().x==9) {
			System.out.println("gol player2");
			if(balon.getPos().y>190 && balon.getPos().y<315) {
				score2++;
				goal=true;
				goals = "" + player1.getName();
				
				player1 = new Item(2, new Point(525, 185), "Cliente 2",pl);
				balon = new Item(0, new Point(427, 240), "Ball",bl);
				player = new Item(1, new Point(300, 185), name,pl1);
			}
		}

		if(balon.getPos().x==829) {
			System.out.println("gol player1");
			if(balon.getPos().y>190 && balon.getPos().y<315) {
				score1++;
				goal=true;
				goals = "" + player.getName();
				
				player1 = new Item(2, new Point(525, 185), "Cliente 2",pl);
				balon = new Item(0, new Point(427, 240), "Ball",bl);
				player = new Item(1, new Point(300, 185), name,pl1);
			}
		}
		
		return goals;
	}

	public String getPlayerName() {
		// TODO Auto-generated method stub
		return player.getName();
	}

	public void setOtherName(String otherName) {
		player1.setName(otherName);
	}
	public void setTime(String time) {
		this.time=time;
	}
	public boolean getGoal() {
		return goal;
	}
	
	public void setGoal(boolean goal) {
		this.goal=goal;
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
	
	public void test() {
		if(balon.getPos().x-40 <= player.getPos().x && player.getPos().x<=balon.getPos().x+40) {			
			if((balon.getPos().y-54<=player.getPos().y && player.getPos().y<=balon.getPos().y+54)) {	
				have1=true;
				catchBall(1);
			}
		}
		if(balon.getPos().x-40 <= player1.getPos().x && player1.getPos().x<=balon.getPos().x+40) {			
			if((balon.getPos().y-54<=player1.getPos().y && player1.getPos().y<=balon.getPos().y+54)) {	
				have2=true;
				catchBall(2);
			}
		}
	}

	public boolean getHave1() {
		// TODO Auto-generated method stub
		return have1;
	}
	
	public boolean getHave2() {
		// TODO Auto-generated method stub
		return have2;
	}
	
	
	public void resultsMatch(String results) {
		remove(field);
		player.setPos(new Point(0,0));
		player1.setPos(new Point(0,0));
		this.add(pr,BorderLayout.CENTER);
		pr.setResults(results);
		repaint();
	}
}
