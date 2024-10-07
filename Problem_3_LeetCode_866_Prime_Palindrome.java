/*

To find the smallest prime palindrome number greater than or equal to a given integer n.

Key Concepts:

	1.	Palindrome:

        •	A number is a palindrome if it reads the same forward and backward.
        •	Examples: 121, 131, 11, 7.

	2.	Prime:

        •	A prime number is a number greater than 1 that is divisible only by 1 and itself.
        •	Examples: 2, 3, 5, 7, 11, 13, etc.

	3.	Why Skip Even-Digit Palindromes?:
    
        •	Any even-digit palindrome is always divisible by 11. This property can be derived from the divisibility rule for 11:
        •	For any even-digit number, if the alternating sum and difference of its digits are zero (which is true for any even-digit palindrome), then it is divisible by 11.
        •	The only exception is the number 11 itself.
        •	Therefore, any even-digit palindrome (other than 11) can never be a prime number.

Approach and Implementation:

1. Edge Case Handling:

	•	If n is less than 11, special handling is required because:
	•	The smallest prime numbers are: 2, 3, 5, 7, and 11.
	•	For values like 8, 9, or 10, the next prime palindrome should be 11.
	•	Directly check and return the appropriate prime number for n <= 11.

2. Skip Even-Digit Palindromes:

	•	For n >= 12, skip even-digit numbers by jumping to the next power of 10 when the number of digits in n is even.
	•	For example:
	•	If n is between 1000 and 9999, skip to 10000 (10^5), because all four-digit palindromes are not prime.

3. Increment and Check:

	•	For numbers not skipped, check both the palindrome property and the prime property.
	•	Increment the number (n++) and repeat the check until a valid prime palindrome is found.


*/
class Solution {
    // Method to check if a number is a palindrome
    public boolean isPalindrome(int number) {
        String n = Integer.toString(number);
        int i = 0, j = n.length() - 1;
        while (i < j) {
            if (n.charAt(i) != n.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // Method to check if a number is prime
    public boolean isPrime(int number) {
        if (number < 2) return false;

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Method to find the smallest prime palindrome greater than or equal to n
    public int primePalindrome(int n) {
        // Handle edge cases for numbers less than 11
        if (n <= 11) {
            if (n <= 2) return 2;
            if (n <= 3) return 3;
            if (n <= 5) return 5;
            if (n <= 7) return 7;
            return 11; // Return 11 for n = 8, 9, 10, or 11
        }

        // For n >= 12, skip even-digit palindromes
        while (true) {
            int length = Integer.toString(n).length();
            if (length % 2 == 0) {
                n = (int) Math.pow(10, length); // Skip to next power of 10
            }

            // Check if n is both a palindrome and a prime number
            if (isPalindrome(n) && isPrime(n)) {
                return n;
            }

            // Increment n to check the next number
            n++;
        }
    }
}
