package main;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import scenes.MainMenu;
import scenes.SettingsMenu;

public class Main extends Application {

	Stage window;
	Scene mainMenu, settingsMenu;
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		mainMenu = new MainMenu(800, 600, window).getScene();
		settingsMenu = new SettingsMenu(800, 600, window).getScene();
		
		//set the first scene for the window as well as setting its title
		window.setScene(mainMenu);
		window.setTitle("Platform the Platformer");
		window.show();
	}

}