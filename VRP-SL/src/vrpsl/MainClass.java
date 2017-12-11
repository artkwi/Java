package vrpsl;

import java.util.ArrayList;

public class MainClass {

	
	public ArrayList<Solution> solutions_array;
	public final static int Q = 20;			//capacity of one vehicle
	public final static int m = 4;			//number of vehicles
	public final static double Wq = 2.0;	//penalty factor for overload capacity
	
	//deklaracja struktury - klasa z chromosomem jakoœci us³ugi i œcie¿ki
	// losowanie populacji
	
	public static void main(String[] args) {
		int customers_number = 10;
		int mi_solutions_number = 13;
		int subsets_number = 4;
		
		// create K subsets
		Subsets subsets = new Subsets(subsets_number);
		//System.out.println(subsets.getSubsets_array().get(1).getId());
		Subsets.showSubsetsServicelever();
		
		
		// stworzenie n klientów
		Customers customers = new Customers(customers_number, subsets_number);
		customers.showCustomers();
		customers.showDistance_matrix();
		
		Solutions solutions = new Solutions(mi_solutions_number, subsets_number, customers_number);
		solutions.showSolutions();
		
		System.out.println("\n\n" +Customers.getTotal_profits());
		
	}
}
