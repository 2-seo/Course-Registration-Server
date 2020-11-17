package view.lecture;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controll.CLecture;
import model.MLecture;

public class LectureTable extends JTable {
	
	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	
//	ListSelectionListener listSelectionHandler;
	Vector<String> header;
	private Vector<MLecture> mLectures;
	
	
	public LectureTable() {
		// attributes
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		this.listSelectionHandler = listSelectionHandler;
//		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		
		
		// data model
		header = new Vector<>();
		header.addElement("No.");
		header.addElement("Title");
		header.addElement("Lecturer");
		header.addElement("Credit");
		header.addElement("Time");
		
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
		
		initialize();
	}

	public void initialize() {

	}
	
	
	public Integer getSelectedLectureId() {
		int selectedIndex = this.getSelectedRow();
		if(selectedIndex >= 0) {
			Integer selectedLectureId = this.mLectures.get(selectedIndex).getId();
			return selectedLectureId;
		}
		return null;
	}

	public void updateLectureTable(Integer majorId) {
//		this.getSelectionModel().removeListSelectionListener(this.listSelectionHandler);
		
		
		// tablemodel의 rowCount를 0으로 만들어서 초괴화를 한다.
		this.tableModel.setRowCount(0);
		
		CLecture cLecture = new CLecture();
		this.mLectures = cLecture.getLecture(majorId);
		
		for (MLecture mLecture : this.mLectures) {
			Vector<String> row = new Vector<String>();
			row.add(mLecture.getId().toString());
			row.add(mLecture.getName());
			row.add(mLecture.getLecturer());
			row.add(mLecture.getCredit());
			row.add(mLecture.getTime());
			this.tableModel.addRow(row);
		}

		if (mLectures.size() > 0) {
			this.getSelectionModel().addSelectionInterval(0, 0);
//			selectedFileName = vDirectories.get(0).getFileName();
		}
		
//		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);

	}

	public Vector<MLecture> getLecture() {
		return this.mLectures;
	}
	
}