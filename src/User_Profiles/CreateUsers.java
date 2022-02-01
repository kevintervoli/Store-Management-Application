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
		
		User us = new User( "Cashier", "cashier", "cashier", "kevin123", 0);
		User us2 = new User ("Manager", "manager", "manager", "kevin123", 1);
		User us3 = new User( "Admin", "admin", "admin", "kevin123", 2);
		a.add(us);
		a.add(us2);
		a.add(us3);
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(fileName, true));
		ostream.writeObject(a);
		ostream.close();
		
		
		Employe e1 = new Employe("C1","C1S","cashier1");
		Employe e2 = new Employe("C2","C2S","cashier2");
		Employe e3 = new Employe("C2","C2S","cashier3");
		c.add(e1);
		c.add(e2);
		c.add(e3);
		
		
		ObjectOutputStream ostream3 = new ObjectOutputStream(new FileOutputStream(employeName, true));
		ostream3.writeObject(c);
		ostream3.close();
		Products pr1 = new Products("Muzike e alltise",5);
		Products pr2 = new Products("College Dropout",2);
		Products pr3 = new Products("Damn",7);
		b.add(pr1);
		b.add(pr2);
		b.add(pr3);
		ObjectOutputStream ostream2 = new ObjectOutputStream(new FileOutputStream(productsFile, true));
		ostream2.writeObject(b);
		ostream2.close();
		System.out.println("Users generated");
		System.out.println("Products generated");

		
	}
}