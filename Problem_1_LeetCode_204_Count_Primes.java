import java.util.*;


class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0; // No prime numbers less than 2

        // Create a list with n elements, all set to true initially
        List<Boolean> isPrimeArr = new ArrayList<>(Collections.nCopies(n, true));

        // Set 0 and 1 to false, as they are not prime
        isPrimeArr.set(0, false);
        isPrimeArr.set(1, false);

        // Mark non-primes using the Sieve of Eratosthenes
        int limit = (int) Math.sqrt(n); // Only check up to √n
        for (int i = 2; i <= limit; i++) {
            if (isPrimeArr.get(i)) { // If i is prime
                for (int j = i * i; j < n; j += i) {
                    isPrimeArr.set(j, false); // Mark all multiples of i as non-prime
                }
            }
        }

        // Count and return the number of prime numbers
        return Collections.frequency(isPrimeArr, true);
    }
}

/*

Sieve of Eratosthenes Algorithm - Quick Notes

Purpose:

Efficiently find all prime numbers less than a given number n.

Concept:

	1.	Initialize a Boolean Array:

        •	Create a list/array of size n with all elements set to true.
        •	Set isPrime[0] and isPrime[1] to false because 0 and 1 are not prime numbers.
        
	2.	Mark Non-Prime Numbers:

        •	Iterate from i = 2 to sqrt(n).
        •	For each prime number i, mark all its multiples as false (starting from i * i).
        
	3.	Why Start Marking from i * i?

        •	Any multiple less than i * i would have already been marked by a smaller prime factor.
        •	Avoids redundant operations, making the algorithm more efficient.
        
	4.	Counting Prime Numbers:
    
	    •   After marking, count all true values in the list. These correspond to prime numbers less than n.

    5. Why Check Till √n in Sieve of Eratosthenes?

        The reason for only iterating up to √n in the Sieve of Eratosthenes lies in the properties of divisors:

            1.	Divisors Come in Pairs:

                •	For any composite number n, its divisors (factors) come in pairs, such as (p, q), where p * q = n.
                •	If both p and q are greater than √n, then p * q would be greater than n, which contradicts p * q = n.

            2.	Smallest Factor Must Be ≤ √n:

                •	If n is composite, at least one of its divisors must be less than or equal to √n. Otherwise, both factors would be greater than √n, and their product would exceed n.
            
            3.	Avoid Redundant Checks:

                •	By stopping at √n, we ensure that we find all prime factors without checking pairs that have already been marked.
                •	For example, if i is a prime and j = i * k, where k < i, then j would have been marked earlier when k was processed. This prevents duplicate work.

        Example:

            For n = 36:

                •	The divisors of 36 are (1, 36), (2, 18), (3, 12), (4, 9), and (6, 6).
                •	Notice that the smaller factor in each pair is less than or equal to √36 = 6.
                •	We only need to check divisors up to 6. If a number larger than 6 divides 36, its corresponding pair divisor would have already been found.

            How This Applies in the Sieve of Eratosthenes:

                •	We only need to check numbers up to √n because any multiple of a prime number p greater than √n would have been marked by a smaller prime factor.
                •	This reduces the number of operations significantly and ensures efficiency, giving the Sieve of Eratosthenes its O(n log log n) time complexity.

        Summary Note:

                •	Checking up to √n: We only iterate up to √n because any composite number n will have at least one factor less than or equal to √n. This helps avoid redundant marking and reduces the number of iterations.

Complexity Analysis:

	•	Time Complexity: O(n log log n)
	•	Space Complexity: O(n)

Edge Cases to Handle:

	1.	n = 0 or n = 1 should return 0 as there are no prime numbers less than 2.
	2.	Ensure that the count excludes 0 and 1 as they are not prime numbers.

Use Cases:

	•	Finding primes in a range.
	•	Efficient prime checking for numbers less than n.

 */