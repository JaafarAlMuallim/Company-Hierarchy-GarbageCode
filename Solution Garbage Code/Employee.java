package application;


public class Employee extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double salary;

	public Employee(String name, String gender, Long id, double salary) {
		super(name, gender, id);
		this.salary = salary;
	}

}
