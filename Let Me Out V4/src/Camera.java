
public class Camera {
	
	private float x, y;
	
	
	public Camera(float x,float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject object) {
		
		
		
		x = object.getX();
		y = object.getY();
		
		if(x <= 650 & y <= 650) {x =0; y= 0;}
		if(x > 650 & y <= 650 & x < 1280 ) {x=630; y=0;}
		if(x > 1200 & y <= 650) {x = 1248; y = 0;}
				
		if(x < 650 & y > 650 & y <= 1270 ) {x = 0; y = 640;}
		if(x > 650 & y > 650 & y <= 1270 & x < 1280) {x = 630;y=640;}
		if(x > 1200 & y > 650 & y <= 1270 & x < 1900 ) {x = 1248;y=640;}
				
		if(x < 650 & y > 1100 & y <= 1910) {x = 0; y = 1280;}
		if(x > 648 & y > 1100 & y <= 1910 & x < 1280) {x = 630;y=1280;}
		if(x > 1200 & y > 1100 & y <= 1910 & x < 1890 ) {x = 1248;y=1280;}
		
		if(x < 650 & y > 1800 ) {x = 0; y = 1888;}
		if(x > 648 & x < 1270 & y > 1800) {x = 630; y = 1888;}
		if(x > 1200 & y > 1800 & x < 1900) {x = 1248; y=1888;}
		
		if(x > 1890 & y > 650) {
			x += ((object.getX() - x) - 688/2) ;
			y += ((object.getY() - y) - 711/2);
			
			
			if(x <= 0) x =0;
			if(x >= 1248) x = 1888;
			if(y <= 650) y = 650;
			if(y >=  1280)  y = 1280;
			
			
			//x = 1888; y = 1280;
			}
		
		
	
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
