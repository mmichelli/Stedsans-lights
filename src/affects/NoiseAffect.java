package affects;

import java.util.ArrayList;

import lights.Light;


import processing.core.PApplet;

public class NoiseAffect extends Affect {

	float offset = 0;
	
	public NoiseAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		
		midiChannel = 1 ;
		midiNumber 	= 9 ;
		weight 		= 1 ;
		affectName = "noise";
	}
	
	public void update ( PApplet p,int x, int y){
		
		 
		for (Light l : lights) {
			l.setValue(p.noise(l.x+x,l.y+x,offset)); 
			
		}
		offset += 0.01;
		
	};

}
