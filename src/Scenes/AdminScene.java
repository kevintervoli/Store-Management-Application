package Scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ButtonControllers.UserManagement;
import Login_GUI.Login;
import User_Profiles.Employe;
import User_Profiles.Products;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Stage;
public class AdminScene {
	 public static Button checkStock;
	 public static Button checkEmploye;
	 public static Button checkStatistics ;
	public static Button logOut;
	public static Button employeList ;
	static BorderPane tab = new BorderPane();

	@SuppressWarnings({ "unchecked", "unused", "hiding" })
	public static StackPane createAdminScene() { 
		Color btnColor= Color.web("#053C5E"); 
		Color color=Color.web("#FFFFFF");
		Color tfCol = Color.web("#F9F9FB");
		Border textFBorder = new Border(new BorderStroke(btnColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
		Background tfBack = new Background(new BackgroundFill(tfCol, CornerRadii.EMPTY, Insets.EMPTY));
		StackPane pane = new StackPane();
		BorderPane bp = new BorderPane();
		pane.getStylesheets().add("css/style.css"); 
		FileInputStream imgStream = null;
		try {
			imgStream = new FileInputStream("Images/c.png");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		 checkStock = new Button("Check Stock");
		 checkEmploye = new Button("Manage Users");
		 checkStatistics = new Button("Check Statistics");
		 logOut = new Button("Log Out");
		 employeList = new Button("Employe List");
		
		pane.getStylesheets().add("css/style.css"); 
		
		checkEmploye.setId("logB");
		employeList.setId("logB");
		checkStock.setId("logB");
		logOut.setId("logB");
		checkStatistics.setId("logB");
		employeList.setOnAction(e->{
			TableView<Employe> empT = new TableView<Employe>();
			empT.setBackground(bckgStyle);
			empT.setEditable(true);
			empT.getStylesheets().add("css/style.css");
			empT.setId(".table-view");
			
			TableColumn<Employe,String> name = new TableColumn<>("NAME");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<>("Name"));
			name.setEditable(true);
			name.setCellFactory(TextFieldTableCell.forTableColumn());
			name.setEditable(true);
			name.setOnEditCommit((CellEditEvent<Employe, String> t)->{
				try {
					AdminScene.editTableContent(empT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			});
			TableColumn<Employe,String> surname = new TableColumn<>("SURNAME");
			surname.setPrefWidth(200);
			surname.setEditable(true);
			surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
			surname.setOnEditCommit((CellEditEvent<Employe, String> t)->{
				try {
					AdminScene.editTableContent(empT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			});
			
			TableColumn<Employe,String> username = new TableColumn<>("USERNAME");
			username.setPrefWidth(200);
			username.setEditable(true);
			username.setCellValueFactory(new PropertyValueFactory<>("Username"));
			username.setOnEditCommit((CellEditEvent<Employe, String> t)->{
				try {
					AdminScene.editTableContent(empT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			});
			
			TableColumn<Employe,String> salary = new TableColumn<>("SALARY");
			salary.setPrefWidth(200);
			salary.setEditable(true);
			salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
			salary.setOnEditCommit((CellEditEvent<Employe, String> t)->{
				try {
					AdminScene.editTableContent(empT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			});
			
			empT.getColumns().addAll(name,surname,username,salary);
			try {
				empT.setItems((ObservableList<Employe>)AdminScene.setContent());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			VBox stab = new VBox();
			bp.setCenter(stab);
			stab.getChildren().addAll(empT);
		});
		checkStock.setOnAction(e->{
			bp.setCenter(tab);
			Color col = Color.web("#FFFFFF");
			TableView<Products> table = new TableView<Products>();
			table.setEditable(true);
			table.getStylesheets().add("css/style.css");
			table.setId(".table-view");
			TableColumn<Products,String> name = new TableColumn<Products,String>("NAME");
			name.setPrefWidth(200);
			name.setEditable(true);
			name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
			name.setResizable(true);
			name.setOnEditCommit((CellEditEvent<Products, String> t)->{
				try {
					AdminScene.addProduct();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			});
			
			TableColumn<Products,String> singer = new TableColumn<Products,String>("SINGER");
			singer.setPrefWidth(200);
			singer.setEditable(true);
			singer.setCellValueFactory(new PropertyValueFactory<Products,String>("Singer"));
			singer.setResizable(true);
			singer.setOnEditCommit((CellEditEvent<Products, String> t)->{
				try {
					AdminScene.addProduct();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			});
			TableColumn<Products,String> genre = new TableColumn<Products,String>("GENRE");
			genre.setPrefWidth(200);
			genre.setEditable(true);
			genre.setCellValueFactory(new PropertyValueFactory<Products,String>("Genre"));
			genre.setResizable(true);
			genre.setOnEditCommit((CellEditEvent<Products, String> t)->{
				try {
					AdminScene.addProduct();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			});
			TableColumn<Products,Long> quantity = new TableColumn<Products,Long>("QUANTITY");
			quantity.setEditable(true);
			quantity.setPrefWidth(200);
			quantity.setCellValueFactory(new PropertyValueFactory<Products,Long>("Quantity"));
			quantity.setResizable(true);
			TableColumn<Products,Double> price = new TableColumn<Products,Double>("PRICE");
			price.setEditable(true);
			price.setPrefWidth(200);
			price.setCellValueFactory(new PropertyValueFactory<>("Price"));
			try {
				table.getColumns().addAll(name,singer,genre,quantity,price);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				table.setItems((ObservableList<Products>) addProduct());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			TextField t1 = new TextField();  
			TextField t2 = new TextField();
			TextField t3 = new TextField();
			TextField t4 = new TextField();
			TextField t5 = new TextField();

			
			
			tab.getStylesheets().add("css/style.css");
			t1.setFont(Font.font("OCR A Extended",12));
			t1.setBorder(textFBorder);
			t1.setBackground(tfBack);
			t1.setPrefSize(200, 30);
			t1.setPromptText("Enter the album name :");
			t1.setAlignment(Pos.CENTER);
			
			t2.setPromptText("Enter the quantity :");
			t2.setFont(Font.font("OCR A Extended",12));
			t2.setBorder(textFBorder);
			t2.setBackground(tfBack);
			t2.setPrefSize(200, 30);
			t2.setAlignment(Pos.CENTER);
			
			t3.setPromptText("Enter the genre :");
			t3.setFont(Font.font("OCR A Extended",12));
			t3.setBorder(textFBorder);
			t3.setBackground(tfBack);
			t3.setPrefSize(200, 30);
			t3.setAlignment(Pos.CENTER);
			
			t4.setPromptText("Enter the singer :");
			t4.setFont(Font.font("OCR A Extended",12));
			t4.setBorder(textFBorder);
			t4.setBackground(tfBack);
			t4.setPrefSize(200, 30);
			t4.setAlignment(Pos.CENTER);
			
			t5.setPromptText("Enter the price :");
			t5.setFont(Font.font("OCR A Extended",12));
			t5.setBorder(textFBorder);
			t5.setBackground(tfBack);
			t5.setPrefSize(200, 30);
			t5.setAlignment(Pos.CENTER);
			
			
			Button addStock = new Button("ADD");
			Button removeStock = new Button("REMOVE");
			addStock.setId("logB");
			removeStock.setId("logB");
			HBox botV = new HBox();
			botV.getChildren().addAll(t1,t2,t3,t4,t5,addStock,removeStock);
			botV.setSpacing(8);
			botV.setAlignment(Pos.CENTER);
			tab.setCenter(table);
			tab.setBottom(botV);

			tab.setPrefSize(1500, 200);
			Stage newStage = new Stage();
			newStage.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
			addStock.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(t1.getText().matches("")|| t2.getText().matches("") || t3.getText().matches("")||t4.getText().matches("")) {
						Stage  st= new Stage();
						Label alertS =new Label("Can not add ore remove an empty field !");
						alertS.setFont(Font.font("OCR A Extended",17));
						alertS.setTextFill(Color.RED);
						StackPane alert = new StackPane();
						alert.setPrefSize(250, 200);
						alert.getChildren().add(alertS);
						Scene scene  = new Scene(alert);
						st.setScene(scene);
						st.show();
						
					}
					else {
						try {
							checkStockValid(t1.getText(),t4.getText(),t3.getText(),Integer.parseInt(t2.getText()),Double.parseDouble(t5.getText()));
						} catch (NumberFormatException | ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					}				
			});
			removeStock.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					try {
						if(t1.getText().matches("")){
							Stage  st= new Stage();
							Label alertS =new Label("Can not add ore remove an empty field !");
							alertS.setFont(Font.font("OCR A Extended",17));
							alertS.setTextFill(Color.RED);
							StackPane alert = new StackPane();
							alert.setPrefSize(250, 200);
							alert.getChildren().add(alertS);
							Scene scene  = new Scene(alert);
							st.setScene(scene);
							st.show();
			}else {
				removeProduct(t1.getText(),Integer.parseInt(t2.getText()),Double.parseDouble(t5.getText()));
			}

					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			});
		});
		checkEmploye.setOnAction(e->{
			bp.setCenter(UserManagement.createWindow());
		});
		logOut.setOnAction(e->{
			((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			Stage newWindow = new Stage();
			new Login(newWindow);
			Scene scene = new Scene(Login.login());
			newWindow.setScene(scene);
			newWindow.show();
			newWindow.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
		});
		
		FontAwesomeIconView first  = new FontAwesomeIconView(FontAwesomeIcon.LINE_CHART,"30");
		FontAwesomeIconView second  = new FontAwesomeIconView(FontAwesomeIcon.USER_SECRET,"30");
		FontAwesomeIconView third  = new FontAwesomeIconView(FontAwesomeIcon.BAR_CHART,"30");
		FontAwesomeIconView fourth  = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK,"30");
		FontAwesomeIconView fifth = new FontAwesomeIconView(FontAwesomeIcon.USER,"30");
		
		HBox hb1 = new HBox(first,checkStock);
		hb1.setSpacing(5);
		HBox hb2 = new HBox(second,checkEmploye);
		hb2.setSpacing(5);
		HBox hb3 = new HBox(third,checkStatistics);
		hb3.setSpacing(5);
		HBox hb4 = new HBox(fourth,logOut);
		hb4.setSpacing(5);
		HBox hb5 = new HBox(fifth,employeList);
		hb5.setSpacing(5);
		
		FlowPane flwp = new FlowPane(hb1,hb2,hb5,hb3,hb4);
		flwp.setVgap(15);
		flwp.setHgap(25);
		flwp.setAlignment(Pos.CENTER);
		flwp.setPrefHeight(35);
		flwp.setBackground(bckgStyle);
		
		Image image = new Image(imgStream);
		ImageView imgView = new ImageView(image);
		imgView.setFitHeight(250);
		imgView.setFitWidth(250);
		
		Text message = new Text("Welcome to the Admin Window , please proceed with your operation !");
		message.setFont(Font.font("OCR A Extended",15));
		message.setFill(btnColor);
		Text message2 = new Text("Thank you !");
		message2.setFont(Font.font("OCR A Extended",15));
		message2.setFill(btnColor);
		VBox centerCashier = new VBox();
		centerCashier.getChildren().addAll(imgView,message,message2);
		centerCashier.setAlignment(Pos.CENTER);
		centerCashier.setPadding(new Insets(15,0,0,0));
		centerCashier.setBackground(bckgStyle);
		bp.setTop(flwp);
		bp.setCenter(centerCashier);
		pane.getChildren().add(bp);
		pane.setPrefSize(1540,800);
		return pane;
	}
	@SuppressWarnings("unchecked")
	public  static ObservableList<Products> addProduct() throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		readProd.close();
		ObservableList<Products> prods = FXCollections.observableArrayList();
		for(int i=0;i<prod.size();i++) {
			prods.add(prod.get(i));
		}
		return prods;
		
	}
	@SuppressWarnings("unchecked")
	public static ObservableList<Employe> setContent() throws FileNotFoundException, IOException, ClassNotFoundException{
		String employeName = "src/Database/employe.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> prod = (ArrayList<Employe>) readProd.readObject();
		readProd.close();
		ObservableList<Employe> prods = FXCollections.observableArrayList();
		for(int i=0;i<prod.size();i++) {
			prods.add(prod.get(i));
		}

		return prods;
		
	}
	public static void editTableContent(TableView<Employe> empT) throws FileNotFoundException, IOException {
		String employeName = "src/Database/employe.dat";
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(employeName, true));
		for (Employe row : empT.getItems()) {
				ostream.writeObject(row);
		}
		ostream.close();

	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void aStock(String name,String singer,String genre,int quantity,double price) throws IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		Products newProd = new Products(name,singer,genre,quantity, price);
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		prod.add(newProd);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
		outstream.writeObject(prod);
		outstream.close();
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void checkStockValid(String name,String singer,String genre,int quantity,double price) throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		int cnt=0;
		int index=0;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name)) {
				cnt++;
				index=i;
			}
		}
		if(cnt==0) {
			aStock(name,singer,genre,quantity,price);
		}
		else {
			prod.get(index).setQuantity(prod.get(index).getQuantity()+quantity);
			ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
			outstream.writeObject(prod);
			outstream.close();
		}
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void removeProduct(String name,int quantity,double price) throws FileNotFoundException, IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = ((ArrayList<Products>) readProd.readObject());
		int index=0;
		for(int i=0;i<prod.size();i++) {
			if(prod.get(i).getName().equals(name) ) {
				index=i;
			}
		}
		if(quantity==0) {
			prod.remove(index);
		}
		else if(prod.get(index).equals(name) || prod.get(index).getQuantity()==quantity) {
			prod.remove(index);
		}
		else {
			prod.get(index).setQuantity(prod.get(index).getQuantity()-quantity);
			ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
			outstream.writeObject(prod);
			outstream.close();
		}
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
		outstream.writeObject(prod);
		outstream.close();

	}
	
}
