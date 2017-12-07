package vrpsl;

import java.util.Random;

public class Customer {
	
	private int id;
	private int demand;
	private int profit;
	private int subset; // belong to one of "K" subsets
	private double coord_x;
	private double coord_y;
	private static int customer_counter = 0;

	public Customer() {
		setId(); 		// increment id of  customer
		setDemand();	// do poprawy
		setProfit(6);	//do poprawy
	}
	
	public Customer(int k) {
		setId(); 		// increment id of  customer
		setDemand();	// do poprawy
		setProfit(6);	//do poprawy
		setSubset(k);	// random subset
		setCoords_xy(10); // Random coordinates xy
	}

	
	
	// getters and setters
	
	public int getSubset() {
		return subset;
	}



	public void setSubset(int subset) {
		Random random = new Random();
		int subs = random.nextInt(subset);
		this.subset = subs;
		Subsets.increseK_subset_amount(subs);
	}


	public void setCoords_xy(double range) {
		this.coord_x = coord_x;
		Random random = new Random();
		this.coord_x = random.nextDouble()*range;
		this.coord_y = random.nextDouble()*range;
	}
	
	
	public double getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(double coord_x) {
		this.coord_x = coord_x;
	}

	public double getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(double coord_y) {
		this.coord_y = coord_y;
	}

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = ++customer_counter;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand() {
		this.demand = 5; 
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public static int getCustomer_counter() {
		return customer_counter;
	}
	
	
}