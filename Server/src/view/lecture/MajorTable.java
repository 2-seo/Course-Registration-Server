package view.lecture;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controll.CLecture;
import model.MMajor;

public class MajorTable extends JTable {
	private static final long serialVersionUID = 1L;

	protected DefaultTableModel tableModel;
	
	protected ListSelectionListener listSelectionHandler;
	protected Vector<String> header;
	protected Vector<MMajor> mMajors;
	
	
	public MajorTable(ListSelectionListener listSelectionHandler) {
		// attributes
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listSelectionHandler = listSelectionHandler;
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
		
		
		// data model
		header = new Vector<>();
		header.addElement("Major");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
		
		initialize();
	}

	public void initialize() {

	}
	
	
	public Integer getSelectedId() {
		int selectedIndex = this.getSelectedRow();
		if(selectedIndex >= 0) {
			Integer selectedMajorId = this.mMajors.get(selectedIndex).getId();
			return selectedMajorId;
		}
		return null;
	}

	public Integer getMajor(Integer collegeId) {
		this.getSelectionModel().removeListSelectionListener(this.listSelectionHandler);
		Integer majorId = -1;
		// tablemodel의 rowCount를 0으로 만들어서 초괴화를 한다.
		this.tableModel.setRowCount(0);
		
		CLecture cLecture = new CLecture();
		this.mMajors = cLecture.getMajor(collegeId);
		
		for (MMajor mMajor : this.mMajors) {
			Vector<String> row = new Vector<>();
			row.add(mMajor.getName());
			this.tableModel.addRow(row);
		}

		if (mMajors.size() > 0) {
			this.getSelectionModel().addSelectionInterval(0, 0);
			majorId = this.mMajors.get(0).getId();
		}
		
		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);

		return majorId;
	}
	
}