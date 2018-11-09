package sprite;

public class Player extends Sprite{
	
    private double velocityX = 0;
    private double velocityY = 0;
	
	public Player(){
		super();
		velocityX = 0;
		velocityY = 0;
	}
	
	public void addVelocity_X(double x){
		velocityX += x;
	}
	
	public double getVelocity_X(){
		return velocityX;
	}
	
	public void addVelocity_Y(double y){
		velocityY += y;
	}
	
	public double getVelocity_Y(){
		return velocityY;
	}
	
    public void update(double time){
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
    
    @Override
    public String toString(){
        return " Position: [" + positionX + "," + positionY + "]" + 
        		" Velocity: [" + velocityX + "," + velocityY + "]";
    }

}
