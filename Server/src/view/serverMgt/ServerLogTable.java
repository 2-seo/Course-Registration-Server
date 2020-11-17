package view.serverMgt;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.MLog;

public class ServerLogTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<String> header;
	private DefaultTableModel tableModel;
	
	private Vector<MLog> mLogs;

	public ServerLogTable() {
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	    header = new Vector<>();
		header.addElement("IP");
		header.addElement("Log");
		header.addElement("Time");
		header.addElement("CCU");
		
		mLogs = new Vector<>();
		
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);

	}
	
	public void initialize() {

	}

	public void updateTableContents(MLog mLog) {

		this.tableModel.setRowCount(0);
//		this.tableModel = new DefaultTableModel(header, 0);
		mLogs.add(mLog);
		
		if(mLogs != null && mLogs.size() > 0) {

			for(MLog log : mLogs) {
				Vector<String> row = new Vector<>();
				
				row.add(log.getIp());
				row.add(log.getLog());
				row.add(log.getTime());
				row.add(log.getConnectionSize());

				this.tableModel.addRow(row);	
			}

		}
		
//		this.setModel(this.tableModel);
		
	}
	
}