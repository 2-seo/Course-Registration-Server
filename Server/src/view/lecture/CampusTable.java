package view.lecture;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controll.CLecture;
import model.MCampus;

public class CampusTable extends JTable {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	
	ListSelectionListener listSelectionHandler;
	Vector<String> header;
	private Vector<MCampus> mCampuses;
	
	
	public CampusTable(ListSelectionListener listSelectionHandler) {
		// attributes
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listSelectionHandler = listSelectionHandler;
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		
		
		// data model
		header = new Vector<>();
		header.addElement("Campus");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);

	}

	public void initialize() {
		
	}
	
	
	public Integer getSelectedId() {
		int selectedIndex = this.getSelectedRow();
		if(selectedIndex >= 0) {
			Integer selectedCollegeId = this.mCampuses.get(selectedIndex).getId();
			return selectedCollegeId;
		}
		return null;
	}

	public Integer getCampus() {
		this.getSelectionModel().removeListSelectionListener(this.listSelectionHandler);
		Integer campusId = -1;
		// tablemodel의 rowCount를 0으로 만들어서 초괴화를 한다.
		this.tableModel.setRowCount(0);
		
		CLecture cLecture = new CLecture();
		this.mCampuses = cLecture.getCampus();
		
		for (MCampus mCampus : this.mCampuses) {
			Vector<String> row = new Vector<String>();
			row.add(mCampus.getName());
			this.tableModel.addRow(row);
		}

		if (mCampuses.size() > 0) {
			this.getSelectionModel().addSelectionInterval(0, 0);
			campusId = mCampuses.get(0).getId();
		}
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		
		return campusId;
	}
	
}