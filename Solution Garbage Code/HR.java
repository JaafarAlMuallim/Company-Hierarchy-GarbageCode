package application;

import java.io.Serializable;

public class HR extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String password;

	public HR(String name, String gender, Long id, String password) {
		super(name, gender, id);
		this.password = password;
	}

}
