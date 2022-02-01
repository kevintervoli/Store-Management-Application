package User_Profiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	protected String name;
	protected String surname;
	protected String username;
	protected String password;
	protected int userStatus; //0-cashier  1-manager  2-admin
	public User() {
		this.name="";
		this.surname="";
		this.username="";
		this.password="";
		this.userStatus=0;
	}
	public User(String name,String surname,String username,String password,int userStatus) {
		this.name=name;
		this.surname=surname;
		this.username=username;
		this.password=password;
		this.userStatus=userStatus;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	@Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}