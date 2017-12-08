package vrpsl;

import java.util.ArrayList;

public class Subsets {
	private ArrayList<Subset> subsets_array;
	private int K_size;
	public static ArrayList<Integer> subsets_amount = new ArrayList<>();
	
	public Subsets() {	
	}
	
	public Subsets(int k_size) {
		setK_size(k_size);
		//subsets_amount = new ArrayList<>(k_size);
		subsets_array = new ArrayList<>();
		for (int i = 0; i < k_size; i++) {
			subsets_array.add(new Subset());
		}
		setZeroSubsets_amount(k_size);

	}

	// increase number of defined set
	
	public static void increseK_subset_amount(int subset_number) {
		subsets_amount.set(subset_number, subsets_amount.get(subset_number)+1);
		//System.out.println(subset_number);
	}
	
	public static int getSubsets_amount_k(int which_subset) {
		return subsets_amount.get(which_subset);
	}

	public static void setZeroSubsets_amount(int K_size) {
		for (int i = 0; i <K_size; i++) {
			subsets_amount.add(0);
		}
	}

	// getters ans setters
	public ArrayList<Subset> getSubsets_array() {
		return subsets_array;
	}

	public void setSubsets_array(ArrayList<Subset> subsets_array) {
		this.subsets_array = subsets_array;
	}

	public int getK_size() {
		return K_size;
	}

	public void setK_size(int k_size) {
		K_size = k_size;
	}

}
