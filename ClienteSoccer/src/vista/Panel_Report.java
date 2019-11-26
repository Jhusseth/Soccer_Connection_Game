package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class Panel_Report extends JPanel implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CLOSE = "close";
	private static final String ESTADISTCS = "estat";
	
	private JTextArea results;
	private JButton btn_close;
	private JButton btn_esta;
	
	

	public Panel_Report() {
		setBackground(Color.BLACK);
		setSize(500,400);
		
		results=new JTextArea("                 \n \n                       Resultados \n \n");
		results.setColumns(20);
		results.setRows(20);
		results.setForeground(Color.WHITE);
		results.setBackground(Color.BLACK);
		results.setEditable(false);
		
		setLayout(new BorderLayout());
		
		
		btn_close = new JButton(" Close ");
		btn_close.setForeground(Color.BLACK);
		btn_close.setBackground(Color.WHITE);
		btn_close.addActionListener(this);
		btn_close.setActionCommand(CLOSE);
		
		btn_esta = new JButton("  Web  ");
		btn_esta.setForeground(Color.BLACK);
		btn_esta.setBackground(Color.WHITE);
		btn_esta.addActionListener(this);
		btn_esta.setActionCommand(ESTADISTCS);
		
		JPanel aux1 = new JPanel();
		aux1.setBackground(Color.BLACK);
		JPanel aux2 = new JPanel();
		aux2.setBackground(SystemColor.desktop);
		
		aux1.setLayout(new GridLayout(1,3));
		
		JPanel aux3 = new JPanel();
		aux3.setBorder(new LineBorder(new Color(148, 0, 211)));
		aux3.setLayout(new BorderLayout());
		aux3.add(new DeepResult(),BorderLayout.NORTH);
		aux3.add(results,BorderLayout.CENTER);
		aux3.add(new DeepResult(), BorderLayout.SOUTH);
		
		aux1.add(new DeepResult());
		aux1.add(aux3);
		aux1.add(new DeepResult());
		
		
		aux2.setLayout(new FlowLayout());
		aux2.add(btn_esta);
		aux2.add(btn_close);
		
		add(aux1, BorderLayout.CENTER);
		add(aux2, BorderLayout.SOUTH); 
	}
	
	public void setResults(String text) {
		this.results.append(text +"\n");
	}
	
	public void goToURL(String URL){
        if (java.awt.Desktop.isDesktopSupported()) {
         java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

         if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
             try {
                 java.net.URI uri = new java.net.URI(URL);
                 desktop.browse(uri);
             } catch (URISyntaxException | IOException ex) {
                 Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(ESTADISTCS)) {
			goToURL("http://hn1fqz7klkspxmzz83oics.webrelay.io/");
		}
        if(e.getActionCommand().equals(CLOSE)) {
			System.exit(0);
		}
		
		
	}

}
