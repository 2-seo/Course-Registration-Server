package controll;

import java.util.Vector;

import model.DataAccessObject;
import model.MAdminAccount;
import model.MManager;
import model.MStudent;

public class CManager {
	
	public CManager() {
		
	}
	
	public MAdminAccount checkID(String id, String pw) {
		DataAccessObject dao = new DataAccessObject();
		return dao.getAdminAccount(id, pw);
		
	}

	public Vector<MManager> getAllManager() {
		DataAccessObject dao = new DataAccessObject();
		Vector<MManager> mManagers = dao.getAllManager();
		return mManagers;
	}

	public void createManager(String name, String id, String pw, String address, String email) {
		DataAccessObject dao = new DataAccessObject();
		dao.createManager(name, id, pw, address, email);
		
	}

	public void updqteManager(int no, String name, String id, String pw, String address, String email) {
		DataAccessObject dao = new DataAccessObject();
		dao.updateManager(no, name, id, pw, address, email);
		
	}

	public void deleteManager(MManager selectedManager) {
		DataAccessObject dao = new DataAccessObject();
		dao.deleteManager(selectedManager);
		
	}
	
}
