package application;

import java.io.Serializable;

public class Band implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected Units unit;
	Job[] jobs;
	protected int id;
	
	public Band(String name, Units unit) {
			this.name = name;
			this.unit = unit;	
			this.id = CommonClass.bandsNum;
		}
	
	public void assign(Job[] jobs) {
		this.jobs = jobs;
	}
	
	public String toString() {
		return this.name;
	}
	public boolean equals(Band o) {
		return this.name.equals(o.name) && this.unit.name.equals(o.unit.name);
	}
	

	}

