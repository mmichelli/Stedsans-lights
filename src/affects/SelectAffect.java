package affects;

import java.util.ArrayList;

import processing.core.PApplet;

import lights.BCF2000;
import lights.Light;

public class SelectAffect extends Affect {
	
	private int[] control = {0,5, 0};

	private Light light ; 

	public SelectAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		initLights();
		
		midiChannel = 4 ;
		midiNumber = 7 ;
		weight = 0 ;
		affectName = "midi";
		button[0] = 9; 
		button[1] = 43; 
		resetButton(0);
		
	}
	
	public void update ( PApplet p,int x, int y)
	{
		
		for (Light l : lights) {
			if(p.mousePressed && l.mouseOver(p, x,y) && light != l )
			{
				light = l;
				BCF2000.sendControllerChange(control[0],control[1],(int)(l.getValue()*BCF2000.MAX));
			}

		}
		
	};
	private void initLights(){
			BCF2000.sendControllerChange(control[0],control[1],0);
			light
			= lights.get(control[2]); 
	}
	

	public void controllerChange(int channel, int number, int value) {
		
		
		if(channel == control[0] && number == control[1])
		{
			light.setValue((float)value/BCF2000.MAX);
		}
		
		
	}
	

}
