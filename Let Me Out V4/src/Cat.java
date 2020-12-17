import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Cat extends GameObject{
	
	Handler handler;
	private BufferedImage cat_image = null;

	public Cat(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		BufferedImageLoader loader = new BufferedImageLoader();
		cat_image = loader.loadImage("/Cat.png");
		cat_image = cat_image.getSubimage(0, 0, 64, 84);
		
	}

	
	public void tick() {
		
		
	}

	
	public void render(Graphics g) {
		g.drawImage(cat_image, x, y, null);
		
	}

	
	public Rectangle getBounds() {
		
		
		return new Rectangle(x, y, 64, 84);
	}

}
