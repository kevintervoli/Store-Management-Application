package Scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Login_GUI.Login;
import User_Profiles.Products;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CashierScene {
	public static Stage cashierStage;
	public CashierScene(Stage pmstage) {
		cashierStage=pmstage;
	}
	public static StackPane createCashScene() {
		Color btnColor= Color.web("#053C5E"); 
		Color color=Color.web("#FFFFFF");
		Background bckgStyle = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
		StackPane pane = new StackPane();
		BorderPane bp = new BorderPane();
		pane.getStylesheets().add("css/style.css"); 
		FileInputStream imgStream = null;
		try {
			imgStream = new FileInputStream("Images/a.jpg");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Button checkStock = new Button("Check Stock");
		Button createBill = new Button("Create Bill");
		Button logOut = new Button("Log Out");
		
		checkStock.setFont(Font.font("OCR A Extended",15));
		checkStock.setTextFill(Color.WHITE);
		checkStock.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStock.getInsets())));
		checkStock.setId("logB2");
		checkStock.setOnAction(e->{
			Color col = Color.web("#C3C9E9");
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
				table.setItems((ObservableList<Products>) CashierScene.addProduct());
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			VBox tab = new VBox();
			tab.getChildren().add(table);
			tab.setBackground(new Background(new BackgroundFill(col, CornerRadii.EMPTY, Insets.EMPTY)));
			Stage newStage = new Stage();
			Scene scene = new Scene(tab);
			newStage.setScene(scene);
			newStage.show();
		});
		
		createBill.setFont(Font.font("OCR A Extended",15));
		createBill.setTextFill(Color.WHITE);
		createBill.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStock.getInsets())));
		createBill.setId("logB2");
		
		logOut.setFont(Font.font("OCR A Extended",15));
		logOut.setTextFill(Color.WHITE);
		logOut.setBackground(new Background(new BackgroundFill(btnColor, new CornerRadii(4), checkStock.getInsets())));
		logOut.setId("logB2");
		logOut.setOnAction(e->{
			((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			Stage newWindow = new Stage();
			new Login(newWindow);
			Scene scene = new Scene(Login.login());
			newWindow.setScene(scene);
			newWindow.show();
		});
		
		FontAwesomeIconView first  = new FontAwesomeIconView(FontAwesomeIcon.LINE_CHART,"27");
		FontAwesomeIconView second = new FontAwesomeIconView(FontAwesomeIcon.GOOGLE_WALLET,"27");
		FontAwesomeIconView third = new FontAwesomeIconView(FontAwesomeIcon.UNLOCK,"27");
		HBox hb1 = new HBox(first,checkStock);
		hb1.setSpacing(5);
		HBox hb2 = new HBox(second,createBill);
		hb2.setSpacing(5);
		HBox hb3 = new HBox(third,logOut);
		hb3.setSpacing(5);
		
		FlowPane flwp = new FlowPane(hb1,hb2,hb3);
		flwp.setHgap(25);
		flwp.setVgap(15);
		flwp.setHgap(25);
		flwp.setAlignment(Pos.CENTER);
		flwp.setPrefHeight(35);
		flwp.setBackground(bckgStyle);
		
		Image image = new Image(imgStream);
		ImageView imgView = new ImageView(image);
		imgView.setFitHeight(250);
		imgView.setFitWidth(250);
		
		Text message = new Text("Welcome to the Cashier Window , please proceed with your operation !");
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

}
