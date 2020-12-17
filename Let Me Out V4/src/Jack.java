import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Jack extends GameObject {
	
	private int warp = 0;
	Handler handler;
	private BufferedImage jack_image;
	Game game;
	
	private BufferedImage[] j_image = new BufferedImage[12];
	
	Animation animd,animup,animr,animl;
	
	private AudioPlayer coin_sound,item_sound,pilla_sound;

	public Jack(int x, int y, ID id, Handler handler,SpriteSheet ss, Game game) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		
		j_image[0] = ss.grabImage(1, 1,64, 84);
		j_image[1] = ss.grabImage(2, 1,64, 84);
		j_image[2] = ss.grabImage(3, 1,64, 84);
		
		j_image[3] = ss.grabImage(4, 1,64, 84);
		j_image[4] = ss.grabImage(5, 1,64, 84);
		j_image[5] = ss.grabImage(6, 1,64, 84);
		
		j_image[6] = ss.grabImage(7, 1,64, 84);
		j_image[7] = ss.grabImage(8, 1,64, 84);
		j_image[8] = ss.grabImage(9, 1,64, 84);
		
		j_image[9] = ss.grabImage(10, 1,64, 84);
		j_image[10] = ss.grabImage(11, 1,64, 84);
		j_image[11] = ss.grabImage(12, 1,64, 84);
		
		
		animd = new Animation(3,j_image[0],j_image[1],j_image[2] );
		animup = new Animation(3,j_image[3],j_image[4],j_image[5] );
		animl = new Animation(3,j_image[6],j_image[7],j_image[8] );
		animr = new Animation(3,j_image[9],j_image[10],j_image[11] );
		
		
		coin_sound = new AudioPlayer("/sound/coin_pickup.wav");
		item_sound = new AudioPlayer("/sound/pickup_item.wav");
		pilla_sound = new AudioPlayer("/sound/pilla_hit.wav");
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		collision();
		
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) {velX = 5; velY = 0;}
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) { velX = -5; velY=0;}
		else if(!handler.isRight()) velX = 0;
		
		animd.runAnimation();animup.runAnimation();animr.runAnimation();animl.runAnimation();
		
		if(game.tim == 0 & game.tis <= 5 & warp == 0) {
			x = 2400;
			y = 900;
			warp = 1;
		}
		
	}
	
	
	
	
	private void collision() {
		
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
				
			}
			if(tempObject.getId() == ID.Axe) {
				if (getBounds().intersects(tempObject.getBounds())) {
					item_sound.play();
					game.axe_+= 1;
					handler.removeObject(tempObject);
					
					
				}
			}
			if(tempObject.getId() == ID.Enemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if(game.axe_ == 1) {pilla_sound.play(); handler.removeObject(tempObject); }
					else {
						x += velX * -1;
						y += velY * -1;
						}
					
				}
			}
			
			if(tempObject.getId() == ID.Ladybug) {
				if(getBounds().intersects(tempObject.getBounds())) {
					coin_sound.play();
					game.lady_bug += 1;
					handler.removeObject(tempObject);
				}
			}
			
			if(tempObject.getId() == ID.Door) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					if(game.lady_bug == 8) handler.removeObject(tempObject);
					else {
					x += velX * -1;
					y += velY * -1; }
					
				}
			}
			if(tempObject.getId() == ID.Key) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					item_sound.play();
					game.Key_collected += 1;
					handler.removeObject(tempObject);}
			}
			if(tempObject.getId() == ID.Keysilva) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					item_sound.play();
					game.Silva_key_col += 1;
					handler.removeObject(tempObject);}
			}
			
			if(tempObject.getId() == ID.last_door) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					if(game.Key_collected == 1 & game.Silva_key_col == 1) {
						handler.removeObject(tempObject);
						game.win = 1;
					}
					else {
					x += velX * -1;
					y += velY * -1; }
				}
			}

			if(tempObject.getId() == ID.cat) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
					handler.removeObject(this);
			
				}
	
			}
		}
	}
	
	
	public void render(Graphics g) {
		
		
		if( velY > 0) {
			animd.drawAnimation(g, x, y, 0);
		}
		else if( velY < 0) {
			animup.drawAnimation(g, x, y, 0);
			
		}
		else if(  velX > 0) {
			animr.drawAnimation(g, x, y, 0);
		}
		else if(  velX < 0) {
			animl.drawAnimation(g, x, y, 0);
		}
		else if ( velX == 0 || velY == 0) {
			jack_image = ss.grabImage(1, 1,64, 84);
			g.drawImage(jack_image,x,y,null);
		}
		
		
		
		
		
	}
	
	

	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 60, 80);
	}

}
