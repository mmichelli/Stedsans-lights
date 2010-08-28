package affects;

import java.util.ArrayList;

import processing.core.PApplet;

import lights.BCF2000;
import lights.Light;

public class SelectAffect extends Affect {
	
	private int[][]ls = {{0,6, 0},
						{1,91, 1},
						{0,93, 2},
						{0,94, 3}};
	
	private Light mouseDown ; 

	public SelectAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		initLights();
		
		midiChannel = 4 ;
		midiNumber = 7 ;
		weight = 0 ;
		affectName = "midi";
		
	}
	
	public void update ( PApplet p,int x, int y)
	{
		mouseDown = null;
		for (Light l : lights) {
			if(p.mousePressed && l.mouseOver(p, x,y))
			{
				mouseDown = l; 
			}

		}
		
	};
	private void initLights(){
		
		for (int[] l : ls) {
			BCF2000.sendControllerChange(l[0],l[1],0 );
			}
		
		
	}
	
	public void noteOn(int channel, int pitch, int velocity) {}

	public void noteOff(int channel, int pitch, int velocity) {}

	public void controllerChange(int channel, int number, int value) {
		int newIndex; 
		for (int[] l : ls) {
			if(channel == l[0] && number == l[1])
			{
				if(mouseDown != null)
				{
					
					newIndex = lights.indexOf(mouseDown);
					if(l[2] != newIndex)
					{
						lights.get(l[2]).setValue(0);
						l[2] = newIndex; 
					}

					mouseDown = null; 
				}

				lights.get(l[2]).setValue((float)value/BCF2000.MAX);
				
			}
		}
		
	}
	

}
