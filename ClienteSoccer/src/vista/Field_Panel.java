package vista;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field_Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Field_Panel() {
		setSize(850,455);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);	
		ImageIcon mg = new ImageIcon("data/field.png");
		g.drawImage(mg.getImage(),0,0,this.getWidth(),this.getHeight(), this);
	}
}
