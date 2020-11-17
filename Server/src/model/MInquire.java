package model;

public class MInquire {

	private String no;
	private String title;
	private String content;
	private String stuNum;
	private String result;
	
	public MInquire(String no, String title, String content, String stuNum, String result) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.stuNum = stuNum;
		this.result = result;
	}

	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getStuNum() {
		return stuNum;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "***forsplitdata***" + no + "***forsplitdata***" + title + "***forsplitdata***" + content + "***forsplitdata***" + stuNum + "***forsplitdata***" + result;
	}
	
	
	
}
