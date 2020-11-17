package view.notice;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controll.CNotice;
import view.MessageFrame;

public class WriteNoticeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel pContainer;
	
	private JPanel pNorth;
	private JPanel pCenter;
	private JPanel pSouth;
	
	private JLabel lblTitle;
	private JTextField txtTtile;
	private JTextArea txtContent;
	private JButton btnOk;
	private JButton btnCancle;
	
	private NoticeTable noticeTable;
	private ActionListener buttonHandler;
	
	public WriteNoticeDialog(NoticeTable noticeTable) {
		this.setSize(600, 500);
		
		this.pContainer = new JPanel();
		this.pContainer.setSize(600, 500);
		this.pContainer.setLayout(new BorderLayout(10, 10));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.noticeTable = noticeTable;
		
		// North
		this.pNorth = new JPanel();
		this.pNorth.setLayout(new BoxLayout(this.pNorth, BoxLayout.X_AXIS));
		
		this.lblTitle = new JLabel("제목");
		this.txtTtile = new JTextField(50);
		
		this.pNorth.add(lblTitle);
		this.pNorth.add(txtTtile);
		
		this.pContainer.add(pNorth, BorderLayout.NORTH);
		
		// Center
		this.pCenter = new JPanel();
		this.pCenter.setLayout(new GridLayout(1,1));
		
		this.txtContent = new JTextArea();
		this.txtContent.setLineWrap(true);
		
		this.pCenter.add(txtContent);
		
		this.pContainer.add(pCenter, BorderLayout.CENTER);
		
		// South
		this.pSouth = new JPanel();
		
		this.btnOk = new JButton("작성");
		this.btnCancle = new JButton("취소");
		
		this.btnOk.setActionCommand("edit");
		this.btnCancle.setActionCommand("cancle");
		
		buttonHandler = new ButtonHandler();
		btnOk.addActionListener(buttonHandler);
		btnCancle.addActionListener(buttonHandler);
		
		this.pSouth.add(btnOk);
		this.pSouth.add(btnCancle);

		this.pContainer.add(pSouth, BorderLayout.SOUTH);
		
		this.add(pContainer);
		
	}
	
	private void edit() {
		String title = this.txtTtile.getText();
		String contents = this.txtContent.getText();
		String writer = "관리자";

		if(!title.equals("") && !contents.equals("")) {
			String message = "공지사항을 등록하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				CNotice cNotice = new CNotice();
				cNotice.createNotice(title, contents, writer);
								
				MessageFrame.showMessage("알림", "공지사항을 작성했습니다.", MessageFrame.INFO_MESSAGE);
			}
			this.dispose();
		} else {			
			MessageFrame.showMessage("알림", "공백이 존재합니다.", MessageFrame.ERROR_MESSAGE);
		}
		
		noticeTable.updateTableContents();
		
	}
	
	private void hideForm() {
		this.dispose();
	}
	
	public void setText(String title, String contents) {
		this.txtTtile.setText(title);
		this.txtContent.setText(contents);
	}
	
	public void clearText() {
		this.txtTtile.setText("");
		this.txtContent.setText("");
	}
	
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			
			if(action == "edit") {
				edit();
			} else if(action == "cancle") {
				hideForm();
			}
			
		}
		
	}

}
