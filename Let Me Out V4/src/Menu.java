import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;

public class Menu {
	
	Game game;
	
	private BufferedImage menubg  = null;
	
	public Rectangle quitButton = new Rectangle(265,500,150,100);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		try {
		menubg = ImageIO.read(getClass().getResource("/LogoV1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(menubg,0,0,null);
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Quit", quitButton.x+25, quitButton.y+65);
		g2d.draw(quitButton);
			
	}	

}
