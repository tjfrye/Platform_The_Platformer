package sprite;

import java.util.Random;

import javafx.scene.image.Image;

public class Platform extends Sprite {
	
	private double platformSpeed;
	
	
	public Platform(int x, int y){
		super(x, y);
		Random r = new Random();
		if(r.nextInt(100) > 80){
			setImage(new Image("file:resources/sprites/Platform_3.png"));
		}
		else if(r.nextInt(100) > 65){
			setImage(new Image("file:resources/sprites/Platform_2.png"));
		}
		else{
			setImage(new Image("file:resources/sprites/Platform_1.png"));
		}
		
		this.platformSpeed = 1;
	}
	
	public Platform(int x, int y, double speed){
		super(x, y);
		setImage(new Image("file:resources/sprites/Platform.png"));
		
		this.platformSpeed = speed;
	}
	
	public boolean update(){
		setPosition(getPosition_X() - platformSpeed, getPosition_Y());
		
		if(getPosition_X() < - 200){
			return true;
		}
		
		return false;
	}
	
	public void setPlatformSpeed(double speed){
		this.platformSpeed = speed;
	}
	
	public double getPlatformSpeed(){
		return platformSpeed;
	}
}