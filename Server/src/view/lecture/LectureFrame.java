package view.lecture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controll.CLecture;
import model.MLecture;
import view.MessageFrame;

public class LectureFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CampusTable campusTable;
	private CollegeTable collegeTable;
	private MajorTable majorTable;
	private LectureTable lectureTable;
	
	private JPanel buttonPanel;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnClose;
	
	private ListSelectionListener listSelectionHandler;
	private ActionListener buttonHandler;
	
	private AddLectureDialog addLectureDialog;
	private	EditLectureDialog editLectureDialog;
	
	private CLecture cLecture;	
	
	public LectureFrame() {
		
		this.setTitle("Lecture Manager");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		listSelectionHandler = new ListSelectionHandler();
		buttonHandler = new ButtonHandler();
		addLectureDialog = new AddLectureDialog(buttonHandler);
		editLectureDialog = new EditLectureDialog(buttonHandler);
		cLecture = new CLecture();
		
		// Campus table Setting
		JScrollPane scrollPane = new JScrollPane();
		this.campusTable = new CampusTable(this.listSelectionHandler);
		scrollPane.setViewportView(this.campusTable);
		scrollPane.setBounds(0, 0, 200, 150);
		this.add(scrollPane);
		
		// College table Setting
		scrollPane = new JScrollPane();
		this.collegeTable = new CollegeTable(this.listSelectionHandler);
		scrollPane.setViewportView(this.collegeTable);
		scrollPane.setBounds(200, 0, 200, 150);
		this.add(scrollPane);
		
		// Major table Setting
		scrollPane = new JScrollPane();
		this.majorTable = new MajorTable(this.listSelectionHandler);
		scrollPane.setViewportView(this.majorTable);
		scrollPane.setBounds(400, 0, 200, 150);
		this.add(scrollPane);
		
		// Lecture table Setting
		scrollPane = new JScrollPane();
		this.lectureTable = new LectureTable();
		scrollPane.setViewportView(this.lectureTable);
		scrollPane.setBounds(0, 150, 600, 250);
		this.add(scrollPane);
		
		// Button Setting
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 400, 600, 100);
		btnAdd = new JButton("강좌추가");
		btnEdit = new JButton("강좌수정");
		btnRemove = new JButton("강좌삭제");
		btnClose = new JButton("닫기");
		
		btnAdd.addActionListener(buttonHandler);
		btnEdit.addActionListener(buttonHandler);
		btnRemove.addActionListener(buttonHandler);
		btnClose.addActionListener(buttonHandler);
		
		btnAdd.setActionCommand("openAddDialog");
		btnEdit.setActionCommand("openEditDialog");
		btnRemove.setActionCommand("removeLecture");
		btnClose.setActionCommand("closeLectureFrame");
		
		this.buttonPanel.add(this.btnAdd);
		this.buttonPanel.add(this.btnEdit);
		this.buttonPanel.add(this.btnRemove);
		this.buttonPanel.add(this.btnClose);
		
		this.add(buttonPanel);
		
		initialize();
		
	}
	
	private void initialize() {
		Integer campusId = this.campusTable.getCampus();
		Integer collegeId = this.collegeTable.getCollege(campusId);
		Integer majorId = this.majorTable.getMajor(collegeId);
		this.lectureTable.updateLectureTable(majorId);
		
	}
	
	
	private void updateGangjwas(Object source) {
		
		if(source.equals(this.campusTable.getSelectionModel())) {
			Integer campusId = this.campusTable.getSelectedId();
			Integer collegeId = this.collegeTable.getCollege(campusId);
			Integer majorId = this.majorTable.getMajor(collegeId);
			this.lectureTable.updateLectureTable(majorId);
			
		} else if(source.equals(this.collegeTable.getSelectionModel())) {
			Integer collegeId = this.collegeTable.getSelectedId();
			Integer majorId = this.majorTable.getMajor(collegeId);
			this.lectureTable.updateLectureTable(majorId);
			
		} else if(source.equals(this.majorTable.getSelectionModel())) {
			Integer majorId = this.majorTable.getSelectedId();
			this.lectureTable.updateLectureTable(majorId);
		}
		
	}
	
	private class ListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {

			updateGangjwas(event.getSource());
	
		}
	}
	
	private void openAddLectureDialog() {
		this.addLectureDialog.setVisible(true);	
	}
	
	private void openEditLectureDialog() {
		Vector<MLecture> mLectures = this.lectureTable.getLecture();
		int selectedRow = this.lectureTable.getSelectedRow();
		if(selectedRow < 0) {
			MessageFrame.showMessage("알림", "강좌를 선택해주세요.", MessageFrame.ERROR_MESSAGE);
			return;
		}
		
		Integer lectureId = mLectures.get(selectedRow).getId();
		String title = mLectures.get(selectedRow).getName();
		String lecturer = mLectures.get(selectedRow).getLecturer();
		String credit = mLectures.get(selectedRow).getCredit();
		String time = mLectures.get(selectedRow).getTime();
		
		this.editLectureDialog.setText(lectureId, title, lecturer, credit, time);
		this.editLectureDialog.setVisible(true);
	}
	
	private void closeEditLectureDialog() {
		editLectureDialog.closeForm();
	}
	
	private void closeAddLectureDialog() {
		this.addLectureDialog.closeForm();
	}
	
	
	private void addLecture() {
		Integer selectedMajorId = this.majorTable.getSelectedId();
		this.addLectureDialog.addLecture(selectedMajorId);
		this.lectureTable.updateLectureTable(selectedMajorId);
	}
	
	private void updateLecture() {
		this.editLectureDialog.updateLecture();
		Integer selectedMajorId = this.majorTable.getSelectedId();
		this.lectureTable.updateLectureTable(selectedMajorId);
		
	}
	
	private void removeLecture() {
		String message = "강좌를 삭제하시겠습니까?";
		String optionTitle = "알림";
		int reply = JOptionPane.showConfirmDialog(null, message, optionTitle, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			Integer selectedLectureId = this.lectureTable.getSelectedLectureId();
			this.cLecture.remvoeLecture(selectedLectureId);
			Integer selectedMajorId = this.majorTable.getSelectedId();
			this.lectureTable.updateLectureTable(selectedMajorId);
		}
	}
	
	private void closeLectureFrame() {
		this.dispose();
	}
	
	private class ButtonHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			
			if(action == "openAddDialog") {
				openAddLectureDialog();
				
			} else if(action == "openEditDialog") {
				openEditLectureDialog();
			
			} else if(action == "updateLecture") {
				updateLecture();
				
			} else if(action.equals("closeEditLectureDialog")) {
				closeEditLectureDialog();
				
			} else if(action == "removeLecture") {				
				removeLecture();
				
			} else if(action == "addLecture") {
				addLecture();
				
			} else if (action == "closeAddLectureDialog") {
				closeAddLectureDialog();
			} else if(action.equals("closeLectureFrame")) {
				closeLectureFrame();
			}
			
		}
		
	}
	
}
