package lights;

import java.util.ArrayList;

import processing.core.PApplet;

public class GridsController {

	
	ArrayList<Grid> grids;
	
	String[] nGrids ; 
	double[][][] ids;
	DMX dmx; 
	int gridWidth  = 0;
	int gridHeight = 0 ;
	BCF2000 device;
	
	public GridsController( double[][][] _ids, String[] _nGrids, BCF2000 _device) {
		
	 
		ids  	= _ids; 
		nGrids 	= _nGrids;
		device	= _device;
		init(); 
		dmx 	= DMX.getInstance(); 
		
	}
	
	private void init() 
	{
		grids = new ArrayList<Grid>();
		getWidthHeight();
		for (int i = 0; i < nGrids.length; i++) {
			grids.add(new Grid(ids,nGrids[i],gridWidth, gridHeight));
		}
	}
	
	public void updateLights(PApplet p)
	{
		updateLights();
		
	}
	
	public void noteOn(int channel, int pitch, int velocity) {
		for (Grid g : grids) {
			g.noteOn(channel, pitch, velocity); 
		}
	}

	public void noteOff(int channel, int pitch, int velocity) {
		for (Grid g : grids) {
			g.noteOff(channel, pitch, velocity); 
		}
	}

	public void controllerChange(int channel, int number, int value) {
		for (Grid g : grids) {
			g.controllerChange(channel, number, value); 
		}
	}
	
	
	private void getWidthHeight()
	{
		gridWidth  = 0 ; 
		gridHeight = ids.length; 
		for (int i = 0; i < gridHeight; i++) 
		{
			gridWidth = Math.max(gridWidth,ids[i].length);
		}
		 
	}
	
	
	private void updateLights()
	{
		
		float average  ;
		float total; 
		int index ;
	//	System.out.println(" --------------------------"); 
		for (int i = 0; i < gridHeight; i++) 
		{
			for (int j = 0; j < gridWidth; j++) 
			{
				index  = (i*gridWidth)+ j ;
				average = 0;
				total = 0;
				
	
				for (Grid g : grids) { 
					average += g.getValueByIndex(index)*g.getWeight();
					total += g.getWeight();
					average *= g.getClip(); 
					
				}	
				//don't think we need to divide it by the average
				
				average = Math.max(0,average); 
				average = Math.min(1,average); 
				//System.out.print(average + "\t");
				
				dmx.setValue((j < ids[i].length )? (int)ids[i][j][0]: -1, average/*/Math.max(1, total)*/,ids[i][j][1] );
			}
		}
		
	}	
	
	
	
	public void draw(PApplet p, int _x, int _y) {
		
		int x = _x ;
		int y = _y;
		
		for (Grid g : grids) {
			int w = x +g.getWidth() ; 
			if( w > p.width)
			{
				y += g.getHeight() ; 
				x =  _x;
			}
			g.update(p, x, y);
			g.draw(p, x, y ); 
			x += g.getWidth(); 
		}
	}

}
