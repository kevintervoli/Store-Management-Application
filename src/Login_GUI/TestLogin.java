package Login_GUI;


import java.io.FileNotFoundException;
import java.io.IOException;

import Scenes.Create_Bill;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLogin extends Application{

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		launch(args);
	}
	@Override
	public void start(Stage arg0){
		Login login = new Login(arg0);
		Scene scene = new Scene(Login.login());
		arg0.setScene(scene);
		arg0.show();
	}
}