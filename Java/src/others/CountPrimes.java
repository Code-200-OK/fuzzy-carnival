package others;

/***Count the number of prime numbers less than a non-negative number, n.**
*/
public class CountPrimes {

	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == false) {
				count++;
				for (int j = 2; i*j < n; j++) {
					notPrime[i*j] = true;
				}
			}
		}

		return count;
	}	
}

/***TC**:O(n(logn)(loglogn)) wikepedia.
**Method**: **Sieve of Eratosthenes**. Start with a array notPrime of size given n. 
Consider a number which is not prime and for all the multiples of that number, set the 
notPrime[i] to be true;*/