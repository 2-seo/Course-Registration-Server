package model;

public class MManager {

	private String no;
	private String name;
	private String id;
	private String pw;
	private String address;
	private String email;
	
	public MManager(String no, String name, String id, String pw, String address, String email) {
		this.no = no;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.address = address;
		this.email = email;
	}
	
	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
		
	
}
