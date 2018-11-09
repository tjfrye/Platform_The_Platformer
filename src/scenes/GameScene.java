package scenes;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sprite.Platform;
import sprite.Player;

public class GameScene {

	Scene scene;
	long lastNanoTime;
	
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
		
		Player player = new Player();
		
		ArrayList<Platform> platformsList = initPlatforms();
		
		player.setImage(new Image("file:resources/sprites/TEST.png"));
		player.render(gc);
		lastNanoTime = System.nanoTime();
		
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				handleKeyboardInput(input, now);
				detectCollision();
				renderScreen();
			}
			
			private void detectCollision() {
				for(Platform p : platformsList){
					if(player.intersects(p)){
						System.out.println("collision");
						player.addVelocity_Y(player.getVelocity_Y() * -1);
					}
				}
				
			}

			private void renderScreen() {
				gc.clearRect(0, 0, screenW, screenH);
				
				player.render(gc);
				
				for(Platform p : platformsList){
					p.render(gc);
				}
				
			}

			private void handleKeyboardInput(ArrayList<String> input, long currentNanoTime) {
				
				double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
				lastNanoTime = currentNanoTime;
				//held keys
				if(input.contains("LEFT")){
					player.addVelocity_X(-10);
				}
				else if(input.contains("RIGHT")){
					player.addVelocity_X(10);
				}
				else{
					//slow down speed
					player.addVelocity_X(player.getVelocity_X() * -0.05);
				}
				
				//pressed and released keys
				if(released.contains("SPACE")){
					player.addVelocity_Y(-300);
					released.remove("SPACE");
				}
				else{
					if(player.getPosition_Y() < (screenH - player.getHeight())){
						//falling speed
						player.addVelocity_Y( 10);
					}
					else if(player.getPosition_Y() > (screenH - player.getHeight())){
						player.setPosition(player.getPosition_X(), (screenH - player.getHeight()));
						player.addVelocity_Y(player.getVelocity_Y() * -1);
					}
				}
				
				player.update(elapsedTime);
			}
		}.start();
	}
	
	private ArrayList<Platform> initPlatforms() {
		ArrayList<Platform> platforms = new ArrayList<Platform>();
		Random r = new Random();
		
		for(int i = 0; i < 6; i++){
			platforms.add(new Platform(i * 100, r.nextInt(400) + 200));
		}
		
		return platforms;
	}

	public Scene getScene(){
		return scene;
	}

}
