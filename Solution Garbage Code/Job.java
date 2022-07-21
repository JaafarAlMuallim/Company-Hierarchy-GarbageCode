package application;

import java.io.Serializable;

public class Job implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected int basicSalary;
	protected int employeeNum = 0;

	public Job(String name, int basicSalary) {
		this.name = name;
		this.basicSalary = basicSalary;
	}
	

	
	public boolean equals(Job job) {
		if(job == null)
			return false;
		return this.name.equals(job.name);
	}
	@Override
	public String toString() {
		return this.name; 
	}
}
