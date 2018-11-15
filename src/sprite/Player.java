package sprite;

public class Player extends Sprite{
	
    private double velocityX = 0;
    private double velocityY = 0;
    
    private double maxVelocity = 400;
    private boolean onPlatform = false;
    private double jumpVelocity = 300;
    private double floorHeight;
    private double floorLeft;
    private double floorRight;
    private double gravity = 10;
	
	public Player(){
		super();
		velocityX = 0;
		velocityY = 0;
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
    	onPlatform = false;
    	addVelocity_Y(-1 * jumpVelocity);
    }

	public void setOnPlatform(boolean b, double platformHeight) {
		onPlatform = b;
		floorHeight = platformHeight;
	}

}
