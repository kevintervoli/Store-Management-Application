package ButtonControllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import User_Profiles.Employe;
import User_Profiles.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserManagement {
	Label name ;
	Label surname;
	Label username;
	Label password;
	Label status;
	public UserManagement() {
		UserManagement.createWindow();
	}

	public static AnchorPane createWindow() {
		Color fontColor= Color.web("#053C5E"); 
		Color backGround = Color.web("#FFFFFF");
		//Style the background of login 
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		//Style the borders of the text fields
	    Border textFBorder = new Border(new BorderStroke(fontColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		AnchorPane pane = new AnchorPane();
		Label name = new Label("NAME : ");
		Label surname = new Label("SURNAME : ");
		Label username = new Label("USERNAME : ");
		Label password = new Label("PASSWORD : ");
		Label status = new Label("STATUS : ");
		VBox labels = new VBox();
		labels.getChildren().addAll(name,surname,username,password,status);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(10);
		VBox forVSpace = new VBox();
		VBox forVSpace2 = new VBox();
		HBox allVbo = new HBox(forVSpace,forVSpace2,labels);
		allVbo.setAlignment(Pos.CENTER);
		allVbo.setSpacing(10);
		
		TextField namef = new TextField();
		TextField surnamef = new TextField();
		TextField usernamef = new TextField();
		PasswordField passwordf = new PasswordField();
		TextField statusf = new TextField();

		
		namef.setFont(Font.font("OCR A Extended",12));
		namef.setPromptText("Enter Username !");
		namef.setBorder(textFBorder);
		namef.setBackground(bckgStyle);
		namef.setPrefSize(200, 30);
		
		surnamef.setFont(Font.font("OCR A Extended",12));
		surnamef.setPromptText("Enter Username !");
		surnamef.setBorder(textFBorder);
		surnamef.setBackground(bckgStyle);
		surnamef.setPrefSize(200, 30);
		
		usernamef.setFont(Font.font("OCR A Extended",12));
		usernamef.setPromptText("Enter Username !");
		usernamef.setBorder(textFBorder);
		usernamef.setBackground(bckgStyle);
		usernamef.setPrefSize(200, 30);
		
		passwordf.setFont(Font.font("OCR A Extended",12));
		passwordf.setPromptText("Enter Username !");
		passwordf.setBorder(textFBorder);
		passwordf.setBackground(bckgStyle);
		passwordf.setPrefSize(200, 30);
		
		statusf.setFont(Font.font("OCR A Extended",12));
		statusf.setPromptText("Enter Username !");
		statusf.setBorder(textFBorder);
		statusf.setBackground(bckgStyle);
		statusf.setPrefSize(200, 30);
		
		
		name.setFont(Font.font("OCR A Extended",17));
		name.setPrefSize(150, 30);
		
		surname.setFont(Font.font("OCR A Extended",17));
		surname.setPrefSize(150, 30);
		
		username.setFont(Font.font("OCR A Extended",17));
		username.setPrefSize(150, 30);
		
		password.setFont(Font.font("OCR A Extended",17));
		password.setPrefSize(150, 30);
		
		status.setFont(Font.font("OCR A Extended",17));
		status.setPrefSize(150, 30);

		VBox tfield = new VBox();
		tfield.getChildren().addAll(namef,surnamef,usernamef,passwordf,statusf);
		tfield.setAlignment(Pos.CENTER);
		tfield.setSpacing(10);
		HBox hb = new HBox();
		hb.getChildren().addAll(labels,tfield);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);

		Button addUser = new Button("ADD USER");
		
		addUser.setFont(Font.font("OCR A Extended",15));
		addUser.setTextFill(Color.WHITE);
		addUser.setBackground(new Background(new BackgroundFill(fontColor, new CornerRadii(4), addUser.getInsets())));
		pane.getStylesheets().add("css/style.css"); 
		addUser.setId("logB2");
		
		Button clear = new Button ("CANCEL");
		
		clear.setFont(Font.font("OCR A Extended",15));
		clear.setTextFill(Color.WHITE);
		clear.setBackground(new Background(new BackgroundFill(fontColor, new CornerRadii(4), addUser.getInsets())));
		pane.getStylesheets().add("css/style.css"); 
		clear.setId("logB2");
		
		Button removeUser = new Button("REMOVE USER");
		removeUser.setFont(Font.font("OCR A Extended",15));
		removeUser.setTextFill(Color.WHITE);
		removeUser.setBackground(new Background(new BackgroundFill(fontColor, new CornerRadii(4), addUser.getInsets()))); 
		removeUser.setId("logB2");
		HBox btns = new HBox(addUser,removeUser,clear);
		btns.setAlignment(Pos.CENTER);
		btns.setSpacing(15);
		removeUser.setOnAction(e->{
			String fileName = "src/Database/firstUsers.dat";
			ObjectInputStream inp = null;
			try {
				inp = new ObjectInputStream(new FileInputStream(fileName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<User> users = null;
			try {
				users =  (ArrayList<User>) inp.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int cnt=0;
			for(int i=0;i<users.size();i++) {
				if(usernamef.getText().equals(users.get(i).getUsername())) {
					cnt++;
					break;
				}
			}
			System.out.println(cnt);
			if(cnt==0) {
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
				Stage  st= new Stage();
				Label alertS =new Label("CAN'T FIND USER !");
				alertS.setFont(Font.font("OCR A Extended",17));
				alertS.setTextFill(Color.RED);
				StackPane alert = new StackPane();
				alert.setPrefSize(250, 75);
				alert.getChildren().add(alertS);
				Scene scene  = new Scene(alert);
				st.setScene(scene);
				st.show();
			}
			else {
				try {
					UserManagement.removeUser(usernamef.getText());
					UserManagement.removeEmploye(usernamef.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("User removed");
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			}
			
		});
		addUser.setOnAction(e->{
			String fileName = "src/Database/firstUsers.dat";
			ObjectInputStream inp = null;
			try {
				inp = new ObjectInputStream(new FileInputStream(fileName));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<User> use = null;
			try {
				use = (ArrayList<User>) inp.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(namef.getText().isEmpty() || surnamef.getText().isEmpty()|| usernamef.getText().isEmpty() || passwordf.getText().isEmpty() || statusf.getText().isEmpty()) {
				Stage  st= new Stage();
				Label alertS =new Label("CHECK EMPTY FIELDS !");
				alertS.setFont(Font.font("OCR A Extended",17));
				alertS.setTextFill(Color.RED);
				StackPane alert = new StackPane();
				alert.setPrefSize(250, 75);
				alert.getChildren().add(alertS);
				Scene scene  = new Scene(alert);
				st.setScene(scene);
				st.show();
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			}
			int cnt=0;
			for(int i=0;i<use.size();i++) {
				if(usernamef.getText().equals(use.get(i).getUsername())) {
					cnt++;
				}
			}
			if(cnt!=0) {
				Stage  st= new Stage();
				Label alertS =new Label("USER ALREADY EXISTS !");
				alertS.setFont(Font.font("OCR A Extended",17));
				alertS.setTextFill(Color.RED);
				StackPane alert = new StackPane();
				alert.setPrefSize(250, 75);
				alert.getChildren().add(alertS);
				Scene scene  = new Scene(alert);
				st.setScene(scene);
				st.show();
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			}
			else {
				try {
					UserManagement.addUser(namef.getText(),surnamef.getText(),usernamef.getText(),passwordf.getText(),Integer.parseInt(statusf.getText()));
					UserManagement.addEmploye(namef.getText(), surnamef.getText(), usernamef.getText());
				} catch (NumberFormatException | ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
			}
		});
		clear.setOnAction(e->{
			((Stage)(((Node)e.getSource()).getScene().getWindow())).close();
		});
		
        
		HBox forHSpace = new HBox();
		HBox forBotSpace = new HBox();
		VBox vbo = new VBox(forHSpace,btns,forBotSpace);
		vbo.setSpacing(10);
		vbo.setAlignment(Pos.CENTER);
		VBox.setVgrow(vbo, Priority.ALWAYS);
		BorderPane bp = new BorderPane();
		bp.setCenter(hb);
		bp.setBottom(vbo);
		pane.getChildren().add(bp);
		bp.setPrefSize(350, 200);
		AnchorPane.setTopAnchor(bp, .0);
		AnchorPane.setBottomAnchor(bp, .0);
		AnchorPane.setLeftAnchor(bp, .0);
		AnchorPane.setRightAnchor(bp, .0);
		pane.setBackground(bckgStyle);
		
		pane.requestFocus();
		pane.setOnMousePressed(e->pane.requestFocus());
		
		return pane;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	public static void addUser(String name,String surname,String username,String password,int status) throws IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		User newUser  = new User(name,surname,username,password,status);
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) inp.readObject();
		users.add(newUser);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(fileName));
		outstream.writeObject(users);
		outstream.close();
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void addEmploye(String name,String surname,String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		String employeName = "src/Database/employe.dat";
		ObjectInputStream secondinp = new ObjectInputStream(new FileInputStream(employeName));
		Employe newEmploye = new Employe(name,surname,username);
		ArrayList<Employe> employe= (ArrayList<Employe>) secondinp.readObject();
		employe.add(newEmploye);
		ObjectOutputStream soutstream = new ObjectOutputStream(new FileOutputStream(employeName));
		soutstream.writeObject(employe);
		soutstream.close();
	}
	@SuppressWarnings({ "unchecked", "resource" })
	public static void removeUser(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users =  (ArrayList<User>) inp.readObject();
		int index=-1;
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getUsername().equals(username)) {
				index=i;
				break;
			}
		}
		users.remove(index);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(fileName));
		outstream.writeObject(users);
		outstream.close();
		
	}
	@SuppressWarnings({ "resource", "unchecked" })
	public static void removeEmploye(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		String employeName = "src/Database/employe.dat";
		ObjectInputStream inp2 = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> employe= (ArrayList<Employe>) inp2.readObject();
		int index2=-1;
		for(int i=0;i<employe.size();i++) {
			if(employe.get(i).getUsername().equals(username)) {
				index2=i;
				break;
			}
		}
		employe.remove(index2);
		ObjectOutputStream outstream2 = new ObjectOutputStream(new FileOutputStream(employeName));
		outstream2.writeObject(employe);
		outstream2.close();
	}
}
