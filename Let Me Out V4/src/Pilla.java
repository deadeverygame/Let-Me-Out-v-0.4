
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pilla extends GameObject {
	
	private BufferedImage image;

	public Pilla(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		try {
			image = ImageIO.read(getClass().getResource("/barbatos.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		image.getSubimage(0, 0, 64, 84);
		
	}

	public void tick() {
		
	}

	
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 60, 80);
	}

}
