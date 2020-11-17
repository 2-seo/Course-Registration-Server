package model;

public class MMajor {

	private Integer id;
	private String name;
	private Integer collegeId;
	
	
	public MMajor(Integer id, String name, Integer collegeId) {
		
		this.id = id;
		this.name = name;
		this.collegeId = collegeId;
	
	}


	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public Integer getCollegeId() {
		return collegeId;
	}
	
	
	
}
