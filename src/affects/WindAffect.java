package affects;

import java.util.ArrayList;

import lights.Light;


import physics.Spring;
import processing.core.PApplet;

public class WindAffect extends Affect {

	
	private ArrayList<Spring> springs;
	

	
	
	
	public WindAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		setUpStrings(w, h);
		midiChannel = 3 ;
		midiNumber 	= 5 ;
		weight 		= 0 ;
		button[0] = 9; 
		button[1] = 41; 
		affectName = "waves";
		resetButton(0);
	}
	
	private void setUpStrings(int w, int h) {
		Light lva; 
		
		springs = new ArrayList<Spring>();
		
		for (int i = 0; i < gridHeight; i++) 
		{
			for (int j = 0; j < gridWidth; j++) 
			{
				lva = getLightVector(j, i);
				
					
					springs.add(new Spring(lva,getLightVector(j+1, i)));
					springs.add(new Spring(lva,getLightVector(j, i+1)));
					springs.add(new Spring(lva,getLightVector(j, i)));
					
				
			}
		}
		
		
		
	}
	private Light getLightVector(int j, int i)
	{
		return (i<gridHeight && j < gridWidth && (j>-1 && i > -1))?lights.get((i*gridWidth)+ j ): new Light();
	}
	
	public void update ( PApplet p,int x, int y)
	{
		for (Spring s : springs) {
			s.update(); 
		}
		for (Light l : lights) {
			if(p.mousePressed && l.mouseOver(p, x,y))
			{
				l.setValue((float)1.0);
				System.out.println( p.mouseX + "  "+l.getDx(x));
			}
	
		}
		
	
	};
	



}
