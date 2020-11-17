package view.managerMgt;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controll.CManager;
import model.MManager;

public class ManagerTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<String> header;
	private DefaultTableModel tableModel;
	
	private Vector<MManager> mManagers;

	public ManagerTable() {
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    header = new Vector<>();
		header.addElement("no");
		header.addElement("아이디");
		header.addElement("이름");
		header.addElement("주소");
		header.addElement("이메일");
		
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);
//		this.getSelectionModel().addListSelectionListener(this.listSelectionHandler);	
		initialize();
	}
	
	public void initialize() {
		CManager cManager = new CManager();
		mManagers = cManager.getAllManager();
		updateTableContents(mManagers);
	}

	public void updateTableContents(Vector<MManager> mStudents) {

		this.tableModel.setRowCount(0);
		this.tableModel = new DefaultTableModel(header, 0);
		
		
		if(mStudents != null) {

			for(MManager mManager : mManagers) {
				Vector<String> row = new Vector<>();
				
				row.add(mManager.getNo());
				row.add(mManager.getId());
				row.add(mManager.getName());
				row.add(mManager.getAddress());
				row.add(mManager.getEmail());
	
				this.tableModel.addRow(row);	
			}
		
			this.getSelectionModel().addSelectionInterval(0, 0);
			
		}
		
		this.setModel(this.tableModel);
		
	}
	
	public MManager getSelectedManager() {
		
		int selectedRow = this.getSelectedRow();
		if(selectedRow < 0) {
			return null;
		}
		return this.mManagers.get(selectedRow);
	}
	
}