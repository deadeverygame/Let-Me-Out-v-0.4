
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends GameObject{
	
	private BufferedImage image;

	public Block(int x, int y, ID id,SpriteSheet ss) {
		super(x, y, id,ss);
		this.draw_image("/BrickWall.png");
		
	}
	
	public void draw_image(String path) { try {
		image = ImageIO.read(getClass().getResource(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	image.getSubimage(0, 0, 32, 32);
	}


	public void tick() {
		
		
	}

	
	public void render(Graphics g) {
		g.drawImage(image,x,y,null);
		
	}


	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 32, 32);
	}

}
