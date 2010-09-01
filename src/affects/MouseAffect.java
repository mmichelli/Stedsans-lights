package affects;

import java.util.ArrayList;

import lights.BCF2000;
import lights.Light;


import processing.core.PApplet;

public class MouseAffect extends Affect {
	
	private int[] control = {0,6};
	private double ratio = 0.9; 

	public MouseAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		
		midiChannel = 0 ;
		midiNumber =0 ;
		weight = 0 ;
		affectName = "click";
		button[0] = 0; 
		button[1] = 60; 
		resetButton(0);
		
	}
	public void update ( PApplet p,int x, int y)
	{
		for (Light l : lights) {
			if(p.mousePressed && l.mouseOver(p, x,y))
			{
				l.setValue((float)1.0);
				System.out.println( p.mouseX + "  "+l.getDx(x));
			}
			else
			{
				l.setValue((float)(l.getValue()*ratio));
			}
		}
		
	};
	

	public void controllerChange(int channel, int number, int value) {
		
		
		if(channel == control[0] && number == control[1])
		{
			System.out.println(Math.sin(Math.PI*0.5*(double)value/BCF2000.MAX)); 
			ratio = value/(double)BCF2000.MAX;
		}
		
		
	}
	
	
}
