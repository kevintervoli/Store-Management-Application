package Scenes;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import ButtonControllers.Login_Controller;
import User_Profiles.Employe;
import User_Profiles.Products;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Create_Bill{
	private static  Date billDate;
	protected static Label albumName;
	protected static Label quantity;
	protected static Label shipping;
	protected static Label contactPerson;
	protected static Label phoneNum;
	protected static Label emailPerson;
	protected static Label msg;	
	protected static Color msgColor;
	
	protected static TextField nam;
	protected static TextField quan;
	protected static TextField shippingfield;
	protected static TextField contact;
	protected static TextField phone;
	protected static TextField email;
	protected static TextField payment;
	protected static Button createButton,cancel;
	static int billNum=0;
	static String billN="0";
	public Create_Bill() {
		billDate= new Date();
	}
	
	
	public static BorderPane billWindow() {
		BorderPane pane = new BorderPane();
		Color fontColor= Color.web("#b2c2cf"); 
		Color backGround = Color.web("#FFFFFF");
		Color head = Color.web("#b2c2cf");
		Color color = Color.web("#E2E5ED");
		Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		Text header = new Text("Welcome to the create bill window !");
		header.setFont(Font.font("OCR A Extended",25));
		header.setFill(head);
		
		
		HBox headB = new HBox(header);
		pane.getStylesheets().add("css/style.css");
		headB.setAlignment(Pos.CENTER);
		headB.setSpacing(40);
		headB.setBackground(bckgStyle);
		
		albumName = new Label("Album Name :");
		quantity = new Label("Quantity : ");
		shipping = new Label("Shipping adress : ");
		contactPerson = new Label("Contact : ");
		phoneNum = new Label("Phone : ");
		emailPerson = new Label("Email : ");
		
		albumName.setFont(Font.font("OCR A Extended",30));
		quantity.setFont(Font.font("OCR A Extended",30));
		shipping.setFont(Font.font("OCR A Extended",30));
		contactPerson.setFont(Font.font("OCR A Extended",30));
		phoneNum.setFont(Font.font("OCR A Extended",30));
		emailPerson.setFont(Font.font("OCR A Extended",30));
		
		
		VBox first = new VBox();
		first.getChildren().addAll(contactPerson,phoneNum,emailPerson,albumName,quantity,shipping);
		first.setSpacing(25);
		first.setAlignment(Pos.CENTER_LEFT);
		nam = new TextField();
		nam.setPromptText("Enter album name ");
		nam.setBorder(textFBorder);
		nam.setPrefSize(300, 30);
		nam.setBackground(bckgStyle);
		
		quan= new TextField();
		quan.setPromptText("Enter quantity ");
		quan.setBorder(textFBorder);
		quan.setPrefSize(300, 30);
		quan.setBackground(bckgStyle);
		
		shippingfield = new TextField();
		shippingfield.setPromptText("Enter shipping adress ");
		shippingfield.setBorder(textFBorder);
		shippingfield.setPrefSize(300, 30);
		shippingfield.setBackground(bckgStyle);
		
		contact = new TextField();
		contact.setPromptText("Enter name and surname : ");
		contact.setBorder(textFBorder);
		contact.setPrefSize(300, 30);
		contact.setBackground(bckgStyle);
		
		phone = new TextField();
		phone.setPromptText("Enter phone number : ");
		phone.setBorder(textFBorder);
		phone.setPrefSize(300, 30);
		phone.setBackground(bckgStyle);
		
		email = new TextField();
		email.setPromptText("Enter email : ");
		email.setBorder(textFBorder);
		email.setPrefSize(300, 30);
		email.setBackground(bckgStyle);
		TextField payment = new TextField();
		
		VBox second = new VBox();
		second.getChildren().addAll(contact,phone,email,nam,quan,shippingfield);
		second.setAlignment(Pos.CENTER);
		second.setSpacing(25);
		
		HBox fields = new HBox();
		fields.getChildren().addAll(first,second);
		createButton = new Button("Create Bill");
		createButton.setId("logB2");
		cancel = new Button("Cancel");
		cancel.setId("logB2");
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(25);
		createButton.setOnAction(e->{

			try {
				if(contact.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || nam.getText().isEmpty() ||quan.getText().isEmpty() || shippingfield.getText().isEmpty()) {
					
					addBill( Login_Controller.currentUser,contact.getText(),phone.getText(),email.getText(),nam.getText(),quan.getText(),shippingfield.getText(),getPayment(nam.getText(),Integer.parseInt(quan.getText())));
					billNum++;
					contact.clear();
					phone.clear();
					email.clear();
					quan.clear();
					nam.clear();
					shippingfield.clear();
				}
				else {
					if(phone.getText().matches("[0-9]{9}") || phone.getText().matches("\\+{1}[0-9]{12}")) {
						if(validation(nam.getText(),Integer.parseInt(quan.getText()))) {
							addBill( Login_Controller.currentUser,contact.getText(),phone.getText(),email.getText(),nam.getText(),quan.getText(),shippingfield.getText(),getPayment(nam.getText(),Integer.parseInt(quan.getText())));
							billNum++;
							contact.clear();
							phone.clear();
							email.clear();
							quan.clear();
							nam.clear();
							shippingfield.clear();
						}
						else {
							Alert fail= new Alert(AlertType.WARNING);
					        fail.setHeaderText("FAIL");
					        fail.setContentText("Not enough stock for that supply !");
					        fail.showAndWait();
						}
				      }else {
					Alert fail= new Alert(AlertType.WARNING);
			        fail.setHeaderText("FAIL");
			        fail.setContentText("Incorrect phone number type !");
			        fail.showAndWait();
				      }
				}
			
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		HBox buton = new HBox();
		buton.getChildren().addAll(createButton,cancel);
		buton.setAlignment(Pos.CENTER);
		buton.setSpacing(15);
		VBox combine = new VBox();
		combine.getChildren().addAll(headB,fields,buton);
		combine.setSpacing(30);
		pane.setCenter(combine);
		pane.setPrefSize(250, 150);
		pane.requestFocus();
		pane.setOnMousePressed(e->pane.requestFocus());
		return pane;
	}
	protected static  void addBill(String currentUser,String contact, String phone,String email,String name,String quantity,String shipping,double price) throws FileNotFoundException, IOException, ClassNotFoundException {
		String billName = "Bill"+billDate.toString().replaceAll("[\\s:]+", "-")+".txt";
		String bills = "src/Bills/"+billName;	
		String employeName = "src/Database/employe.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> prod = (ArrayList<Employe>) readProd.readObject();
		readProd.close();
		int index = 0;
		String employe;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getUsername().equals(currentUser)) {
				index=i;
			}
		}
		employe=prod.get(index).getName();
		try(PrintWriter print = new PrintWriter(new FileOutputStream(bills,false))){
			print.println("Bill made by : "+employe);
			print.println("--------------------------");
			print.println("Contact : "+contact);
			print.println("Phone : "+phone);
			print.println("Email : "+email);
			print.println("Album Name : "+name);
			print.println("Quantity : "+quantity);
			print.println("Shipping adress : "+shipping);
			print.println("--------------------------");
			if(price==0) {
				print.println("Price to pay : 0 because of not enough quantity");
			}
			else {
				print.println("Price to pay : "+price);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	protected static double getPayment(String name, int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		int index=0;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name)) {
				index=i;
			}
		}
		double payment;
		if(quantity>prod.get(index).getQuantity()) {
			payment= prod.get(index).getQuantity()*prod.get(index).getPrice();
		}else if( prod.get(index).getQuantity()==0) {
			payment= 0*prod.get(index).getPrice();
		}
		else {
			payment= quantity*prod.get(index).getPrice();
		}
		
		prod.get(index).setQuantity(prod.get(index).getQuantity()-quantity);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
		outstream.writeObject(prod);
		outstream.close();
		return payment;
	}
	public static boolean validation(String name,int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		int index=0;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name)) {
				index=i;
			}
		}
		if(prod.get(index).getQuantity()<quantity) {
			return false;
		}
		return true;
	}
}
