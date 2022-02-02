package Scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.stage.WindowEvent;


public class ManagerScene {
	@SuppressWarnings({ "unchecked", "unused" })
	public static StackPane createManagerScene() {

		Color btnColor= Color.web("#053C5E"); 
		Color color=Color.web("#FFFFFF");
		Color tfCol = Color.web("#F9F9FB");
		Border textFBorder = new Border(new BorderStroke(btnColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
		Background tfBack = new Background(new BackgroundFill(tfCol, CornerRadii.EMPTY, Insets.EMPTY));
		StackPane pane = new StackPane();
		BorderPane bp = new BorderPane();
		
		FileInputStream imgStream = null;
		try {
			imgStream = new FileInputStream("Images/b.png");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		

		Button checkStock = new Button("Check Stock");
		Button checkCashiers = new Button("Check Cashiers");
		Button checkStatistics = new Button("Check Statistics");
		Button logOut = new Button("Log Out");

		pane.getStylesheets().add("css/style.css"); 
		
		checkStock.setFont(Font.font("OCR A Extended",15));
		checkStock.setTextFill(Color.WHITE);
		checkStock.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStock.getInsets())));
		checkStock.setId("logB");
		checkStock.setOnAction(e->{
			
			checkStock.setDisable(true);
			checkCashiers.setDisable(true);
			checkStatistics.setDisable(true);
			logOut.setDisable(true);
			
			Color col = Color.web("#FFFFFF");
			TableView<Products> table = new TableView<Products>();
			
			table.setEditable(true);
			table.getStylesheets().add("css/style.css");
			table.setId(".table-view");
			table.setPrefSize(290,200);
			TableColumn<Products,String> name = new TableColumn<>("NAME");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<>("Name"));
			
			TableColumn<Products,Long> quantity = new TableColumn<>("QUANTITY");
			quantity.setMinWidth(85);
			quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
			try {
				table.getColumns().addAll(name,quantity);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				table.setItems((ObservableList<Products>) ManagerScene.addProduct());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BorderPane tab = new BorderPane();
			TextField t1 = new TextField();
			TextField t2 = new TextField();
			tab.getStylesheets().add("css/style.css");
			t1.setFont(Font.font("OCR A Extended",12));
			t1.setBorder(textFBorder);
			t1.setBackground(tfBack);
			t1.setPrefSize(200, 30);
			t1.setPromptText("Enter the album name :");
			t2.setPromptText("Enter the quantity :");
			t2.setFont(Font.font("OCR A Extended",12));
			t2.setBorder(textFBorder);
			t2.setBackground(tfBack);
			t2.setPrefSize(200, 30);
			Button addStock = new Button("ADD");
			Button removeStock = new Button("REMOVE");
			addStock.setId("logB");
			removeStock.setId("logB");
			HBox botV = new HBox();
			botV.getChildren().addAll(t1,t2,addStock,removeStock);
			botV.setSpacing(8);
			botV.setAlignment(Pos.CENTER);
			tab.setCenter(table);
			tab.setBottom(botV);
			tab.setPadding(new Insets(10,10,10,10));
			tab.setPrefSize(570, 200);
			Stage newStage = new Stage();
			newStage.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
			addStock.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					if(t1.getText().matches("")) {
						Stage  st= new Stage();
						Label alertS =new Label("Can not add ore remove an empty field !");
						alertS.setFont(Font.font("OCR A Extended",17));
						alertS.setTextFill(Color.RED);
						StackPane alert = new StackPane();
						alert.setPrefSize(250, 75);
						alert.getChildren().add(alertS);
						Scene scene  = new Scene(alert);
						st.setScene(scene);
						st.show();
						st.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
						checkStock.setDisable(false);
						checkCashiers.setDisable(false);
						checkStatistics.setDisable(false);
						logOut.setDisable(false);
						newStage.close();
					}
					else {
						try {
							checkStockValid(t1.getText(),Integer.parseInt(t2.getText()));
						} catch (NumberFormatException | ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						checkStock.setDisable(false);
						checkCashiers.setDisable(false);
						checkStatistics.setDisable(false);
						logOut.setDisable(false);
						newStage.close();
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
							alert.setPrefSize(250, 75);
							alert.getChildren().add(alertS);
							Scene scene  = new Scene(alert);
							st.setScene(scene);
							st.show();
							st.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
							newStage.close();
							checkStock.setDisable(false);
							checkCashiers.setDisable(false);
							checkStatistics.setDisable(false);
							logOut.setDisable(false);
							newStage.close();
			}else {
				removeProduct(t1.getText(),Integer.parseInt(t2.getText()));
				checkStock.setDisable(false);
				checkCashiers.setDisable(false);
				checkStatistics.setDisable(false);
				logOut.setDisable(false);
				newStage.close();
			}

					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					checkStock.setDisable(false);
					checkCashiers.setDisable(false);
					checkStatistics.setDisable(false);
					logOut.setDisable(false);
					newStage.close();

				}
				
			});
			newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		          public void handle(WindowEvent we) {
		        	  checkStock.setDisable(false);
						checkCashiers.setDisable(false);
						checkStatistics.setDisable(false);
						logOut.setDisable(false);
						checkCashiers.setDisable(false);
		          }
			});
			Scene scene = new Scene(tab);
			newStage.setScene(scene);
			newStage.show();
		});
		
		checkCashiers.setFont(Font.font("OCR A Extended",15));
		checkCashiers.setTextFill(Color.WHITE);
		checkCashiers.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkCashiers.getInsets())));
		checkCashiers.setId("logB");
		checkCashiers.setOnAction(e->{
			Color col = Color.web("#C3C9E9");
			TableView<Employe> empT = new TableView<Employe>();
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
			empT.getColumns().addAll(name,surname,username);
			try {
				empT.setItems((ObservableList<Employe>)ManagerScene.setTableContent());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			VBox stab = new VBox();
			stab.getChildren().addAll(empT);
			Stage nStage = new Stage();
			Scene scene = new Scene(stab);
			nStage.setScene(scene);
			nStage.show();
			nStage.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
			
		});
		
		checkStatistics.setFont(Font.font("OCR A Extended",15));
		checkStatistics.setTextFill(Color.WHITE);
		checkStatistics.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStatistics.getInsets())));
		checkStatistics.setId("logB");
		
		logOut.setFont(Font.font("OCR A Extended",15));
		logOut.setTextFill(Color.WHITE);
		logOut.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), logOut.getInsets())));
		logOut.setId("logB");
		
		logOut.setOnAction(e->{
			((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			Stage newWindow = new Stage();
			new Login(newWindow);
			Scene scene = new Scene(Login.login());
			newWindow.setScene(scene);
			newWindow.show();
			newWindow.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
		});
		
		FontAwesomeIconView first  = new FontAwesomeIconView(FontAwesomeIcon.LINE_CHART,"27");
		FontAwesomeIconView second  = new FontAwesomeIconView(FontAwesomeIcon.USER,"27");
		FontAwesomeIconView third  = new FontAwesomeIconView(FontAwesomeIcon.BAR_CHART,"27");
		FontAwesomeIconView fourth  = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK,"27");
		
		HBox hb1 = new HBox(first,checkStock);
		hb1.setSpacing(5);
		HBox hb2 = new HBox(second,checkCashiers);
		hb2.setSpacing(5);
		HBox hb3 = new HBox(third,checkStatistics);
		hb3.setSpacing(5);
		HBox hb4 = new HBox(fourth,logOut);
		hb4.setSpacing(5);
		
		FlowPane flwp = new FlowPane(hb1,hb2,hb3,hb4);
		flwp.setHgap(25);
		flwp.setAlignment(Pos.CENTER);
		
		Image image = new Image(imgStream);
		ImageView imgView = new ImageView(image);
		imgView.setFitHeight(250);
		imgView.setFitWidth(250);
		
		Text message = new Text("Welcome to the Manager Window , please proceed with your operation !");
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
		pane.requestFocus();
		pane.setOnMousePressed(e->pane.requestFocus());
		return pane;
	}
	@SuppressWarnings("unchecked")
	public static ObservableList<Employe> setTableContent() throws FileNotFoundException, IOException, ClassNotFoundException{
		String employeName = "src/Database/employe.dat";
		String fileName = "src/Database/firstUsers.dat";
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> prod = (ArrayList<Employe>) readProd.readObject();
		readProd.close();
		ObservableList<Employe> prods = FXCollections.observableArrayList();
		ObjectInputStream readUs = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) readUs.readObject();
		readUs.close();
		for(int i=0;i<prod.size();i++) {
			if(users.get(i).getUserStatus()==0) {
				prods.add(prod.get(i));
			}
		}

		return prods;
		
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
	public static void editTableContent(TableView<Employe> empT) throws FileNotFoundException, IOException {
		String employeName = "src/Database/employe.dat";
		ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(employeName, true));
		for (Employe row : empT.getItems()) {
				ostream.writeObject(row);
		}
		ostream.close();

	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void aStock(String name,int quantity) throws IOException, ClassNotFoundException {
		String productsFile = "src/Database/products.dat";
		Products newProd = new Products(name,quantity);
		ObjectInputStream readProd = new ObjectInputStream(new FileInputStream(productsFile));
		ArrayList<Products> prod = (ArrayList<Products>) readProd.readObject();
		prod.add(newProd);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
		outstream.writeObject(prod);
		outstream.close();
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void checkStockValid(String name,int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
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
			aStock(name,quantity);
		}
		else {
			prod.get(index).setQuantity(prod.get(index).getQuantity()+quantity);
			ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(productsFile));
			outstream.writeObject(prod);
			outstream.close();
		}
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void removeProduct(String name,int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
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
