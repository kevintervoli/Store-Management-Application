package User_Profiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Products implements Serializable{
	protected String name;
	protected long quantity;
	public Products(){
		this.name="";
		this.quantity=0;
	}
	public Products( String name, long quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
	
}
