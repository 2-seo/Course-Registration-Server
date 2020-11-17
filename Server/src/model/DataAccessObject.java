package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;

public class DataAccessObject {
	
	private String url;
	private String sql;

	private Connection conn;
	private Statement st;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DataAccessObject() {
		
		url = "jdbc:postgresql://localhost:5432/ObjectFinalProject";
		String dbID = "postgres";
		String dbPW = "root";


		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, dbID, dbPW);
			
			st = conn.createStatement();
			
		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void closeDB() {
		try {
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public MAdminAccount getAdminAccount(String id, String pw) {
		
		sql = "SELECT * FROM admin WHERE id='" + id + "' AND pw='" + pw + "'";
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("login success");
				String adminID = rs.getString("id");
				String adminPW = rs.getString("pw");
				MAdminAccount mAdminAccount = new MAdminAccount(adminID, adminPW);
				
				rs.close();
				return mAdminAccount;
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}

	public Vector<MNotice> getNotice() {
		
		sql = "SELECT * FROM notice";
		Vector<MNotice> mNotices = null;
		
		try {
			rs = st.executeQuery(sql);
			mNotices = new Vector<>();
			
			while(rs.next()) {
				
				String noticeID = rs.getString("id");
				String subject = rs.getString("title");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				MNotice mAdminAccount = new MNotice(noticeID, subject, contents, writer);
				mNotices.add(mAdminAccount);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mNotices;
	}


	public void createNotice(String title, String contents, String writer) {
		sql = "INSERT INTO notice(title, contents, writer) VALUES (?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setString(3, writer);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void updateNotice(Integer id, String title, String contents) {
		sql = "UPDATE notice SET title = ?, contents = ? WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setInt(3, id);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deleteNotice(Integer id) {
		sql = "DELETE FROM notice WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Vector<MCampus> getCampus() {
		
		sql = "SELECT * FROM campus";
		Vector<MCampus> mCampuses = null;
		
		try {
			rs = st.executeQuery(sql);
			mCampuses = new Vector<>();
			
			while(rs.next()) {
				
				Integer campusID = rs.getInt("campusId");
				String campusName = rs.getString("name");

				MCampus mCampus = new MCampus(campusID, campusName);
				mCampuses.add(mCampus);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mCampuses;

	}

	public Vector<MCollege> getCollege(Integer campusId) {
		sql = "SELECT * FROM college WHERE \"campusId\" =" + campusId;
		
		Vector<MCollege> mColleges = null;
		
		try {
			rs = st.executeQuery(sql);
			mColleges = new Vector<>();
			
			while(rs.next()) {
				
				Integer collegeID = rs.getInt("collegeId");
				String campusName = rs.getString("name");
				Integer campusID = rs.getInt("campusId");

				MCollege mCollege = new MCollege(collegeID, campusName, campusID);
				mColleges.add(mCollege);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mColleges;
	}


	public Vector<MMajor> getMajor(Integer collegeId) {
		sql = "SELECT * FROM major WHERE \"collegeId\" =" + collegeId;
		Vector<MMajor> mMajors = null;
		
		try {
			rs = st.executeQuery(sql);
			mMajors = new Vector<>();
			
			while(rs.next()) {
				
				Integer majorID = rs.getInt("majorId");
				String campusName = rs.getString("name");
				Integer collegeID = rs.getInt("collegeId");

				MMajor mCollege = new MMajor(majorID, campusName, collegeID);
				mMajors.add(mCollege);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mMajors;
	}
	
	public Vector<MMajor> getAllMajor() {
		sql = "SELECT * FROM major WHERE \"collegeId\" != 1 AND \"collegeId\" != 7";
		Vector<MMajor> mMajors = null;
		
		try {
			rs = st.executeQuery(sql);
			mMajors = new Vector<>();
			
			while(rs.next()) {
				
				Integer majorID = rs.getInt("majorId");
				String campusName = rs.getString("name");
				Integer collegeID = rs.getInt("collegeId");

				MMajor mCollege = new MMajor(majorID, campusName, collegeID);
				mMajors.add(mCollege);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mMajors;
	}


	public Vector<MLecture> getLeture(Integer majorId) {
		sql = "SELECT * FROM lecture WHERE \"majorId\" =" + majorId;
		Vector<MLecture> mLectures = null;
		
		try {
			rs = st.executeQuery(sql);
			mLectures = new Vector<>();
			
			while(rs.next()) {
				
				Integer lectureId = rs.getInt("id");
				String name = rs.getString("name");
				String lecturer = rs.getString("lecturer");
				String credit = rs.getString("credit");
				String time = rs.getString("time");
				Integer majorID = rs.getInt("majorId");
				String data = lectureId + " " + name + " " + lecturer + " " + credit + " " + time + " " + majorID;
				Scanner scanner = new Scanner(data);

				MLecture mLecture = new MLecture(scanner);
				mLecture.read();
				mLectures.add(mLecture);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mLectures;
	}


	public void createLecture(String title, String lecturer, Integer credit, String time, Integer majorId) {
		sql = "INSERT INTO lecture(name, lecturer, credit, time, \"majorId\") VALUES (?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, lecturer);
			pstmt.setInt(3, credit);
			pstmt.setString(4, time);
			pstmt.setInt(5, majorId);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void upateLecture(Integer lectureId, String title, String lecturer, Integer credit, String time) {
		
		sql = "UPDATE lecture SET name = ?, lecturer = ?, credit = ?, time = ?  WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, lecturer);
			pstmt.setInt(3, credit);
			pstmt.setString(4, time);
			pstmt.setInt(5, lectureId);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void removeLecture(Integer lectureId) {
		
		sql = "DELETE FROM lecture WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lectureId);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Vector<MStudent> getAllStudent() {
		sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\", major.name AS major_name, address, student.hint\n"
				+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\";";
		Vector<MStudent> mStudnet = null;
		
		try {
			rs = st.executeQuery(sql);
			mStudnet = new Vector<>();
			
			while(rs.next()) {
				
				Integer stuNum = rs.getInt("stuNum");
				String name = rs.getString("name");
				String password = rs.getString("pw");
				Integer majorId = rs.getInt("majorId");
				String major = rs.getString("major_name");
				String address = rs.getString("address");
				String hint = rs.getString("hint");

				MStudent mStudent = new MStudent(stuNum, name, password, majorId, major, address, hint);
				mStudnet.add(mStudent);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mStudnet;
	}


	public Vector<MStudent> getStudnet(String sql) {
		
		Vector<MStudent> mStudnets = null;
		
		try {
			rs = st.executeQuery(sql);
			mStudnets = new Vector<>();
			
			while(rs.next()) {
				
				Integer stuNum = rs.getInt("stuNum");
				String name = rs.getString("name");
				String password = rs.getString("pw");
				Integer majorId = rs.getInt("majorId");
				String major = rs.getString("major_name");
				String address = rs.getString("address");
				String hint = rs.getString("hint");

				MStudent mStudent = new MStudent(stuNum, name, password, majorId, major, address, hint);
				mStudnets.add(mStudent);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return mStudnets;
		
	}
	
	public MStudent getStudnet(String id, String pw) {
		sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\",major.name AS major_name, address, hint\n"
				+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\"\n"
				+ "WHERE \"stuNum\" = " + id + " AND pw = '" + pw + "'";
		MStudent mStudent = null;
		
		try {
			rs = st.executeQuery(sql);

			while(rs.next()) {
				Integer stuNum = rs.getInt("stuNum");
				String name = rs.getString("name");
				String password = rs.getString("pw");
				Integer majorId = rs.getInt("majorId");
				String major = rs.getString("major_name");
				String address = rs.getString("address");
				String hint = rs.getString("hint");
	
				mStudent = new MStudent(stuNum, name, password, majorId, major, address, hint);
			}		
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return mStudent;
	}


	public void createStudnet(String studentName, int majorId, String address) {
		
		sql = "INSERT INTO student(name, pw, \"majorId\", address) values (?, ?, ?, ?);";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);
			pstmt.setString(2, "0000");
			pstmt.setInt(3, majorId);
			pstmt.setString(4, address);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void updateStudent(Integer stuNum, String studentName, String password, int majorId, String address) {
		sql = "UPDATE student SET name = ?, pw = ?, \"majorId\" = ?, address = ?  WHERE \"stuNum\" = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);
			pstmt.setString(2, password);
			pstmt.setInt(3, majorId);
			pstmt.setString(4, address);
			pstmt.setInt(5, stuNum);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void removeStudent(Integer stuNum) {
		sql = "DELETE FROM student WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuNum);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Vector<MManager> getAllManager() {
		sql = "SELECT * FROM admin";
		Vector<MManager> mManagers = null;
		
		try {
			rs = st.executeQuery(sql);
			mManagers = new Vector<>();
			
			while(rs.next()) {
				
				String no = rs.getString("no");
				String name = rs.getString("name");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String address = rs.getString("address");
				String email = rs.getString("email");

				MManager mManager = new MManager(no, name, id, pw, address, email);
				mManagers.add(mManager);
				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mManagers;
	}


	public void createManager(String name, String id, String pw, String address, String email) {
		sql = "INSERT INTO admin(name, id, pw, address, email) values (?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, address);
			pstmt.setString(5, email);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void updateManager(int no, String name, String id, String pw, String address, String email) {
		sql = "UPDATE admin SET name = ?, id = ?, pw = ?, address = ?, email = ? WHERE no = ?";		

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, address);
			pstmt.setString(5, email);
			pstmt.setInt(6, no);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void deleteManager(MManager selectedManager) {
		sql = "DELETE FROM admin WHERE no = ?";
		int no = Integer.parseInt(selectedManager.getNo());
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Vector<MDirectory> getDirectories(String fileName, String tableName) {

		if(tableName.equals("campus")) {
			sql = "SELECT * FROM " + tableName;
			
		} else if(tableName.equals("college")) {
			sql = "SELECT * FROM " + tableName + " WHERE \"campusId\" = " + fileName;
			
		} else if(tableName.equals("major")) {
			sql = "SELECT * FROM " + tableName + " WHERE \"collegeId\" = " + fileName;
			
		}
		
		Vector<MDirectory> mDirectorys = null;
		
		try {
			rs = st.executeQuery(sql);
			mDirectorys = new Vector<>();
			
			if(tableName.equals("campus")) {
				
				while(rs.next()) {
					
					String id = rs.getString("campusId");
					String name = rs.getString("name");
					String fileName2 = rs.getString("campusId");
					Scanner scanner = new Scanner(id + " " + name + " " + fileName2);
					MDirectory mDirectory = new MDirectory(scanner);
					mDirectory.read();
					mDirectorys.add(mDirectory);
					
				}
				
			} else if(tableName.equals("college")) {
				
				while(rs.next()) {
					
					String id = rs.getString("collegeId");
					String name = rs.getString("name");
					String fileName2 = rs.getString("collegeId");
					Scanner scanner = new Scanner(id + " " + name + " " + fileName2);
					MDirectory mDirectory = new MDirectory(scanner);
					mDirectory.read();
					mDirectorys.add(mDirectory);
					
				}
				
			} else if(tableName.equals("major")) {
				
				while(rs.next()) {
					
					String id = rs.getString("majorId");
					String name = rs.getString("name");
					String fileName2 = rs.getString("majorId");
					Scanner scanner = new Scanner(id + " " + name + " " + fileName2);
					MDirectory mDirectory = new MDirectory(scanner);
					mDirectory.read();
					mDirectorys.add(mDirectory);
					
				}
				
			}			
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mDirectorys;
		
	}


	public Vector<MLecture> getResult(String stuNum, String tableName) {
		sql = "SELECT * FROM " + tableName + " INNER JOIN lecture l on l.id = " + tableName + ".lectureid WHERE stunum = " + stuNum;
		Vector<MLecture> mLectures = null;
		
		try {
			rs = st.executeQuery(sql);
			mLectures = new Vector<>();
			
			while(rs.next()) {
				
				Integer lectureId = rs.getInt("id");
				String name = rs.getString("name");
				String lecturer = rs.getString("lecturer");
				String credit = rs.getString("credit");
				String time = rs.getString("time");
				Integer majorID = rs.getInt("majorId");
				String data = lectureId + " " + name + " " + lecturer + " " + credit + " " + time + " " + majorID;
				Scanner scanner = new Scanner(data);

				MLecture mLecture = new MLecture(scanner);
				mLecture.read();
				mLectures.add(mLecture);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mLectures;
	}


	public void saveResult(String orginData) {
		
		System.out.println(orginData);
		String[] dataArr = orginData.split(" ");
		
		Integer stunum = Integer.parseInt(dataArr[1]); 

		try {
			
			if(dataArr[2].equals("basket")) {								
				sql = "INSERT INTO basket VALUES (?, ?) ON CONFLICT (lectureid) DO NOTHING";
				
			} else if(dataArr[2].equals("sincheong")) {				
				sql = "INSERT INTO sincheong VALUES (?, ?) ON CONFLICT (lectureid) DO NOTHING";
				
			}
		
		
			for(int i=3; i < dataArr.length; i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, stunum);
				pstmt.setInt(2, Integer.parseInt(dataArr[i]));			
				
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void deleteResult(String orginData) {
		
		System.out.println(orginData);
		String[] dataArr = orginData.split(" ");
		
		Integer stunum = Integer.parseInt(dataArr[1]); 

		try {
			
			if(dataArr[2].equals("basket")) {								
				sql = "DELETE FROM basket WHERE stunum = ? AND lectureid = ?;";
				
			} else if(dataArr[2].equals("sincheong")) {				
				sql = "DELETE FROM sincheong WHERE stunum = ? AND lectureid = ?;";
				
			}
		
		
			for(int i=3; i < dataArr.length; i++) {
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, stunum);
				pstmt.setInt(2, Integer.parseInt(dataArr[i]));			
				
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Vector<MInquire> getInquire() {
		
		sql = "SELECT * FROM inquire";
		Vector<MInquire> mInquires = null;
		
		try {
			rs = st.executeQuery(sql);
			mInquires = new Vector<>();
			
			while(rs.next()) {
				
				String no = rs.getString("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String stunum = rs.getString("stunum");
				String result = rs.getString("result");
				MInquire mAdminAccount = new MInquire(no, title, contents, stunum, result);
				mInquires.add(mAdminAccount);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mInquires;
		
	}
	
	public Vector<MInquire> getMyInquire(String orginData) {
		String[] dataArr = orginData.split(" ");
		sql = "SELECT * FROM inquire WHERE stunum = " + dataArr[1];
		Vector<MInquire> mInquires = null;
		
		try {
			rs = st.executeQuery(sql);
			mInquires = new Vector<>();
			
			while(rs.next()) {
				
				String no = rs.getString("no");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String stunum = rs.getString("stunum");
				String result = rs.getString("result");
				MInquire mAdminAccount = new MInquire(no, title, contents, stunum, result);
				mInquires.add(mAdminAccount);
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mInquires;
	}


	public void updqteUser(String data) {
		String[] dataArr = data.split(" ");
		System.out.println(dataArr);
		System.out.println("===================================");
		for(String data2 : dataArr) {
			System.out.println(data2);
		}
		System.out.println("===================================");
		sql = "UPDATE student SET name = ?, pw = ?, address = ?, hint = ? WHERE \"stuNum\" = ?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataArr[2]);
			pstmt.setString(2, dataArr[3]);
			pstmt.setString(3, dataArr[4]);
			pstmt.setString(4, dataArr[5]);
			pstmt.setInt(5, Integer.parseInt(dataArr[1]));

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
 
			e.printStackTrace();
		}
		
	}
	
	public MStudent findUser(String data) {
	
		String[] dataArr = data.split(" ");
		sql = "SELECT \"stuNum\", student.name, student.pw, student.\"majorId\",major.name AS major_name, address, hint\n"
				+ "FROM student INNER JOIN major ON student.\"majorId\" = major.\"majorId\" "
				+ "WHERE \"stuNum\" = " + dataArr[1] + " AND hint = '"+ dataArr[2] +"'";
		MStudent mStudent = null;
		
		try {
			rs = st.executeQuery(sql);			
			
			while(rs.next()) {
				
				Integer stuNum = rs.getInt("stuNum");
				String name = rs.getString("name");
				String password = rs.getString("pw");
				Integer majorId = rs.getInt("majorId");
				String major = rs.getString("major_name");
				String address = rs.getString("address");
				String hint = rs.getString("hint");

				mStudent = new MStudent(stuNum, name, password, majorId, major, address, hint);
				
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return mStudent;
	
	}


	public void createAnswer(String inquireNo, String answerContents) {
		
		sql = "INSERT INTO inquire_result VALUES (?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inquireNo));
			pstmt.setString(2, answerContents);			

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "UPDATE inquire SET result = '답변완료' WHERE no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inquireNo));				

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public MInquireResult getInquireResult(String inquireNo) {
		
		sql = "SELECT * FROM inquire_result WHERE no = " + inquireNo;		
		MInquireResult mInquireResult = null;
		
		try {
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String no = rs.getString("no");				
				String contents = rs.getString("contents");
				mInquireResult = new MInquireResult(no, contents);						
			}
			
			rs.close();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mInquireResult;
		
	}


	public void updateAnswer(String inquireNo, String answerContents) {
		
		sql = "UPDATE inquire_result SET contents = '" + answerContents + "' WHERE no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(inquireNo));				

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void createInquire(String originData) {
		
		sql = "INSERT INTO inquire(title, contents, stunum, result) VALUES (?, ?, ?, '답변대기')";
		String[] dataArr = originData.split(" ");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataArr[1]);			
			pstmt.setString(2, dataArr[2]);			
			pstmt.setInt(3, Integer.parseInt(dataArr[3]));

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void updqteInquire(String data) {
	
		String[] dataArr = data.split(" ");
		sql = "UPDATE inquire SET title = ?, contents = ? WHERE no = ?";				

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dataArr[1]);
			pstmt.setString(2, dataArr[2]);
			pstmt.setInt(3, Integer.parseInt(dataArr[3]));			

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
