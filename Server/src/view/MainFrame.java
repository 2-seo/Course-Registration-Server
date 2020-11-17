package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.inquire.InquireFrame;
import view.lecture.LectureFrame;
import view.managerMgt.ManagerMgtFrame;
import view.notice.NoticeDialog;
import view.serverMgt.ServerMgtFrame;
import view.studentMgt.StudentMgtFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	
	private ActionListener buttonHandler; 
	
	public MainFrame() {
		
		this.setTitle("MJU Manager");
		this.setSize(300, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		buttonHandler = new buttonHandler();
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3, 2));
		
		btn1 = new JButton("傍瘤荤亲");
		btn1.addActionListener(buttonHandler);
		btn1.setActionCommand("notice");
		
		btn2 = new JButton("碍谅包府");
		btn2.addActionListener(buttonHandler);
		btn2.setActionCommand("lecture");
		
		btn3 = new JButton("巩狼包府");
		btn3.addActionListener(buttonHandler);
		btn3.setActionCommand("inquire");
		
		btn4 = new JButton("切积包府");
		btn4.addActionListener(buttonHandler);
		btn4.setActionCommand("student");
		
		btn5 = new JButton("包府磊包府");
		btn5.addActionListener(buttonHandler);
		btn5.setActionCommand("manager");
		
		btn6 = new JButton("辑滚包府");
		btn6.addActionListener(buttonHandler);
		btn6.setActionCommand("server");
		
		
		container.add(btn1);
		container.add(btn2);
		container.add(btn3);
		container.add(btn4);
		container.add(btn5);
		container.add(btn6);
		
		this.add(container);
		
		initialize();
	}
	
	private void initialize() {
		
	}
	
	private class buttonHandler implements ActionListener {

		NoticeDialog noticeDialog = null;
		LectureFrame lectureFrame = null;
		InquireFrame inquireFrame = null;
		StudentMgtFrame studentMgtFrame = null;
		ManagerMgtFrame managerMgtFrame = null;
		ServerMgtFrame serverMgtFrame = null;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			
			if(action == "notice") {
				if(noticeDialog != null) {
					noticeDialog.setVisible(true);
				} else {
					noticeDialog = new NoticeDialog();
					noticeDialog.setVisible(true);
				}
			} else if(action == "lecture") {
				if(lectureFrame != null) {
					lectureFrame.setVisible(true);
				} else {
					lectureFrame = new LectureFrame();
					lectureFrame.setVisible(true);
				}
			} else if(action == "student") {
				if(studentMgtFrame != null) {
					studentMgtFrame.setVisible(true);
					studentMgtFrame.initialize();
				} else {
					studentMgtFrame = new StudentMgtFrame();					
					studentMgtFrame.setVisible(true);
					studentMgtFrame.initialize();
				}
			} else if(action == "manager") {
				if(managerMgtFrame != null) {
					managerMgtFrame.setVisible(true);
				} else {
					managerMgtFrame = new ManagerMgtFrame();
					managerMgtFrame.setVisible(true);
				}
			} else if(action == "server") {
				if(serverMgtFrame != null) {
					serverMgtFrame.setVisible(true);
				} else {
					serverMgtFrame = new ServerMgtFrame();
					serverMgtFrame.setVisible(true);
				}
			} else if(action == "inquire") {
				if(inquireFrame != null) {
					inquireFrame.initialize();
					inquireFrame.setVisible(true);
				} else {
					inquireFrame = new InquireFrame();
					inquireFrame.initialize();
					inquireFrame.setVisible(true);
				}
			}
			
		}
		
	}

}
