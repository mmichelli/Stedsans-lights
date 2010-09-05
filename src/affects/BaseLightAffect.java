package affects;

import java.util.ArrayList;

import lights.BCF2000;
import lights.Light;

public class BaseLightAffect extends Affect {

	int[] contrast = {0,121};
	
	public BaseLightAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		midiChannel = 7 ;
		midiNumber = 8 ;
		weight = 0 ;
		affectName = "Base";
		button[0] = 9; 
		button[1] = 49; 
		weight 	  = 0.5f ;
		clip = 1; 
		
	
	}
	public void noteOn(int channel, int pitch, int velocity) {
		
		if(channel == button[0] && pitch == button[1] && velocity>0 )
		{
			pos = !pos;
			clip = (pos)?0:0.5f;
			 
		}
		BCF2000.sendControllerChange(button[0],button[1],(int)(clip*BCF2000.MAX) );
		BCF2000.sendNoteOn(button[0], button[1], (pos)?127:0);
		
	}

	public void noteOff(int channel, int pitch, int velocity) {
		
		System.out.println(channel +"  "+ button[0] +"  "+ pitch +"  "+ button[1]);
		if(channel == button[0] && pitch == button[1] )
		{
			System.out.println("                       Off:  "+pos); 
			BCF2000.sendNoteOn(button[0], button[1], (pos)?127:0);
		}
		
	}
public void controllerChange(int channel, int number, int value) {
		

		if(channel == contrast[0] && number == contrast[1])
		{
			
		}
		else if(channel == midiChannel && number == midiNumber)
		{
			float v = (2*(float)value/BCF2000.MAX) ;
			for (Light l : lights) {
				
				clip = v;
				l.setValue(v -1);
			}
		}
		
	}
	public float setWeight(float weight) {

		
		return this.weight = weight;
	}
}
