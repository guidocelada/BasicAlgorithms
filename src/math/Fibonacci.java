package math;
import java.util.*;

public class Fibonacci {
  
  static HashMap<Long,Long> fibonacci;
  static HashSet<Long> isFibonacci;
    
  public static long calculateFibonacci(long n) { //calculate until result reach n
      if (fibonacci.containsKey(n)) 
          return fibonacci.get(n);          
      
      fibonacci.put(n,calculateFibonacci(n-1) + calculateFibonacci(n-2));
      isFibonacci.add(fibonacci.get(n));
      
      return fibonacci.get(n);
  }
    
  public static boolean isFibonacciMethod(long n) {
      int i = 0;
      while (calculateFibonacci(i) < n)
          i++;
      
      return isFibonacci.contains(n);
  }
    
  public static void main(String[] args) { 
    Scanner in = new Scanner(System.in);
    long T = in.nextLong();
    
    fibonacci = new HashMap<Long,Long>();
    fibonacci.put((long)0,(long)0); fibonacci.put((long)1,(long)1);
      
    isFibonacci = new HashSet<Long>();
    isFibonacci.add((long)0); isFibonacci.add((long)1);
    
    while (T-- > 0) { 
      if (isFibonacciMethod(in.nextLong()))
          System.out.println("IsFibo");
      else
          System.out.println("IsNotFibo");      
    }
    
    in.close();
  }

}
