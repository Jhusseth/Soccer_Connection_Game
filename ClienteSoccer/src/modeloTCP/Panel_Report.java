package modeloTCP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class Panel_Report extends JPanel implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CLOSE = "close";
	private static final String NEWG = "close";
	
	private JTextArea results;
	private JButton btn_close;
	private JButton btn_newG;
	private Client_GUI gui;
	
	

	public Panel_Report(Client_GUI gui) {
		setBackground(Color.BLACK);
		setSize(500,400);
		this.gui = gui;
		
		results=new JTextArea("                       Resultados \n \n");
		results.setColumns(20);
		results.setRows(6);
		results.setForeground(Color.WHITE);
		results.setBackground(Color.BLACK);
		results.setEditable(false);
		
		setLayout(new BorderLayout());
		
		
		btn_close = new JButton("    Close    ");
		btn_close.setForeground(Color.BLACK);
		btn_close.setBackground(Color.WHITE);
		btn_close.addActionListener(this);
		btn_close.setActionCommand(CLOSE);
		
		btn_newG = new JButton("New Game");
		btn_newG.setForeground(Color.BLACK);
		btn_newG.setBackground(Color.WHITE);
		btn_newG.addActionListener(this);
		btn_newG.setActionCommand(NEWG);
		
		JPanel aux1 = new JPanel();
		aux1.setBackground(Color.BLACK);
		JPanel aux2 = new JPanel();
		aux2.setBackground(SystemColor.desktop);
		
		aux1.setLayout(new GridLayout(3,3));
		
		aux1.add(new DeepResult());
		aux1.add(new DeepResult());
		aux1.add(new DeepResult());
		
		aux1.add(new DeepResult());
		aux1.add(results);
		aux1.add(new DeepResult());
		aux1.add(new DeepResult());
		aux1.add(new DeepResult());
		aux1.add(new DeepResult());	
		aux2.setLayout(new FlowLayout());
		aux2.add(btn_newG);
		aux2.add(btn_close);
		
		add(aux1, BorderLayout.CENTER);
		add(aux2, BorderLayout.SOUTH); 
	}
	
	public void setResults(String text) {
		this.results.append("  " + text + "\n");
	}
	
//	@Override
//	public void paint(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paint(g);
//		
//		g.setColor(Color.WHITE);
//		g.setFont(new Font("Verdana",Font.BOLD,20));
//		
//		g.drawString(results.getText(), 100, 50);
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
