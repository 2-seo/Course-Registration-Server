package view.studentMgt;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.MMajor;


public class MajorFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private MajorTable majorTable;

	private JPanel pFooter;
	private JButton btnOk;
	
	
	public MajorFrame(ActionListener buttonHandler) {
		
		this.setTitle("Major Search");
		this.setSize(230, 330);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		// Major table Setting
		JScrollPane scrollPane = new JScrollPane();
		majorTable = new MajorTable(buttonHandler);

		scrollPane.setViewportView(this.majorTable);
		scrollPane.setBounds(0, 0, 230, 250);
		this.add(scrollPane);
		
		// OK Button
		pFooter = new JPanel();
		btnOk = new JButton("»Æ¿Œ");
		btnOk.setActionCommand("ok");
		btnOk.addActionListener(buttonHandler);
		pFooter.add(btnOk);
		pFooter.setBounds(0, 260, 230, 100);
		this.add(pFooter);
		
		
	}

	public MMajor getSelectedMajor() {		
		
		return this.majorTable.getSelectedMajor();
	}
}
