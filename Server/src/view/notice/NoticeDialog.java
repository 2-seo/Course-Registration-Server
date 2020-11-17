package view.notice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controll.CNotice;
import model.MNotice;
import view.MessageFrame;

public class NoticeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private NoticeTable noticeTable;
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnClose;

	private ActionListener buttonHandler;
	
	private WriteNoticeDialog writeNoticeDialog;
	private EditNoticeDialog editNoticeDialog;
	
	public NoticeDialog() {
		
		this.setTitle("Notice Manager");
		this.setSize(600, 380);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		// Notice table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.noticeTable = new NoticeTable();
		scrollPane.setViewportView(this.noticeTable);
		scrollPane.setBounds(0, 0, 600, 300);
		this.add(scrollPane);
		
		// Button Panel Setting
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 300, 600, 100);
		
		buttonHandler = new ButtonHandler();
		
		btnAdd = new JButton("글쓰기");
		btnEdit = new JButton("편집");
		btnRemove = new JButton("삭제");
		btnClose = new JButton("닫기");
		
		btnAdd.setActionCommand("add");
		btnEdit.setActionCommand("edit");
		btnRemove.setActionCommand("remove");
		btnClose.setActionCommand("close");
		
		btnAdd.addActionListener(buttonHandler);
		btnEdit.addActionListener(buttonHandler);
		btnRemove.addActionListener(buttonHandler);
		btnClose.addActionListener(buttonHandler);
		
		this.buttonPanel.add(btnAdd);
		this.buttonPanel.add(btnEdit);
		this.buttonPanel.add(btnRemove);
		this.buttonPanel.add(btnClose);
		
		this.add(buttonPanel);
		
		initialize();
		
	}
	
	public void initialize() {
		writeNoticeDialog = new WriteNoticeDialog(this.noticeTable);
		editNoticeDialog = new EditNoticeDialog(this.noticeTable);
	}
	
	private void openWriteNoticeDialog() {

		writeNoticeDialog.setVisible(true);
	
	}
	
	private void openEditNoticeDialog() {
		
		int selectedRow = this.noticeTable.getSelectedRow();
		
		if(selectedRow != -1) {
			
			Vector<MNotice> mNotices = this.noticeTable.getMNotices();
			MNotice mNotice = mNotices.get(selectedRow);
			Integer id = Integer.parseInt(mNotice.getId()); 
			String title = mNotice.getTitle();
			String contents = mNotice.getContents();
			this.editNoticeDialog.setText(title, contents);
			this.editNoticeDialog.setId(id);
			
			this.editNoticeDialog.setVisible(true);
			
		} else {
			System.out.println("no selected row");
			return;
		}
		
		
	}
	
	private void deleteNotice() {
		int selectedRow = this.noticeTable.getSelectedRow();
		
		if(selectedRow != -1) {
			

			String message = "공지사항을 삭제하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				CNotice cNotice = new CNotice();
				
				Vector<MNotice> mNotices = this.noticeTable.getMNotices();
				MNotice mNotice = mNotices.get(selectedRow);
				Integer id = Integer.parseInt(mNotice.getId());
				cNotice.deleteNotice(id);
				this.noticeTable.updateTableContents();
				
				MessageFrame.showMessage("알림", "선택한 공지사항을 삭제했습니다.", MessageFrame.INFO_MESSAGE);
			}
		} else {
			System.out.println("no selected row");
			MessageFrame.showMessage("알림", "삭제할 공지사항을 선택해주세요.", MessageFrame.ERROR_MESSAGE);
		}
	}
	
	private void closeNoticeDialog() {
		this.dispose();
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			
			if(action == "add") {
				openWriteNoticeDialog();
				
			} else if(action == "edit") {
				openEditNoticeDialog();
				
			} else if(action == "remove") {
				deleteNotice();
			} else if(action == "close") {
				closeNoticeDialog();
			}
			
		}
		
	}
	
}
