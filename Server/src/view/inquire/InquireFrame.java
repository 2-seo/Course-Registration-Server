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
		btnAnswer = new JButton("�亯�ϱ�");
		btnAnswerEdit = new JButton("�亯����");
		btnClose = new JButton("�ݱ�");		
		
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
		
		if(mInquire != null && !mInquire.getResult().equals("�亯�Ϸ�")) {
			this.answerFrame.setInquireInfo(mInquire);
			this.answerFrame.setVisible(true);
		
		} else if(mInquire.getResult().equals("�亯�Ϸ�")) {
			String message = "�̹� �亯�� �Ϸ�Ǿ����ϴ�.\n�亯�� �����Ͻðڽ��ϱ�?";
			String optionTitle = "�˸�";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				openEditAnswerFrame();
			}
						
		} else {
			
			MessageFrame.showMessage("�˸�", "�亯�� ���ǻ����� �������ּ���.", MessageFrame.ERROR_MESSAGE);
			
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
		
		if(mInquire != null && !mInquire.getResult().equals("�亯���")) {
			
			CInquireResult cInquireResult = new CInquireResult();
			String inquireNo = mInquire.getNo();
			MInquireResult mInquireResult = cInquireResult.getInquireResult(inquireNo);
			
			this.editAnswerFrame.setInquireInfo(mInquire);
			this.editAnswerFrame.setInquireResultInfo(mInquireResult);
			this.editAnswerFrame.setVisible(true);
		
		} else if(mInquire.getResult().equals("�亯���")) {
			String message = "���� �亯�� �ۼ����� �ʾҽ��ϴ�.\n�亯�� �ۼ��Ͻðڽ��ϱ�?";
			String optionTitle = "�˸�";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				openAnswerFrame();
			}
						
		} else {
			JOptionPane.showMessageDialog(this, "������ �亯�� ���ǻ����� �������ּ���.");
			
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
