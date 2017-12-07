package vrpsl;

public class Subset {
	public static int customers_of_subsets=0;
	private int id;
	private double service_level;
	private static int subset_counter = 0;
	
	public Subset() {
		setId();
		setService_level(0.65);
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
	public void setService_level(double d) {
		this.service_level = d;
	}
	
	

}
