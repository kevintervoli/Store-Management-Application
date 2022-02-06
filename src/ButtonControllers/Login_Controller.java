package ButtonControllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import Login_GUI.Login;
import Scenes.AdminScene;
import Scenes.CashierScene;
import Scenes.ManagerScene;
import User_Profiles.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login_Controller extends Login implements EventHandler<ActionEvent> {
	public static String currentUser;
	public static int currentStatus;
	Color backGround = Color.web("#C4D6ED");

	public Login_Controller() {
		super(stage);
	}

	public Login_Controller(Stage primaryStage) {
		super(primaryStage);
		stage = primaryStage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(ActionEvent arg0) {
		String text = "";
		Color color = null;
		String fileName = "src/Database/firstUsers.dat";

		ObjectInputStream istream = null;
		try {
			istream = new ObjectInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<User> readUser = new ArrayList<User>();
//		Userz us = null;
		try {
			readUser = (ArrayList<User>) istream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			readUser.add(us);

		if (username.getText().isEmpty() && password.getText().isEmpty()) {
			text = "Check Credentials !";
			color = Color.RED;
		} else if (username.getText().isEmpty()) {
			text = "Enter Username !";
			color = Color.RED;
		} else if (password.getText().isEmpty()) {
			text = "Password is missing !";
			color = Color.RED;
		} else {
			for (int j = 0; j < readUser.size(); j++) {
				System.out.println(readUser.get(j).getUsername() + " " + readUser.get(j).getPassword());
				if (username.getText().equals(readUser.get(j).getUsername())) {
					if (password.getText().equals(readUser.get(j).getPassword())) {
						currentUser = username.getText();
						if (readUser.get(j).getUserStatus() == 0) {
							currentStatus = 0;
							text = "Login Successfully !";
							Stage newWindow = new Stage();
							new CashierScene(Login.stage);
							Scene scene = new Scene(CashierScene.createCashScene());
							newWindow.setScene(scene);
							newWindow.setTitle("Cashier");
							stage.hide();
							newWindow.show();
							newWindow.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
						} else if (readUser.get(j).getUserStatus() == 1) {
							currentStatus = 1;
							text = "Login Successfully !";
							Stage newWindow = new Stage();
							Scene scene = null;
							try {
								scene = new Scene(ManagerScene.createManagerScene());
							} catch (ClassNotFoundException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							newWindow.setScene(scene);
							newWindow.setTitle("Manager");
							stage.hide();
							newWindow.show();
							try {
								ManagerScene.checkLeftStock();
							} catch (ClassNotFoundException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							newWindow.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
						} else if (readUser.get(j).getUserStatus() == 2) {
							currentStatus = 2;
							text = "Login Successfully !";
							Stage newWindow = new Stage();
							Scene scene = new Scene(AdminScene.createAdminScene());
							newWindow.setScene(scene);
							newWindow.setTitle("Admin");
							stage.hide();
							newWindow.show();
							newWindow.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
						}
					} else {
						text = "Check Credentials !";
						color = Color.RED;
					}
				} else {
					text = "Check Credentials !";
					color = Color.RED;
				}
			}
		}
		msg.setText(text);
		msg.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		msg.setTextFill(color);
	}
}