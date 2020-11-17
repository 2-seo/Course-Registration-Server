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

public class EditNoticeDialog extends JDialog {

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
	
	private Integer id;
	
	public EditNoticeDialog(NoticeTable noticeTable) {
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
		
		this.lblTitle = new JLabel("����");
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
		
		this.btnOk = new JButton("����");
		this.btnCancle = new JButton("���");
		
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
	

	public void setId(Integer id) {
		this.id = id;		
	}
	
	private void edit() {
		
		String title = this.txtTtile.getText();
		String contents = this.txtContent.getText();
//		String writer = "������";

		if(!title.equals("") && !contents.equals("")) {
			String message = "���������� �����Ͻðڽ��ϱ�?";
			String optionTitle = "�˸�";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				CNotice cNotice = new CNotice();
				cNotice.updateNotice(this.id, title, contents);
				
				System.out.println("Success");
			}
			this.dispose();
		} else {
			System.out.println("text is null");
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
