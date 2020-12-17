
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ladybug extends GameObject{
	
	private BufferedImage sprite_sheet = null;
	private BufferedImage[] j_image = new BufferedImage[4];
	Animation anim;

	public Ladybug(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/sprite/Ladybug_sprite.png");
		
		
		j_image[0] = sprite_sheet.getSubimage((1 * 64)-64,(1*64)-64, 64, 64);
		j_image[1] = sprite_sheet.getSubimage((2 * 64)-64,(1*64)-64, 64, 64);
		j_image[2] = sprite_sheet.getSubimage((3 * 64)-64,(1*64)-64, 64, 64);
		j_image[3] = sprite_sheet.getSubimage((4 * 64)-64,(1*64)-64, 64, 64);
		
		anim = new Animation(10,j_image[0],j_image[1],j_image[2],j_image[3]);
		
	}
	

	
	public void tick() {
		
		anim.runAnimation();
	}

	
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
		
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,40,40);
	}

}
