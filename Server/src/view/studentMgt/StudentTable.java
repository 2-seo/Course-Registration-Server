package view.studentMgt;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controll.CStudent;
import model.MStudent;

public class StudentTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<String> header;
	private DefaultTableModel tableModel;
	
	private Vector<MStudent> mStudents;

	public StudentTable() {
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    header = new Vector<>();
		header.addElement("학번");
		header.addElement("이름");
		header.addElement("전공");
		header.addElement("주소");			
		
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
//		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);	
//		initialize();
	}
	
	public void initialize() {
		CStudent cStudent = new CStudent();
		mStudents = cStudent.getAllStudent();
		updateTableContents(mStudents);
	}

	public void updateTableContents(Vector<MStudent> mStudents) {
		this.mStudents = mStudents;
		this.tableModel.setRowCount(0);
//		this.tableModel = new DefaultTableModel(header, 0);
		
		
		if(mStudents != null) {

			for(MStudent mStudent : this.mStudents) {
				Vector<String> row = new Vector<>();
				
				row.add(mStudent.getStuNum().toString());
				row.add(mStudent.getName());
				row.add(mStudent.getMajor());
				row.add(mStudent.getAddress());
	
				this.tableModel.addRow(row);	
			}
		
			this.getSelectionModel().addSelectionInterval(0, 0);
		}
		
//		this.setModel(this.tableModel);
		
	}
	
	public MStudent getSelectedStudnet() {
		int selectedRow = this.getSelectedRow();
		return this.mStudents.get(selectedRow);
	}
	
}