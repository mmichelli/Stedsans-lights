package affects;

import java.util.ArrayList;

import lights.Light;

	public class AffectFactory {

		public static final String NOISE = "NoiseAffect";
		public static final String WIND = "WindAffect";
		public static final String SPRING = "SpringAffect";
		public static final String MOUSE = "MouseAffect";
		public static final String SELECT = "SelectAffect";
		public static final String WAVE = "WaveAffect";
		public static final String BASE = "BaseLightAffect";
		
		

		public static Affect makeAffect(String name, ArrayList<Light> lights, int gridWidth, int gridHeight ) {
    		
    		if(name ==  AffectFactory.NOISE)
    		{
    			return  new NoiseAffect(lights, gridWidth, gridHeight); 
    		}	
    		else if(name ==  AffectFactory.WAVE)
    		{
    			return new WaveAffect(lights, gridWidth, gridHeight); 
    		}
    		else if(name ==  AffectFactory.BASE)
    		{
    			return new BaseLightAffect(lights, gridWidth, gridHeight); 
    		}
    		else if(name ==  AffectFactory.WIND)
    		{
    			return new WindAffect(lights, gridWidth, gridHeight); 
    		}
    		else if(name ==  AffectFactory.SELECT)
    		{
    			return new SelectAffect(lights, gridWidth, gridHeight); 
    		}    		
    		else if(name ==  AffectFactory.SPRING)
    		{
    			return new SpringAffect(lights, gridWidth, gridHeight); 
    		}	
    		else
    		{
    			return new MouseAffect(lights, gridWidth, gridHeight);
    		}
    		
    	}
	 
    
    
	
}
