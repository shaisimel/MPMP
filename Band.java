import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Band {

	private static final long MAX_BAND_MEMBERS = 2000000;
	
	public static void main(String[] args) {
		List<Integer> listOfIntegers =new ArrayList<>();

		for(int i=1; i <=100; i++) {
			listOfIntegers.add(i);
		}
		
		// Running on as many cores as the CPU has to reduce run time
		listOfIntegers.parallelStream().forEach(target -> {
	    	boolean found = false;
			for(long option=1; option < MAX_BAND_MEMBERS && option > 0; option++) {
				TreeSet<Long> validSolutions = new TreeSet<Long>();
				
				for(long i=1; i<= option; i++) {
					if(option%i == 0) {
						// Adding both sides of the equation to the solutions list
						validSolutions.add(i);
						validSolutions.add(option/i);
					}
					// If we have more solutions than the target, we can stop looking.
					if(validSolutions.size() > target) {
						break;
					}
						
				}
				
				if(validSolutions.size()==target) {
					synchronized (listOfIntegers) {
						System.out.println("Target = " + target + " Solution = " + option);
						int number = 1;
						for(long sol : validSolutions) {
							long di = option / sol;
							long ans = di * sol;
							System.out.println(number +". " + sol + " x " + di + " = " + ans);
							number++;
						}
					}
					found = true;
					break;
				}	
			}
			if(!found) {
				System.out.println("Couldn't find a solution for " + target);
			}
		});
	}
}