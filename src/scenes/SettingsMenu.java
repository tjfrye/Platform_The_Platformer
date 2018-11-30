package scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import sprite.Player;

public class SettingsMenu extends Menu{

	public SettingsMenu(int screenW, int screenH, Stage window) {
		super(screenW, screenH, window);
		
		Label label_difficulty = new Label("Set Difficulty:");
		label_difficulty.setFont(new Font("Arial", 20));
		
		Button button_easy = createButton("Easy");
		button_easy.setOnAction(e -> setDifficulty(0));
		
		Button button_medium = createButton("Medium");
		button_easy.setOnAction(e -> setDifficulty(1));
		
		Button button_hard = createButton("Hard");
		button_easy.setOnAction(e -> setDifficulty(2));
		
		Button button_back = createButton("Back");
		button_back.setOnAction(e -> {window.setScene(new MainMenu(width, height, window).getScene());});
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_difficulty, button_easy, button_medium, button_hard, button_back);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
				
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}
	
	public void setDifficulty(int mod) {
		Player.difficulty = mod;
	}

}
