package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class Sprite{
    private Image image;
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double maxVelocity;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
        
        maxVelocity = 500;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }
    
    public double getHeight(){
    	return height;
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }
    
    public double getPosition_Y(){
		return positionY;
    }
    
    public double getPosition_X(){
    	return positionX;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
        
        if(velocityX > maxVelocity){
        	velocityX = maxVelocity;
        }
        else if(velocityX < -maxVelocity){
        	velocityX = -maxVelocity;
        }
        
        if(velocityY > maxVelocity){
        	velocityY = maxVelocity;
        }
        else if(velocityY < -maxVelocity){
        	velocityY = -maxVelocity;
        }
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
        
        if(velocityX > maxVelocity){
        	velocityX = maxVelocity;
        }
        else if(velocityX < -maxVelocity){
        	velocityX = -maxVelocity;
        }
        
        if(velocityY > maxVelocity){
        	velocityY = maxVelocity;
        }
        else if(velocityY < -maxVelocity){
        	velocityY = -maxVelocity;
        }
    }
    
    public double getVelocity_X(){
    	return velocityX;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }
    
    public Rectangle2D getBoundary(){
    	return new Rectangle2D(positionX, positionY, width, height);
    }
    
    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}