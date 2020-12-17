
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends GameObject {
	
	private BufferedImage image;

	public Door(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		if(this.id == ID.last_door)
			this.draw_image("/Final_Door.png");
		else
			this.draw_image("/Door2.png");
		
		
	}
	
	public void draw_image(String path) { try {
		image = ImageIO.read(getClass().getResource(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	if(this.id == ID.last_door)
		image.getSubimage(0, 0, 50, 90);
	else
		image.getSubimage(0, 0, 84, 64);
	
	}

	public void tick() {
		
		
	}

	
	public void render(Graphics g) {
		if(this.id == ID.last_door)
			g.drawImage(image,x,y,null);
		else
			g.drawImage(image,x,y,null);
	}

	
	public Rectangle getBounds() {
		
		if(this.id == ID.last_door)
			return new Rectangle(x,y,50,85);
		else
			return new Rectangle(x,y,84,50);
	}

}
