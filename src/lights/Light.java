package lights;

import processing.core.PApplet;

 public class Light {
	
	public int x = 0, y=0, s = 10, id=-1,g = 10;
	protected float value = 0; 
	private double velocity = 0; 
	public boolean fixed = false;


	public	Light(int _x, int _y, int _id, int _s, int _g )
	{
		x 	= _x;
		y 	= _y; 
		id 	= _id;
		s 	= _s;
		g 	= _g;
	

	}
	public	Light( )
	{
		x 	= 0;
		y 	= 0; 
		id 	= 0;
		s 	= 0;
		g 	= 0;
		
		value = (float)0.0;

	}
	
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		if(!fixed)
		{
			this.velocity = velocity;
		}
	}
	public void step() {
		if(!fixed)
		{
			this.value += velocity;
		}
	}
	
	public boolean mouseOver ( PApplet p,int x, int y)
	{
		return ((p.mouseX >= this.getDx(x))&&(p.mouseX < (this.getDx(x)+ (this.s)))
				&& (p.mouseY >= this.getDy(y))&&(p.mouseY < (this.getDy(y) + (this.s))));
	}
	
	
	
	public void setValue(float v)
	{
		value = v;
	}
	public void addValue(float v)
	{
		value += v;
	}
	
	public float getValue()
	{
		return value;
	}
	public int getDx(int _x)
	{
		return x * (s + g) + _x;
	}
	public int getDy(int _y)
	{
		return y * (s + g) + _y;
	}	
	public void draw( PApplet p, int _x, int _y, float _alpha, boolean np)
	{	
		float v = value*((np)?1:-1);
		int f;
		if(v < 0)
			 f = p.color(v*-255,0,0);
		else
			 f = p.color(v*255,v*255,v*255);
		p.stroke((int)(Math.max(_alpha, 0.1)*255));
		p.fill(f);
		p.rect(getDx(_x), getDy(_y), s, s);
	}
	
	
}
