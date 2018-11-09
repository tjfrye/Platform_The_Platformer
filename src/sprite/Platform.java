package sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class Platform extends Sprite {
	
	public Platform(int x, int y){
		super(x, y);
		setImage(new Image("file:resources/sprites/Platform.png"));
	}
}