package view.studentMgt;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controll.CStudent;
import model.MMajor;

public class CreateStudentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String strStudentName;
	private int majorId;
	private String majorName;
	private String strAddress;

	private JPanel pName;
	private JLabel lblStuName;
	private JTextField txtStuName;			
	
	private JPanel pMajor;
	private JLabel lblMajor;
	private JTextField txtMajor;
	private JButton btnSearch;
	
	private JPanel pAddress;
	private JLabel lblAddress;
	private JTextField txtAddress;
	
	private JPanel pButton;	
	private JButton btnOk;
	private JButton btnCancle;
	
	private JPanel pFooter;
	private JLabel lblInfo1;
	private JLabel lblInfo2;
	
	private MajorFrame majorFrame;
	
//	private ActionListener buttonHandler;
	
	public CreateStudentFrame(ActionListener buttonHandler) {
		
		this.setTitle("Student Register");
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);		

		majorFrame = new MajorFrame(buttonHandler);
		
		// Form Setting
		pName = new JPanel();
		lblStuName = new JLabel("이름");
		txtStuName = new JTextField(20);
		pName.add(lblStuName);
		pName.add(txtStuName);
		pName.setBounds(5, 10, 300, 40);
		this.add(pName);
		
		pMajor = new JPanel();
		lblMajor = new JLabel("전공");
		txtMajor = new JTextField(13);
		txtMajor.setEditable(false);
		btnSearch = new JButton("검색");
		btnSearch.setActionCommand("majorSearch");
		btnSearch.addActionListener(buttonHandler);
		pMajor.add(lblMajor);
		pMajor.add(txtMajor);
		pMajor.add(btnSearch);
		pMajor.setBounds(4, 50, 300, 40);
		this.add(pMajor);
				
		pAddress = new JPanel();
		lblAddress = new JLabel("주소");
		txtAddress = new JTextField(20);
		pAddress.add(lblAddress);
		pAddress.add(txtAddress);		
		pAddress.setBounds(5, 90, 300, 40);
		this.add(pAddress);

		pButton = new JPanel();
		btnOk = new JButton("등록");
		btnCancle = new JButton("취소");
		btnOk.setActionCommand("regist");
		btnCancle.setActionCommand("cancle");
		btnOk.addActionListener(buttonHandler);
		btnCancle.addActionListener(buttonHandler);
		pButton.add(btnOk);
		pButton.add(btnCancle);				
		pButton.setBounds(5, 130, 300, 40);
		this.add(pButton);
		
		pFooter = new JPanel();
		pFooter.setLayout(new BoxLayout(pFooter, BoxLayout.Y_AXIS));
		lblInfo1 = new JLabel("학번 자동 부여 / 학번은 로그인 아이디로 사용");
		lblInfo2 = new JLabel("최초 비밀번호: 0000");
		lblInfo1.setForeground(Color.red);
		lblInfo2.setForeground(Color.red);
		pFooter.add(lblInfo1);
		pFooter.add(lblInfo2);
		pFooter.setBounds(30, 180, 300, 150);
		this.add(pFooter);

		initialize();
		
	}
	
	private void initialize() {
		
	}
	
	public void reset() {
		this.txtMajor.setText("");
	}
	
	public void openMajorDialog() {
		
		Thread t = new Thread(new Runnable(){
	        public void run(){
	        	majorFrame.setVisible(true);
	        }
	    });
		t.start();	  
	  
	}
	
	public void createStudent() {
		
		this.strStudentName = this.txtStuName.getText();
		this.strAddress = this.txtAddress.getText();
		if(this.strStudentName.equals("") || this.strAddress.equals("")) {
			showMessage("공백이 존재합니다.");
			
			return;
		}
		
		String message = "학생을 등록하시겠습니까?";
		String title = "알림";
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			CStudent cStudent = new CStudent();
			cStudent.createStudent(strStudentName, majorId, strAddress);
			this.dispose();
		}
		
	}
	
	public void getSelectedMajor() {
		MMajor mMajor = this.majorFrame.getSelectedMajor();
		
		majorId = mMajor.getId();
		majorName = mMajor.getName();
		
		this.txtMajor.setText(majorName);
		this.majorFrame.dispose();
	}
	
	private void showMessage(String message) {
		
		Thread t = new Thread(new Runnable(){
	        public void run(){
	            JOptionPane.showMessageDialog(null, message);
	        }
	    });
	  t.start();
	}
	
}
