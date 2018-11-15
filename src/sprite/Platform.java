package sprite;

import javafx.scene.image.Image;

public class Platform extends Sprite {
	
	private double platformSpeed;
	
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
	
	public void setPlatformSpeed(int speed){
		this.platformSpeed = speed;
	}
}