package vrpsl;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.CustomMarshal;

public class Solution {
	
	private ArrayList<Double> service_lvl_chromosome;
	private ArrayList<Integer> rout_chromosome;
	private float biased;
	
	private ArrayList<Integer> neighbour_distance_array = new ArrayList<>();
	private ArrayList<Integer> deopt_customers_distance_array = new ArrayList<>();
	private ArrayList<Integer> cumulative_neighbour_distance_array = new ArrayList<>(); //D[i]
	private ArrayList<Integer> cumulative_demand_array = new ArrayList<>();	// Q[i]
	
	
	// SPLIT
	private ArrayList<Integer> p_array;
	private ArrayList<Integer> A_array;
	private ArrayList<Integer> pred_array;
	
	// M fleet split
	private ArrayList<ArrayList<Integer>> p2_array;
	private ArrayList<ArrayList<Integer>> pred2_array;
	
	// SPLIT
	
	// COST FUNCTION
	private double cost =  0;
	private int total_profit = 0;
	
	public Solution() {
		
	}
	
	// Constructor with mi size, concerning K_subsets and n_customers
	public Solution(int mi, int K_subsets, int n_customers) {
		// Service level chromosome has K_subsets numbers of fields
		service_lvl_chromosome = new ArrayList<>(K_subsets+1);
		
		
		
		// Random solution length between [1..n]
		Random random = new Random();
		int solution_size = random.nextInt(n_customers)+1;
		rout_chromosome = new ArrayList<>(solution_size);
		
		// add field to chromosome when field has never occured
		for (int i = 0; i < solution_size; i++) {
			int current_field = random.nextInt(n_customers)+1;
			while (rout_chromosome.contains(current_field)) {
				current_field = random.nextInt(n_customers)+1;
			}
			rout_chromosome.add(current_field);
			
		}
		setZeroServiceLevChromosome(K_subsets);
		setService_lvl_chromosome();
		fillNeighbourDistanceArray();
		fillCumulativeNeighbourDistanceArray();
		fillDepotCustomerArray();
		fillCumulativeDemand();
		linearSplit();
		
		computeCostFunction();
		showSolution();
		
	}
	
	
	// DODAJ WSPÓ£CZYNNIK KARY WQ
	public void computeCostFunction() {
		this.cost = Customers.getTotal_profits() - computeTotalProfit()  + Math.max(0, p_array.get(p_array.size()-1) -MainClass.Q);
	}
	
	public int computeTotalProfit() {
		for (Integer field : rout_chromosome) {
			total_profit +=Customers.getCustomers_array().get(field).getProfit();
		}
		return total_profit;
	}
	
	
	// SPLIT 
	
	// nie u¿ywane
	public void linearSplit_M_fleet() {
		p2_array = new ArrayList<ArrayList<Integer>>();
		pred2_array = new ArrayList<ArrayList<Integer>>();
		int n = rout_chromosome.size();
		
		for (int k = 0; k <= MainClass.m; k++) {
			p2_array.add(new ArrayList<>());
			p2_array.get(k).add(0);
			pred2_array.add(new ArrayList<>());
			for (int t = 0; t <= n; n++) {
				p2_array.get(k).add(999999);
				pred2_array.get(k).add(0);
			}
		}
		
		for (int k = 0; k <= MainClass.m ; k++) {
			
			A_array = new ArrayList<>();
			A_array.add(k);
			for (int t = k+1 ; t <= n; t++) {
				if (!A_array.isEmpty()) {
					p2_array.get(k+1).set(t, p2_array.get(k).get(A_array.get(0)) + f(A_array.get(0), t));
					pred2_array.get(k+1).set(t, A_array.get(0));
					if (t < n) {
						//if (!dominates(i, j))
					}
				}
			}
			
		}
		
	}
	
	public void linearSplit() {
		p_array = new ArrayList<>();
		A_array = new ArrayList<>();
		pred_array = new ArrayList<>();
		int n = rout_chromosome.size();
		
		p_array.add(0);
		A_array.add(0);
		
		for (int t = 1; t <= n; t++) {
			p_array.add(p_array.get(A_array.get(0))+f(A_array.get(0),t));
			pred_array.add(A_array.get(0));
			if (t < n) {
				if (!dominates(A_array.get(A_array.size()-1),t)) {
					while ((A_array.size() > 0) && (dominates(t,A_array.get(A_array.size()-1)))) {
						A_array.remove(A_array.size()-1);
					}
					A_array.add(t);
				}
//				while (cumulative_demand_array.get(t+1) > MainClass.Q + cumulative_demand_array.get(0)) {
//					A_array.remove(0);
//				}
			}
		}	
	}
	
	
	public Integer f(int i, int x) {
		//if (cumulative_demand_array.get(x) - cumulative_demand_array.get(i) <= MainClass.Q) {
			return (p_array.get(i) + c(i,x));
		//} else return 99999;
	}
	
	public Integer c(int i, int j) {
		return (deopt_customers_distance_array.get(i+1) + cumulative_neighbour_distance_array.get(j) - cumulative_neighbour_distance_array.get(i+1) + deopt_customers_distance_array.get(j));
	}
	
	public Boolean dominates(int i, int j) {
		if (i <= j) {
			if ((p_array.get(i) + deopt_customers_distance_array.get(i+1) - cumulative_neighbour_distance_array.get(i+1) <= p_array.get(j) + deopt_customers_distance_array.get(j+1) - cumulative_neighbour_distance_array.get(j+1))&&(cumulative_demand_array.get(i)==cumulative_demand_array.get(j))) {
				return true;
			}	
		} 
		if (i > j) {
			if (p_array.get(i) + deopt_customers_distance_array.get(i+1) - cumulative_neighbour_distance_array.get(i+1) <= p_array.get(j) + deopt_customers_distance_array.get(j+1) - cumulative_neighbour_distance_array.get(j+1))  {
				return true;
			}
		}
		return false;
	}
	
	public void showSolution() {
		System.out.println("\n\nSolution  chromoseome:  - COST=" + getCost());
		for (Integer field : rout_chromosome) {
			System.out.print(field + " ");
		}
		System.out.println("\nneighbours distance");
		for (Integer neighbour : neighbour_distance_array) {
			System.out.print(neighbour + " ");
		}
		System.out.println("\ndepot-customer distance");
		for (Integer neighbour : deopt_customers_distance_array) {
			System.out.print(neighbour + " ");
		}
		System.out.println("\ncumulative neighbours distance");
		for (Integer neighbour : cumulative_neighbour_distance_array) {
			System.out.print(neighbour + " ");
		}
		System.out.println("\ncumulative demand");
		for (Integer neighbour : cumulative_demand_array) {
			System.out.print(neighbour + " ");
		}
		System.out.println("\np_array:");
		for (Integer p : p_array) {
			System.out.print(p + " ");
		}
		
		System.out.println("\npred_array:");
		for (Integer integer : pred_array) {
			System.out.print(integer + " ");
		}
		System.out.println("\nA_array:");
		for (Integer integer : A_array) {
			System.out.print(integer + " ");
		}
		
	}
	
	// SPLIT
	

	// fill cumulative demand POPRAW
		public void fillCumulativeDemand() {
			cumulative_demand_array.add(0);
			cumulative_demand_array.add(Customers.getDemand(rout_chromosome.get(0)));
			for (int i = 1; i < rout_chromosome.size(); i++) {
				cumulative_demand_array.add(cumulative_demand_array.get(i) + Customers.getDemand(rout_chromosome.get(i)));
				}	
			}
	
	// fill distances between depot and i-customer
	public void fillDepotCustomerArray() {
		deopt_customers_distance_array.add(0);
		for (int i = 0; i < rout_chromosome.size(); i++) {
			deopt_customers_distance_array.add(Customers.getDistance(0, rout_chromosome.get(i)));
			}	
		}
	
	// sum up previous distance to distance between previous and current customer
	public void fillCumulativeNeighbourDistanceArray() {
		cumulative_neighbour_distance_array.add(0);
		cumulative_neighbour_distance_array.add(Customers.getDistance(0, rout_chromosome.get(0)));
		for (int i = 1; i < rout_chromosome.size(); i++) {
				cumulative_neighbour_distance_array.add(cumulative_neighbour_distance_array.get(i)+Customers.getDistance(rout_chromosome.get(i-1), rout_chromosome.get(i)));
			}	
		}
	
	// fill distances between previous and current customer
	public void fillNeighbourDistanceArray() {
		neighbour_distance_array.add(0);
		neighbour_distance_array.add(Customers.getDistance(0, rout_chromosome.get(0)));
		for (int i = 1; i < rout_chromosome.size(); i++) {
				neighbour_distance_array.add(Customers.getDistance(rout_chromosome.get(i-1), rout_chromosome.get(i)));
			}	
		}
	
	public void setZeroServiceLevChromosome(int K_subsets ) {
		for (int i = 0; i < K_subsets; i++) {
			service_lvl_chromosome.add(0.0);
		}
	}
	
	// getters and setters
	public ArrayList<Double> getService_lvl_chromosome() {
		return service_lvl_chromosome;
	}
	
	// Compute service level chromosome
	public void setService_lvl_chromosome() {
		for (Integer field : rout_chromosome) {
			int subset = Customers.checkSubsetOfField(field);
			service_lvl_chromosome.set(subset, service_lvl_chromosome.get(subset)+1);
		}
		for (int i = 0; i < service_lvl_chromosome.size(); i++) {
			double subset_amout = (double) Subsets.getSubsets_amount_k(i);
			if (subset_amout != 0) {
			service_lvl_chromosome.set(i,service_lvl_chromosome.get(i) / subset_amout);
			}
		}
		
	}
	
	public ArrayList<Integer> getRout_chromosome() {
		return rout_chromosome;
	}
	public void setRout_chromosome(ArrayList<Integer> rout_chromosome) {
		this.rout_chromosome = rout_chromosome;
	}
	public float getDopasowanie() {
		return biased;
	}
	public void setDopasowanie(float dopasowanie) {
		this.biased = dopasowanie;
	}

	public double getCost() {
		return cost;
	}
	
	
}
