package primes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Primes {
  
  /*
   * Please implement this method to return a list of all prime numbers in the
   * given range (inclusively). A prime number is a natural number that has
   * exactly two distinct natural number divisors, which are 1 and the prime
   * number itself. The first prime numbers are: 2, 3, 5, 7, 11, 13
   */
  public static List<Integer> getPrimeNumbers(int from, int to) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = from; i <= to; i++)
      if (isPrime(i))
        list.add(i);
    return list;
  }

  public static boolean isPrime(int number) {
    if (number < 2) return false;
    
    for (int j = 2; j < number; j++)
      if (number % j == 0)
        return false;
    return true;
  }
  
  public static List<Integer> getPrimeNumbersEratosthenes(int from, int to) {
    boolean[] primes = getPrimesEratosthenes(to);
    List<Integer> primesList = new ArrayList<Integer>();
    
    for (int i = from; i <= to; i++) {
      if (primes[i]) primesList.add(i); 
    }
    
    return primesList;
  }
  
  public static boolean[] getPrimesEratosthenes(int max) {
    boolean[] isPrime = new boolean[max + 1];
    
    /* asume every number is prime (except 0 & 1) */
    Arrays.fill(isPrime, true);
    isPrime[0] = false; isPrime[1] = false;
    
    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i]) 
        /* cross off all the multiples of i */
        for (int j = (int) Math.sqrt(i); j < isPrime.length; j += i ) 
          isPrime[j] = false;
    }
    
    return isPrime;
  }
  
  public static void main(String[] args) {
    long startTime, endTime;
    int from = 1;
    int to = 2000000;
    
    startTime = System.currentTimeMillis();
    getPrimeNumbersEratosthenes(from, to);
    endTime = System.currentTimeMillis();
    System.out.println("getPrimeNumbersEratosthenes: " + (endTime - startTime) + " ms");
    
    startTime = System.currentTimeMillis();
    getPrimeNumbers(from, to);
    endTime = System.currentTimeMillis();
    System.out.println("getPrimeNumbers: " + (endTime - startTime) + " ms"); 
  }

}
