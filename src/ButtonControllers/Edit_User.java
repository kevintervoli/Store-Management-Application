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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
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

public class Edit_User {
	protected static TableView<User> editableTable = new TableView<User>();

	public static StackPane userEdit() throws FileNotFoundException, ClassNotFoundException, IOException {
		Color btnColor = Color.web("#053C5E");
		Color color = Color.web("#FFFFFF");
		Color tfCol = Color.web("#EEEEEE");
		Border textFBorder = new Border(
				new BorderStroke(btnColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 2, 0)));
		Background bckgStyle = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
		Background tfBack = new Background(new BackgroundFill(tfCol, CornerRadii.EMPTY, Insets.EMPTY));
		StackPane pane = new StackPane();

		Label currentUs = new Label("Current Username");
		Label currentPassword = new Label("Current Password");
		Label currentStatus = new Label("Current Status");
		Label newUs = new Label("New Username");
		Label newPass = new Label("New Password");
		Label newStatus = new Label("New Status");

		TextField currUsT = new TextField();
		TextField currPassT = new TextField();
		TextField currStatusT = new TextField();
		TextField newUsT = new TextField();
		TextField newPassT = new TextField();
		TextField newStatusT = new TextField();

		VBox labelBox = new VBox(currentUs, newUs, currentPassword, newPass, currentStatus, newStatus);
		labelBox.setSpacing(15);

		currentUs.setFont(Font.font("OCR A Extended", 16));
		currentUs.setPrefSize(170, 40);

		newUs.setFont(Font.font("OCR A Extended", 16));
		newUs.setPrefSize(170, 40);

		currentPassword.setFont(Font.font("OCR A Extended", 16));
		currentPassword.setPrefSize(170, 40);

		newPass.setFont(Font.font("OCR A Extended", 16));
		newPass.setPrefSize(170, 40);

		currentStatus.setFont(Font.font("OCR A Extended", 16));
		currentStatus.setPrefSize(170, 40);

		newStatus.setFont(Font.font("OCR A Extended", 16));
		newStatus.setPrefSize(170, 40);

		VBox fieldsBox = new VBox(currUsT, newUsT, currPassT, newPassT, currStatusT, newStatusT);
		fieldsBox.setSpacing(25);

		currUsT.setFont(Font.font("OCR A Extended", 12));
		currUsT.setBorder(textFBorder);
		currUsT.setBackground(tfBack);
		currUsT.setPrefSize(200, 30);

		newUsT.setFont(Font.font("OCR A Extended", 12));
		newUsT.setBorder(textFBorder);
		newUsT.setBackground(tfBack);
		newUsT.setPrefSize(200, 30);

		currPassT.setFont(Font.font("OCR A Extended", 12));
		currPassT.setBorder(textFBorder);
		currPassT.setBackground(tfBack);
		currPassT.setPrefSize(200, 30);

		newPassT.setFont(Font.font("OCR A Extended", 12));
		newPassT.setBorder(textFBorder);
		newPassT.setBackground(tfBack);
		newPassT.setPrefSize(200, 30);

		currStatusT.setFont(Font.font("OCR A Extended", 12));
		currStatusT.setBorder(textFBorder);
		currStatusT.setBackground(tfBack);
		currStatusT.setPrefSize(200, 30);

		newStatusT.setFont(Font.font("OCR A Extended", 12));
		newStatusT.setBorder(textFBorder);
		newStatusT.setBackground(tfBack);
		newStatusT.setPrefSize(200, 30);

		HBox allBox = new HBox(labelBox, fieldsBox);
		allBox.setAlignment(Pos.CENTER);
		allBox.setSpacing(45);
		Button edit = new Button("Edit Users");
		edit.setId("logB");
		edit.setOnAction(e -> {
			try {
				editableTable.getItems().clear();
				editUsers(currUsT.getText(), newUsT.getText(), currStatusT.getText(), newStatusT.getText(),
						currPassT.getText(), newPassT.getText());
				editableTable.setItems(fillEditTable());
				editableTable.refresh();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox mix = new VBox();
		mix.getChildren().addAll(allBox, edit);
		mix.setAlignment(Pos.TOP_CENTER);
		StackPane tablePane = new StackPane();
		tablePane.getChildren().add(createTable());
		tablePane.setAlignment(Pos.TOP_RIGHT);
		tablePane.setMinWidth(880);
		;
		tablePane.setMinHeight(700);
		FlowPane pan = new FlowPane();
		pan.getChildren().addAll(mix, tablePane);
		pan.setHgap(25);
		pan.setAlignment(Pos.TOP_CENTER);
		pane.getChildren().add(pan);
		return pane;
	}

	@SuppressWarnings("unused")
	protected static void editUsers(String username, String newUsername, String status, String newStatus,
			String password, String newPassword) throws FileNotFoundException, IOException, ClassNotFoundException {
		String employeName = "src/Database/employe.dat";
		ObjectInputStream secondinp = new ObjectInputStream(new FileInputStream(employeName));
		ArrayList<Employe> employe = (ArrayList<Employe>) secondinp.readObject();
		
		String fileName = "src/Database/firstUsers.dat";
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) inp.readObject();
		int index = -1;
		int index2=-1;
		for(int i=0;i<employe.size();i++) {
			if(employe.get(i).getUsername().equals(username)){
				index2=i;
			}
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				index = i;
			}
		}
		if(index==-1 || index2 == -1) {
			Alert fail = new Alert(AlertType.ERROR);
			fail.setHeaderText("FAIL");
			fail.setContentText("CANT FIND USER !");
			fail.showAndWait();
		}
		else {
			if(newUsername.isEmpty() && password.isEmpty() && status.isEmpty()) {
				Alert fail = new Alert(AlertType.ERROR);
				fail.setHeaderText("FAIL");
				fail.setContentText("EMPTY FIELDS !");
				fail.showAndWait();
			}
			else { 
				if(newUsername !="" && newPassword !="" && newStatus !="") {
					users.get(index).setUserStatus(Integer.parseInt(newStatus));
					users.get(index).setPassword(newPassword);
					users.get(index).setUsername(newUsername);
					
					employe.get(index2).setUsername(newUsername);
				}
				else {
					if (username.isEmpty()) {
						Alert fail = new Alert(AlertType.ERROR);
						fail.setHeaderText("FAIL");
						fail.setContentText("USERNAME CAN NOT BE EMPTY !");
						fail.showAndWait();
					} else {
						if (newUsername.isEmpty()) {
							if (newPassword.isEmpty() && newStatus.isEmpty()) {
								Alert fail = new Alert(AlertType.ERROR);
								fail.setHeaderText("FAIL");
								fail.setContentText("PASSWORD OR STATUS CAN NOT BE EMTPY");
								fail.showAndWait();
							} else {
								if (password.isEmpty() || newPassword.isEmpty()) {
									users.get(index).setUserStatus(Integer.parseInt(newStatus));
								} else if (newStatus.isEmpty() || status.isEmpty()) {
									users.get(index).setPassword(newPassword);
								} else {
									users.get(index).setPassword(newPassword);
									users.get(index).setUserStatus(Integer.parseInt(newStatus));
								}
							}
						} else {
							users.get(index).setUsername(newUsername);
							employe.get(index2).setUsername(newUsername);
						}

					}
				}
			}
		}
		ObjectOutputStream outstream = new ObjectOutputStream(new FileOutputStream(fileName));
		outstream.writeObject(users);
		outstream.close();
		
		ObjectOutputStream outstream2 = new ObjectOutputStream(new FileOutputStream(employeName));
		outstream2.writeObject(employe);
		outstream2.close();
		
		
	}

	protected static TableView<User> createTable() throws FileNotFoundException, ClassNotFoundException, IOException {
		editableTable = new TableView<User>();
		editableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		editableTable.setEditable(true);
		editableTable.getStylesheets().add("css/style.css");
		editableTable.setId(".table-view");
		editableTable.setMinHeight(700);
		TableColumn<User, String> username = new TableColumn<>("USERNAME");
		username.setMinWidth(200);
		username.setCellValueFactory(new PropertyValueFactory<>("Username"));

		TableColumn<User, String> password = new TableColumn<>("PASSWORD");
		password.setMinWidth(200);
		password.setCellValueFactory(new PropertyValueFactory<>("Password"));

		TableColumn<User, Integer> userStatus = new TableColumn<>("USER STATUS");
		userStatus.setMinWidth(200);
		userStatus.setCellValueFactory(new PropertyValueFactory<>("userStatus"));

		editableTable.getColumns().addAll(username, password, userStatus);
		editableTable.setItems(fillEditTable());

		return editableTable;
	}

	@SuppressWarnings({ "unchecked", "resource" })
	protected static ObservableList<User> fillEditTable()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		String fileName = "src/Database/firstUsers.dat";
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(fileName));
		ArrayList<User> users = (ArrayList<User>) inp.readObject();
		ObservableList tableUsers = FXCollections.observableArrayList();
		for (int i = 0; i < users.size(); i++) {
			tableUsers.add(users.get(i));
		}
		return tableUsers;
	}

}
