package Scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.SpringLayout.Constraints;
import ButtonControllers.UserManagement;
import Login_GUI.Login;
import User_Profiles.Employe;
import User_Profiles.Products;
import User_Profiles.User;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
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
	private static Button checkStock;
	private static Button checkEmploye;
	private static Button checkStatistics ;
	private static Button logOut;
	private static Button employeList ;
	private static Button usersList;
	private static Button createBill;
	static BorderPane tab = new BorderPane();

	@SuppressWarnings({ "unchecked", "unused", "hiding" })
	public static StackPane createAdminScene() { 
		Color btnColor= Color.web("#053C5E"); 
		Color color=Color.web("#FFFFFF");
		Color tfCol = Color.web("#EEEEEE");
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
		 usersList = new Button("Users List");
		 createBill = new Button("Create Bill");
		
		pane.getStylesheets().add("css/style.css"); 
		
		checkEmploye.setId("logB");
		employeList.setId("logB");
		checkStock.setId("logB");
		logOut.setId("logB");
		checkStatistics.setId("logB");
		usersList.setId("logB");
		createBill.setId("logB");
		createBill.setFont(Font.font("OCR A Extended",15));
		createBill.setTextFill(Color.WHITE);
		createBill.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStock.getInsets())));
		createBill.setId("logB");
		createBill.setOnAction(e->{
			new Create_Bill();
			bp.setCenter(Create_Bill.billWindow());
		});
		employeList.setOnAction(e->{
			Color col = Color.web("#C3C9E9");
			TableView<Employe> empT = new TableView<Employe>();
			empT.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			empT.setPrefSize(290,800);
			empT.setEditable(true);
			empT.getStylesheets().add("css/style.css");
			empT.setId(".table-view");
			
			TableColumn<Employe,String> name = new TableColumn<>("NAME");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<>("Name"));
	
			
			TableColumn<Employe,String> surname = new TableColumn<>("SURNAME");
			surname.setMinWidth(200);
			surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
			
			TableColumn<Employe,String> username = new TableColumn<>("USERNAME");
			username.setMinWidth(200);
			username.setCellValueFactory(new PropertyValueFactory<>("Username"));
			
			TableColumn<Employe,String> salary = new TableColumn<>("SALARY");
			salary.setMinWidth(200);
			salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
			
			TableColumn<Employe,Integer> soldItems = new TableColumn<>("SOLD ITEMS");
			soldItems.setMinWidth(200);
			soldItems.setCellValueFactory(new PropertyValueFactory<>("soldItem"));
			
			empT.getColumns().addAll(name,surname,username,salary,soldItems);
			try {
				empT.setItems((ObservableList<Employe>)AdminScene.setContent());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			VBox stab = new VBox();
			stab.getChildren().addAll(empT);
			stab.setPrefSize(700, 500);
			bp.setCenter(stab);
		});
		checkStock.setOnAction(e->{
			bp.setCenter(tab);
			Color col = Color.web("#FFFFFF");
			TableView<Products> table = new TableView<Products>();
			table.setPrefHeight(500);
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			table.setEditable(true);
			table.getStylesheets().add("css/style.css");
			table.setId(".table-view");
			TableColumn<Products,String> name = new TableColumn<Products,String>("NAME");
			name.setMinWidth(200);
			name.setEditable(true);
			name.setCellValueFactory(new PropertyValueFactory<Products,String>("Name"));
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
			singer.setMinWidth(200);
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
			genre.setMinWidth(200);
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
			quantity.setMinWidth(200);
			quantity.setCellValueFactory(new PropertyValueFactory<Products,Long>("Quantity"));
			quantity.setResizable(true);
			TableColumn<Products,Double> price = new TableColumn<Products,Double>("PRICE");
			price.setEditable(true);
			price.setMinWidth(200);
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
			tab.setPadding(new Insets(10,10,10,10));
			tab.setPrefHeight(500);
			Stage newStage = new Stage();
			newStage.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
			addStock.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(t1.getText().matches("")|| t2.getText().matches("") || t3.getText().matches("")||t4.getText().matches("")) {
						Alert fail= new Alert(AlertType.WARNING);
				        fail.setHeaderText("FAIL");
				        fail.setContentText("Empty field !");
				        fail.showAndWait();
						
					}
					else if(t2.getText().matches("[^0-9]+")) {
						Alert fail= new Alert(AlertType.WARNING);
				        fail.setHeaderText("FAIL");
				        fail.setContentText("Enter an integer for quantity");
				        fail.showAndWait();
					}
					else if(t5.getText().matches("[^0-9]+")) {
						Alert fail= new Alert(AlertType.WARNING);
				        fail.setHeaderText("FAIL");
				        fail.setContentText("Enter an integer for price");
				        fail.showAndWait();
					}
					else {
						try {
							table.getItems().clear();
							checkStockValid(t1.getText(),t4.getText(),t3.getText(),Integer.parseInt(t2.getText()),Double.parseDouble(t5.getText()));
							table.setItems((ObservableList<Products>) addProduct());
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
							Alert fail= new Alert(AlertType.WARNING);
					        fail.setHeaderText("FAIL");
					        fail.setContentText("Empty field !");
					        fail.showAndWait();
			}else {
				table.getItems().clear();
				removeProduct(t1.getText(),Integer.parseInt(t2.getText()),Double.parseDouble(t5.getText()));
				table.setItems((ObservableList<Products>) addProduct());
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
		usersList.setOnAction(e->{
			TableView<User> table = new TableView<User>();
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			table.setEditable(true);
			table.getStylesheets().add("css/style.css");
			table.setId(".table-view");
			table.setPrefSize(290,800);
			TableColumn<User,String> name = new TableColumn<>("NAME");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<>("Name"));
			
			TableColumn<User,String> surname = new TableColumn<>("SURNAME");
			surname.setMinWidth(200);
			surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
			
			TableColumn<User,String> username = new TableColumn<>("USERNAME");
			username.setMinWidth(200);
			username.setCellValueFactory(new PropertyValueFactory<>("Username"));
			
			TableColumn<User,String> password = new TableColumn<>("PASSWORD");
			password.setMinWidth(200);
			password.setCellValueFactory(new PropertyValueFactory<>("Password"));
			
			TableColumn<User,Double> salary = new TableColumn<>("SALARY");
			salary.setMinWidth(200);
			salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
			
			
			table.getColumns().addAll(name,surname,username,password,salary);
			try {
				table.setItems(AdminScene.setUserContent());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			VBox stab = new VBox();
			stab.getChildren().addAll(table);
			stab.setPrefSize(700, 500);
			bp.setCenter(stab);
		});
		checkStatistics.setOnAction(e->{
			StackPane pan = new StackPane();
			/*Pie chart*/
			String employeName = "src/Database/employe.dat";
			ObjectInputStream secondinp = null
					;
			try {
				secondinp = new ObjectInputStream(new FileInputStream(employeName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<Employe> employe = null;
			try {
			 employe = (ArrayList<Employe>) secondinp.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
			for(int i=0;i<employe.size();i++) {
				pieData.add(new PieChart.Data(employe.get(i).getName(), employe.get(i).getSoldItem()));
			}
			PieChart pieChart = new PieChart(pieData);
			pieChart.getData().forEach(data->{
				 String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
				 Tooltip toolTip = new Tooltip(percentage);
				 Tooltip.install(data.getNode(), toolTip);
			});
			
			/* Bar Chart*/
			ObservableList<Employe> list = FXCollections.observableArrayList();
			for(int i=0;i<employe.size();i++) {
				list.add(employe.get(i));
			}
			CategoryAxis xAxis=new CategoryAxis();
			NumberAxis yAxis=new NumberAxis();
			BarChart<String, Number> bar=new BarChart<String, Number>(xAxis, yAxis);
			xAxis.setLabel("Employee");
			yAxis.setLabel("Sold items");
			XYChart.Series series=new XYChart.Series<>();
			for(int i=0;i<list.size();i++)
			{
				series.getData().add(new XYChart.Data(list.get(i).getName(), list.get(i).getSoldItem()));
			}
			bar.getData().add(series);
			bar.setAlternativeRowFillVisible(true);
			HBox charts = new HBox();
			charts.getChildren().addAll(pieChart,bar);
			charts.setAlignment(Pos.CENTER);
			charts.setSpacing(10);
			pan.getChildren().add(charts);
			bp.setCenter(pan);
					
		});
		
		FontAwesomeIconView first  = new FontAwesomeIconView(FontAwesomeIcon.LINE_CHART,"30");
		FontAwesomeIconView second  = new FontAwesomeIconView(FontAwesomeIcon.USER_SECRET,"30");
		FontAwesomeIconView third  = new FontAwesomeIconView(FontAwesomeIcon.BAR_CHART,"30");
		FontAwesomeIconView fourth  = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK,"30");
		FontAwesomeIconView fifth = new FontAwesomeIconView(FontAwesomeIcon.USER,"30");
		FontAwesomeIconView sixth = new FontAwesomeIconView(FontAwesomeIcon.USERS,"30");
		FontAwesomeIconView seventh = new FontAwesomeIconView(FontAwesomeIcon.MONEY,"30");
		
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
		HBox hb6 = new HBox(sixth,usersList);
		hb6.setSpacing(5);
		HBox hb7 = new HBox(seventh,createBill);
		hb7.setSpacing(5);
		
		FlowPane flwp = new FlowPane(hb1,hb7,hb2,hb5,hb6,hb3,hb4);
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
	@SuppressWarnings({ "unchecked", "resource" })
	public static ObservableList<User> setUserContent() throws FileNotFoundException, IOException, ClassNotFoundException{
		String userName = "src/Database/firstUsers.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(userName));
		ArrayList<User> user = (ArrayList<User>) readProd.readObject();
		readProd.close();
		ObservableList<User> userO = FXCollections.observableArrayList();
		for(int i=0;i<user.size();i++) {
			userO.add(user.get(i));
		}
		return userO;
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
		setContent();
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
		setContent();

	}
	
}
