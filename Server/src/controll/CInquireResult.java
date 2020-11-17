package controll;

import model.DataAccessObject;
import model.MInquireResult;

public class CInquireResult {

	public CInquireResult()	{
		
	}

	public MInquireResult getInquireResult(String inquireNo) {
		
		DataAccessObject dao = new DataAccessObject();
		return dao.getInquireResult(inquireNo);
		
	}

	
	
}
