package model;

import java.util.Scanner;

public class MLecture {

	private Integer id;
	private String name;
	private String lecturer;
	private String credit;
	private String time;
	private Integer majorId;
	
	private Scanner scanner;
	
	public MLecture(Scanner scanner) {
		this.scanner = scanner;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLecturer() {
		return lecturer;
	}

	public String getCredit() {
		return credit;
	}

	public String getTime() {
		return time;
	}

	public Integer getMajorId() {
		return majorId;
	}
	
	public void read() {
		this.id = scanner.nextInt();
		this.name = scanner.next();
		this.lecturer = scanner.next();
		this.credit = scanner.next();
		this.time = scanner.next();
		this.majorId = scanner.nextInt();
	}

	@Override
	public String toString() {
		return id + " " + name + " " + lecturer + " " + credit + " "
				+ time + " " + majorId;
	}
	
	
	
}
