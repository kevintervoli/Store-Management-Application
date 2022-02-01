package Scenes;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Create_Bill{
	protected static Label albumName;
	protected static Label quantity;
	protected static Label shipping;
	protected static TextField nam;
	protected static TextField quan;
	protected static TextField shippingfield;
	protected static Button createButton,cancel;
	public Create_Bill() {
		
	}
	public static BorderPane billWindow() {
		BorderPane pane = new BorderPane();
		Color fontColor= Color.web("#053C5E"); 
		Color backGround = Color.web("#FFFFFF");
		Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Text header = new Text("Welcome to the create bill window !");
		HBox headB = new HBox(header);
		pane.getStylesheets().add("css/style.css");
		headB.setAlignment(Pos.CENTER);
		headB.setSpacing(10);
		
		albumName = new Label("Album Name :");
		quantity = new Label("Quantity : ");
		shipping = new Label("Shipping adress : ");
		albumName.setFont(Font.font("OCR A Extended",17));
		quantity.setFont(Font.font("OCR A Extended",17));
		shipping.setFont(Font.font("OCR A Extended",17));
		
		VBox first = new VBox();
		first.getChildren().addAll(albumName,quantity,shipping);
		
		nam = new TextField();
		nam.setPromptText("Enter album name ");
		nam.setBorder(textFBorder);
		quan= new TextField();
		quan.setPromptText("Enter quantity ");
		quan.setBorder(textFBorder);
		shippingfield = new TextField();
		shippingfield.setPromptText("Enter shipping adress ");
		
		VBox second = new VBox();
		second.getChildren().addAll(nam,quan,shippingfield);
		
		createButton = new Button("Create Bill");
		createButton.setId("logB");
		cancel = new Button("Cancel");
		cancel.setId("logB");
		StackPane btnB = new StackPane(createButton,cancel);
//		btnB.setSpacing(10);
		btnB.setAlignment(Pos.CENTER_LEFT);
		
		HBox centerB = new HBox(first,second);
		VBox allB = new VBox(centerB,btnB);
	    pane.setTop(headB); 
		pane.setCenter(allB);
		pane.setPrefSize(250, 150);
		return pane;
	}
}
