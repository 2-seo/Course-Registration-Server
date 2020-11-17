package model;

public class MInquireResult {

	private String no;
	private String contents;
	
	MInquireResult(String no, String contents) {
		this.no = no;
		this.contents = contents;		
	}

	public String getNo() {
		return no;
	}

	public String getContents() {
		return contents;
	}

	@Override
	public String toString() {
		return "***forsplitdata***" + no + "***forsplitdata***" + contents;
	}
	
		
	
}
