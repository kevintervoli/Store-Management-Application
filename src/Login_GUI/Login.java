package Login_GUI;
import ButtonControllers.Hyperlink_Controller;
import ButtonControllers.Login_Controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
import javafx.stage.Stage;
import javafx.application.HostServices;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class Login {
	public static Label message;
	public static Button loginB;
	public static Hyperlink forgotPassword=new Hyperlink();
	public static Stage stage;
	public static TextField username;
	public static PasswordField password;
	public static Label msg;
	
	public Login(Stage primaryStage) {
		stage=primaryStage;
	}
	
	public static AnchorPane login() {
		AnchorPane pane = new AnchorPane();
		Color fontColor= Color.web("#053C5E"); 
		Color backGround = Color.web("#FFFFFF");
		//Style the background of login 
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		//Style the borders of the text fields
	    Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Login_Controller handle = new Login_Controller(stage);
		
		//Beginning of the Login pane
		
		Text head = new Text("Management Login");
		head.setFont(Font.font("OCR A Extended",25));
		head.setFill(fontColor);
		FontAwesomeIconView headIcon = new FontAwesomeIconView(FontAwesomeIcon.ADDRESS_CARD,"55");
		headIcon.setTabSize(10);
		headIcon.setFill(fontColor);
		VBox headBox = new VBox();
		headBox.getChildren().addAll(head,headIcon);
		headBox.setAlignment(Pos.CENTER);
		headBox.setSpacing(10);
		headBox.setPrefSize(400,205);
		
		//Center of the Login Pane
		FontAwesomeIconView userIc = new FontAwesomeIconView(FontAwesomeIcon.USER,"30");
		userIc.setFill(fontColor);
		FontAwesomeIconView lockIc = new FontAwesomeIconView(FontAwesomeIcon.LOCK,"30");
		lockIc.setFill(fontColor);
		
		username = new TextField();
		username.setFont(Font.font("OCR A Extended",12));
		username.setPromptText("Enter Username !");
		username.setBorder(textFBorder);
		username.setBackground(bckgStyle);
		username.setPrefSize(200, 30);
		username.setOnAction(handle);
		
		password = new PasswordField();
		password.setFont(Font.font("OCR A Extended",12));
		password.setPromptText("Enter Password !");
		password.setBorder(textFBorder);
		password.setBackground(bckgStyle);
		password.setPrefSize(200, 30);
		password.setOnAction(handle);
		
		
		HBox h1 = new HBox();
		h1.getChildren().addAll(userIc,username);
		h1.setAlignment(Pos.CENTER);
		h1.setSpacing(10);
		h1.setPrefWidth(400);
		h1.setPrefHeight(70);
		
		HBox h2 = new HBox();
		h2.getChildren().addAll(lockIc,password);
		h2.setSpacing(10);
		h2.setAlignment(Pos.CENTER);
		h2.setPrefWidth(400);
		h2.setPrefHeight(70);
			
		msg= new Label("");
		msg.setFont(Font.font("OCR A Extended",15));
        HBox loginMsgBox = new HBox(msg);
		msg.setBackground(bckgStyle);
        msg.setPadding(new Insets(6, 110, 6, 110));
        loginMsgBox.setAlignment(Pos.CENTER);
       
        
		VBox vb = new VBox();
		vb.getChildren().addAll(loginMsgBox,h1,h2);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);
		vb.setPrefWidth(400);
		
		
		// Buttons 
		loginB = new Button("Login");
		loginB.setFont(Font.font("OCR A Extended",15));
		loginB.setTextFill(Color.WHITE);
		loginB.setBackground(new Background(new BackgroundFill(fontColor, new CornerRadii(4), loginB.getInsets())));
	
		loginB.setOnAction(handle);
		
		pane.getStylesheets().add("css/style.css"); 


		loginB.setId("logB");
	
		forgotPassword.setText("Copyright");
		forgotPassword.setFont(Font.font("OCR A Extended",15));
		forgotPassword.setOnAction(e->{
			Desktop d = Desktop.getDesktop();
			try {
				URI url = new URI("https://www.linkedin.com/in/kevintervoli/");
				d.browse(url);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox bot = new VBox(30,loginB,forgotPassword);
		bot.setAlignment(Pos.TOP_CENTER);
		bot.setPrefSize(400, 264);
		bot.setPadding(new Insets(15,0,0,0));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(headBox);
		borderPane.setCenter(vb);
		borderPane.setBottom(bot);
		pane.setBackground(bckgStyle);
		pane.getChildren().addAll(borderPane);
		AnchorPane.setTopAnchor(borderPane, .0);
		AnchorPane.setBottomAnchor(borderPane, .0);
		AnchorPane.setLeftAnchor(borderPane, .0);
		AnchorPane.setRightAnchor(borderPane, .0);
		
		pane.setPrefSize(424, 606);
		pane.requestFocus();
		pane.setOnMousePressed(e->pane.requestFocus());
		
		
		return pane;
	}	
}
