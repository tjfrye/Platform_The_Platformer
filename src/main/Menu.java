package main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

	Stage window;
	Scene mainMenu, scene2;
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		//create elements for the main menu scene and then add them to the scene
		Label label_title = new Label("Platform the Platformer");
		Button button_start = new Button("Start");
		Button button_settings = new Button("Settings");
		Button button_highscores = new Button("Highscores");
		Button button_exit = new Button("Exit");
		VBox layout1 = new VBox(5);
		layout1.getChildren().addAll(label_title, button_start, button_settings, button_highscores, button_exit);
		
		//create and set background image for the main menu
		Image mm_bg_img = SwingFXUtils.toFXImage(ImageIO.read(getClass().getResource("/backgrounds/mm_background.png")), null);
		BackgroundImage mainMenuBackground = new BackgroundImage(mm_bg_img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		layout1.setBackground(new Background(mainMenuBackground));
		
		mainMenu = new Scene(layout1, 800, 600);
		
		//set the first scene for the window as well as setting its title
		window.setScene(mainMenu);
		window.setTitle("Platform the Platformer");
		window.show();
	}

}
