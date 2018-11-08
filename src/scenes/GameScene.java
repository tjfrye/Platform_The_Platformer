package scenes;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sprite.Sprite;

public class GameScene {

	Scene scene;
	
	public GameScene(int screenW, int screenH, Stage primaryStage) {
		
		Group root = new Group();
		scene = new Scene(root);
		
		Canvas canvas = new Canvas(screenW, screenH);
		root.getChildren().add(canvas);
		
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> released = new ArrayList<String>();
		
		scene.setOnKeyPressed( new EventHandler<KeyEvent>() { 
			public void handle(KeyEvent e){
				String code = e.getCode().toString();
				
				if(!input.contains(code)){
					input.add(code);
				}
			}
		});
		
		scene.setOnKeyReleased( new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e){
				String code = e.getCode().toString();
				input.remove(code);
				
				released.add(code);
			}
		});
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, screenW, screenH);
		gc.fillRoundRect(0, screenH - 20, screenW, 20, 10, 10);
		
		Sprite player = new Sprite();
		player.setImage(new Image("file:resources/sprites/TEST.png"));
		player.render(gc);
		
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				handleKeyboardInput(input);
				renderScreen();
			}
			
			private void renderScreen() {
				gc.clearRect(0, 0, screenW, screenH);
				
				player.render(gc);
				
			}

			private void handleKeyboardInput(ArrayList<String> input) {
				
				double elapsedTime = 0.01;
				//held keys
				if(input.contains("LEFT")){
					player.addVelocity(-10, 0);
				}
				else if(input.contains("RIGHT")){
					player.addVelocity(10, 0);
				}
				else{
					player.addVelocity(player.getVelocity_X() * -1, 0);
				}
				
				//pressed and released keys
				if(released.contains("SPACE")){
					player.addVelocity(0, -300);
					released.remove("SPACE");
				}
				else{
					if(player.getPosition_Y() < (screenH - player.getHeight())){
						player.addVelocity(0, 5);
					}
					else if(player.getPosition_Y() > (screenH - player.getHeight())){
						player.setPosition(player.getPosition_X(), (screenH - player.getHeight()));
						player.addVelocity(player.getVelocity_X(), 0);
					}
				}
				
				player.update(elapsedTime);
			}
		}.start();
	}
	
	public void outputInput(ArrayList<String> input){
		for(String s : input){
			System.out.println(s);
		}
	}
	
	public Scene getScene(){
		return scene;
	}

}
