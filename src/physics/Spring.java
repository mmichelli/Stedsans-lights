package physics;

import lights.*;

public class Spring {

	private Light la; 
	private Light lb;
	
	private double Mass = 1.5;
	private double Stiffness = 0.1;
	private double Damping = 0.85;
	
	public Spring( Light _la, Light _lb) {
		la = _la; 
		lb = _lb;
		
	}
	
	public void update() {
		double fource = (  lb.getValue() - la.getValue()) * Stiffness;
		
		double factor = fource / Mass;
		la.setVelocity(Damping * (la.getVelocity() + factor));
		la.step();
		
	}

}
