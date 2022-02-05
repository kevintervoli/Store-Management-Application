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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Create_Bill{
	private static ArrayList<Products> produktArr=new ArrayList<Products>();;
	private static  Date billDate;
	protected static double pay=0;
	protected static Label shipping;
	protected static Label contactPerson;
	protected static Label phoneNum;
	protected static Label emailPerson;
	protected static Label msg;	
	protected static Color msgColor;
	protected static ProgressBar progress ;


	protected static TextField shippingfield;
	protected static TextField contact;
	protected static TextField phone;
	protected static TextField email;
	protected static TextField payment;
	
	protected static TextField tf1;
	protected static TextField tf2;
	
	protected static Button add;
	protected static Button remove;
	protected static Button createButton;
	protected static Button generateBill;
	protected static TableView<Products> produkte;
	protected static TableView<Products> items;
	@SuppressWarnings("unused")
	private static ArrayList<Products> billArray = new ArrayList<Products>();
	protected static BorderPane bp = new BorderPane();
	static int billNum=0;
	static String billN="0";
	public Create_Bill() {
		billDate= new Date();
	}
	
	
	@SuppressWarnings({ "unused", "unchecked", "resource" })
	public static BorderPane billWindow() {
		BorderPane pane = new BorderPane();
		Color fontColor= Color.web("#b2c2cf"); 
		Color backGround = Color.web("#FFFFFF");
		Color head = Color.web("#b2c2cf");
		Color color = Color.web("#E2E5ED");
		Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		Text header = new Text("Welcome to the create bill window !");
		header.setFont(Font.font("OCR A Extended",20));
		header.setFill(head);
		
		
		HBox headB = new HBox(header);
		pane.getStylesheets().add("css/style.css");
		headB.setAlignment(Pos.CENTER);
		headB.setSpacing(40);
		headB.setBackground(bckgStyle);
		
		shipping = new Label("Shipping adress : ");
		contactPerson = new Label("Contact : ");
		phoneNum = new Label("Phone : ");
		emailPerson = new Label("Email : ");
		

		shipping.setFont(Font.font("OCR A Extended",20));
		contactPerson.setFont(Font.font("OCR A Extended",20));
		phoneNum.setFont(Font.font("OCR A Extended",20));
		emailPerson.setFont(Font.font("OCR A Extended",20));
		
		
		VBox first = new VBox();
		first.getChildren().addAll(contactPerson,phoneNum,emailPerson,shipping);
		first.setSpacing(25);
		first.setAlignment(Pos.CENTER_LEFT);

		
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
		second.getChildren().addAll(contact,phone,email,shippingfield);
		second.setAlignment(Pos.CENTER);
		second.setSpacing(25);
		
		HBox fields = new HBox();
		fields.getChildren().addAll(first,second);
		createButton = new Button("Create Bill");
		createButton.setId("logB2");
		
		fields.setAlignment(Pos.CENTER);
		fields.setSpacing(25);
		createButton.setOnAction(e->{
			try {
				if(contact.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()  || shippingfield.getText().isEmpty()) {
					Alert fail= new Alert(AlertType.WARNING);
			        fail.setHeaderText("FAIL");
			        fail.setContentText("Check empty fields");
			        fail.showAndWait();
				}
				else {
					if(phone.getText().matches("[0-9]{9}") || phone.getText().matches("\\+{1}[0-9]{12}")) {
							addBill( Login_Controller.currentUser,contact.getText(),phone.getText(),email.getText(),shippingfield.getText(),priceToPay());
							contact.clear();
							phone.clear();
							email.clear();
							shippingfield.clear();
							for(int i=0;i<produktArr.size();i++) {
								produktArr.remove(i);
							}
							String employeName = "src/Database/employe.dat";
							ObjectInputStream readEmployes= new ObjectInputStream(new FileInputStream(employeName));
							ArrayList<Employe> employes = (ArrayList<Employe>) readEmployes.readObject();
							int index=-1;
							for(int i=0;i<employes.size();i++) {
								if(employes.get(i).getUsername().equals(Login_Controller.currentUser)) {
									index=i;
								}
							}
							pay=0;
							employes.get(index).setSoldItem(employes.get(index).getSoldItem()+Integer.parseInt(tf2.getText()));
							ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(employeName));
							outstream.writeObject(employes);
							outstream.close();
							
						}
				else {
					Alert fail= new Alert(AlertType.WARNING);
			        fail.setHeaderText("FAIL");
			        fail.setContentText("Incorrect phone number type !");
			        fail.showAndWait();
				      }
				}
			
			} catch (NumberFormatException | IOException e1) {ProgressBar progress = new ProgressBar();
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		HBox buton = new HBox();
		buton.getChildren().addAll(createButton);
		buton.setAlignment(Pos.CENTER);
		buton.setSpacing(15);
		VBox combine = new VBox();
		combine.getChildren().addAll(headB,fields,buton);
		combine.setSpacing(40);
		pane.setCenter(combine);
		pane.setPrefSize(250, 150);
		pane.requestFocus();
		pane.setOnMousePressed(e->pane.requestFocus());
		return pane;
	}
	@SuppressWarnings({ "unchecked", "resource", "unused" })
	protected static StackPane addItemsToTableBill() {
		Color fontColor= Color.web("#b2c2cf"); 
		Color backGround = Color.web("#FFFFFF");
		Color head = Color.web("#b2c2cf");
		Color color = Color.web("#E2E5ED");
		Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		StackPane pane = new StackPane();
		Label label = new Label("Product Name :");
		Label label2 = new Label("Quantity : ");
		tf1 = new TextField();
		tf2 = new TextField();
		label.setFont(Font.font("OCR A Extended",20));
		label2.setFont(Font.font("OCR A Extended",20));
		tf1 = new TextField();
		tf1.setPromptText("Enter product name : ");
		tf1.setBorder(textFBorder);
		tf1.setPrefSize(300, 30);
		tf1.setBackground(bckgStyle);
		
		tf2 = new TextField();
		tf2.setPromptText("Enter quantity : ");
		tf2.setBorder(textFBorder);
		tf2.setPrefSize(300, 30);
		tf2.setBackground(bckgStyle);
		add = new Button("ADD");
		remove = new Button("REMOVE");
		remove.setId("logB");
		remove.setOnAction(e->{
			try {
				remove(tf1.getText(),Integer.parseInt(tf2.getText()));
				produkte.getItems().clear();
				items.getItems().clear();
				produkte.setItems((ObservableList<Products>)Create_Bill.produkteRemoved());
				items.setItems((ObservableList<Products>) AdminScene.addProdukte());
				produkte.refresh();
				items.refresh();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		generateBill = new Button("GENERATE BILL");
		generateBill.setOnAction(e->{
			if(Login_Controller.currentStatus==0) {
				CashierScene.bp.setCenter(Create_Bill.billWindow());
			}
			else if(Login_Controller.currentStatus==0) {
				ManagerScene.bp.setCenter(Create_Bill.billWindow());
			}
			else {
				AdminScene.bp.setCenter(Create_Bill.billWindow());
			}
			produkte.getItems().clear();
		});
		pane.getStylesheets().add("css/style.css");
		add.setId("logB");
		generateBill.setId("logB");
		add.setOnAction(e->{
			try {
				if(tf1.getText().isEmpty() || tf2.getText().isEmpty()) {
					Alert fail= new Alert(AlertType.WARNING);
			        fail.setHeaderText("FAIL");
			        fail.setContentText("Empty field !");
			        fail.showAndWait();
				}
				else if(tf2.getText().matches("[^0-9]+")) {
					Alert fail= new Alert(AlertType.WARNING);
			        fail.setHeaderText("FAIL");
			        fail.setContentText("Enter an integer !");
			        fail.showAndWait();
				}
				else {
					
					String productsFile = "src/Database/products.dat";
					ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
					ArrayList<Products> prod = ((ArrayList<Products>) readProd.readObject());
					int cnt=0;
					int index=-1;
					for(int i=0;i<prod.size();i++) {
						if(prod.get(i).getName().equals(tf1.getText())) {
							cnt++;
							index=i;
						}
						}
					if(cnt ==0) {
						Alert fail= new Alert(AlertType.WARNING);
				        fail.setHeaderText("FAIL");
				        fail.setContentText("Cant find product");
				        fail.showAndWait();
					}
					else {
						if(prod.get(index).getQuantity()<Integer.parseInt(tf2.getText()) || prod.get(index).getQuantity()==0) {
							Alert fail= new Alert(AlertType.WARNING);
					        fail.setHeaderText("FAIL");
					        fail.setContentText("Not enough stock !");
					        fail.showAndWait();
						}
						else {
						produkte.getItems().clear();
						items.getItems().clear();
						produkte.setItems((ObservableList<Products>)Create_Bill.addProducts(tf1.getText(),tf2.getText()));
						items.setItems((ObservableList<Products>) AdminScene.addProdukte());
						produkte.refresh();
						items.refresh();
						}
					}
					}
				}
			catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		});
		VBox v1 = new VBox(label,label2);
		v1.setSpacing(15);
		VBox v2 = new VBox(tf1,tf2);
		v2.setSpacing(15);
		HBox hb = new HBox(v1,v2);
		hb.setSpacing(10);
		HBox butons = new HBox(add,remove,generateBill);
		butons.setSpacing(10);
		VBox allB = new VBox(hb,butons);
		allB.setSpacing(10);
		hb.setSpacing(10);
		pane.getChildren().add(allB);
		pane.setAlignment(Pos.CENTER);
		return pane;
	}
	@SuppressWarnings({ "unchecked", "resource" })
	protected static ObservableList<Products> addProducts(String name,String quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = ((ArrayList<Products>) readProd.readObject());
		ObservableList<Products> prods = FXCollections.observableArrayList();
		int index=-1;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name)) {
				index=i;
				break;
			}
		}

		prod.get(index).setQuantity(prod.get(index).getQuantity()-Long.parseLong(quantity));
		produktArr.add(new Products(name,"","",Long.parseLong(quantity),prod.get(index).getPrice()));
		for(int i=0;i<produktArr.size();i++) {
			prods.add(produktArr.get(i));
		}
		
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(productsFile));
		ostream.writeObject(prod);
		ostream.close();
		return prods;
	}
	protected static ObservableList<Products> produkteRemoved(){
		ObservableList<Products> prods = FXCollections.observableArrayList();
		for(int i=0;i<produktArr.size();i++) {
			prods.add(produktArr.get(i));
		}
		return prods;
	}
	@SuppressWarnings("unchecked")
	protected static StackPane tableItems() throws FileNotFoundException, ClassNotFoundException, IOException{
		StackPane pane = new StackPane();
		produkte = new TableView<Products>();
		produkte.setMinHeight(100);
		produkte.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		produkte.setEditable(true);
		produkte.getStylesheets().add("css/style.css");
		produkte.setId(".table-view");
		TableColumn<Products,String> name = new TableColumn<Products,String>("NAME");
		name.setMinWidth(200);
		name.setEditable(true);
		name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));	
		TableColumn<Products,Long> quantity = new TableColumn<Products,Long>("QUANTITY");
		quantity.setMinWidth(200);
		quantity.setEditable(true);
		quantity.setCellValueFactory(new PropertyValueFactory<Products,Long>("Quantity"));
		try {
			produkte.getColumns().addAll(name,quantity);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		produkte.setItems(FXCollections.observableArrayList());
		pane.getChildren().add(produkte);
		pane.setPrefHeight(300);
		return pane;
	}

	@SuppressWarnings("unchecked")
	protected static StackPane table() {
		StackPane pane = new StackPane();
		
		items = new TableView<Products>();
		items.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		items.setEditable(true);
		items.getStylesheets().add("css/style.css");
		items.setId(".table-view");
		TableColumn<Products,String> name = new TableColumn<Products,String>("NAME");
		name.setMinWidth(200);
		name.setEditable(true);
		name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
		TableColumn<Products,String> singer = new TableColumn<Products,String>("SINGER");
		singer.setMinWidth(200);
		singer.setEditable(true);
		singer.setCellValueFactory(new PropertyValueFactory<Products,String>("Singer"));
		singer.setResizable(true);
	
		TableColumn<Products,String> genre = new TableColumn<Products,String>("GENRE");
		genre.setMinWidth(200);
		genre.setEditable(true);
		genre.setCellValueFactory(new PropertyValueFactory<Products,String>("Genre"));
		genre.setResizable(true);

		TableColumn<Products,Long> quantity = new TableColumn<Products,Long>("QUANTITY");
		quantity.setEditable(true);
		quantity.setMinWidth(200);
		quantity.setCellValueFactory(new PropertyValueFactory<Products,Long>("Quantity"));
		quantity.setResizable(true);
		TableColumn<Products,Double> price = new TableColumn<Products,Double>("PRICE");
		price.setEditable(true);
		price.setMinWidth(200);
		price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		try {
			items.getColumns().addAll(name,singer,genre,quantity,price);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			items.setItems((ObservableList<Products>) AdminScene.addProdukte());
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pane.getChildren().add(items);
		return pane;
	}
	
	@SuppressWarnings("unchecked")
	protected static  void addBill(String currentUser,String contact, String phone,String email,String shipping,double payment) throws FileNotFoundException, IOException, ClassNotFoundException {
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
			print.println("Shipping adress : "+shipping);
			print.println("--------------------------");
			print.println("Price to pay : "+payment);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "resource" })
	protected static boolean validation(String name,int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
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
	@SuppressWarnings({ "resource", "unchecked" })
	protected static void remove(String name,int quantity) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = ((ArrayList<Products>) readProd.readObject());
		int index =-1;
		for(int i=0;i<produktArr.size();i++) {
			if(produktArr.get(i).getName().equals(name)) {
			index=i;
			}
		}
		if(quantity==produktArr.get(index).getQuantity()) {
			produktArr.remove(index);	
		}
		else if(quantity>produktArr.get(index).getQuantity() || quantity==0) {
			produktArr.remove(index);	
		}
		else {
			produktArr.get(index).setQuantity(produktArr.get(index).getQuantity()-quantity);
		}
		int index2=-1;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name)) {
				index2=i;
				break;
			}
		}

		prod.get(index2).setQuantity(prod.get(index2).getQuantity()+Long.parseLong(tf2.getText()));
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(productsFile));
		ostream.writeObject(prod);
		ostream.close();;
	}
	protected static  double priceToPay() {
		double ppay=0;
		for(int i=0;i<produktArr.size();i++) {
			System.out.println(produktArr.get(i).getQuantity()+" "+produktArr.get(i).getPrice());
		}
		for(int i=0;i<produktArr.size();i++) {
			ppay += (produktArr.get(i).getQuantity()*produktArr.get(i).getPrice());
		}
		return ppay;

	}

}