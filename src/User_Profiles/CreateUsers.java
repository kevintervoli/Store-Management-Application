package User_Profiles;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateUsers {
	public static ArrayList<User> a = new ArrayList<>();
	public static ArrayList<Products> b = new ArrayList<>();
	public static ArrayList<Employe> c = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		String productsFile = "src/Database/products.dat";
		String employeName = "src/Database/employe.dat";
		
		User us = new User( "Cashier", "cashier", "cashier", "epoka123", 0,450);
		User us2 = new User ("Manager", "manager", "manager", "epoka123", 1,550);
		User us3 = new User( "Admin", "admin", "admin", "epoka123", 2,750);
		a.add(us);
		a.add(us2);
		a.add(us3);
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(fileName, true));
		ostream.writeObject(a);
		ostream.close();
		
		
		Employe e1 = new Employe("Cashier","Cashier","cashier",450,0);
		Employe e2 = new Employe("Manager","Manager","manager",550,0);
		Employe e3 = new Employe("Admin","Admin","admin",750,0);
		c.add(e1);
		c.add(e2);
		c.add(e3);
		
		
		ObjectOutputStream ostream3 = new ObjectOutputStream(new FileOutputStream(employeName, true));
		ostream3.writeObject(c);
		ostream3.close();
		Products pr1 = new Products("Music to be murdered by","Eminem","HIP-HOP",25,250);
		Products pr2 = new Products("College Dropout","Kanye West","RAP",25,450);
		Products pr3 = new Products("Damn","Kendrick Lamar","HIP-HOP",25,720);
		Products pr4 = new Products("The off-season","J Cole","HIP-HOP",25,350);
		Products pr5 = new Products("USA","MC Kresha","HIP-HOP",25,450);
		b.add(pr1);
		b.add(pr2);
		b.add(pr3);
		b.add(pr4);
		b.add(pr5);
		ObjectOutputStream ostream2 = new ObjectOutputStream(new FileOutputStream(productsFile, true));
		ostream2.writeObject(b);
		ostream2.close();
		System.out.println("Users generated");
		System.out.println("Products generated");

		
	}
}