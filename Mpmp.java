public class Mpmp {

	/*
	 * Dear Matt Parker, Unless I'm very mistaken, the solution to you card game
	 * puzzle for N cards would be (N^2)-1. Given that we don't know the starting
	 * positions and we only know how many cards there are until we win, the
	 * strategy is to go through all possible combinations without repeating the
	 * same combination twice.
	 * 
	 * So how can we do it? I started by asking, well what if instead of 4 cards we
	 * had just 1. How many steps would be needed? (Well, since you must have at
	 * least one 1 card facing up and you can't start from the winning position you
	 * can't play with only 1 card, but let's ignore that for a moment) If you have
	 * only 1 card, once you flip it, you win the game as you went through all the
	 * positions. Easy. Done. 1 step to win 1 card game. So if our function is F
	 * then: 
	 * F(1) = 1 
	 * 
	 * What if we had 2 cards? Well in that case you can only start
	 * with one card facing up and the other down. If you flip the one that facing
	 * down you win. If we flipped the one that is facing up, we have to flip both
	 * cards again to win. We get to 3 flips to guarantee to win the game by going
	 * through the positions. 
	 * F(2) = F(1) + 1 + F(1) = 3 
	 * 
	 * What about the 3? You can
	 * try going through all the positions of 2 other cards, if you didn't win, flip
	 * the 3rd card, and then if you still didn't win, try going through all the
	 * positions of the remaining 2 cards again to win. That will guarantee that you
	 * went through all the combinations and must win. In our case that will be 7
	 * turns. 
	 * 
	 * F(3) = 2*(F(2)) + 1 = 7 
	 * 
	 * So what about 4 cards? Well, you can keep doing
	 * the same thing again. Go through all the positions of the other 3 cards, flip
	 * the 4th, and flip all other 3 again. F(4) = 15 We see that we always go
	 * through all the solutions of the remaining cards twice and we also take a
	 * turn to flip the last card. We can say that to know the number of turns in
	 * takes to win for N cards using function F and using recursion 
	 * F(N) = (2 * F(N-1)) + 1
	 * 
	 * But to make things easier, we can also say we are transitions between all
	 * possible positions until we get to the winning one. Each cards has only 2
	 * states, either up or down. And we have N cards, that would make 2^N possible
	 * states. But since we only count the number of transitions and the states, we
	 * need to subtract one. So in our case 
	 * 
	 * F(N) = (2^N) - 1;
	 * 
	 * So I've written a short code to run through all possible starting
	 * combinations and use the strategy mentioned above to prove it. Originally I
	 * thought of using an array to do it, generating first all the possible
	 * combinations and storing them in the array and then running through all of
	 * them, But then I also realized since we only have 2 states (up and down) we
	 * can use bits of a single variable to represent those states. So in the code
	 * below 0 will represent the down state and 1 will represent up. Now, since we
	 * use bits, we can store the data in a single variable, and flip it's bits
	 * according to our strategy. Also, we don't need to generate all possible
	 * starting positions, as they are given to us for free by just looping through
	 * all the numbers from 1 to (2^N)-1.
	 * 
	 * So in the following code I've used recursion along with bitwise operations to
	 * make this a little more interesting.
	 * 
	 * A note to Matt, 
	 * You can also make this puzzle a little trickier by saying,
	 * instead of having cards we have 6 sided dice and you have to get them to all
	 * show sixes. Well in that case using the same strategy we can say that since
	 * that each die has 6 sides for N dice our function will be 
	 * F(N) = (6^N) - 1
	 * 
	 * What if we have M sided dice? 
	 * F(M, N) = (M^N) - 1
	 * 
	 * This code will not work for dice and would need to be refactored in order to
	 * solve it for M sided dice.
	 * 
	 * This puzzle reminded me of the Miss, Hit, Bullseye game. Description here:
	 * https://www.pleacher.com/mp/puzzles/mgames/hitmiss.html Maybe a future MPMP
	 * could be to come up with a strategy that will guarantee you would win this
	 * game in the minimum amount moves.
	 * 
	 * Anyways, keep up the great work. Looking forward to the next puzzle! :D
	 * 
	 */

	static final int NUMBER_OF_CARDS = 4; // Solve for size n. Since we are using int which has 32 bits, you can only
					      // set this up to 32 (or 31 if you also print the steps.) It will take very
					      // long to run though
	static final int TARGET_POSITION = (int) (Math.pow(2, NUMBER_OF_CARDS) - 1); // The target is to get all the bits
																					// set as 1, that would be (2^n) - 1
	static final boolean PRINT_STEPS = true; // If you don't want to print the steps, set this to false, this will make
						 // the program run faster

	// Since we are using a recursion to solve the riddle, it's easier to track how
	// many steps we went through and the current position with a global variable
	// This is not a best practice in programming
	static int stepPrintCounter = 0;
	static int currentPostion = 0;

	public static void main(String[] args) {
		int maxSteps = 0; // Used to track the result of how many steps are needed to win

		// Starting from 1 as you must have at least 1 bit set as 1 (per the rules, at
		// least one of the cards has to face up)
		// running up to the target number as you can't also start from the winning
		// position
		for (int startingPostion = 1; startingPostion < TARGET_POSITION; startingPostion++) {
			System.out.println("Game: " + startingPostion); // The starting position is actually the number of game we
									// are currently playing, neat :)
			currentPostion = startingPostion; // Setting the global parameter
			stepPrintCounter = 0; // Resetting the counter for print
			printPositions(currentPostion);

			// Find the amount of steps needed to win.
			maxSteps = Math.max(maxSteps, flipBits(NUMBER_OF_CARDS - 1));
			System.out.println("----------");
		}

		System.out.println("Maximum amount of steps taken: " + maxSteps);
	}

	public static int flipBits(int index) {
		int stepTaken = 0;
		if (index > 0) {
			stepTaken = flipBits(index - 1); // Before flipping the bit at the index position, lets try first flipping
							 // the ones to the right of it.
		}
		if (!isInWinningPosition()) {
			currentPostion ^= (1 << index); // Flip the bit in position index (by XORing the current number with a
							// number that has only the bit at position index set to 1)
			stepTaken++;
			printPositions(currentPostion);
			if (!isInWinningPosition() && index > 0) { // If we still didn't win after flipping the bit at position
								   // index, lets try flipping the bits to the right again
				stepTaken += flipBits(index - 1);
			}
		}
		return stepTaken;
	}

	// check if all bits are 1 meaning all the cards are facing up (target position
	// has all the bits set to 1)
	public static boolean isInWinningPosition() {
		return currentPostion == TARGET_POSITION;
	}

	// The Integer.toBinaryString method cuts off the leading zeros. A cool trick to
	// print them is to OR the number to print with one that has all zeros but have
	// the
	// bit at the front set to 1, and then cut off the leading bit.
	static final int ZERO_PADDING = (1 << NUMBER_OF_CARDS);

	public static void printPositions(int pos) {
		if (PRINT_STEPS) {
			if (stepPrintCounter == 0) {
				System.out.print("Starting postion");
			} else {
				System.out.print("Step " + stepPrintCounter);
			}
			System.out.println(": " + Integer.toBinaryString(ZERO_PADDING | pos).substring(1));
			stepPrintCounter++;
		}
	}
}
