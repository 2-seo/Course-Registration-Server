package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controll.CManager;
import model.MAdminAccount;

public class LoginDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtID;
	private JPasswordField txtPW;
	
	private ActionListener loginHandler;
	private WindowListener windowHandler;

	public LoginDialog() {
		
		this.setTitle("MJU Management tool");
		this.setSize(300, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		this.windowHandler = new WindowHandler();
		this.addWindowListener(windowHandler);
			
		// LOGO image
		ImageIcon originIcon = new ImageIcon("img/logo.png");  
        Image originImg = originIcon.getImage(); 
        Image changedImg= originImg.getScaledInstance(200, 200, Image.SCALE_SMOOTH );
        ImageIcon icon = new ImageIcon(changedImg);
        JLabel lblLogo = new JLabel(icon);
        lblLogo.setBounds(55, 20, 200, 200);
        this.add(lblLogo);
      
		JPanel pIdRow = new JPanel();
		JLabel lblID = new JLabel("ID ");
		txtID = new JTextField(12);
		
		pIdRow.add(lblID);
		pIdRow.add(txtID);
		pIdRow.setBounds(0, 250, 300, 30);
		this.add(pIdRow);
		
		JPanel pPwRow = new JPanel();
		JLabel lblPw = new JLabel("PW ");
		txtPW = new JPasswordField(12);
		
		pPwRow.add(lblPw);
		pPwRow.add(txtPW);
		pPwRow.setBounds(0, 280, 300, 30);
		this.add(pPwRow);		
		
		JPanel pBtnRow = new JPanel();
		JButton btnLogin = new JButton("Login");
		loginHandler = new LoginHandler();
		btnLogin.addActionListener(loginHandler);
		this.getRootPane().setDefaultButton(btnLogin);
		pBtnRow.setBounds(0, 330, 300, 30);
		pBtnRow.add(btnLogin);
		this.add(pBtnRow);				
				
	}

	private void initialize() {
		
	}
	
	private void checkID() {
		
		String message = "";
		if(txtID.getText().equals("") || txtPW.getText().equals("")) {
			
			message = "아이디와 패스워드를 입력해주세요.";
			JOptionPane.showMessageDialog(this, message, "검색 결과", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		CManager cLogin = new CManager();
		MAdminAccount mAdminAccount = cLogin.checkID(txtID.getText(), txtPW.getText());
		if(mAdminAccount != null) {
			MainFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
			this.dispose();			
			
		} else {
			message = "아이디와 패스워드를 확인하세요.";
			JOptionPane.showMessageDialog(null, message, "검색 결과", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private class LoginHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			checkID();			
			
		}
	}
	
	private class WindowHandler implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);	
		}

		@Override
		public void windowClosed(WindowEvent e) {
					
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
