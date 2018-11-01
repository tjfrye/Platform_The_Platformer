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
import scenes.MainMenu;

public class Menu extends Application {

	Stage window;
	Scene mainMenu, scene2;
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		mainMenu = new MainMenu(800, 600).getScene();
		
		//set the first scene for the window as well as setting its title
		window.setScene(mainMenu);
		window.setTitle("Platform the Platformer");
		window.show();
	}

}
