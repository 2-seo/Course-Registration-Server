package model;

public class MCollege {

	private Integer id;
	private String name;
	private Integer campusId;
	
	
	public MCollege(Integer id, String name, Integer campusId) {
		
		this.id = id;
		this.name = name;
		this.campusId = campusId;
	
	}


	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public Integer getCampusId() {
		return campusId;
	}
	
	
	
}
