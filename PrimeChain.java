import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

public class PrimeChain {
	
	public static HashMap<Integer, Node> nodesMap;

	static ArrayList<Node> nodesPath;
	static ArrayList<ArrayList<Node>> solutionsFound;
	static PrimeCheckr p = new PrimeCheckr(10000);
	
	public static void main(String[] args) {
		for(int target =12 ; target <= 12; target++)
		{
			initMap(target);
			solutionsFound = new ArrayList<ArrayList<Node>>();
			for(Node n : nodesMap.values()) {
				nodesPath = new ArrayList<Node>();
				moveToNode(n, target);	
			}
			
			System.out.println("Total Solutions found for: " + target + ": " + solutionsFound.size());
			
			for(ArrayList<Node> sol : solutionsFound) {
				for(Node p : sol) {
					System.out.print(p.value+",");
				}
				System.out.println("");
				for (int i = 0; i < sol.size(); i++) {
					int a = sol.get(i%target).value+sol.get((i+1)%target).value;
					System.out.print(sol.get(i%target) + "+" + sol.get((i+1)%target) + "=" + a);
					if(sol.size() - 1 > i) {
						System.out.print(",");
					}
				}		
				System.out.println();
				System.out.println("-----------------------------------------");
				break;
			}
		}
		
		System.out.println("Done");
	}
	
	public static void moveToNode(Node n, int target) {
		if(// solutionsFound.size()==0 && 
				!nodesPath.contains(n)) {
			nodesPath.add(n);
			if(nodesPath.size()==target) {
				if(p.isPrime(nodesPath.get(0).value + nodesPath.get(nodesPath.size()-1).value)) {
					solutionsFound.add(new ArrayList<Node>(nodesPath));
				}
				
			} else {
				for(Node t : n.linkedNodes) {
					moveToNode(t, target);
				}
			}
			nodesPath.remove(nodesPath.size()-1);
		}
	}

	public static void initMap(int limit) {
		nodesMap = new HashMap<Integer, Node>();
		for (int i=1; i <= limit; i++) {
			Node n = new Node(i);
			nodesMap.put(i, n);
		}
		
		// chart links
		for (int i=1; i <= limit; i++) {
			Node currentNode = nodesMap.get(i);
			for(int j=i+1; j <= limit; j++) {
				Node compareNode = nodesMap.get(j);
				if(p.isPrime(currentNode.value+compareNode.value)) {
					currentNode.linkedNodes.add(compareNode);
					compareNode.linkedNodes.add(currentNode);
				}
			}
		}
	}
}

class Node {
	public HashSet<Node> linkedNodes;
	public int value;
	
	public Node(int val) {
		value = val;
		linkedNodes = new HashSet<Node>();
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}

class PrimeCheckr{
	boolean[] primes;
	//set up the primesieve
	public PrimeCheckr(int limit) {
		primes=new boolean[limit];
	    Arrays.fill(primes,true);        // assume all integers are prime.
	    primes[0]=primes[1]=false;       // we know 0 and 1 are not prime.
	    for (int i=2;i<primes.length;i++) {
	        //if the number is prime,
	        //then go through all its multiples and make their values false.
	        if(primes[i]) {
	            for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	            }
	        }
	    }
	}

	public boolean isPrime(int n) {
	    return primes[n]; //simple, huh?
	}
}