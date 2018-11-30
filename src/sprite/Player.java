package sprite;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player extends Sprite{
	
	public static String currentUsername;
	public static int score;
	public static int difficulty;
	
    private double velocityX = 0;
    private double velocityY = 0;
    
    private double maxVelocity = 400;
    private boolean onPlatform = false;
    private double jumpVelocity = 300;
    private double floorHeight;
    private double floorLeft;
    private double floorRight;
    private double gravity = 10 + difficulty;
    private int jumps = 0;
    private int maxJumps = 5 - difficulty;
	
	public Player(){
		super();
		velocityX = 0;
		velocityY = 0;
	}
	
	public Player(String name){
		super();
		
		velocityX = 0;
		velocityY = 0;
		
		if(name.equals("Easy Evan")){
			this.setImage("file:resources/sprites/EasyEvan.png");
			maxJumps = 10;
		}
		else if(name.equals("Medium Matt")){
			this.setImage("file:resources/sprites/MediumMatt.png");
			maxJumps = 5;
		}
		else if(name.equals("Hard Hank")){
			this.setImage("file:resources/sprites/HardHank.png");
			maxJumps = 2;
		}
		else{
			this.setImage("file:resources/sprites/TEST.png");
		}
	}
	
	public void addVelocity_X(double x){
		velocityX += x;
		
		velocityX = speedLimit(velocityX);
	}
	
	public double getVelocity_X(){
		return velocityX;
	}
	
	public void addVelocity_Y(double y){
		velocityY += y;
		
		velocityY = speedLimit(velocityY);
	}
	
	public double getVelocity_Y(){
		return velocityY;
	}
	
    public void update(double time){
        positionX += velocityX * time;
        positionY += velocityY * time;
        
        if(onPlatform){
        	if(positionX > floorRight || positionX < floorLeft){
        		onPlatform = false;
        	}
        	else{
        		positionY = floorHeight - getHeight();
        	}
        }
        else{
        	addVelocity_Y(gravity);
        }
    }
    
    @Override
    public String toString(){
        return " Position: [" + positionX + "," + positionY + "]" + 
        		" Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
    private double speedLimit(double velocity){
		if(velocity > maxVelocity){
			velocity = maxVelocity;
		}
		else if(velocity < maxVelocity * -1){
			velocity = maxVelocity * -1;
		}
		
		return velocity;
    }
    
    public void jump(){
    	if(jumps < maxJumps){
    		onPlatform = false;
    		addVelocity_Y(-1 * jumpVelocity);
    		jumps++;
    		
			Media sound_jump = new Media(new File("resources/music/jump.wav").toURI().toString());
			MediaPlayer mediaplayer_jump = new MediaPlayer(sound_jump);
			mediaplayer_jump.play();
    	}
    }

	public void setOnPlatform(boolean b, double platformHeight) {
		jumps = 0;
		onPlatform = b;
		floorHeight = platformHeight;
	}

}
