package main;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import scenes.GameScene;
import scenes.MainMenu;
import scenes.StartMenu;
import database.Queries;

public class Main extends Application {

	Stage window;
	Scene login;
	
	public static void main(String[] args){
		Queries.connect();
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		
		// THIS IS WHAT SHOULD BE STARTED
		//login = new StartMenu(800, 600, window).getScene();
		
		login = new MainMenu(800, 600, window).getScene();
		
		//set the first scene for the window as well as setting its title
		window.setScene(login);
		window.setTitle("Platform the Platformer");
		window.show();
	}
	
}