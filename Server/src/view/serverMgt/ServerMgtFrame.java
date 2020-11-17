package view.serverMgt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import server.DataServer;

public class ServerMgtFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ServerLogTable serverLogTable;
	
	private JPanel pContainer;
	
	private JPanel pFooter;
	private JLabel lblLoginServerCondition;
	private JButton btnLoginServerOn;
	private JButton btnLoginServerOff;
	private JButton btnClose;
	
	private ActionListener buttonHandler;
	private DataServer loginServerSocket;
	
	public ServerMgtFrame() {
		
		this.setTitle("Server Manager");
		this.setSize(600, 510);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);		

		pContainer = new JPanel();
		pContainer.setLayout(new BoxLayout(pContainer, BoxLayout.Y_AXIS));
		pContainer.setBounds(0, 0, 600, 480);
		
		
		// Notice table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.serverLogTable = new ServerLogTable();
		scrollPane.setViewportView(this.serverLogTable);
//		scrollPane.setBounds(0, 0, 600, 350);		
		pContainer.add(scrollPane);
		
		loginServerSocket = new DataServer(serverLogTable);
		
		// Button Setting
		lblLoginServerCondition = new JLabel("서버가 닫쳐있습니다.");
		pContainer.add(lblLoginServerCondition);
		
		buttonHandler = new ButtonHandler();
		
		btnLoginServerOn = new JButton("서버 켜기");
		btnLoginServerOn.setActionCommand("LoginServerOn");
		btnLoginServerOn.addActionListener(buttonHandler);
		
		btnLoginServerOff = new JButton("서버 끄기");
		btnLoginServerOff.setActionCommand("LoginServerOff");
		btnLoginServerOff.addActionListener(buttonHandler);
		btnLoginServerOff.setEnabled(false);
		
		btnClose = new JButton("닫기");
		btnClose.setActionCommand("closeServerMgtFrame");
		btnClose.addActionListener(buttonHandler);
		
		pFooter = new JPanel();
		pFooter.add(btnLoginServerOn);
		pFooter.add(btnLoginServerOff);
		pFooter.add(btnClose);
		pContainer.add(pFooter);
		
		
		this.add(pContainer);
		
	}
	
	private void loginServerOn() {
		String message = "서버를 키시겠습니까?";
		String optionTitle = "알림";
		int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			loginServerSocket.startServer();
			this.lblLoginServerCondition.setText("서버가 켜져있습니다.");
			this.btnLoginServerOn.setEnabled(false);
			this.btnLoginServerOff.setEnabled(true);
		}
	}
	
	private void loginServerOff() {
		String message = "서버를 종료하시겠습니까?";
		String optionTitle = "알림";
		int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			loginServerSocket.stopServer();
			this.lblLoginServerCondition.setText("서버가 닫쳐있습니다.");
			this.btnLoginServerOn.setEnabled(true);
			this.btnLoginServerOff.setEnabled(false);
		}
	}
	
	private void closeServerMgtFrame() {
		this.dispose();
	}
	
	class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals("LoginServerOn")) {
				loginServerOn();
			} else if(action.equals("LoginServerOff")) {
				loginServerOff();
			} else if(action.equals("closeServerMgtFrame")) {
				closeServerMgtFrame();
			}
			
		}
		
	}

}
