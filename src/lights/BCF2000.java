package lights;

import processing.core.PApplet;
import themidibus.MidiBus;

public class BCF2000 {
	
	private static BCF2000 instance = null; 
	protected MidiBus device; // The MidiBus
	public static final int MAX = 127;
	
	private BCF2000(PApplet p) {
		MidiBus.list(); 
		device = new MidiBus(p,0,2);
	}
	
	
	
    public static BCF2000 getInstance(PApplet p) {
	      if(instance == null) {
	         instance = new BCF2000(p);
	      }
	      return instance;
	 }
  
  

	
	public static void sendNoteOn(int channel,int  number, int value)
	{
		BCF2000.instance.device.sendNoteOn(channel, number, value);
	}
	public static void sendNoteOff(int channel,int  number, int value)
	{
		BCF2000.instance.device.sendNoteOff(channel, number, value);
	}
	public static void sendControllerChange(int channel,int  number, int value)
	{
		BCF2000.instance.device.sendControllerChange(channel, number, value);
	}

}
