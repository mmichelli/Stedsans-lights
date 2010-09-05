package affects;

import java.util.ArrayList;

import lights.BCF2000;
import lights.Light;


import processing.core.PApplet;

public class MouseAffect extends Affect {
	
	private int[] control = {0,6};
	private double ratio = 0.9; 
	private int[] flicker = {9,35};
	boolean flicking = false;
	

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
				l.setValue(1f);
				System.out.println("index: " + lights.indexOf(l));
			}
			else if(!flicking)
			{
				l.setValue((float)(l.getValue()*ratio));
			}
		}
		
	};
	public void noteOn(int channel, int pitch, int velocity) {
		
		if(channel == flicker[0] && pitch == flicker[1]  )
		{
			lights.get(48).setValue(velocity/100);
			flicking = velocity == 100;
		}
		

		if(channel == button[0] && pitch == button[1] && velocity>0 )
		{
			pos = !pos;
		}
		
		BCF2000.sendNoteOn(button[0], button[1], (pos)?127:0);
	}

	public void controllerChange(int channel, int number, int value) {
		
		
		if(channel == control[0] && number == control[1])
		{
			System.out.println(Math.sin(Math.PI*0.5*(double)value/BCF2000.MAX)); 
			ratio = value/(double)BCF2000.MAX;
		}
		
		
	}
	
	
}
