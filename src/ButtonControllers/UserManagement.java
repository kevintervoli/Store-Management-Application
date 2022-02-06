package ButtonControllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import User_Profiles.Employe;
import User_Profiles.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UserManagement {
	Label name;
	Label surname;
	Label username;
	Label password;
	Label status;

	public UserManagement() {
		UserManagement.createWindow();
	}

	@SuppressWarnings("unchecked")
	public static AnchorPane createWindow() {
		Color fontColor = Color.web("#053C5E");
		Color backGround = Color.web("#FFFFFF");
		// Style the background of login
		Background bckgStyle = new Background(new BackgroundFill(backGround, CornerRadii.EMPTY, Insets.EMPTY));
		// Style the borders of the text fields
		Border textFBorder = new Border(
				new BorderStroke(fontColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		AnchorPane pane = new AnchorPane();
		Label name = new Label("NAME : ");
		Label surname = new Label("SURNAME : ");
		Label username = new Label("USERNAME : ");
		Label password = new Label("PASSWORD : ");
		Label status = new Label("STATUS : ");
		Label salary = new Label("SALARY : ");
		VBox labels = new VBox();
		labels.getChildren().addAll(name, surname, username, password, status, salary);
		labels.setAlignment(Pos.CENTER);
		labels.setSpacing(40);
		VBox forVSpace = new VBox();
		VBox forVSpace2 = new VBox();
		HBox allVbo = new HBox(forVSpace, forVSpace2, labels);
		allVbo.setAlignment(Pos.CENTER);
		allVbo.setSpacing(10);

		TextField namef = new TextField();
		TextField surnamef = new TextField();
		TextField usernamef = new TextField();
		PasswordField passwordf = new PasswordField();
		TextField statusf = new TextField();
		TextField salaryf = new TextField();

		namef.setFont(Font.font("OCR A Extended", 18));
		namef.setPromptText("Enter name!");
		namef.setBorder(textFBorder);
		namef.setBackground(bckgStyle);
		namef.setPrefSize(350, 40);

		surnamef.setFont(Font.font("OCR A Extended", 18));
		surnamef.setPromptText("Enter surname !");
		surnamef.setBorder(textFBorder);
		surnamef.setBackground(bckgStyle);
		surnamef.setPrefSize(350, 40);

		usernamef.setFont(Font.font("OCR A Extended", 18));
		usernamef.setPromptText("Enter Username !");
		usernamef.setBorder(textFBorder);
		usernamef.setBackground(bckgStyle);
		usernamef.setPrefSize(350, 40);

		passwordf.setFont(Font.font("OCR A Extended", 18));
		passwordf.setPromptText("Enter password !");
		passwordf.setBorder(textFBorder);
		passwordf.setBackground(bckgStyle);
		passwordf.setPrefSize(350, 40);

		statusf.setFont(Font.font("OCR A Extended", 18));
		statusf.setPromptText("0-Cashier  1-Manager  2-Admin ");
		statusf.setBorder(textFBorder);
		statusf.setBackground(bckgStyle);
		statusf.setPrefSize(350, 40);

		salaryf.setFont(Font.font("OCR A Extended", 18));
		salaryf.setPromptText("Enter salary !");
		salaryf.setBorder(textFBorder);
		salaryf.setBackground(bckgStyle);
		salaryf.setPrefSize(350, 40);

		name.setFont(Font.font("OCR A Extended", 25));
		name.setPrefSize(170, 40);

		surname.setFont(Font.font("OCR A Extended", 25));
		surname.setPrefSize(170, 40);

		username.setFont(Font.font("OCR A Extended", 25));
		username.setPrefSize(170, 40);

		password.setFont(Font.font("OCR A Extended", 25));
		password.setPrefSize(170, 40);

		status.setFont(Font.font("OCR A Extended", 25));
		status.setPrefSize(157, 40);

		salary.setFont(Font.font("OCR A Extended", 25));
		salary.setPrefSize(170, 40);
		VBox tfield = new VBox();
		tfield.getChildren().addAll(namef, surnamef, usernamef, passwordf, statusf, salaryf);
		tfield.setAlignment(Pos.CENTER);
		tfield.setSpacing(40);
		Button addUser = new Button("ADD USER");

		addUser.setFont(Font.font("OCR A Extended", 15));
		addUser.setId("logB");

		Button clear = new Button("CANCEL");

		clear.setFont(Font.font("OCR A Extended", 15));
		pane.getStylesheets().add("css/style.css");
		clear.setId("logB");

		Button removeUser = new Button("REMOVE USER");
		removeUser.setId("logB");
		HBox btns = new HBox(addUser, removeUser, clear);
		btns.setAlignment(Pos.CENTER);
		btns.setSpacing(15);
		removeUser.setOnAction(e -> {
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
				users = (ArrayList<User>) inp.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int cnt = 0;
			for (int i = 0; i < users.size(); i++) {
				if (usernamef.getText().equals(users.get(i).getUsername())) {
					cnt++;
					break;
				}
			}
			System.out.println(cnt);
			if (cnt == 0) {
				Alert fail = new Alert(AlertType.ERROR);
				fail.setHeaderText("FAIL");
				fail.setContentText("Cant find user !");
				fail.showAndWait();

			} else {
				try {
					UserManagement.removeUser(usernamef.getText());
					UserManagement.removeEmploye(usernamef.getText());
					namef.clear();
					surnamef.clear();
					usernamef.clear();
					passwordf.clear();
					statusf.clear();
					salaryf.clear();
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

			}

		});
		addUser.setOnAction(e -> {
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
			if (namef.getText().isEmpty() || surnamef.getText().isEmpty() || usernamef.getText().isEmpty()
					|| passwordf.getText().isEmpty() || statusf.getText().isEmpty() || salaryf.getText().isEmpty()) {
				Stage st = new Stage();
				Alert fail = new Alert(AlertType.WARNING);
				fail.setHeaderText("FAIL");
				fail.setContentText("Check empty fields !");
				fail.showAndWait();
				st.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
			} else {
				if (passwordf.getText().matches("([A-Za-z]{5}[0-9]{3})")) {
					int cnt = 0;
					for (int i = 0; i < use.size(); i++) {
						if (usernamef.getText().equals(use.get(i).getUsername())) {
							cnt++;
						}
					}
					if (cnt != 0) {
						Stage st = new Stage();
						Alert fail = new Alert(AlertType.ERROR);
						fail.setHeaderText("FAIL");
						fail.setContentText("User already exists ");
						fail.showAndWait();
						st.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
					} else {
						try {
							UserManagement.addUser(namef.getText(), surnamef.getText(), usernamef.getText(),
									passwordf.getText(), Integer.parseInt(statusf.getText()),
									Double.parseDouble(salaryf.getText()));
							UserManagement.addEmploye(namef.getText(), surnamef.getText(), usernamef.getText(),
									Integer.parseInt(salaryf.getText()));
							namef.clear();
							surnamef.clear();
							usernamef.clear();
							passwordf.clear();
							statusf.clear();
							salaryf.clear();
						} catch (NumberFormatException | ClassNotFoundException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					Stage st = new Stage();
					Alert fail = new Alert(AlertType.ERROR);
					fail.setHeaderText("FAIL");
					fail.setContentText("Password doesnt fullfil requirements");
					fail.showAndWait();
					st.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
				}
			}
		});
		clear.setOnAction(e -> {
			namef.clear();
			surnamef.clear();
			usernamef.clear();
			passwordf.clear();
			statusf.clear();
			salaryf.clear();
		});

		VBox vbo = new VBox(btns);
		FontAwesomeIconView ico = new FontAwesomeIconView(FontAwesomeIcon.USER, "100");
		HBox hb = new HBox();
		hb.getChildren().addAll(labels, tfield);
		hb.setAlignment(Pos.CENTER);
		pane.getStylesheets().add("css/style.css");

		VBox vboB = new VBox();

		HBox iconBox = new HBox();

		iconBox.getChildren().add(ico);
		iconBox.setAlignment(Pos.CENTER);
		vboB.getChildren().addAll(iconBox, hb, vbo);
		vboB.setSpacing(15);

		HBox hboB = new HBox();
		hboB.getChildren().add(vboB);
		hboB.setAlignment(Pos.CENTER);
		hboB.setSpacing(15);
		hboB.setPrefSize(200, 200);

		vbo.setSpacing(10);
		vbo.setAlignment(Pos.CENTER);
		VBox.setVgrow(vbo, Priority.ALWAYS);
		BorderPane bp = new BorderPane();
		bp.setCenter(hboB);
		pane.getChildren().add(bp);
		bp.setPrefSize(350, 200);
		AnchorPane.setTopAnchor(bp, .0);
		AnchorPane.setBottomAnchor(bp, .0);
		AnchorPane.setLeftAnchor(bp, .0);
		AnchorPane.setRightAnchor(bp, .0);
		pane.setBackground(bckgStyle);

		pane.requestFocus();
		pane.setOnMousePressed(e -> pane.requestFocus());

		return pane;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static void addUser(String name, String surname, String username, String password, int status, double salary)
			throws IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		User newUser = new User(name, surname, username, password, status, salary);
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) inp.readObject();
		users.add(newUser);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(fileName));
		outstream.writeObject(users);
		outstream.close();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static void addEmploye(String name, String surname, String username, double salary)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		String employeName = "src/Database/employe.dat";
		ObjectInputStream secondinp = new ObjectInputStream(new FileInputStream(employeName));
		Employe newEmploye = new Employe(name, surname, username, salary, 0);
		ArrayList<Employe> employe = (ArrayList<Employe>) secondinp.readObject();
		employe.add(newEmploye);
		ObjectOutputStream soutstream = new ObjectOutputStream(new FileOutputStream(employeName));
		soutstream.writeObject(employe);
		soutstream.close();
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static void removeUser(String username) throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) inp.readObject();
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				index = i;
				break;
			}
		}
		users.remove(index);
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(fileName));
		outstream.writeObject(users);
		outstream.close();

	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static void removeEmploye(String username)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		String employeName = "src/Database/employe.dat";
		ObjectInputStream inp2 = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> employe = (ArrayList<Employe>) inp2.readObject();
		int index2 = -1;
		for (int i = 0; i < employe.size(); i++) {
			if (employe.get(i).getUsername().equals(username)) {
				index2 = i;
				break;
			}
		}
		employe.remove(index2);
		ObjectOutputStream outstream2 = new ObjectOutputStream(new FileOutputStream(employeName));
		outstream2.writeObject(employe);
		outstream2.close();
	}
}