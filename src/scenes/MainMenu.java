package scenes;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import sprite.Player;

public class MainMenu extends Menu{
	
	public MainMenu(int screenW, int screenH, Stage window){
		super(screenW, screenH, window);
		//create elements for the main menu scene and then add them to the scene
		Label label_name = new Label("Currently Logged in as " + Player.currentUsername + "");
		
		Button button_start = createButton("Start");
		button_start.setOnAction(e -> {window.setScene(new CharacterSelect(width, height, window).getScene());});
		
		Button button_settings = createButton("Settings");
		button_settings.setOnAction(e -> {window.setScene(new SettingsMenu(width, height, window).getScene());});
		
		Button button_highscores = createButton("Highscores");
		button_highscores.setOnAction(e -> {window.setScene(new HighscoresMenu(width, height, window).getScene());});
		
		Button button_exit = createButton("Exit");
		button_exit.setOnAction(e -> exitApp());
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_name, button_start, button_settings, button_highscores, button_exit);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
				
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}
}
