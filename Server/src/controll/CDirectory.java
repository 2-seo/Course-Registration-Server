package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MDirectory;

public class CDirectory {
	
	public CDirectory() {
		
	}
	
	public Vector<MDirectory> getDirectories(String fileName, String tableName){
		DataAccessObject dataAccessObject = new DataAccessObject();
		Vector<MDirectory> mDirectories = dataAccessObject.getDirectories(fileName, tableName);

		return mDirectories;
	}

}
