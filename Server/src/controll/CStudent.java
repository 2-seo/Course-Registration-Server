package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MManager;
import model.MStudent;

public class CStudent {
	
	public CStudent() {	
		
	}
	
	public void createStudent(String studentName, int majorId, String Address) {
		DataAccessObject dao = new DataAccessObject();
		dao.createStudnet(studentName, majorId, Address);
	}
	
	public Vector<MStudent> getStudnets(String mode, String data) {

		DataAccessObject dao = new DataAccessObject();
		String sql = "";

		if(mode.equals("stuNum")) {
			sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\",major.name AS major_name, address, hint\n"
					+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\"\n"
					+ "WHERE \"stuNum\" = " + data;
			
		} else if(mode.equals("name")) {
			sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\", major.name AS major_name, address, hint\n"
					+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\"\n"
					+ "WHERE student.name LIKE '%" + data + "%'";
			
		} else if(mode.equals("major")) {
			sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\", major.name AS major_name, address, hint\n"
					+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\"\n"
					+ "WHERE major.name LIKE '%" + data + "%'";
			
		} else if(mode.equals("address")) {
			sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\", major.name AS major_name, address, hint\n"
					+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\"\n"
					+ "WHERE student.address LIKE '%" + data + "%'";

		}
		
		Vector<MStudent> mStudents = dao.getStudnet(sql);
		
		return mStudents;
		
	}
	
	public MStudent getStudent(String id, String pw) {
		DataAccessObject dao = new DataAccessObject();
		MStudent mStudnet = dao.getStudnet(id, pw);
		return mStudnet;
	}
	
	public Vector<MStudent> getAllStudent() {
		
		DataAccessObject dao = new DataAccessObject();;
		Vector<MStudent> mStudents = dao.getAllStudent();
		return mStudents;
		
	}

	public void updateStudent(int stuNum, String studentName, String password, int majorId, String address) {
		
		DataAccessObject dao = new DataAccessObject();
		dao.updateStudent(stuNum, studentName, password, majorId, address);
		
	}

	public void removeStudnet(Integer stuNum) {
		DataAccessObject dao = new DataAccessObject();
		dao.removeStudent(stuNum);
		
	}
	
	public void updateUser(String data) {
		DataAccessObject dao = new DataAccessObject();
		dao.updqteUser(data);
	}

	public MStudent findUser(String orginData) {
		DataAccessObject dao = new DataAccessObject();
		return dao.findUser(orginData);
	}

}
