package vrpsl;

import java.util.Random;

public class Subset {
	public static int customers_of_subsets=0;
	private int id;
	private double service_level;
	private double penalty;
	private static int subset_counter = 0;
	
	public Subset() {
		setId();
		setService_level();
		setPenalty();
		customers_of_subsets++;
	}
	
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = ++subset_counter;
	}
	public double getService_level() {
		return service_level;
	}
	public void setService_level() {
		Random random = new Random();
		this.service_level = Math.round((1.0 - (random.nextDouble()/2.0)) *100.00) / 100.00;	
	}


	public double getPenalty() {
		return penalty;
	}


	public void setPenalty() {
		Random random = new Random();
		this.penalty = (random.nextDouble())*2+3.0;	
	}
	
	
	
	

}
