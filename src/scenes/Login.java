package scenes;

import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils; 
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import database.Queries;
import sprite.Player;

public class Login extends Menu{
	
	Label label_attempt;
	
	public Login(int screenW, int screenH, Stage window){
		super(screenW, screenH, window);
		//create elements for the main menu scene and then add them to the scene
		label_attempt = new Label("Please Login:");
		label_attempt.setFont(new Font("Arial", 20));
		
		Label label_name = new Label("Username:");
		TextField text_name = new TextField();
		
		Label label_password = new Label("Password:");
		TextField text_password = new TextField();
		
		Button button_login = createButton("Login");
		button_login.setOnAction(e -> loginAttempt(text_name.getCharacters().toString(), text_password.getCharacters().toString()));
		
		Button button_back = createButton("Back");
		button_back.setOnAction(e -> {window.setScene(new StartMenu(width, height, window).getScene());});
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_attempt, label_name, text_name, label_password, text_password, button_login, button_back);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
				
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}
	
	public void loginAttempt(String username, String password) {
		if(Queries.login(username, password) != 0) {
			label_attempt.setText("Login Successful");
			Player.currentUsername = username;
			window.setScene(new MainMenu(width, height, window).getScene());
		} else {
			label_attempt.setText("Failed to Login");
		}
	}
}