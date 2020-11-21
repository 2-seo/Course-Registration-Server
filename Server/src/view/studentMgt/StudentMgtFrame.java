package view.studentMgt;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controll.CStudent;
import model.MStudent;
import view.MessageFrame;

public class StudentMgtFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private StudentTable studentTable;
	
	private JPanel buttonPanel;
	private JButton btnFullSearch;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnClose;
	
	private JPanel searchPanel;
	private ButtonGroup btnGroup;
	private JRadioButton rbtnStuNum;
	private JRadioButton rbtnName;
	private JRadioButton rbtnMajor;
	private JRadioButton rbtnAddress;
	private JTextField txtSearch;
	private JButton btnSearch;
	
	private ActionListener buttonHandler;
	
	private CreateStudentFrame createStudentDialog;
	private EditStudentDialog editStudentDialog;
	
	public StudentMgtFrame() {
		
		this.setTitle("Studnet Manager");
		this.setSize(600, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);

		buttonHandler = new ButtonHandler();
		createStudentDialog = new CreateStudentFrame(buttonHandler);
		editStudentDialog = new EditStudentDialog(buttonHandler);
		
		// Student table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.studentTable = new StudentTable();
		scrollPane.setViewportView(this.studentTable);
		scrollPane.setBounds(0, 0, 600, 350);
		this.add(scrollPane);
						
		// Search Panel Setting
		btnSearch = new JButton("검색");
		btnSearch.setActionCommand("StudentSearch");
		btnSearch.addActionListener(buttonHandler);
		
		rbtnStuNum = new JRadioButton("학번");
		rbtnStuNum.setSelected(true);
		rbtnName = new JRadioButton("이름");
		rbtnMajor = new JRadioButton("전공");
		rbtnAddress = new JRadioButton("주소");
		
		txtSearch = new JTextField(10);
		
		btnGroup = new ButtonGroup();
		btnGroup.add(rbtnStuNum);
		btnGroup.add(rbtnName);
		btnGroup.add(rbtnMajor);
		btnGroup.add(rbtnAddress);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 350, 600, 50);
		
		searchPanel.add(rbtnStuNum);
		searchPanel.add(rbtnName);
		searchPanel.add(rbtnMajor);
		searchPanel.add(rbtnAddress);
		searchPanel.add(txtSearch);
		searchPanel.add(btnSearch);
		
		this.add(searchPanel);
		
		// Button Setting
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 400, 600, 50);
		
		btnFullSearch = new JButton("전체 검색");
		btnAdd = new JButton("학생 등록");
		btnEdit = new JButton("학생 정보 수정");
		btnRemove = new JButton("학생 삭제");
		btnClose = new JButton("닫기");
		
		btnFullSearch.setActionCommand("fullSearch");
		btnAdd.setActionCommand("openCreateStudentDialog");
		btnEdit.setActionCommand("openEditStudentDialog");
		btnRemove.setActionCommand("removeStudent");
		btnClose.setActionCommand("closeStudentMgtFrame");
		
		btnFullSearch.addActionListener(buttonHandler);
		btnAdd.addActionListener(buttonHandler);
		btnEdit.addActionListener(buttonHandler);
		btnRemove.addActionListener(buttonHandler);
		btnClose.addActionListener(buttonHandler);
		
		this.buttonPanel.add(btnFullSearch);
		this.buttonPanel.add(btnAdd);
		this.buttonPanel.add(btnEdit);
		this.buttonPanel.add(btnClose);
		
		this.add(buttonPanel);
		
	}
	
	public void initialize() {
		this.studentTable.initialize();
	}
	
	private void openCreateStudnetDialog() {
		if(editStudentDialog.isVisible()) {
			editStudentDialog.dispose();
		}
		this.createStudentDialog.setVisible(true);
		this.createStudentDialog.reset();
	}
	
	private void createStudent() {
		this.createStudentDialog.createStudent();
		this.studentTable.initialize();
	}
	
	private void closeCreateStudnetDialog() {
		this.createStudentDialog.dispose();
	}
	
	
	private void fullSearchStudent() {
		this.studentTable.initialize();		
	}
	
	private void openEditStudentDialog() {
		if(createStudentDialog.isVisible()) {
			createStudentDialog.dispose();
		}
		int selectedRow = this.studentTable.getSelectedRow();
		if(selectedRow < 0) {
//			showMessage("학생을 선택해주세요.");
			JOptionPane.showMessageDialog(StudentMgtFrame.this, "학생을 선택해주세요.", "검색 결과", JOptionPane.ERROR_MESSAGE);
			return;
		}
		MStudent mStudent = this.studentTable.getSelectedStudnet();
		Integer stuNum = mStudent.getStuNum();
		String name = mStudent.getName();
		Integer majorId = mStudent.getMajorId();
		String major = mStudent.getMajor();
		String password = mStudent.getPassword();
		String address = mStudent.getAddress();
		this.editStudentDialog.setStudentInfo(stuNum, name, majorId, major, password, address);
		this.editStudentDialog.setVisible(true);		
	}
	
	private void updateStudent() {
		this.editStudentDialog.updateStudent();
		this.studentTable.initialize();
	}
	
	private void closeEditStudentDialog() {
		this.editStudentDialog.dispose();
	}
	
	private void searchStudent() {
		CStudent cStudent = new CStudent();
		String mode = "";
		String data = "";
		String message = "";
		
		if(rbtnStuNum.isSelected()) {
			mode = "stuNum";
			
		} else if(rbtnName.isSelected()) {
			mode = "name";
			
		} else if(rbtnMajor.isSelected()) {
			mode = "major";
			
		} else if(rbtnAddress.isSelected()) {
			mode = "address";
		}
		
		data = this.txtSearch.getText();
		
		if(data.equals("")) {
			message = "공백은 불가합니다.";			
			MessageFrame.showMessage("알림", message, MessageFrame.ERROR_MESSAGE);
			return;
		}
		
		Vector<MStudent> mStudents = cStudent.getStudnets(mode, data);
		
		if(mStudents != null && mStudents.size() > 0) {
			
			message = "검색 완료";			
			this.studentTable.updateTableContents(mStudents);						
//			MessageFrame.showMessage("알림", message, MessageFrame.INFO_MESSAGE);
			
		} else {

			message = "검색 결과가 존재하지 않습니다.";
//			MessageFrame.showMessage("알림", message, MessageFrame.ERROR_MESSAGE);
			
		}

	}
	
	private void openMajorDialog() {
		if(createStudentDialog.isVisible()) {
			this.createStudentDialog.openMajorDialog();
		} else if(editStudentDialog.isVisible()) {
			this.editStudentDialog.openMajorDialog();
		}
	}
	
	
	private void getSelectedMajor() {
		if(createStudentDialog.isVisible()) {
			this.createStudentDialog.getSelectedMajor();
		
		} else if(editStudentDialog.isVisible()) {
			this.editStudentDialog.getSelectedMajor();

		}
	}
	
	private void removeStudent() {
		String message = "학생 정보를 삭제하시겠습니까?";
		String title = "알림";
		int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			CStudent cStudent = new CStudent();
			MStudent mStudent = this.studentTable.getSelectedStudnet();
			Integer stuNum = mStudent.getStuNum();
			cStudent.removeStudnet(stuNum);
		}
	}
	
	private void closeStudentMgtFrame() {
		this.dispose();
	}
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String action = e.getActionCommand();
			
			if(action == "openCreateStudentDialog") {
				openCreateStudnetDialog();
			} else if(action == "openEditStudentDialog") {
				openEditStudentDialog();

			} else if(action == "updateStudentInfo") {
				updateStudent();
			} else if(action == "fullSearch") {
				fullSearchStudent();
				
			} else if(action == "StudentSearch") {
				searchStudent();
				
			} else if(action == "majorSearch") {
				openMajorDialog();
				
			} else if(action == "regist") {
				createStudent();
				
			} else if(action == "cancle") {
				closeCreateStudnetDialog();
				
			} else if(action == "ok") {
				getSelectedMajor();
				
			} else if(action == "closeEditStudentDialog") {
				closeEditStudentDialog();
				
			} else if(action == "removeStudent") {
				removeStudent();
				
			} else if(action == "closeStudentMgtFrame") {
				closeStudentMgtFrame();
				
			}
			
		}
				
	}


}
