package view.inquire;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controll.CInquire;
import model.MInquire;
import view.MessageFrame;

public class AnswerFrame extends JFrame {
		
	private static final long serialVersionUID = 1L;
	
	private JTextField txtTtile;
	private JTextArea txtContents;
	private JTextArea txtAnswerContents;
	
	private MInquire mInquire;
	
	
	public AnswerFrame(ActionListener buttonHandler) {
		
		this.setTitle("답변하기");
		this.setSize(400, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel line1 = new JPanel();
			JLabel lblTitle = new JLabel("제목 ");
			txtTtile = new JTextField(25);
			txtTtile.setEditable(false);
			line1.add(lblTitle);
			line1.add(txtTtile);
		this.add(line1);
			
		JPanel line2 = new JPanel();
			txtContents = new JTextArea(10, 30);
			txtContents.setLineWrap(true);			
			txtContents.setEditable(false);
			line2.add(txtContents);
		this.add(line2);
		
		JPanel line3 = new JPanel();			
			txtAnswerContents = new JTextArea(10, 30);
			txtAnswerContents.setLineWrap(true);				
			line3.add(txtAnswerContents);
		this.add(line3);
		
		JPanel line4 = new JPanel();
			JButton btnAnswer = new JButton("답변하기");
			JButton btnClose = new JButton("닫기");
			btnAnswer.setActionCommand("createAnswer");
			btnClose.setActionCommand("closeAnswerFrame");
			btnAnswer.addActionListener(buttonHandler);
			btnClose.addActionListener(buttonHandler);
			line4.add(btnAnswer);
			line4.add(btnClose);
		this.add(line4);
					
	}
	
	public void setInquireInfo(MInquire mInquire) {
		this.mInquire = mInquire;
		this.txtTtile.setText(this.mInquire.getTitle());
		this.txtContents.setText(this.mInquire.getContent());
		
	}
	
	public void createAnswer() {
		
		String message = "문의사항 답변을 작성하시겠습니까?";
		String optionTitle = "알림";
		int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
		
			if(!this.txtAnswerContents.getText().equals("")) {
				CInquire cInquire = new CInquire();
				String inquireNo = this.mInquire.getNo();
				String answerContents = this.txtAnswerContents.getText();
				cInquire.createAnswer(inquireNo, answerContents);
				
				this.dispose();
				
			} else {
								
				MessageFrame.showMessage("알림", "답변 내용을 작성해주세요.", MessageFrame.ERROR_MESSAGE);
				
			}
		}
		
	}
	
	public void closeAnswerFrame() {
		this.dispose();
	}

	

}
