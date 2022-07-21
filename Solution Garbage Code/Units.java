package application;

import java.io.Serializable;

public class Units implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name, level;
	
	public Units(String name, String level){
			this.name = name;
			this.level = level;
	}
	
	public boolean exists(Units unit) {
		if(unit == null)
			return false;
		return this.name.equals(unit.name);
	}
	@Override
	public String toString() {
		return this.name + " (" + this.level +")";
	}
	public boolean equals(Units o) {
		return this.name.equals(o.name) && this.level.equals(o.level);
	}
}
