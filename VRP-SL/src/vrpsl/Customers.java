package vrpsl;

import java.util.ArrayList;

public class Customers {
	private static ArrayList<Customer> customers_array;
	private static ArrayList<ArrayList<Integer>> distance_matrix;
	private int size;
	private static int total_profits = 0;
	// default constructor create 5 Customers
	public Customers() {}
	
	
	// constructor with "n" numbers of Customers and assigning to [1..K] set
	public Customers(int n_customers, int k_sets) {
		customers_array = new ArrayList<>();
		distance_matrix = new ArrayList<ArrayList<Integer>>();
		setCustomers_array(n_customers, k_sets);
		setSize(n_customers);
		computeDistance_matrix(n_customers);
		computeTotalProfits();
	}
	
	public static int getTotal_profits() {
		return total_profits;
	}


	public static void computeTotalProfits() {
		for (Customer customer : customers_array) {
			total_profits += customer.getProfit();
		}
	}
	
	// Distance between 2 specified customers
	public static Integer getDemand(int customer) {
		return customers_array.get(customer).getDemand();
	}
	
	public static Integer getDistance(int customer_1, int customer_2) {
		return distance_matrix.get(customer_1).get(customer_2);
	}
	
	public void computeDistance_matrix(int n_customers) {
		for (int i = 0; i <= n_customers; i++) {
			distance_matrix.add(new ArrayList<>());
			for (int j = 0; j <= n_customers; j++) {
				if (i==j) {
					distance_matrix.get(i).add(0);
				} else {
				Customer c1 = customers_array.get(j);
				Customer c2 = customers_array.get(i);
				Integer distance = (int) Math.round(Math.sqrt(Math.pow((c1.getCoord_x()-c2.getCoord_x()), 2) + Math.pow((c1.getCoord_y() - c2.getCoord_y()), 2))); 
				distance_matrix.get(i).add(distance);
				}
			}			
		}

		
	}
	
	public void showDistance_matrix() {
		for (ArrayList<Integer> distance_row : distance_matrix) {
			for (Integer distance : distance_row) {
//				System.out.print(Math.round(distance*100.0)/100.0+" ");
				System.out.print(distance+ " ");
			}
			System.out.println("");
		}
	}
	
	public static Integer checkSubsetOfField(int field) {
		return customers_array.get(field).getSubset();
	}
	
	// Show Customers in array
	public void showCustomers() {
		for (int i=0; i < customers_array.size(); i++) {
			Customer c = customers_array.get(i);
			System.out.println(i + ": id: " + c.getId() + 
			"   Demand: " + c.getDemand() + 
			"   Profit: " + c.getProfit() +
			"   Subset: " + c.getSubset() +
			"   Coordinates [X,Y]=[" + c.getCoord_x() + "," + c.getCoord_y() + "]\n");
		}
	}
	
	// getters and setters
	public static ArrayList<Customer> getCustomers_array() {
		return customers_array;
	}
	
	
	// add "n"  Customers with "k" subsets
	public void setCustomers_array(int n, int k) {
		for (int i = 0; i <= n; i++) {
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
