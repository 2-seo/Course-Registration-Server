package view.managerMgt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controll.CManager;
import model.MManager;
import view.MessageFrame;

public class EditManagerDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CManager cManager;
	
	private JPanel pContainer;
	private JPanel pName;
	private JPanel pId;
	private JPanel pPassword;
	private JPanel pAddress;
	private JPanel pEmail;
	private JPanel pButtonContinaer;
	
	
	private JLabel lblName;
	private JLabel lblId;
	private JLabel lblPassword;
	private JLabel lblAddress;
	private JLabel lblEmail;
	
	private JTextField txtId;
	private JTextField txtPassword;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtEmail;	
	
	private JButton btnOk;
	private JButton btnCancle; 
	
	private String no;
	
	public EditManagerDialog(ActionListener buttonHandler) {
		
		this.setTitle("Update Manager");
		this.setSize(380, 240);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		cManager = new CManager();
		
		pContainer = new JPanel();
		pContainer.setSize(380, 240);
		pContainer.setLayout(null);
		
		pName = new JPanel();
		lblName = new JLabel("이름");
		txtName = new JTextField(20);
		pName.add(lblName);
		pName.add(txtName);
		pName.setBounds(0, 20, 380, 30);
		
		pId = new JPanel();
		lblId = new JLabel("아이디");
		txtId = new JTextField(20);
		pId.add(lblId);
		pId.add(txtId);		
		pId.setBounds(-11, 50, 380, 30);
		
		pPassword = new JPanel();
		lblPassword = new JLabel("비밀번호");
		txtPassword = new JTextField(20);
		pPassword.add(lblPassword);
		pPassword.add(txtPassword);		
		pPassword.setBounds(-11, 80, 380, 30);
		
		pAddress = new JPanel();
		lblAddress = new JLabel("주소");
		txtAddress = new JTextField(20);
		pAddress.add(lblAddress);
		pAddress.add(txtAddress);
		pAddress.setBounds(-3, 110, 380, 30);
		
		pEmail = new JPanel();
		lblEmail = new JLabel("이메일");
		txtEmail = new JTextField(20);
		pEmail.add(lblEmail);
		pEmail.add(txtEmail);
		pEmail.setBounds(0, 140, 380, 30);
		
		pButtonContinaer = new JPanel();
		btnOk = new JButton("수정");
		btnCancle = new JButton("취소");
		btnOk.setActionCommand("updateManager");
		btnCancle.setActionCommand("closeEditManagerDialog");
		btnOk.addActionListener(buttonHandler);
		btnCancle.addActionListener(buttonHandler);
		
		pButtonContinaer.add(btnOk);
		pButtonContinaer.add(btnCancle);
		pButtonContinaer.setBounds(-8, 180, 380, 30);
		
		pContainer.add(pName);
		pContainer.add(pId);
		pContainer.add(pPassword);
		pContainer.add(pAddress);
		pContainer.add(pEmail);
		pContainer.add(pButtonContinaer);
		
		this.add(pContainer);		
		
	}
	
	public void setMajorId(Integer majorId) {
//		this.majorId = majorId;
	}
	
	public void closeForm() {
		this.dispose();
	}
	
	public void setValue(MManager selectedManager) {
		
		this.no = selectedManager.getNo();		
		String name = selectedManager.getName();
		String id = selectedManager.getId();
		String pw = selectedManager.getPw();
		String address = selectedManager.getAddress();
		String email = selectedManager.getEmail();
		
		this.txtName.setText(name);
		this.txtId.setText(id);
		this.txtPassword.setText(pw);
		this.txtAddress.setText(address);
		this.txtEmail.setText(email);
		
	}

	public void updateManager() {

		String id = this.txtId.getText();
		String pw = this.txtPassword.getText();
		String name = this.txtName.getText();
		String address = this.txtAddress.getText();
		String email = this.txtEmail.getText();
		
		if(!name.equals("") && !id.equals("") && !pw.equals("") && !address.equals("") && !email.equals("")) {;
			
			
			String message = "관리자를 수정하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				
				cManager.updqteManager(Integer.parseInt(no), name, id, pw, address, email);
				
				this.txtName.setText("");
				this.txtId.setText("");
				this.txtPassword.setText("");
				this.txtAddress.setText("");
				this.txtEmail.setText("");
				this.dispose();
			}
			
		} else {
			
			MessageFrame.showMessage("알림", "공백이 존재합니다.", MessageFrame.ERROR_MESSAGE);
		}
		
	}
	
	
	

}
