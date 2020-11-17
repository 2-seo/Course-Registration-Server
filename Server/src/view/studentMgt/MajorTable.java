package view.studentMgt;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controll.CLecture;
import model.MMajor;

public class MajorTable extends JTable {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel tableModel;
	
	
	private Vector<String> header;
	private Vector<MMajor> mMajors;
	
	
	public MajorTable(ActionListener buttonHandler) {
		// attributes
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		
		// data model
		header = new Vector<>();
		header.addElement("Major");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
		
		initialize();
	}

	public void initialize() {
		getMajor();
	}
	
	
//	public Integer getSelectedId() {
//		int selectedIndex = this.getSelectedRow();
//		if(selectedIndex >= 0) {
//			Integer selectedMajorId = this.mMajors.get(selectedIndex).getId();
//			return selectedMajorId;
//		}
//		return null;
//	}

	public void getMajor() {
		
		// tablemodel의 rowCount를 0으로 만들어서 초괴화를 한다.
		this.tableModel.setRowCount(0);
		this.tableModel = new DefaultTableModel(header, 0);
		
		CLecture cLecture = new CLecture();
		this.mMajors = cLecture.getAllMajor();
		
		for (MMajor mMajor : this.mMajors) {
			Vector<String> row = new Vector<>();
			row.add(mMajor.getName());
			this.tableModel.addRow(row);
		}

		if (mMajors.size() > 0) {
			this.getSelectionModel().addSelectionInterval(0, 0);
		}
		this.setModel(this.tableModel);

	}

	public MMajor getSelectedMajor() {
		int selectedRow = this.getSelectedRow();
//		System.out.println(selectedRow);
		
		MMajor selectedMajor = this.mMajors.get(selectedRow);
		return selectedMajor;
	}
	
}