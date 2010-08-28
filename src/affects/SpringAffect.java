package affects;

import java.util.ArrayList;

import lights.Light;


import physics.Spring;
import processing.core.PApplet;

public class SpringAffect extends Affect {

	
	private ArrayList<Spring> springs;

	
	
	
	public SpringAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		setUpStrings(w, h);
		midiChannel = 2 ;
		midiNumber 	= 2 ;
		weight 		= 0 ;
		
		affectName = "spring";
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
					
					springs.add(new Spring(lva,getLightVector(j, i-1)));
					
					springs.add(new Spring(lva,getLightVector(j-1, i)));
				
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
				l.setValue((float)2.0);
				
			}
	

		}
		
	
	};
	



}
