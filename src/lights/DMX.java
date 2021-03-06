package lights;

import com.juanjo.openDmx.OpenDmx;

public class DMX {
	
	
	private static DMX instance = null; 
	private int minValue = 0; 
	private int maxValue = 212; 
	private int minRange = 0; 
	private int maxRange = 512; 
	
	
	static final double[][][] DMX_IDS =  {{{0,0.8},{1,0.8},{2,0.8},{3,0.8},{4,0.8},{5,0.8},{6,0.8},{7,0.8}},{{8,0.8},{9,0.8},{10,0.8},{11,0.8},{12,0.8},{13,0.8},{14,0.8},{15,0.8}},{{16,0.8},{17,0.8},{18,0.8},{19,0.8},{20,0.8},{21,0.8},{22,0.8},{23,0.8}},{{24,0.8},{25,0.8},{26,0.8},{27,0.8},{28,0.8},{29,0.8},{30,0.8},{31,0.8}},{{32,0.8},{33,0.8},{34,0.8},{35,0.8},{36,0.8},{37,0.8},{38,0.8},{39,0.8}},{{40,0.8},{41,0.8},{42,0.8},{43,0.8},{44,0.8},{45,0.8},{46,0.8},{47,0.8}},{{48,0.8},{49,0.8},{50,0.8},{51,0.8},{52,0.8},{53,0.8},{54,0.8},{55,0.8}},{{56,0.8},{57,0.8},{58,0.8},{59,0.8},{60,0.8},{61,0.8},{62,0.8},{63,0.8}}};
	
	/*  Some javascripe to generate the grid, so that you can generate then tweak
	 * 	
	  	var grid = []; 
	  	var gamma = 0.8; 
		var count = 0; 
		for (var i = 0; i < 8 ;i++) {
		    var ng = [];
		    var g = [];  
			grid.push(ng); 
		
			for (var j = 0; j < 8; j++) {
				 
				ng[j] = [count,gamma]; 
				count++;
			};
		};

		console.log(JSON.stringify(grid).replace(/\[/g,"{").replace(/\]/g,"}"));

	 */
	
	
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
	}
	public int getValue(int id) {
		return OpenDmx.getValue(id);	
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
