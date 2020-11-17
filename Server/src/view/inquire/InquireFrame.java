package view.inquire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controll.CInquireResult;
import model.MInquire;
import model.MInquireResult;
import view.MessageFrame;

public class InquireFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private InquireTable inquireTable;
	private JPanel buttonPanel;
	private JButton btnAnswer;
	private JButton btnAnswerEdit;
	private JButton btnClose;
	
	private ActionListener buttonHandler;
	private AnswerFrame answerFrame;
	private EditAnswerFrame editAnswerFrame;
	
	public InquireFrame() {
		
		this.setTitle("Inquire Manager");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		buttonHandler = new ButtonHandler();
		answerFrame = new AnswerFrame(buttonHandler);
		editAnswerFrame = new EditAnswerFrame(buttonHandler);
		
		// Campus table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.inquireTable = new InquireTable();
		scrollPane.setViewportView(this.inquireTable);
		scrollPane.setBounds(0, 0, 600, 380);
		this.add(scrollPane);
		
		// Button Setting
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 400, 600, 100);
		btnAnswer = new JButton("답변하기");
		btnAnswerEdit = new JButton("답변수정");
		btnClose = new JButton("닫기");		
		
		btnAnswer.addActionListener(buttonHandler);
		btnAnswerEdit.addActionListener(buttonHandler);
		btnClose.addActionListener(buttonHandler);		
		
		btnAnswer.setActionCommand("openAnswerFrame");
		btnAnswerEdit.setActionCommand("openEditAnswerFrame");
		btnClose.setActionCommand("closeInquireFrmae");		
		
		this.buttonPanel.add(this.btnAnswer);
		this.buttonPanel.add(this.btnAnswerEdit);
		this.buttonPanel.add(this.btnClose);		
		
		this.add(buttonPanel);
		
	}
	
	public void initialize() {
		this.inquireTable.updateInquireTable();
	}
	
	private void openAnswerFrame() {
		MInquire mInquire = this.inquireTable.getSelectedInquire();
		
		if(mInquire != null && !mInquire.getResult().equals("답변완료")) {
			this.answerFrame.setInquireInfo(mInquire);
			this.answerFrame.setVisible(true);
		
		} else if(mInquire.getResult().equals("답변완료")) {
			String message = "이미 답변이 완료되었습니다.\n답변을 수정하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				openEditAnswerFrame();
			}
						
		} else {
			
			MessageFrame.showMessage("알림", "답변할 문의사항을 선택해주세요.", MessageFrame.ERROR_MESSAGE);
			
		}
		
	}
	
	private void closeInquireFrmae() {
		this.dispose();
	}
	
	private void createAnswer() {
		this.answerFrame.createAnswer();
		this.inquireTable.updateInquireTable();
	}
	
	private void closeAnswerFrame() {
		this.answerFrame.closeAnswerFrame();
	}
	
	private void openEditAnswerFrame() {
		MInquire mInquire = this.inquireTable.getSelectedInquire();		
		
		if(mInquire != null && !mInquire.getResult().equals("답변대기")) {
			
			CInquireResult cInquireResult = new CInquireResult();
			String inquireNo = mInquire.getNo();
			MInquireResult mInquireResult = cInquireResult.getInquireResult(inquireNo);
			
			this.editAnswerFrame.setInquireInfo(mInquire);
			this.editAnswerFrame.setInquireResultInfo(mInquireResult);
			this.editAnswerFrame.setVisible(true);
		
		} else if(mInquire.getResult().equals("답변대기")) {
			String message = "아직 답변이 작성되지 않았습니다.\n답변을 작성하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				openAnswerFrame();
			}
						
		} else {
			JOptionPane.showMessageDialog(this, "수정할 답변의 문의사항을 선택해주세요.");
			
		}
	}
	
	private void updateAnswer() {
		this.editAnswerFrame.updateAnswer();
		this.inquireTable.updateInquireTable();
	}
	
	private void closeEditAnswerFrame() {
		this.editAnswerFrame.closeEditAnswerFrame();
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals("openAnswerFrame")) {
				openAnswerFrame();
			} else if(action.equals("openEditAnswerFrame")) {
				openEditAnswerFrame();
			} else if(action.equals("closeInquireFrmae")) {
				closeInquireFrmae();
			} else if(action.equals("createAnswer")) {
				createAnswer();
			} else if(action.equals("closeAnswerFrame")) {
				closeAnswerFrame();
			} else if(action.equals("updateAnswer")) {
				updateAnswer();
			} else if(action.equals("closeEditAnswerFrame")) {
				closeEditAnswerFrame();
			}
			
		}
		
	}

}
