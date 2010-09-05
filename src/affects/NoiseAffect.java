package affects;

import java.util.ArrayList;

import lights.BCF2000;
import lights.Light;


import processing.core.PApplet;

public class NoiseAffect extends Affect {

	float offset = 0;
	float scale = 0.5f;
	int[] autoInc = {9,37};
	boolean autoOn = false; 
	
	
	public NoiseAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		
		midiChannel = 1 ;
		midiNumber 	= 9 ;
		weight 		= 0.2f ;
		affectName = "noise";
		button[0] = 9; 
		button[1] = 38;
		resetButton(0);
		 
	}
	
	public void update ( PApplet p,int x, int y){
		p.noiseDetail(4,0.7f);
		
		for (Light l : lights) {
			l.setValue((float)Math.pow( p.noise((l.x+x)*scale,(l.y+x)*scale, offset*scale), 3)); 
			
		}
		offset += 0.01;
		
		if(autoOn && weight < 1)
		{
			setWeight(Math.min(1f,this.weight + 0.005f));
			BCF2000.sendControllerChange(midiChannel,midiNumber,(int)(this.weight * BCF2000.MAX));
		}
		else if(autoOn)
		{
			autoOn = false; 
			BCF2000.sendNoteOn(autoInc[0], autoInc[1], (autoOn)?127:0);	
		}
			
		
	};
	public void noteOn(int channel, int pitch, int velocity) {
		
		if(channel == autoInc[0] && pitch == autoInc[1] && velocity>0 )
		{
			autoOn = !autoOn;
		}
		

		if(channel == button[0] && pitch == button[1] && velocity>0 )
		{
			pos = !pos;
		}
		BCF2000.sendNoteOn(autoInc[0], autoInc[1], (autoOn)?127:0);
		BCF2000.sendNoteOn(button[0], button[1], (pos)?127:0);
	}
	
	public void controllerChange(int channel, int number, int value) {

		if(channel == midiChannel && number == midiNumber)
		{
			autoOn = false;
			BCF2000.sendNoteOn(autoInc[0], autoInc[1], (autoOn)?127:0);
		}
		
	}
	
	
}
