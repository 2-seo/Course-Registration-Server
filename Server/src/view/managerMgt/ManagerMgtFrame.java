package view.managerMgt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controll.CManager;
import model.MManager;
import view.MessageFrame;

public class ManagerMgtFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private ManagerTable managerTable;
	
	private JPanel pButton;
	private JButton btnCreate;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnClose;
	
	private ActionListener buttonHandler;
	private CreateManagerDialog createManagerDialog;
	private EditManagerDialog editManagerDialog;
	
	public ManagerMgtFrame() {
		
		this.setTitle("MJU Manager");
		this.setSize(600, 470);
		this.setLocationRelativeTo(null);		
		this.setResizable(false);
		this.setLayout(null);
		buttonHandler = new ButtonHandler();
		createManagerDialog = new CreateManagerDialog(buttonHandler);
		editManagerDialog = new EditManagerDialog(buttonHandler);
		
		// Table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.managerTable = new ManagerTable();		
		scrollPane.setViewportView(this.managerTable);
		scrollPane.setBounds(0, 0, 600, 350);
		this.add(scrollPane);
		
		// Button Setting
		pButton = new JPanel();
		btnCreate = new JButton("관리자 추가");
		btnCreate.setActionCommand("openCreateManagerDialog");
		btnCreate.addActionListener(buttonHandler);
		btnEdit = new JButton("관리자 수정");
		btnEdit.setActionCommand("openEditManagerDialog");
		btnEdit.addActionListener(buttonHandler);
		btnRemove = new JButton("관리자 삭제");
		btnRemove.setActionCommand("deleteManager");
		btnRemove.addActionListener(buttonHandler);
		btnClose = new JButton("닫기");
		btnClose.setActionCommand("closeManagerMgtFrame");
		btnClose.addActionListener(buttonHandler);
		
		
		pButton.add(btnCreate);
		pButton.add(btnEdit);
		pButton.add(btnRemove);
		pButton.add(btnClose);
		pButton.setBounds(0, 370, 600, 50);
		this.add(pButton);
		
		
	}
	
	private void openManagerDialog() {
		this.createManagerDialog.setVisible(true);		
	}
	
	private void createManager() {
		this.createManagerDialog.createManager();
		this.managerTable.initialize();
	}
	
	private void closeCreateManagerDialog() {
		this.createManagerDialog.dispose();
	}
	
	private void openEditManagerDialog() {
		MManager selectedManager = this.managerTable.getSelectedManager();
		if(selectedManager != null) {
		this.editManagerDialog.setValue(selectedManager);
		this.editManagerDialog.setVisible(true);
		} else {
			System.out.println("수정할 관리자를 선택해주세요.");
			MessageFrame.showMessage("알림", "수정할 관리자를 선택해주세요.", MessageFrame.ERROR_MESSAGE);
		}
	}
	
	private void closeEditManagerDialog() {
		this.editManagerDialog.dispose();
	}
	
	private void updateManager() {
		this.editManagerDialog.updateManager();
		this.managerTable.initialize();
	}
	
	private void deleteManager() {							
		
		MManager selectedManager = this.managerTable.getSelectedManager();
		if(selectedManager != null) {
			
			String message = "관리자를 삭제하시겠습니까?";
			String optionTitle = "알림";
			int reply = JOptionPane.showConfirmDialog(this, message, optionTitle, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
				CManager cManager = new CManager();
				cManager.deleteManager(selectedManager);
				this.managerTable.initialize();
				
			}
				
		} else {
			
			MessageFrame.showMessage("알림", "삭제할 관리자를 선택해주세요.", MessageFrame.ERROR_MESSAGE);
			
		}
		
	}
	
	private void closeManagerMgtFrame() {
		this.dispose();
	}
	
	private class ButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if(action.equals("openCreateManagerDialog")) {
				openManagerDialog();
				
			} else if(action.equals("createManager")) {			
				createManager();
				
			} else if(action.equals("closeCreateManagerDialog")) {
				closeCreateManagerDialog();
			
			} else if(action.equals("openEditManagerDialog")) {
				openEditManagerDialog();
				
			} else if(action.equals("closeEditManagerDialog")) {
				closeEditManagerDialog();
				
			} else if(action.equals("updateManager")) {
				updateManager();
				
			} else if(action.equals("deleteManager")) {
				deleteManager();
			} else if(action.equals("closeManagerMgtFrame")) {
				closeManagerMgtFrame();
				
			}
		}
		
	}
	
}
