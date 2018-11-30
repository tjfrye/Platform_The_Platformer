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
import javafx.stage.Stage;
import sprite.Player;

public class CharacterSelect extends Menu {
	
	public CharacterSelect(int screenW, int screenH, Stage window){
		super(screenW, screenH, window);
		
		Label label_Title = new Label("Select a character");
		
		Button button_evan = createButton("Easy Evan");
		button_evan.setOnAction(e -> {window.setScene(new GameScene(width, height, window, new Player("Easy Evan")).getScene());});
		
		Button button_matt = createButton("Medium Matt");
		button_matt.setOnAction(e -> {window.setScene(new GameScene(width, height, window, new Player("Medium Matt")).getScene());});
	
		Button button_hank = createButton("Hard Hank");
		button_hank.setOnAction(e -> {window.setScene(new GameScene(width, height, window, new Player("Hard Hank")).getScene());});
		
		GridPane GP = new GridPane();
		VBox VB = new VBox(10);
		VB.getChildren().addAll(label_Title, button_evan, button_matt, button_hank);
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
		
		//create and set background image for the main menu
		Image mm_bg_img = new Image("file:resources/backgrounds/mm_background.png");
		GP.setBackground(new Background(new BackgroundImage (mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		
		s = new Scene(GP, screenW, screenH);
	}

}
