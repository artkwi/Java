package vrpsl;

import java.util.ArrayList;

public class MainClass {

	
	public ArrayList<Solution> solutions_array;
	
	//deklaracja struktury - klasa z chromosomem jako�ci us�ugi i �cie�ki
	// losowanie populacji
	
	public static void main(String[] args) {
		int customers_number = 10;
		int mi_solutions_number = 13;
		int subsets_number = 4;
		
		// create K subsets
		Subsets subsets = new Subsets(subsets_number);
		//System.out.println(subsets.getSubsets_array().get(1).getId());
		
		
		// stworzenie n klient�w
		Customers customers = new Customers(customers_number, subsets_number);
		customers.showCustomers();
		
		Solutions solutions = new Solutions(mi_solutions_number, subsets_number, customers_number);
		solutions.showSolutions();
		
	}
}
