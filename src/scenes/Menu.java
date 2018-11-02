package scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Menu {

	Scene s;
	Stage window;
	int height;
	int width;
	
	public Menu(int screenW, int screenH, Stage primaryStage) {
		window = primaryStage;
		height = screenH;
		width = screenW;
		
		VBox VB = new VBox();
		VB.getChildren().add(new Label("Scene has not been implemented yet"));
		
		Button b = createButton("Back to Main Menu");
		b.setOnAction(e -> {window.setScene(new MainMenu(width, height, window).getScene());});
		VB.getChildren().add(b);
		
		GridPane GP = new GridPane();
		GP.getChildren().add(VB);
		GP.setAlignment(Pos.CENTER);
		
		s = new Scene(GP, width, height);
	}
	
	public Scene getScene(){
		return s;
	}
	
	protected Button createButton(String text){
		Button b = new Button(text);
		b.setPrefSize(150, 50);
		
		return b;
	}
	
	public void exitApp(){
		window.close();
	}
}
