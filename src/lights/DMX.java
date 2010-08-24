package lights;

import com.juanjo.openDmx.OpenDmx;

public class DMX {
	
	
	private static DMX instance = null; 
	private int minValue = 0; 
	private int maxValue = 212; 
	private int minRange = 0; 
	private int maxRange = 512; 
	
	
	static final int[][] DMX_IDS =  {{ 0, 1, 2, 3, 4},
									{ 5, 6, 7, 8, 9},
									{10,11,12,13,14},
									{10,11,12,13,14},
									{10,11,12,13,14},
									{15,16,17,18,78}};
	
	
	
	
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
