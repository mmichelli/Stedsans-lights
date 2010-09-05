package affects;

import java.util.ArrayList;

import processing.core.PApplet;

import lights.BCF2000;
import lights.Light;

public class RowOutAffect extends Affect {

	
	int[] on = {14,60};
	int[] off = {15,60};
	int rowClip = 0; 
	
	public RowOutAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		weight = 1;
		button[0] = 12; 
		button[1] = 60;
		affectName = "Row Out";
	}
	
	public void noteOn(int channel, int pitch, int velocity) {
		
		if(channel == on[0] && pitch == on[1] && velocity>0 )
		{
			rowClip = Math.min(rowClip +1 , 8); 
		}
		
		if(channel == off[0] && pitch == off[1] && velocity>0 )
		{
			rowClip = Math.max(rowClip-1, 0);;
		}
	
		if(channel == button[0] && pitch == button[1] && velocity>0 )
		{
			pos = !pos;
		}
		
		BCF2000.sendNoteOn(button[0], button[1], (pos)?127:0);
	}
	public void update ( PApplet p,int x, int y)
	{
		float v; 
		for (Light l : lights) {
			v = (l.x > (7 - rowClip))?(Math.max(-3,l.getValue()-0.05f)):(Math.min(0,l.getValue()+0.09f));
			if(l.id != 0)
				l.setValue(v);
		
		}
		
	};


}
