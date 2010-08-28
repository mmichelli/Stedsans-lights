package affects;

import java.util.ArrayList;

import lights.Light;

import processing.core.PApplet;

public abstract class Affect {
	public String affectName = "none";
	protected ArrayList<Light> lights; 
	protected int gridWidth  = 0 ; 
	protected int gridHeight = 0 ; 
	protected int midiChannel  ;
	protected int midiNumber ;
	protected float weight  ;
	
	
	
	public void noteOn(int channel, int pitch, int velocity) {}

	public void noteOff(int channel, int pitch, int velocity) {}

	public void controllerChange(int channel, int number, int value) {

		
	}
	
	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public float setWeight(float weight) {
		return this.weight = weight;
	}

	/**
	 * @return the midiChannel
	 */
	public int getMidiChannel() {
		return midiChannel;
	}

	/**
	 * @param midiChannel the midiChannel to set
	 */
	public void setMidiChannel(int midiChannel) {
		this.midiChannel = midiChannel;
	}

	/**
	 * @return the midiNumber
	 */
	public int getMidiNumber() {
		return midiNumber;
	}

	/**
	 * @param midiNumber the midiNumber to set
	 */
	public void setMidiNumber(int midiNumber) {
		this.midiNumber = midiNumber;
	}

	public  Affect(ArrayList<Light> lts, int w, int h) {
		lights = lts;
		gridWidth  = w ; 
		gridHeight = h ;
	}
	
	public void update ( PApplet p,int x, int y){};
	


		
	

}
