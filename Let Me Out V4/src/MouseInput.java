import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
	
	private Game game;
	
	public MouseInput(Game game) {
		this.game = game;
	}
	public void mouseClicked(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.State == Game.STATE.GAME) {
			//Menu
			if (mx >= 0 && mx <= 100) {
				if (my >= 670 && my <= 720) {
					game.isRunning = false;
				}
			}
		}
		
			//Mute
			if (mx >= 0 && mx <= 40) {
				if (my >= 8 && my <= 48) {
					if (game.Mute == 1) {
						game.Mute = 0;
					}
					else {
						game.Mute = 1;
					}
				}
			}
		
	}

	public void mousePressed(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.State == Game.STATE.MENU){
			//Play
			if (mx >= 265 && mx <= 415){
				if (my >= 340 && my <= 440)
				{
					Game.State = Game.STATE.GAME;
				}
			}
		
			//Quit
			if (mx >= 265 && mx <= 415){
				if (my >= 500 && my <= 600)
				{
					System.exit(1);
				}
			}
		}
		
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
