package lights;

import affects.AffectFactory;
import processing.core.PApplet;
import processing.core.PFont;


/*
 *  Grid < Class
 *  	Lights 		< Class[][]
 *  	Controller 	< Interface
 *  
 *  GridsController	//Updates Grid 
 *  LightMixer < Class 	//Updates the lights
 *  |
 */

public class Main extends PApplet {
	
	private static final long serialVersionUID = 5629084174219345456L;

	private GridsController gc; 
	PFont font;

	
	String[] nGrids = {AffectFactory.MOUSE,AffectFactory.NOISE,AffectFactory.SPRING,AffectFactory.WIND ,AffectFactory.SELECT}; 
	
	
	  public static void main(String args[])
	    {
	      PApplet.main(new String[] { "--present", lights.Main.class.getName() });
	    }


    
	public void setup() {
		frameRate(25);
		size(1100,500 );
		gc = new GridsController(DMX.DMX_IDS,nGrids,BCF2000.getInstance(this));
		
		
		//font = loadFont("Calibri-13.vlw"); 
		//textFont(font, 13); 
		
		
		
	}
	
	
	
	public void draw() {

		background(0);
		 
		gc.updateLights(this); 
		gc.draw(this, 20, 20);
		
		
		  
	}
	

	public void stop() {
		DMX.getInstance().stop();
		
	
	}
	

	public void noteOn(int channel, int pitch, int velocity) {
		// Receive a noteOn
		println();
		println("Note On:");
		println("--------");
		println("Channel:"+channel);
		println("Pitch:"+pitch);
		println("Velocity:"+velocity);
		
		gc.noteOn( channel,  pitch,  velocity);
	}

	public void noteOff(int channel, int pitch, int velocity) {
		// Receive a noteOff
		println();
		println("Note Off:");
		println("--------");
		println("Channel:"+channel);
		println("Pitch:"+pitch);
		println("Velocity:"+velocity);
		
		gc.noteOff( channel,  pitch,  velocity);
	}

	public void controllerChange(int channel, int number, int value) {
		// Receive a controllerChange
		println();
		println("Controller Change:");
		println("--------");
		println("Channel:"+channel);
		println("Number:"+number);
		println("Value:"+value);
		
		gc.controllerChange( channel,  number,  value);
	}

}
