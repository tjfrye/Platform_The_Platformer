package main;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import scenes.GameScene;
import scenes.StartMenu;

public class Main extends Application {

	Stage window;
	Scene login, settingsMenu;
	
	public static void main(String[] args){
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		login = new StartMenu(800, 600, window).getScene();
		
		//set the first scene for the window as well as setting its title
		window.setScene(login);
		window.setTitle("Platform the Platformer");
		window.show();
	}

}