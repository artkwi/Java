package vrpsl;

import java.util.ArrayList;

public class Customers {
	private static ArrayList<Customer> customers_array;
	private int size;
	// default constructor create 5 Customers
	public Customers() {
		customers_array = new ArrayList<>();
		setCustomers_array(5);
		setSize(5);
	}
	// constructor with "n" number of Customers
	public Customers(int n_customers) {
		customers_array = new ArrayList<>();
		setCustomers_array(n_customers);
		setSize(n_customers);
	}
	
	// constructor with "n" numbers of Customers and assigning to [1..K] set
	public Customers(int n_customers, int k_sets) {
		customers_array = new ArrayList<>();
		setCustomers_array(n_customers, k_sets);
		setSize(n_customers);
	}
	
	
	public static Integer checkSubsetOfField(int field) {
		return customers_array.get(field-1).getSubset();
	}
	
	// Show Customers in array
	public void showCustomers() {
		for (int i=0; i < getSize(); i++) {
			Customer c = customers_array.get(i);
			System.out.println(i + ": id: " + c.getId() + 
			"   Demand: " + c.getDemand() + 
			"   Profit: " + c.getProfit() +
			"   Subset: " + c.getSubset() +
			"   Coordinates [X,Y]=[" + c.getCoord_x() + "," + c.getCoord_y() + "]\n");
		}
	}
	
	// getters and setters
	public ArrayList<Customer> getCustomers_array() {
		return customers_array;
	}
	// add "n"  Customers
	public void setCustomers_array(int n) {
		for (int i = 0; i < n; i++) {
			customers_array.add(new Customer());
		}
	}
	
	public void setCustomers_array(int n, int k) {
		for (int i = 0; i < n; i++) {
			customers_array.add(new Customer(k));
		}
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
