package application;

import java.io.Serializable;

public class Candidate extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Job position;
	protected Band band;
	protected int exp;
	protected String cv;
	protected int heldInterviews;
	protected String educationLevel;
	protected String status;
	final int maxInterview = 3;

	public Candidate(String name, String gender, Long id, Job position, Band band, int exp, String educationLevel, String cv) {
		super(name, gender, id);
		this.exp = exp;
		this.cv = cv;
		this.position = position;
		this.band = band;
		this.heldInterviews = 0;
		this.educationLevel = educationLevel;
		this.status = "Active";
	}
	@Override
	public String toString() {
		return "Name: " + this.name + "\nID: " + this.id + "\nPosition: "+ this.position.name + " in "+ band.name + "\nEducation Level: " + this.educationLevel + "\nYears of Experience: "+ this.exp + "\nStatus: " + this.status;
	}
	
	public boolean equals(Candidate o) {
		return this.id.equals(o.id);
	}
}
