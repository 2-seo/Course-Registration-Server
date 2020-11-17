package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MNotice;

public class CNotice {

	
	public CNotice() {

	}
	
	public Vector<MNotice> getNotice() {
		DataAccessObject dao = new DataAccessObject();
		Vector<MNotice> mNotices = dao.getNotice();
		
		return mNotices;
	}
	
	public void createNotice(String title, String contents, String writer) {
		DataAccessObject dao = new DataAccessObject();
		dao.createNotice(title, contents, writer);
	}
	
	public void updateNotice(Integer id, String title, String contents) {
		DataAccessObject dao = new DataAccessObject();
		dao.updateNotice(id, title, contents);
	}
	
	public void deleteNotice(Integer id) {
		DataAccessObject dao = new DataAccessObject();
		dao.deleteNotice(id);
	}
	
}
