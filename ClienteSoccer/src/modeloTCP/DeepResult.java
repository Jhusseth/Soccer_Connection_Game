package modeloTCP;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DeepResult extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeepResult () {
		setSize(200,200);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon mg = new ImageIcon("data/fondoR.gif");
		g.drawImage(mg.getImage(),0,0,this.getWidth(),this.getHeight(), this);
	}
	
	

}
