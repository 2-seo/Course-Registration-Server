package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MLecture;

public class CResult {

	public CResult() {
		
	}
	
	public Vector<MLecture> getResult(String stuNum, String tableName) {
		
		DataAccessObject dao = new DataAccessObject();
		Vector<MLecture> mLectures = dao.getResult(stuNum, tableName);
		
		return mLectures;
	}


	public void save(String orginData) {
		
		DataAccessObject dao = new DataAccessObject();
		dao.saveResult(orginData);
		
	}

	public void deleteResult(String orginData) {

		DataAccessObject dao = new DataAccessObject();
		dao.deleteResult(orginData);		
		
	}
	
}
