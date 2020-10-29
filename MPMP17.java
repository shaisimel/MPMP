
public class MPMP17 {

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
		int lastBit = 0;
		for(int j=1; j<=NUMBER_OF_KENNELS;j++) {
			int nextBit = n & 1;
			if(nextBit==1 && lastBit==1) {
				return false;
			}
			lastBit = nextBit;
			n >>= 1;
		}
		return true;
	}
	
	public static String numToBin(int n) {
		StringBuilder sb = new StringBuilder();
		for(int j=1; j<=NUMBER_OF_KENNELS;j++) {
			int b = n & 1;
			sb.append(b==1?"C":"D");
			n >>= 1;
		}
		sb.reverse();
		return sb.toString();
	}
}
