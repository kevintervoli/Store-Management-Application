package Login_GUI;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import Scenes.CashierScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestLogin extends Application{

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		launch(args);
	}
	@SuppressWarnings("unused")
	@Override
	public void start(Stage arg0){
		Login login = new Login(arg0);
		new CashierScene(arg0);
		Scene scene = new Scene(CashierScene.createCashScene());
		arg0.getIcons().add(new Image(new File("Images/icon.png").toURI().toString()));
		arg0.setScene(scene);
		arg0.show();
	}
}