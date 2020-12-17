import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Axe extends GameObject {
	
	private BufferedImage sprite_sheet = null;
	private BufferedImage[] j_image = new BufferedImage[4];
	Animation anim;

	public Axe(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		BufferedImageLoader loader = new BufferedImageLoader();
		sprite_sheet = loader.loadImage("/sprite/Axe_sprite.png");
		
		
		j_image[0] = sprite_sheet.getSubimage((1 * 100)-100,(1*100)-100, 100, 100);
		j_image[1] = sprite_sheet.getSubimage((2 * 100)-100,(1*100)-100, 100, 100);
		j_image[2] = sprite_sheet.getSubimage((3 * 100)-100,(1*100)-100, 100, 100);
		j_image[3] = sprite_sheet.getSubimage((4 * 100)-100,(1*100)-100, 100, 100);
		
		anim = new Animation(10,j_image[0],j_image[1],j_image[2],j_image[3]);
		
		
	}

	
	public void tick() {
		anim.runAnimation();
	}

	
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
		
	}


	public Rectangle getBounds() {
		
		return new Rectangle(x,y,84,30);
	}

}
