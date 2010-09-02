package lights;

import com.juanjo.openDmx.OpenDmx;

public class DMX {
	
	
	private static DMX instance = null; 
	private int minValue = 0; 
	private int maxValue = 212; 
	private int minRange = 0; 
	private int maxRange = 512; 
	
	
	static final int[][] DMX_IDS =  {{1,2,3,4,5,6,7,8},{9,10,11,12,13,14,15,16},{17,18,19,20,21,22,23,24},{25,26,27,28,29,30,31,32},{33,34,35,36,37,38,39,40},{41,42,43,44,45,46,47,48},{49,50,51,52,53,54,55,56},{57,58,59,60,61,62,63,64}};
	/*  Some javascripe to generate the grid, so that you can generate then tweak
	 * 	var grid = []; 
		var count = 0; 
		for (var i = 0; i < 8 ;i++) {
		    var ng = []; 
			grid.push(ng); 
			for (var j = 0; j < 8; j++) {
				count++; 
				ng[j] = count; 
			};
};

JSON.stringify(grid).replace(/\[/g,"{").replace(/\]/g,"}");
	 * 
	 * 
	 * 
	 * 
	 */
	//write a function to print this out
	
	
    public void stop() {
    	OpenDmx.disconnect();
    	instance = null; 
	}
	public void setValue(int id, int value) {
		OpenDmx.setValue(Math.max(minRange, Math.min(id, maxRange)), Math.max(minValue, Math.min(value, maxValue)));
	}
	public void setValue(int id, float value, double gamma) {
		
		double corrected=Math.pow(value , gamma); 
		setValue(id, (int)(corrected*maxValue));
		//System.out.println(value);
	}
    public static DMX getInstance() {
	      if(instance == null) {
	         instance = new DMX();
	      }
	      return instance;
	 }
    
    
	protected DMX() {

		start();	
	}
    
    private void start() {
		if (!OpenDmx.connect(OpenDmx.OPENDMX_TX)) {
			System.out.println("Open Dmx widget not detected!");
			  return;
			} 
		else{ 
			System.out.println("Open Dmx widget up running!");
		}
	}
    

    

}
