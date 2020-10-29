
public class MPMP17 {

	// This code is used to solve the MPMP17 (cats and dogs). https://youtu.be/8gppjTZ1vCE
	
	public static final int NUMBER_OF_KENNELS = 10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		System.out.println("D is Dog and C is Cat.");
		
		for(int i=0; i<Math.pow(2, NUMBER_OF_KENNELS); i++) {
			boolean isValid = isValidArrangment(i);
			System.out.println(numToBin(i) + "-" + isValid);
			if(isValid) {
				count++;
			}
		}
		
		System.out.println("There are " + count + " ways to arrange the cats and dogs such that no two cats are in adjacent kennels.");
	}

	
	public static boolean isValidArrangment(int n) {
		while(n!=0) {
			if((n&3)==3) { // 3 is 11 in binary (two ones next to each other)
				return false;
			}
			n >>= 1;
		}
		return true;
	}
	
	public static String numToBin(int n) {
		StringBuilder sb = new StringBuilder();
		for(int j=1; j<=NUMBER_OF_KENNELS;j++) {
			sb.append((n & 1)==1?"C":"D");
			n >>= 1;
		}
		sb.reverse();
		return sb.toString();
	}
}
