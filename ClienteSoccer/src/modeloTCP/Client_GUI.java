package modeloTCP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client_GUI extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DX = 5;
	private static final int DY = 5;

	private Field_Panel field;
	private Item player1;
	private Item player;
	private Item balon;
	private String time;
	private boolean have;

	public Client_GUI() {
		ImageIcon pl=new ImageIcon("data/player2.png");
		ImageIcon bl=new ImageIcon("data/ball.png");
		time="Start";
		player1 = new Item(0, new Point(60, 60), "",pl);
		balon = new Item(0, new Point(40, 40), "",bl);
		
		init();
		field = new Field_Panel();
		setLayout(new BorderLayout());
		add(field, BorderLayout.CENTER);
		setSize(860, 487);
		setLocationRelativeTo(null);
		setFocusable(true);
		addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		new ThreadClient(this).start();
	}

	public void init() {
		String name = JOptionPane.showInputDialog("Name of Cliente:");
		setTitle(":: Cliente :: " + name + " ::");
		ImageIcon pl=new ImageIcon("data/player1.png");

		player = new Item(0, new Point(30, 30), name,pl);
		setVisible(true);
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

		if (key.getKeyCode() == 27) {
			System.exit(0);
		}
		if (key.getKeyCode() == 38) {
			Point point = getPlayer();
			Point newPoint=new Point(point.x, point.y - DY);
			if(have) {
				setBalon(new Point(newPoint.x+50, newPoint.y+75));
			}else {
				//verificar si toca el balon
			}
			setPlayer(newPoint);
		}
		if (key.getKeyCode() == 40) {
			Point point = getPlayer();

			setPlayer(new Point(point.x, point.y + DY));
		}
		if (key.getKeyCode() == 37) {
			Point point = getPlayer();

			setPlayer(new Point(point.x-DX, point.y));
		}
		if (key.getKeyCode() == 39) {
			Point point = getPlayer();

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
		g.setFont(new Font("Verdana",Font.BOLD,50 ));
		g.setColor(Color.BLACK);

		g.drawString(time, this.getWidth()/2, 75);
		g.setFont(new Font("Verdana",Font.BOLD,10 ));

		g.drawImage(balon.getImage().getImage(),balon.getPos().x, balon.getPos().y,25,25, null);
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
	public boolean getHave() {
		return have;
	}




}
