package sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite {
	protected Image image;
    protected double positionX;
    protected double positionY;    

    private double width;
    private double height;

    public Sprite(){
        positionX = 0;
        positionY = 0;    
    }
    
    public Sprite(int x, int y){
    	positionX = x;
    	positionY = y;
    }

    public void setImage(Image i){
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename){
        Image i = new Image(filename);
        setImage(i);
    }
    
    public double getHeight(){
    	return height;
    }
    
    public double getWidth(){
    	return width;
    }

    public void setPosition(double x, double y){
        positionX = x;
        positionY = y;
    }
    
    public double getPosition_X(){
    	return positionX;
    }
    
    public double getPosition_Y(){
    	return positionY;
    }

    public void render(GraphicsContext gc){
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s){
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString(){
        return " Position: [" + positionX + "," + positionY + "]";
    }
}
