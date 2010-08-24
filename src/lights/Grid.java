package lights;

import java.util.ArrayList;

import processing.core.PApplet;

public class Grid {
	
	
	ArrayList<Light> lights;
	private int[][] ids;

	int blockSize = 40; 
	int gap  = 0;
	int gridGap = 50;
	int gridWidth  = 0;
	int gridHeight = 0 ;
	int midiChannel = 0 ;
	int midiNumber =0 ;
	Affect affect; 
	
	public Grid(int _ids[][], String name, int _gridWidth, int _gridHeight) {
		ids  		= _ids; 
		gridWidth  	= _gridWidth; 
		gridHeight  = _gridHeight; 
		
		loadGrid(); 
		makeAffect(name); 
		setupMidi() ;
		 
	}
	
	private void makeAffect(String name) {
		
		if(name ==  "NoiseAffect")
		{
			affect = new NoiseAffect(lights, gridWidth, gridHeight); 
		}	
		else if(name ==  "WindAffect")
		{
			affect = new WindAffect(lights, gridWidth, gridHeight); 
		}
		
		else if(name ==  "SpringAffect")
		{
			affect = new SpringAffect(lights, gridWidth, gridHeight); 
		}	
		else
		{
			affect = new MouseAffect(lights, gridWidth, gridHeight);
		}
		
	}
	private void setupMidi() 
	{
		this.midiChannel 	= affect.getMidiChannel(); 
		this.midiNumber 	= affect.getMidiNumber(); 
		System.out.println("midiChannel:"+midiChannel+" midiNumber:"+midiNumber+" getWeight:"+affect.getWeight()); 
		BCF2000.sendControllerChange(midiChannel,midiNumber,(int)(affect.getWeight()*BCF2000.MAX) ); 
		
	}
	
	public void noteOn(int channel, int pitch, int velocity) {}

	public void noteOff(int channel, int pitch, int velocity) {}

	public void controllerChange(int channel, int number, int value) {
		if(channel == midiChannel && number == midiNumber  )
		{
			affect.setWeight((float)value/BCF2000.MAX);
		}
		
		
	}
	
	
	private void loadGrid() {
		lights = new ArrayList<Light>();
		int id = 0;
		for (int i = 0; i < gridHeight; i++) 
		{
			for (int j = 0; j < gridWidth; j++) 
			{
				id = (j < ids[i].length )? ids[i][j]: -1; 
				lights.add(new Light(j,i, id ,blockSize,gap)); 
			}
		}
	}
	
	public double getGammaByIndex(int index)
	{
		return lights.get(index).getGamma();
	}
	
	public float getValueByIndex(int index)
	{
		return lights.get(index).getValue();
	}
	
	public float getWeight()
	{
		return affect.getWeight();
	}
	
	public void setWeight(float w)
	{
		affect.setWeight(w);
	}
	public void update(PApplet p, int x, int y)  {
		
		affect.update(p, x, y);
	}
	
	
	public void draw(PApplet p) {
		
		draw(p, 0, 0);
	}	
	
	public void draw(PApplet p, int x, int y) {
		
		float a = this.affect.getWeight(); 
		for (Light l : lights) {
			
			l.draw(p, x, y , a); 
		}
	}
	
	
	
	/**
	 * @return the _lights
	 */
	public ArrayList<Light> get_lights() {
		return lights;
	}

	/**
	 * @param _lights the _lights to set
	 */
	public void set_lights(ArrayList<Light> _lights) {
		this.lights = _lights;
	}

	/**
	 * @return the width of the grid
	 */
	public int getWidth() {
		return gridWidth *(blockSize + gap) + gridGap;
	}



	/**
	 * @return the height of the grid
	 */
	public int getHeight() {
		return ids.length *(blockSize + gap) + gridGap;
	}




	/**
	 * @return the size
	 */
	public int getSize() {
		return blockSize;
	}

	/**
	 * @param size the size to set
	 */
	public void setBlockSize(int size) {
		this.blockSize = size;
	}

	/**
	 * @return the gap
	 */
	public int getGap() {
		return gap;
	}

	/**
	 * @param gap the gap to set
	 */
	public void setGap(int gap) {
		this.gap = gap;
	}

	



}
