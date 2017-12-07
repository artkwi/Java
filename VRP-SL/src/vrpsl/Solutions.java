package vrpsl;

import java.awt.List;
import java.util.ArrayList;

public class Solutions {
	private ArrayList<Solution> solutions_array = new ArrayList<>();
	private int mi_size;
	
	
	public Solutions() {
	}
	public Solutions(int mi, int k_subsets, int n_customers) {
		setMi_size(mi);
		setSolutions_array(mi, k_subsets, n_customers);
	}

	
	
	// Print all solutions
	public void showSolutions() {
		for (Solution solution : solutions_array) {
			ArrayList<Double> service_lvl_chromosome = solution.getService_lvl_chromosome();
			for (Double field : service_lvl_chromosome) {
				System.out.print(field + "  ");
			}
			System.out.print("  ");
			ArrayList<Integer> chromoseome = solution.getRout_chromosome();
			for (Integer field : chromoseome) {
				System.out.print(field+" ");
			}
			System.out.println("");
		}
	
	}
	
	// getters and setters
	public ArrayList<Solution> getSolutions_array() {
		return solutions_array;
	}
	public void setSolutions_array(int mi, int k_subsets, int n_customers) {
		for (int i = 0; i < 4*mi; i++) {
			solutions_array.add(new Solution(mi, k_subsets, n_customers));
		}
	}
	public int getMi_size() {
		return mi_size;
	}
	public void setMi_size(int mi_size) {
		this.mi_size = mi_size;
	}
	
	

}
