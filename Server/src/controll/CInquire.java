package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MInquire;
import model.MNotice;

public class CInquire {

	
	public CInquire() {

	}
	
	public Vector<MInquire> getInquire() {
		DataAccessObject dao = new DataAccessObject();
		Vector<MInquire> mInquires = dao.getInquire();
		
		return mInquires;
	}

	public Vector<MInquire> getMyInquire(String orginData) {
		DataAccessObject dao = new DataAccessObject();
		Vector<MInquire> mInquires = dao.getMyInquire(orginData);
		
		return mInquires;
	}

	public void createAnswer(String inquireNo, String answerContents) {
		DataAccessObject dao = new DataAccessObject();
		dao.createAnswer(inquireNo, answerContents);
		
	}

	public void updateAnswer(String inquireNo, String answerContents) {
		DataAccessObject dao = new DataAccessObject();
		dao.updateAnswer(inquireNo, answerContents);
		
	}

	public void createInquire(String originData) {
		DataAccessObject dao = new DataAccessObject();
		dao.createInquire(originData);
		
	}

	public void updateInquire(String originData) {
		DataAccessObject dao = new DataAccessObject();
		dao.updqteInquire(originData);
	}


}
