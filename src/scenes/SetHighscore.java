package scenes;

import database.Queries;
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
import sprite.Player;

public class SetHighscore extends Menu {
	
	Label label_highscore;

	public SetHighscore(int screenW, int screenH, Stage primaryStage) {
		super(screenW, screenH, primaryStage);
		
		label_highscore = new Label("New Highscore: " + Player.score + "!!!");
		label_highscore.setFont(new Font("Arial", 20));
		
		Button button_post = createButton("Post Highscore");
		button_post.setOnAction(e -> setHighscore());
		
		Button button_back = createButton("Back to Main Menu");
		button_back.setOnAction(e -> {window.setScene(new MainMenu(width, height, window).getScene());});
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_highscore, button_post, button_back);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
				
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}
	
	public void setHighscore() {
		if(Queries.updateHighscore(Player.currentUsername, Player.score) != 0) {
			label_highscore.setText("Highscore Post Successful");
			window.setScene(new MainMenu(width, height, window).getScene());
		} else {
			label_highscore.setText("Failed to Post Highscore");
		}
	}
}
