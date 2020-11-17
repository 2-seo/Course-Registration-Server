package model;

public class MNotice {
	
	private String id;
	private String title;
	private String contents;
	private String writer;
	
	public MNotice(String id, String subject, String contents, String writer) {
		
		this.id = id;
		this.title = subject;
		this.contents = contents;
		this.writer = writer;
						
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getWriter() {
		return writer;
	}

	@Override
	public String toString() {
		return "***forsplitdata***" + id + "***forsplitdata***" + title + "***forsplitdata***" + contents + "***forsplitdata***" + writer;
	}

	
	
}
