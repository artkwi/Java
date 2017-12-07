package vrpsl;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Solution {
	
	private ArrayList<Double> service_lvl_chromosome;
	private ArrayList<Integer> rout_chromosome;
	private float biased;
	
	public Solution() {
		
	}
	
	// Constructor with mi size, concerning K_subsets and n_customers
	public Solution(int mi, int K_subsets, int n_customers) {
		// Service level chromosome has K_subsets numbers of fields
		service_lvl_chromosome = new ArrayList<>(K_subsets);
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
			service_lvl_chromosome.set(i, service_lvl_chromosome.get(i) / Subsets.getSubsets_amount_k(i));
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
