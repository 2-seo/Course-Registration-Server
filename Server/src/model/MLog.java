package model;

public class MLog {

	private String ip;
	private String log;
	private String time;
	private String connectionSize;
	
	public MLog(String ip, String log, String time, String connectionSize) {		
		this.ip = ip;
		this.log = log;
		this.time = time;
		this.connectionSize = connectionSize;
	}

	public String getIp() {
		return ip;
	}

	public String getLog() {
		return log;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getConnectionSize() {
		return connectionSize;
	}	
	
}
