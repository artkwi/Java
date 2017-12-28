package vrpsl;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Solutions {
	private static ArrayList<Solution> solutions_array = new ArrayList<>();
	private static int feasible_number = 0;
	private static int infeasible_number = 0;
	private int mi_size;
	private Solution C_child = new Solution();
	
	
	public Solutions() {
	}
	public Solutions(int mi, int k_subsets, int n_customers) {
		setMi_size(mi);
		setSolutions_array(mi, k_subsets, n_customers);
		computDiversity();
		computeBiasedFunction();
		C_child = crossoverParents();
	}
	
	// 	CROSSOVER
	public Solution crossoverParents() {
		// initialization P1 and P2
		
		//Math.max(solutions_array.get(findParents().get(0)).getRout_chromosome().size(), solutions_array.get(findParents().get(1)).getRout_chromosome().size());
		
		System.out.println("findpar P1"  + findParents().get(0) + "  P1:" + findParents().get(0) + " P2:" + findParents().get(1));
		Solution P1 = new Solution(solutions_array.get(findParents().get(0)));
		Solution P2 = new Solution(solutions_array.get(findParents().get(1)));
	
		System.out.println("chromosome size P1: " + P1.getRout_chromosome().size());
		System.out.println("chromosome size P2: " + P2.getRout_chromosome().size());
		if (P1.getRout_chromosome().size() < P2.getRout_chromosome().size()) {

			P1 = new Solution(solutions_array.get(findParents().get(1)));
			P2 = new Solution(solutions_array.get(findParents().get(0)));
			System.out.println("chromosome size P1: " + P1.getRout_chromosome().size());
			System.out.println("chromosome size P2: " + P2.getRout_chromosome().size());
		}
		Solution C = new Solution(P1);
		// fill route_chromosome with 0
		for (int i  = 0; i < P1.getRout_chromosome().size() ; i++) {
			C.getRout_chromosome().set(i, 0);
		}
		
		// choose random a and b
		Random random = new Random();
		int a = 0;
		int b = 0;
		while (a>= b) {
			a = random.nextInt(P1.getRout_chromosome().size());
			b = random.nextInt(P1.getRout_chromosome().size());
		}
		
		// Random service level chromosome between P1 and P2
		ArrayList<Double> temp_service_lvl_chromosome = new ArrayList<>();
		for(int i = 0; i < C.getService_lvl_chromosome().size(); i++) {
		
			double temp_lvl = 0.0;
			// when P1 and P2 service chromosome are equal
			if (Math.max( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i)) == Math.min( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i))) {
				temp_lvl = Math.max( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i));
			} else {
				// when P1 and P2 service chromosome are different 
				do {
					temp_lvl = random.nextDouble();
					//System.out.print("temp_lvl: " + temp_lvl);
					//System.out.print("   max(P1,P2)=" + Math.max( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i)));
					//System.out.println("   min(P1,P2)=" + Math.min( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i)));
				}
				while (( temp_lvl > Math.max( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i))) || ( temp_lvl < Math.min( P1.getService_lvl_chromosome().get(i) , P2.getService_lvl_chromosome().get(i))));
			}
			temp_service_lvl_chromosome.add(temp_lvl);
	
		}
		System.out.print("  temp_srv_lvl_chrom: ");
		for (Double field : temp_service_lvl_chromosome) {
			System.out.print(" " + field);
		}
		
		// [a,b] path from P1
		
		for (int i = a ; i <= b ; i++) {
			C.getRout_chromosome().set(i, P1.getRout_chromosome().get(i));
			C.setZeroServiceLevChromosome(4);
			C.setService_lvl_chromosome();
			if (checkTargetServiceLevel(temp_service_lvl_chromosome, C.getService_lvl_chromosome())) {
				C.getRout_chromosome().remove(i);
				C.setZeroServiceLevChromosome(4);
				C.setService_lvl_chromosome();
			}	
		}
		
		System.out.println("\na=" + a + "  b=" + b);
		System.out.println("C.size= "  + C.getRout_chromosome().size() +"   " + C.getRout_chromosome() + "   P1.size=" + P1.getRout_chromosome().size() + "  P2.size=" + P2.getRout_chromosome().size() );
		
		
		// [b+1, P2.size()] path
		for (int i = b+1 ; i < P2.getRout_chromosome().size(); i++) {
			C.getRout_chromosome().set(i, P2.getRout_chromosome().get(i));
			C.setZeroServiceLevChromosome(4);
			C.setService_lvl_chromosome();
			if (checkTargetServiceLevel(temp_service_lvl_chromosome, C.getService_lvl_chromosome())) {
				C.getRout_chromosome().remove(i);
				C.setZeroServiceLevChromosome(4);
				C.setService_lvl_chromosome();
			}	
		}
		System.out.println("\n[" + a + ";" + P2.getRout_chromosome().size() +"]");
		System.out.println("C.size= "  + C.getRout_chromosome().size() +"    " + C.getService_lvl_chromosome() +"   " + C.getRout_chromosome() + "   P1.size=" + P1.getRout_chromosome().size() + "  P2.size=" + P2.getRout_chromosome().size() );
		
		// [0, a-1] path
		a = Math.max(P2.getService_lvl_chromosome().size(), a);
				for (int i = 0 ; i < a; i++) {
					C.getRout_chromosome().set(i, P2.getRout_chromosome().get(i));
					C.setZeroServiceLevChromosome(4);
					C.setService_lvl_chromosome();
					if (checkTargetServiceLevel(temp_service_lvl_chromosome, C.getService_lvl_chromosome())) {
						C.getRout_chromosome().remove(i);
						C.setZeroServiceLevChromosome(4);
						C.setService_lvl_chromosome();
					}
				}
			System.out.println("\n[" + 0 + ";" +  P2.getRout_chromosome().size() +"]");
			System.out.println("C.size= "  + C.getRout_chromosome().size() +"   " + C.getRout_chromosome() + "   P1.size=" + P1.getRout_chromosome().size() + "  P2.size=" + P2.getRout_chromosome().size() );
				
		// remove 0 fields in route chromosome
				Iterator<Integer> iterator = C.getRout_chromosome().iterator();
				while (iterator.hasNext()) {
					int field = iterator.next();
					if (field==0) {
						iterator.remove();
					}
				}
		return C;
	}
	
	// check temporary target service level
	public Boolean checkTargetServiceLevel(ArrayList<Double> temp_service_lvl_chrom, ArrayList<Double> service_lvl_chrom) {
		Boolean crossed = false;
		for (int i = 0; i < temp_service_lvl_chrom.size(); i++) {
			if (temp_service_lvl_chrom.get(i) < service_lvl_chrom.get(i)) {
				crossed =  true;
			}
		}
		return crossed;
	}
	
	
	// find parents
	public ArrayList<Integer> findParents() {
		int p1_temp = 0;
		int p2_temp = 1;
		for(int i = 1; i < solutions_array.size() ; i++) {
			if (solutions_array.get(p1_temp).getBiased() < solutions_array.get(i).getBiased()) {
				p2_temp = p1_temp;
				p1_temp = i;
			}
		}
		ArrayList<Integer> temp_array = new ArrayList<>();
		temp_array.add(p1_temp);
		temp_array.add(p2_temp);
		return temp_array;
	}
	// 

	// compute fi diversity function
	public void computDiversity() {
		for (int i = 0; i < solutions_array.size() ; i++) {
			double distance_sum  =0;
			for (int j = 0; j< solutions_array.size() ; j++) {
				int common_edges = 0;
				int all_edges = solutions_array.get(i).getRout_chromosome().size();
				for (int k = 0; k < solutions_array.get(i).getRout_chromosome().size()-1 ; k++) {
					for (int p = 0 ; p < solutions_array.get(j).getRout_chromosome().size()-1 ; p++) {
						if ((solutions_array.get(i).getRout_chromosome().get(k) == solutions_array.get(j).getRout_chromosome().get(p)) && (solutions_array.get(i).getRout_chromosome().get(k+1) == solutions_array.get(j).getRout_chromosome().get(p+1))) {
							common_edges++;
						}
						
					}	
					
				}
			double distance = 1.00 - ((common_edges*1.000)/all_edges);
			distance_sum += distance;
			}
			solutions_array.get(i).setDiversity(distance_sum/(solutions_array.size()));
		}
	}
	
	// compute biased function
	public void computeBiasedFunction() {
		for (int i = 0; i < solutions_array.size(); i++) {
			solutions_array.get(i).setBiased(1.00/(solutions_array.get(i).getCost() - solutions_array.get(i).getDiversity()));
		}
	}
	
	
	// Print all solutions
	public void showSolutions() {
		int j=0;
		for (Solution solution : solutions_array) {
			System.out.print("nr: " + j++ + "  " );
			System.out.print("   size: " + solution.getRout_chromosome().size()+ "  ");
			
			System.out.print("   srv lvl: ");
			System.out.print(solution.getService_lvl_chromosome());
			System.out.print("  route chromosome: ");
			System.out.println(solution.getRout_chromosome());
			//System.out.println("    cost=" + solution.getCost() +"  diversity=" + solution.getDiversity() + "  biased f=" + solution.getBiased());
		}
		System.out.println("P1=" + findParents().get(0) + "  P2=" + findParents().get(1));
		System.out.print("C: \nsize: " + C_child.getRout_chromosome().size());
		System.out.print("   service level chromosome:  ");
		System.out.print(C_child.getService_lvl_chromosome());
		System.out.print("    route: ");
		System.out.println(C_child.getRout_chromosome());
		
	
	}
	
	// getters and setters
	public static  ArrayList<Solution> getSolutions_array() {
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
	public static int getFeasible_number() {
		return feasible_number;
	}
	public static void setFeasible_number(int feasible_number) {
		if (feasible_number == 1) {
			Solutions.feasible_number++;
		}
		if (feasible_number == -1) {
			Solutions.feasible_number--;
		}
	}
	public static int getInfeasible_number() {
		return infeasible_number;
	}
	public static void setInfeasible_number(int infeasible_number) {
		if (infeasible_number == 1) {
			Solutions.infeasible_number++;
		}
		if (infeasible_number == -1) {
			Solutions.infeasible_number--;
		}
	}
	
	

}
