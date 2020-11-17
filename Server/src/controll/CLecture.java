package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MCampus;
import model.MCollege;
import model.MLecture;
import model.MMajor;

public class CLecture {

	public CLecture() {
		
	}
	
	public Vector<MCampus> getCampus() {
		
		DataAccessObject dao = new DataAccessObject();
		Vector<MCampus> mCampuses = dao.getCampus();
		
		return mCampuses;
	}

	public Vector<MCollege> getCollege(Integer campusId) {
		DataAccessObject dao = new DataAccessObject();
		Vector<MCollege> mColleges = dao.getCollege(campusId);
		
		return mColleges;
	}

	public Vector<MMajor> getMajor(Integer collegeId) {
		DataAccessObject dao = new DataAccessObject();
		Vector<MMajor> mMajors = dao.getMajor(collegeId);
		
		return mMajors;
	}
	
	public Vector<MMajor> getAllMajor() {
		DataAccessObject dao = new DataAccessObject();
		Vector<MMajor> mMajors = dao.getAllMajor();
		
		return mMajors;
	}

	public Vector<MLecture> getLecture(Integer majorId) {
		DataAccessObject dao = new DataAccessObject();
		Vector<MLecture> mLectures = dao.getLeture(majorId);
		
		return mLectures;
	}
	
	public void createLecture(String title, String lecturer, Integer credit, String time, Integer majorId) {
		DataAccessObject dao = new DataAccessObject();
		dao.createLecture(title, lecturer, credit, time, majorId);
	}

	public void updateLecture(Integer lectureId, String title, String lecturer, Integer credit, String time) {
		DataAccessObject dao = new DataAccessObject();
		dao.upateLecture(lectureId, title, lecturer, credit, time);
	}
	
	public void remvoeLecture(Integer lectureId) {
		DataAccessObject dao = new DataAccessObject();
		dao.removeLecture(lectureId);
	}
}
