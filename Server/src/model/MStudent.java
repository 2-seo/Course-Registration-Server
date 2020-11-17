package model;

public class MStudent {

	private Integer stuNum;
	private String name;
	private String password;
	private Integer majorId;
	private String major;
	private String address;
	private String hint;
	
	public MStudent(Integer stuNum, String name, String password, Integer majorId, String major, String address, String hint) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.password = password;
		this.majorId = majorId;
		this.major = major;
		this.address = address;
		this.hint = hint;
	}
	
	public Integer getStuNum() {
		return stuNum;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public String getMajor() {
		return major;
	}
	public String getAddress() {
		return address;
	}
	public String getHint() {
		return hint;
	}

	@Override
	public String toString() {
		String data = stuNum + " " + name + " " + password + " " + majorId + " " + major + " " + address + " " + hint;		
		return data;
	}
	
}
