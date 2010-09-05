package affects;

import java.util.ArrayList;

import processing.core.PApplet;

import lights.BCF2000;
import lights.Light;

/*
 * The angel can be controlled by a rotary knob over the grid-fader. Maybe it is possible to make the program reset the midi-knob to 0 when it hits 127 and vica versa? IF this works we kan turn the wave around forever which would be excelent! I guess we also need a rotary for OFFSET control.

// UNIVERSAL WAVE EFFECT
// z = wave height, intendent range is 0-255 but it will exceed this and need clipping.
// The values 75 and 180  is kind of brightness and contrast,
// the values are experimental and gives shorter wave-tops and clipped wave-bottoms. 
angle=0 //wave angle in radians (0 - 2*PI for 0-360 degrees)
offset=0 //wave offset, range 0 - 2*PI. 
               // offset=0 and then increasing to start the wave from the edge. 
               // offset=PI and then increase angle for a "propeller" like effect.
rate=0.5 // How thight the waves are
cx=3.5   // x rotation center (3.5 is for x ranging from 0 to 7)
cy=3.5   // Y rotation center

z=75-180*cos((x-cx)*rate*sin(angle)+(y-cy)*rate*cos(angle)+offset)
 * 
 */

public class WaveAffect extends Affect {

	final double ANGLE     = 1.5831647959112811;
	double 	angle      = ANGLE; //wave angle in radiant (0 - 2*PI for 0-360 degrees)
	
	double 	destAngle  = 0;
	double 	offset = 0.0; //wave offset, range 0 - 2*PI. 
	double 	cOffset = 3.15;
	double 	rate=0.43; 
	double 	cx=3.5;   // x rotation center (3.5 is for x ranging from 0 to 7)
	double 	cy=3.5; 
	int val = 0; 
	int last = 0; 
	int[] wv = 	  {6,1};
	int[] angleCnrl = {0,1};
	int[] gap = {0,7};
	int[] swish = {9,42};
	int[] wave = {9,46};
	boolean swishActive = false; 
	boolean waveActive = false; 
	double waveDest = 0; 
	double step = 0; 
	int[] on = {14,60};
	int[] off = {15,60};
	int rowClip = 0; 
	
	
	
	
	public WaveAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		//0,4
		midiChannel = 0 ;
		midiNumber = 4 ;
		weight = 0 ;
		affectName = "Wave";
		BCF2000.sendControllerChange(gap[0],gap[1],(int)(rate*BCF2000.MAX));
		BCF2000.sendControllerChange(angleCnrl[0],angleCnrl[1],32);
	}

	public void update ( PApplet p,int x, int y)
	{
		for (Light l : lights) {
			l.setValue(0);
		}
		
		if(waveActive && waveDest > offset)
		{
			 
			offset = Math.min(waveDest, offset + cOffset/15);
		}
		else if(waveActive)
		{
			switchOffWave();
		}
		if(waveActive)
		{
			for (Light l : lights) {
				l.addValue(Math.max(-0.1f,(float)(Math.cos((l.x-cx)*rate*Math.sin(ANGLE )+(l.y-cy)*rate*Math.cos(ANGLE)+offset+cOffset))));
			}
		}
	
		if(swishActive && destAngle > angle)
		{
			 
			angle = Math.min(destAngle, angle + (2*Math.PI)/40);
		}
		else if(swishActive)
		{
			switchOffSwish();
		}
		
		if(swishActive)
		{
			for (Light l : lights) {
				l.addValue(Math.max(-0.3f,(float)(Math.cos((l.x-cx+step)*rate*Math.sin(angle )+(l.y-cy)*rate*Math.cos(angle)))));
			}
		}
		


		
	};
	
	public void noteOn(int channel, int pitch, int velocity) {
		
		if(channel == swish[0] && pitch == swish[1] && velocity>0 )
		{
			destAngle = ((swishActive )? destAngle:angle) + Math.PI;
			swishActive = true;
		}
		
		if(channel == wave[0] && pitch == wave[1] && velocity>0 )
		{
			
			wave();
			
		}
		
		if(channel == on[0] && pitch == on[1] && velocity>0 )
		{
			wave();
			step = Math.min(step +.6 , (8*0.6)); 
		}
		
		if(channel == off[0] && pitch == off[1] && velocity>0 )
		{
			step = Math.max(step-.6, 0);;
		}
		
		updateButtons();
		
	}
	private void wave()
	{
		offset = step; 
		waveDest = cOffset*2;
		waveActive = true;
		
	}
	
	private void updateButtons()
	{
		
		BCF2000.sendNoteOn(swish[0], swish[1], (swishActive)?127:0);
		BCF2000.sendNoteOn(wave[0], wave[1], (waveActive)?127:0);
	}
	
	private void switchOffSwish()
	{
		swishActive = false;
		updateButtons();
	}
	private void switchOffWave()
	{
		waveActive = false;
		updateButtons();
	}
	
	
	public void noteOff(int channel, int pitch, int velocity) {
	
		updateButtons();
		
	}
	
	public void controllerChange(int channel, int number, int value) {
		

		if(channel == wv[0] && number == wv[1])
		{
			offset = 4*Math.PI*((float)value/BCF2000.MAX);
		}
		if(channel == gap[0] && number == gap[1])
		{
			rate = (float)value/BCF2000.MAX;
			;
		}
		
		
		if(channel == angleCnrl[0] && number == angleCnrl[1])
		{
			if(value == 127 && last == 127)
			{
				val++;
				
			}
			else if (value == 0 && last == 0)
			{
				val--;
				
			}
			else
			{
				val = value; 
				
			}
			last = value;
			angle = 2*Math.PI*((float)val/BCF2000.MAX);
			System.out.println(value); 
			System.out.println(2*Math.PI*((float)val/BCF2000.MAX)); 
			switchOffSwish();
	
		}
		
		
	}
	
	

}
