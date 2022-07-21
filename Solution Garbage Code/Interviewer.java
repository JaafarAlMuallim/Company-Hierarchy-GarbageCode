package application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Interviewer extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String password;
	protected Map<String, Boolean> time = new HashMap<String, Boolean>();
	protected int heldInterviewes = 0;
	
	public Interviewer(String name, String gender, Long id, String password, boolean time) {
		super(name, gender, id);
		this.password = password;
		this.time.put("8:00", true);  this.time.put("8:30", true);
		this.time.put("9:00", true);  this.time.put("9:30", true);
		this.time.put("10:00", true); this.time.put("10:30", true);	
		this.time.put("11:00", true); this.time.put("11:30", true);	
		this.time.put("13:00", true); this.time.put("13:30", true);
		
	}
	
	public String toString() {
		return "Name: " + this.name + "\nID: " + this.id;
	}
}
