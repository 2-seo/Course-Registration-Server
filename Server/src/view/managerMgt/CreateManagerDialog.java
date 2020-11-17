package view.managerMgt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controll.CManager;
import view.MessageFrame;

public class CreateManagerDialog extends JFrame {

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
	
	public CreateManagerDialog(ActionListener buttonHandler) {
		
		this.setTitle("Create Manager");
		this.setSize(380, 240);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		cManager = new CManager();
		
		pContainer = new JPanel();
		pContainer.setSize(380, 240);
		pContainer.setLayout(null);
		
		pName = new JPanel();
		lblName = new JLabel("�̸�");
		txtName = new JTextField(20);
		pName.add(lblName);
		pName.add(txtName);
		pName.setBounds(0, 20, 380, 30);
		
		pId = new JPanel();
		lblId = new JLabel("���̵�");
		txtId = new JTextField(20);
		pId.add(lblId);
		pId.add(txtId);		
		pId.setBounds(-11, 50, 380, 30);
		
		pPassword = new JPanel();
		lblPassword = new JLabel("��й�ȣ");
		txtPassword = new JTextField(20);
		pPassword.add(lblPassword);
		pPassword.add(txtPassword);		
		pPassword.setBounds(-11, 80, 380, 30);
		
		pAddress = new JPanel();
		lblAddress = new JLabel("�ּ�");
		txtAddress = new JTextField(20);
		pAddress.add(lblAddress);
		pAddress.add(txtAddress);
		pAddress.setBounds(-3, 110, 380, 30);
		
		pEmail = new JPanel();
		lblEmail = new JLabel("�̸���");
		txtEmail = new JTextField(20);
		pEmail.add(lblEmail);
		pEmail.add(txtEmail);
		pEmail.setBounds(0, 140, 380, 30);
		
		pButtonContinaer = new JPanel();
		btnOk = new JButton("���");
		btnCancle = new JButton("���");
		btnOk.setActionCommand("createManager");
		btnCancle.setActionCommand("closeCreateManagerDialog");
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

	public void createManager() {

		String name = this.txtName.getText();
		String id = this.txtId.getText();
		String pw = this.txtPassword.getText();
		String address = this.txtAddress.getText();
		String email = this.txtEmail.getText();
		
		if(!id.equals("") && !name.equals("") && !pw.equals("") && !address.equals("") && !email.equals("")) {;
			
			
			String message = "�����ڸ� �߰��Ͻðڽ��ϱ�?";
			String optionTitle = "�˸�";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				
				cManager.createManager(name, id, pw, address, email);
				
				this.txtName.setText("");
				this.txtId.setText("");
				this.txtPassword.setText("");
				this.txtAddress.setText("");
				this.txtEmail.setText("");
				this.dispose();
			}
			
		} else {
			
			MessageFrame.showMessage("�˸�", "������ �����մϴ�.", MessageFrame.ERROR_MESSAGE);
			
		}
		
	}
	
	
	

}
