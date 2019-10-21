package GUI_Server_Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field_Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client_GUI cli;
	
	public Field_Panel(Client_GUI cli) {
		setSize(860,487);	
		this.cli = cli;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);	
		ImageIcon mg = new ImageIcon("data/Field.png");
		g.drawImage(mg.getImage(),0,0,this.getWidth(),this.getHeight(), this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana",Font.BOLD,14 ));
		g.drawString("Player1: "+ cli.getScore1(), 54, 20);
		g.drawString("Time: "+ cli.getTime(), 400, 20);
		g.drawString("Player2: "+ cli.getScore2(), 725, 20);
	}
}
