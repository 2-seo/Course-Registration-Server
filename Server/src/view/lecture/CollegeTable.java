package view.lecture;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controll.CLecture;
import model.MCollege;

public class CollegeTable extends JTable {
	private static final long serialVersionUID = 1L;

	protected DefaultTableModel tableModel;
	
	protected ListSelectionListener listSelectionHandler;
	protected Vector<String> header;
	protected Vector<MCollege> mColleges;
	
	public CollegeTable(ListSelectionListener listSelectionHandler) {
		// attributes
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listSelectionHandler = listSelectionHandler;
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		
		
		// data model
		header = new Vector<>();
		header.addElement("College");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
		
		initialize();
	}

	public void initialize() {
		
	}
	
	
	public Integer getSelectedId() {
		int selectedIndex = this.getSelectedRow();
		if(selectedIndex >= 0) {
			Integer selectedCollegeId = this.mColleges.get(selectedIndex).getId();
			return selectedCollegeId;
		}
		return null;
	}

	public Integer getCollege(Integer campusId) {
		this.getSelectionModel().removeListSelectionListener(this.listSelectionHandler);
		Integer collegeId = -1;
		
		// tablemodel의 rowCount를 0으로 만들어서 초괴화를 한다.
		this.tableModel.setRowCount(0);
		
		CLecture cLecture = new CLecture();
		this.mColleges = cLecture.getCollege(campusId);
		
		for (MCollege mCollege : this.mColleges) {
			Vector<String> row = new Vector<String>();
			row.add(mCollege.getName());
			this.tableModel.addRow(row);
		}

		if (mColleges.size() > 0) {
			this.getSelectionModel().addSelectionInterval(0, 0);
			collegeId = mColleges.get(0).getId();
		}
		
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);

		return collegeId;
	}
	
}