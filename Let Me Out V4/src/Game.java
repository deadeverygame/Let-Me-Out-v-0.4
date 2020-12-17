import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{
	
	public int timerHack = 0;
	
	private static final long serialVersionUID = 1L;
	
	private AudioPlayer bgMusic;
	
	public boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private BufferedImage level = null;
	private Camera camera;
	private Menu menu;
	
	private SpriteSheet ss;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	
	public int lady_bug = 0;
	public int axe_ = 0;
	public int Key_collected = 0;
	public int Silva_key_col = 0;
	public int endding_check = 0;
	public int Mute = 0;
	public Rectangle menuButton = new Rectangle(0,670,100,50);
	public Rectangle playButton = new Rectangle(265,340,150,100);
	
	public int tis = 59;
	public int tim = 4;
	
	private BufferedImage bughud = null;
	private BufferedImage backbug_hud = null;
	private BufferedImage backaxe_hud = null;
	private BufferedImage backkey_hud = null;
	private BufferedImage backsilvakey_hud = null;
	private BufferedImage axehud = null;
	private BufferedImage keyhud = null;
	private BufferedImage silvakeyhud = null;
	private BufferedImage end_image = null;
	private BufferedImage lose_image = null;
	private BufferedImage mute  = null;
	private BufferedImage unmute  = null;
	public int win = 0;
	
	
	public static enum STATE{
		MENU,
		GAME
	};
	public static STATE State = STATE.MENU;
	
	Game() {
		
		new Window(688, 760,"Let Me Out", this);
		start();
		
		handler = new Handler();
		camera = new Camera(0, 0);
		menu = new Menu();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(this));
		
		try {
			floor = ImageIO.read(getClass().getResource("/WoodFloor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level4.0.png");
		
		sprite_sheet = loader.loadImage("/jack_sprite.png");
		ss = new SpriteSheet(sprite_sheet);
		
		try {
			end_image = ImageIO.read(getClass().getResource("/End.png"));
			lose_image = ImageIO.read(getClass().getResource("/Lose1.png"));
			backbug_hud = ImageIO.read(getClass().getResource("/LadybugMiniShadow.png"));
			backaxe_hud = ImageIO.read(getClass().getResource("/AxeMiniShadow.png"));
			backkey_hud = ImageIO.read(getClass().getResource("/GoldKeyShadow.png"));
			backsilvakey_hud = ImageIO.read(getClass().getResource("/SilverKeyShadow.png"));
			bughud = ImageIO.read(getClass().getResource("/Ladybug.png"));
			axehud = ImageIO.read(getClass().getResource("/AxeMini.png"));
			keyhud = ImageIO.read(getClass().getResource("/GoldKey.png"));
			silvakeyhud = ImageIO.read(getClass().getResource("/SilverKey.png"));
			mute = ImageIO.read(getClass().getResource("/mute.png"));
			unmute = ImageIO.read(getClass().getResource("/unmute.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadLevel(level);
		bgMusic = new AudioPlayer("/sound/BG_music_by_ahesive.mp3");
		BufferStrategy bs = this.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Play", playButton.x+25, playButton.y+65);
		g2d.draw(playButton);
		this.timer_start();
		
	}
	
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();	
	} 
	
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		State = STATE.MENU;
		bgMusic.close();
		String[] met = null;
		main(met);
		stop();
	}
	
	public void tick() {
		
		for(int i = 0;i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		
		if (State == STATE.GAME) {
			handler.tick();
			if(timerHack == 0) {
				tis = 59;
				tim = 4;
				timerHack = 1;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		/////////////////////////////
		
		g2d.translate(-camera.getX(), -camera.getY());
		if (State == STATE.GAME) {
			for(int xx = 0; xx< 30*100; xx+= 67) {
				for(int yy = 0; yy < 30*100; yy += 67) {
					g.drawImage(floor,xx,yy,null);
				}
			}
		
			handler.render(g);
		
			g2d.translate(camera.getX(), camera.getY());
		//////////////////////////////////////// HUD	
		if (Mute == 0) {
			g.drawImage(unmute, 0, 8,null);
			bgMusic.play();
			Mute = 2;
		}
		if (Mute == 1) {
			g.drawImage(mute, 0, 8, null);
			bgMusic.stop();
		}
		if (Mute == 2) {
			g.drawImage(unmute, 0, 8,null);
		}
		
		g.setColor(Color.GRAY);
		g.fillRect(0,672 , 760, 50 );
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0,672 , 760, 50 );
		
		for(int i = 1;i <= 8;i++) {
			g.drawImage(backbug_hud, i*30, 680, null);
			
		}
		
		for(int i = 1;i <= lady_bug;i++) {
			g.drawImage(bughud, i*30, 680, null);
			}
		
		
		Font font = new Font("Arial", Font.PLAIN, 50);
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		
		g.drawImage(backaxe_hud, 400, 680, null);
		if(this.axe_ == 1) {
			g.drawImage(axehud, 400, 680, null);
		}
		
		g.drawImage(backsilvakey_hud, 460, 670, null);
		if(this.Silva_key_col == 1) {
			g.drawImage(silvakeyhud, 460, 670, null);
		}
		
		g.drawImage(backkey_hud, 560, 670, null);
		if(Key_collected == 1) {
			g.drawImage(keyhud, 560, 670, null);
		}
	
		
		//////////////////////////////////// TIME
		if (tis < 10) {
			g.drawString(tim+" : 0"+ tis, 500, 60);
			
		}
		else
		 g.drawString(tim+" : "+ tis, 500, 60);
		
		}
		else if (State == STATE.MENU) {
			menu.render(g);
		}
		
		if(win == 1) {
			g.drawImage(end_image, 0, 0, null);
			Font fnt0 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("Menu", menuButton.x+10, menuButton.y+35);
			g2d.setColor(Color.WHITE);
			g2d.draw(menuButton);
		}
		else if(win == 2) {
			g.drawImage(lose_image, 0, 0, null);
			Font fnt0 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			g.drawString("Menu", menuButton.x+10, menuButton.y+35);
			g2d.setColor(Color.WHITE);
			g2d.draw(menuButton);
		}
		
		///////////////////////////////
		g.dispose();
		bs.show();
		
	}
	public void timer_start() {
		for(tim = 6; tim >= 0; tim--) {
			for(tis = 59;tis >= 0; tis--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
		}
	}
		win = 2;
}
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		handler.addObject(new Axe( 100, 2100 ,ID.Axe,handler, ss));
		
		handler.addObject(new Jack(2368, 1536, ID.Player, handler, ss,this));
		
		for(int xx = 0 ;xx < w;xx++) {
			for(int yy = 0;yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && blue == 0 && green == 0)
					handler.addObject(new Block(xx*32, yy*32, ID.Block, ss));
				
				if(red == 0 && blue == 255 && green == 0) 
					handler.addObject(new Door(xx*32, yy*32, ID.Door, ss));
					
				
				if(red == 0 && blue == 0 && green == 255)
					handler.addObject(new Pilla(xx*32, yy*32, ID.Enemy, handler, ss));
				
				if(red == 0 && blue == 255 && green == 255 )
					handler.addObject(new Ladybug(xx*32, yy*32, ID.Ladybug , ss));
				
				if(red == 255 && blue == 255 && green == 0 )
					handler.addObject(new Key(xx*32, yy*32, ID.Key , ss));
				
				if(red == 255 && blue == 0 && green == 106 )
					handler.addObject(new silva_key(xx*32, yy*32, ID.Keysilva , ss));
				
				if(red == 255 && blue == 255 && green == 255 )
					handler.addObject(new Door(xx*32, yy*32, ID.last_door , ss));
				if(red == 66 & blue == 6 & green == 6)
					handler.addObject(new Cat(xx*32 , yy*32, ID.cat, ss ));
					
							
			}
		}
					
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
