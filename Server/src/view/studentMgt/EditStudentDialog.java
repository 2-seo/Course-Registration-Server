package view.studentMgt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controll.CStudent;
import model.MMajor;
import view.MessageFrame;

public class EditStudentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private Integer stuNum;
	private String strStudentName;
	private String strPassword;
	private Integer majorId;
	private String majorName;
	private String strAddress;

	private JPanel pName;
	private JLabel lblStuName;
	private JTextField txtStuName;			
	
	private JPanel pMajor;
	private JLabel lblMajor;
	private JTextField txtMajor;
	private JButton btnSearch;
	
	private JPanel pPassword;
	private JLabel lblPassword;
	private JTextField txtPassword;
	
	private JPanel pAddress;
	private JLabel lblAddress;
	private JTextField txtAddress;
	
	private JPanel pButton;	
	private JButton btnOk;
	private JButton btnCancle;
	
	private MajorFrame majorDialog;

	public EditStudentDialog(ActionListener buttonHandler) {
		
		this.setTitle("Student Register");
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);		

		majorDialog = new MajorFrame(buttonHandler);
		
		// Form Setting
		pName = new JPanel();
		lblStuName = new JLabel("�̸�");
		txtStuName = new JTextField(20);
		pName.add(lblStuName);
		pName.add(txtStuName);
		pName.setBounds(5, 10, 300, 40);
		this.add(pName);
		
		pMajor = new JPanel();
		lblMajor = new JLabel("����");
		txtMajor = new JTextField(13);
		txtMajor.setEditable(false);
		btnSearch = new JButton("�˻�");
		btnSearch.setActionCommand("majorSearch");
		btnSearch.addActionListener(buttonHandler);
		pMajor.add(lblMajor);
		pMajor.add(txtMajor);
		pMajor.add(btnSearch);
		pMajor.setBounds(4, 50, 300, 40);
		this.add(pMajor);
		
		pPassword = new JPanel();
		lblPassword = new JLabel("��й�ȣ");
		txtPassword = new JTextField(18);
		pPassword.add(lblPassword);
		pPassword.add(txtPassword);
		pPassword.setBounds(5, 90, 300, 40);
		this.add(pPassword);
				
		pAddress = new JPanel();
		lblAddress = new JLabel("�ּ�");
		txtAddress = new JTextField(20);
		pAddress.add(lblAddress);
		pAddress.add(txtAddress);		
		pAddress.setBounds(5, 120, 300, 40);
		this.add(pAddress);

		pButton = new JPanel();
		btnOk = new JButton("����");
		btnCancle = new JButton("���");
		btnOk.setActionCommand("updateStudentInfo");
		btnCancle.setActionCommand("closeEditStudentDialog");
		btnOk.addActionListener(buttonHandler);
		btnCancle.addActionListener(buttonHandler);
		pButton.add(btnOk);
		pButton.add(btnCancle);				
		pButton.setBounds(5, 170, 300, 40);
		this.add(pButton);

	}
	
	public void openMajorDialog() {
		majorDialog.setVisible(true);
	}
	
	public void updateStudent() {
		
		this.strStudentName = this.txtStuName.getText();
		this.strPassword = this.txtPassword.getText();
		this.strAddress = this.txtAddress.getText();
		if(this.strStudentName.equals("") || this.strPassword.equals("") || this.strAddress.equals("")) {

//			JOptionPane.showMessageDialog(this, "������ �����մϴ�.");			
			MessageFrame.showMessage("�˸�", "������ �����մϴ�.", MessageFrame.ERROR_MESSAGE);
			return;
		}
		
		String message = "�л� ������ �����Ͻðڽ��ϱ�?";
		String title = "�˸�";
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
		    
		    CStudent cStudent = new CStudent();
		    cStudent.updateStudent(this.stuNum, strStudentName, strPassword, this.majorId, strAddress);
		    JOptionPane.showMessageDialog(this, "�л� ������ ������Ʈ�߽��ϴ�.");		    
			MessageFrame.showMessage("�˸�", "�л� ������ ������Ʈ�߽��ϴ�.", MessageFrame.INFO_MESSAGE);
		    this.dispose();
		} else {
	 
		}
		
		
		
	}
	
	public void getSelectedMajor() {
		MMajor mMajor = this.majorDialog.getSelectedMajor();
		
		this.majorId = mMajor.getId();
		this.majorName = mMajor.getName();
		this.txtMajor.setText(majorName);
		
		this.majorDialog.dispose();
		
	}

	public void setStudentInfo(int stuNum, String name, Integer majorId, String major, String password, String address) {
		this.stuNum = stuNum;
		this.txtStuName.setText(name);
		this.majorId = majorId;
		this.txtMajor.setText(major);
		this.txtPassword.setText(password);
		this.txtAddress.setText(address);
		
	}
	
}
