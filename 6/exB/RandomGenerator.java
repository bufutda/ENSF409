
//  RandomGenerator.java

import java.util.Random;

/**
 * Provides a method to spawn a random integer.
 * @author M. Moussavi
 */
class RandomGenerator {

/**
 * creates a random number ranging between lo and hi,
 * @param lo the lower bound of the random integer
 * @param hi the upper bound of the random integer
 * @return the random integer
 */
	int discrete(int lo, int hi)
	{
		if(lo >= hi){
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}

		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}

}
