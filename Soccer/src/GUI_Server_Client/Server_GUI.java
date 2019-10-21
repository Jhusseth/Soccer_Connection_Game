package GUI_Server_Client;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

import controller.ControllerServer;
import server.ServerAdmin;

public class Server_GUI extends JFrame implements ActionListener, ViewG{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String SEND = "SEND";

	private JTextArea date;
	
	private JButton btnSend;
	
	private TextField sendText;
	
	private ControllerServer cs;
	
	public Server_GUI() {
		
		setSize(240,220);
		setTitle("::: Server :::");
		date = new JTextArea(" ");
		date.setEditable(false);
		date.setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(date, BorderLayout.CENTER);
		
		JPanel aux = new JPanel();
		aux.setBorder(new EmptyBorder(1, 1, 1, 1));
		aux.setBackground(Color.GRAY);
		aux.setLayout(new BorderLayout());
		
		JPanel aux2 = new JPanel();
		aux2.setBackground(Color.LIGHT_GRAY);
		aux2.setLayout(new FlowLayout());
		
		JPanel aux3 = new JPanel();
		aux3.setBackground(Color.LIGHT_GRAY);
		aux3.setLayout(new FlowLayout());
		
		btnSend = new JButton("SEND");
		btnSend.addActionListener(this);
		btnSend.setActionCommand(SEND);
		sendText= new TextField(20);
		sendText.setForeground(Color.BLACK);
		aux2.add(btnSend);
		aux3.add(sendText);
		
		aux.add(aux3,BorderLayout.CENTER);
		aux.add(aux2,BorderLayout.SOUTH);
		getContentPane().add(aux, BorderLayout.SOUTH);
		 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	
	public void conexion() {
	}
	

	public static void main(String[] args) {
		
		Server_GUI window = new Server_GUI();
		//window.setVisible(true);	
		ViewG view = window;
		ServerAdmin model = new ServerAdmin();
		ControllerServer control = new ControllerServer(view,model);
		
		view.setController(control);
		model.setController(control);
		control.initializeActivity();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(SEND)) {
			conexion();
		}
		
	}


	@Override
	public void addMessage(String msg) {
		// TODO Auto-generated method stub
		date.append(msg + "\n");
	}


	@Override
	public void setController(ControllerServer control) {
		// TODO Auto-generated method stub
		cs = control;
	}


	@Override
	public void makeVisible() {
		setVisible(true);
		
	}

}
