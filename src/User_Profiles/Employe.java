package User_Profiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employe implements Serializable {
	protected String name;
	protected String surname;
	protected String username;
	protected double salary;
	public Employe() {
		this.name="";
		this.surname="";
		this.username="";
		this.salary=0;
	}
	public Employe(String name, String surname,String username,double salary) {
		this.name=name;
		this.surname=surname;
		this.username=username;
		this.salary=salary;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
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