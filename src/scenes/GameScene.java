package scenes;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import database.Queries;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sprite.Platform;
import sprite.Player;

public class GameScene {

	Scene scene;
	long lastNanoTime;
	Random r = new Random();
	int score = 0;
	Label scoreLabel;
	int speedIncreases = 0;
	
	public GameScene(int screenW, int screenH, Stage primaryStage, Player p) {
		
		Group root = new Group();
		scene = new Scene(root);
		scoreLabel = new Label("Score: " + score);
		scoreLabel.setFont(new Font("Arial", 32));
		root.getChildren().add(scoreLabel);
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
		
		Player player;
		if(p != null){
			player = p;
		}
		else{
			player = new Player();
			player.setImage(new Image("file:resources/sprites/TEST.png"));
		}
		
		ArrayList<Platform> platformsList = initPlatforms();
		

		player.render(gc);
		lastNanoTime = System.nanoTime();
		
		playMusic("resources/music/Music1.wav", true);
		
		new AnimationTimer(){
			@Override
			public void handle(long now) {
				handleKeyboardInput(input, now);
				detectCollision();
				renderScreen();
				
			}

			private void renderScreen() {
				gc.clearRect(0, 0, screenW, screenH);
				
				player.render(gc);
				
				for(Platform p : platformsList){
					p.render(gc);
				}
				
			}

			private void handleKeyboardInput(ArrayList<String> input, long currentNanoTime) {
				boolean collision = detectCollision();
				
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
					player.jump();
					released.remove("SPACE");

				}
				else if(!collision){
					//player hit bottom of screen
					if(player.getPosition_Y() > (screenH - player.getHeight() + 20)){
						Player.score = score;
						if(Queries.getHighscore(Player.currentUsername) < Player.score) {
							primaryStage.setScene(new SetHighscore(screenW, screenH, primaryStage).getScene());
						} else {
							primaryStage.setScene(new YouDiedMenu(screenW, screenH, primaryStage).getScene());

						}
						//stops game loop
						playMusic("resources/music/falling.wav", false);
						this.stop();
					}
				}
				player.update(elapsedTime);
				
				for(Platform p : platformsList){
					if(p.update()){
						p.setPosition(screenW, r.nextInt(400) + 200);
						score = score + 1;
						scoreLabel.setText("Score: " + score);
						
						p.setPlatformSpeed(p.getPlatformSpeed() * 1.15);
					}
				}
			}

			private boolean detectCollision() {
				
				for(Platform p : platformsList){
					if(player.intersects(p)){
						//on platform
						if(player.getPosition_X() + player.getWidth() > p.getPosition_X() && player.getPosition_Y() + player.getHeight() > p.getPosition_Y()){
							player.addVelocity_Y((player.getVelocity_Y() * -1) - 10);
							player.setOnPlatform(true, p.getPosition_Y());
							return true;
							
						}
						//below platform
						else if(player.getPosition_X() > p.getPosition_X() && player.getPosition_Y() < p.getPosition_Y()){
							player.addVelocity_Y((player.getVelocity_Y() * -1));
							player.setPosition(player.getPosition_X(), p.getPosition_Y() + p.getHeight());
							return false;
						}
						//side of platform
						else{
							player.addVelocity_X((player.getVelocity_X() * -1) - 10);
							return false;
						}
						
					}
				}
				
				return false;
			}
				

		}.start();
	}
	
	private void playMusic(String resource, boolean repeat) {
		if(repeat){
			Media sound = new Media(new File(resource).toURI().toString());
			MediaPlayer mediaplayer = new MediaPlayer(sound);
			mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
			
			mediaplayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					System.out.println("music ended");
					
				}
				
			});
			
			mediaplayer.setOnError(new Runnable() {

				@Override
				public void run() {
					System.out.println("music error");
					
				}
				
			});
			
			mediaplayer.setOnStopped(new Runnable() {

				@Override
				public void run() {
					System.out.println("music halted");
				}
				
			});
			
			mediaplayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					System.out.println("end of music");
					
				}
				
			});
			
			mediaplayer.play();
		}
		else{
			MediaPlayer player = new MediaPlayer(new Media(new File(resource).toURI().toString()));
			player.play();
		}

	}
	
	private ArrayList<Platform> initPlatforms() {
		ArrayList<Platform> platforms = new ArrayList<Platform>();
		
		for(int i = 0; i < 4; i++){
			platforms.add(new Platform(i * 250, r.nextInt(400) + 200));
		}
		
		return platforms;
	}

	public Scene getScene(){
		return scene;
	}

}
