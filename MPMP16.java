import java.io.*; 
  
class MPMP16 { 
      
    /* Function to get no of set 
    bits in binary representation  
    of positive integer n */
    static int countSetBits(int n) 
    { 
        long count = 0; 
        while (n > 0) 
        { 
            count += n & 1; 
            n >>= 1; 
        } 
          
        return (int)count; 
    } 
      
    static int countOfOddsPascal(int n) 
    { 
          
        // Count number of 1's in binary 
        // representation of n. 
        int c = countSetBits(n); 
          
        // Number of odd numbers in n-th 
        // row is 2 raised to power the 
        // count. 
        return (int)Math.pow(2, c); 
    } 
      
    // Driver code 
    public static void main (String[] args) 
    { 
        try {
			FileWriter csvWriter = new FileWriter("pascal.csv");
			
			long n = 0;
	    	long s = 0;
	    	csvWriter.append("Line Number,Odd Numbers,Total Numbers,Percantage\n");
	        for (int i=0; i <= 1000000; i++) {
	        	n += countOfOddsPascal(i);
	        	s += i + 1;
	        	double d = ((double) n/s);
	        	csvWriter.append(String.valueOf(i)).append(",")
	        		.append(String.valueOf(n)).append(",")
	        		.append(String.valueOf(s)).append(",")
	        		.append(String.valueOf(d)).append("\n");
	        }
			csvWriter.flush();
			csvWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Done");
    } 
} 
  