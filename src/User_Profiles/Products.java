package User_Profiles;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Products implements Serializable {
	protected String name;
	protected String singer;
	protected String genre;
	protected long quantity;
	protected double price;

	public Products(String name, String singer, String genre, long quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.singer = singer;
		this.genre = genre;
		this.price = price;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		return "User{" + ", name='" + name + '\'' + ", quantity='" + quantity + '\'' + '}';
	}

}