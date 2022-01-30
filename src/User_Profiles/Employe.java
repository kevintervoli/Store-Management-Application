package User_Profiles;

import java.io.Serializable;

public class Employe implements Serializable {
	protected String name;
	protected String surname;
	protected String username;
	public Employe() {
		this.name="";
		this.surname="";
		this.username="";
	}
	public Employe(String name, String surname,String username) {
		this.name=name;
		this.surname=surname;
		this.username=username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
