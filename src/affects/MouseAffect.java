package affects;

import java.util.ArrayList;

import lights.Light;


import processing.core.PApplet;

public class MouseAffect extends Affect {
	


	public MouseAffect(ArrayList<Light> lts, int w, int h) {
		super(lts, w, h);
		
		midiChannel = 0 ;
		midiNumber =0 ;
		weight = 0 ;
		affectName = "click";
		
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
				l.setValue((float)(l.getValue()*0.8));
			}
		}
		
	};
	
	
}
