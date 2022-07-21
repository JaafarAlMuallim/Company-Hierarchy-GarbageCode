package application;

import java.io.Serializable;
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name, gender;

	 protected Long id;
	
	public Person(String name, String gender, Long id) {
		this.name = name;
		this.gender = gender;
		this.id = id;
	}
	public Person(String name, Long id) {
		this.name = name;
		this.id = id;
	}
	
	public boolean equals(Person o) {
			return o.id == this.id;
		}
}
