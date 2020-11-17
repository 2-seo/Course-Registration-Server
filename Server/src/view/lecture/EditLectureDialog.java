package view.lecture;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controll.CLecture;
import view.MessageFrame;

public class EditLectureDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CLecture cLecture;
	
	private JPanel pContainer;
	private JPanel pTitleContainer;
	private JPanel pLectureContinaer;
	private JPanel pCreditContinaer;
	private JPanel pTimeContinaer;
	private JPanel pButtonContinaer;
	
	
	private JLabel lblTitle;
	private JLabel lblLecturer;
	private JLabel lblCredit;
	private JLabel lblTime;
	
	private JTextField txtTitle;
	private JTextField txtLecturer;
	private JTextField txtCredit;
	private JTextField txtTime;	

	private ActionListener buttonHandler;
	
	private JButton btnOk;
	private JButton btnCancle; 

	private Integer lectureId;
	
	public EditLectureDialog(ActionListener buttonHandler) {
		
		this.setTitle("Lecture Edit Manager");
		this.setSize(370, 240);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		cLecture = new CLecture();
		
		pContainer = new JPanel();
		pContainer.setSize(370, 240);
		pContainer.setLayout(null);
		
		pTitleContainer = new JPanel();
		lblTitle = new JLabel("Title");
		txtTitle = new JTextField(20);
		pTitleContainer.add(lblTitle);
		pTitleContainer.add(txtTitle);
		pTitleContainer.setBounds(0, 20, 380, 30);
		
		pLectureContinaer = new JPanel();
		lblLecturer = new JLabel("Lecturer");
		txtLecturer = new JTextField(20);
		pLectureContinaer.add(lblLecturer);
		pLectureContinaer.add(txtLecturer);		
		pLectureContinaer.setBounds(-11, 50, 380, 30);
		
		pCreditContinaer = new JPanel();
		lblCredit = new JLabel("Credit");
		txtCredit = new JTextField(20);
		pCreditContinaer.add(lblCredit);
		pCreditContinaer.add(txtCredit);
		pCreditContinaer.setBounds(-3, 80, 380, 30);
		
		pTimeContinaer = new JPanel();
		lblTime = new JLabel("Time");
		txtTime = new JTextField(20);
		pTimeContinaer.add(lblTime);
		pTimeContinaer.add(txtTime);
		pTimeContinaer.setBounds(0, 110, 380, 30);
		
		pButtonContinaer = new JPanel();
		btnOk = new JButton("수정");
		btnCancle = new JButton("취소");
		btnOk.setActionCommand("updateLecture");
		btnCancle.setActionCommand("closeAddLectureDialog");
		this.buttonHandler = buttonHandler;
		btnOk.addActionListener(this.buttonHandler);
		btnCancle.addActionListener(this.buttonHandler);
		
		pButtonContinaer.add(btnOk);
		pButtonContinaer.add(btnCancle);
		pButtonContinaer.setBounds(-8, 150, 380, 30);
		
		pContainer.add(pTitleContainer);
		pContainer.add(pLectureContinaer);
		pContainer.add(pCreditContinaer);
		pContainer.add(pTimeContinaer);
		pContainer.add(pButtonContinaer);
		
		this.add(pContainer);		
		
	}
	
	public void setText(Integer lectureId, String title, String lecturer, String credit, String time) {

		this.lectureId = lectureId;
		this.txtTitle.setText(title);
		this.txtLecturer.setText(lecturer);
		this.txtCredit.setText(credit);
		this.txtTime.setText(time);
		
	}

	public void updateLecture() {

		String title = this.txtTitle.getText();
		String lecturer = this.txtLecturer.getText();
		Integer credit = Integer.parseInt(this.txtCredit.getText());
		String time = this.txtTime.getText();
		
		if(!title.equals("") && !lecturer.equals("") && !this.txtCredit.getText().equals("") && !time.equals("")) {
			
			String message = "강좌를 수정하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				
				cLecture.updateLecture(this.lectureId, title, lecturer, credit, time);
				
				this.txtTitle.setText("");
				this.txtLecturer.setText("");
				this.txtCredit.setText("");
				this.txtTime.setText("");
				
				this.dispose();
				
			}
			
		} else {
			
			MessageFrame.showMessage("알림", "공백이 존재합니다.", MessageFrame.ERROR_MESSAGE);
			
		}
		
	}
	
	public void closeForm() {
		this.dispose();
	}
	


}
