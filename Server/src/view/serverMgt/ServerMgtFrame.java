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
		lblLoginServerCondition = new JLabel("������ �����ֽ��ϴ�.");
		pContainer.add(lblLoginServerCondition);
		
		buttonHandler = new ButtonHandler();
		
		btnLoginServerOn = new JButton("���� �ѱ�");
		btnLoginServerOn.setActionCommand("LoginServerOn");
		btnLoginServerOn.addActionListener(buttonHandler);
		
		btnLoginServerOff = new JButton("���� ����");
		btnLoginServerOff.setActionCommand("LoginServerOff");
		btnLoginServerOff.addActionListener(buttonHandler);
		btnLoginServerOff.setEnabled(false);
		
		btnClose = new JButton("�ݱ�");
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
		String message = "������ Ű�ðڽ��ϱ�?";
		String optionTitle = "�˸�";
		int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			loginServerSocket.startServer();
			this.lblLoginServerCondition.setText("������ �����ֽ��ϴ�.");
			this.btnLoginServerOn.setEnabled(false);
			this.btnLoginServerOff.setEnabled(true);
		}
	}
	
	private void loginServerOff() {
		String message = "������ �����Ͻðڽ��ϱ�?";
		String optionTitle = "�˸�";
		int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			loginServerSocket.stopServer();
			this.lblLoginServerCondition.setText("������ �����ֽ��ϴ�.");
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
