package vrpsl;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Solution {
	
	private ArrayList<Double> service_lvl_chromosome;
	private ArrayList<Integer> rout_chromosome;
	private float biased;
	
	private ArrayList<Double> neighbour_distance_array = new ArrayList<>();;
	private ArrayList<Double> deopt_customers_distance_array = new ArrayList<>();
	private ArrayList<Double> cumulative_neighbour_distance_array = new ArrayList<>();
	private ArrayList<Double> cumulative_demand_array = new ArrayList<>();
	
	public Solution() {
		
	}
	
	// Constructor with mi size, concerning K_subsets and n_customers
	public Solution(int mi, int K_subsets, int n_customers) {
		// Service level chromosome has K_subsets numbers of fields
		service_lvl_chromosome = new ArrayList<>(K_subsets+1);
		
		
		
		// Random solution length beetwen [1..n]
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
		
		//fillDepotCustomerArray();
	}
	
	
	

	// fill cumulative demand POPRAW
		public void fillCumulativeDemand() {
			cumulative_demand_array.add(0.0);
			for (int i = 1; i < rout_chromosome.size(); i++) {
				cumulative_demand_array.add(cumulative_demand_array.get(i-1) + Customers.getDemand(i));
				}	
			}
	
	// fill distances between depot and i-customer
	public void fillDepotCustomerArray() {
		deopt_customers_distance_array.add(0.0);
		for (int i = 1; i < rout_chromosome.size(); i++) {
			deopt_customers_distance_array.add(Customers.getDistance(0, i));
			}	
		}
	
	// sum up previous distance to distance between previous and current customer
	public void fillCumulativeNeighbourDistanceArray() {
		cumulative_neighbour_distance_array.add(0.0);
		for (int i = 1; i < rout_chromosome.size(); i++) {
				cumulative_neighbour_distance_array.add(cumulative_neighbour_distance_array.get(i-1)+Customers.getDistance(rout_chromosome.get(i-1), rout_chromosome.get(i)));
			}	
		}
	
	// fill distances between previous and current customer
	public void fillNeighbourDistanceArray() {
		neighbour_distance_array.add(0.0);
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
	
	
}
