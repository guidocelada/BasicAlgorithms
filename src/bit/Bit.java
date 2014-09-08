package bit;

/**
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class Bit {
  
  /**
   * gets the bit at the ith position (from right to left) (Ex: 3,1 -> 3= 0011 & (0010))
   */
  public static boolean getBit(int number, int i) {
    return ((number & (1 << i)) != 0);
  }
  
  public static int setBit(int number, int i) {
    return (number | (1 << i));
  }
  
  /**
   * From right to left puts 0. e.g in binary: (111111, 1, 3) = 110001
   */
  public static int clear(int number, int from, int to) {
    int allOnes = ~0; //32 bit all ones eg. 111111
    int left = allOnes << (to + 1); //eg. 110000
    int right = ~(allOnes << from); //eg 000001
    
    int mask = left | right; //eg 110001
    int ret = number & mask;
    
    System.out.println("We want to clear the number: \n" +Integer.toBinaryString(number) + " from " + from + " to " + to);
    System.out.println("\nFirst, we make an all ones binary value (~0): \n" +Integer.toBinaryString(allOnes));
    System.out.println("\nThen, we create the left part of the mask (allOnes << (to + 1)): \n" +Integer.toBinaryString(left));
    System.out.println("\nand the right one (~(allOnes << from)): \n" +Integer.toBinaryString(right));
    System.out.println("\nSo the mask would be (left | right): \n" +Integer.toBinaryString(mask));
    System.out.println("\nApplying the mask to the number results in (number & mask): \n" +Integer.toBinaryString(number) + " => \n" + Integer.toBinaryString(ret) + "\n");
   
    return ret;
  }
  
  /**
   * eg in binary: 1000000, 101, 0, 2 =  1000101
   */
  public static int insert(int number, int numberToInsert, int i, int j) {
    int insert = numberToInsert << i;
    int n = clear(number, i, j);
    int ret = n | insert;
    
    System.out.println("Insert " + Integer.toBinaryString(numberToInsert) + " into " + Integer.toBinaryString(number) + " from " + i + " to " + j );
    System.out.println(Integer.toBinaryString(insert) + " | \n" + Integer.toBinaryString(n));
    System.out.println("Result: " + Integer.toBinaryString(ret));
    
    return ret;
  }
  
  /**
   * with max length = 32
   */
  public static String toBinaryString(double number) {
    StringBuilder string = new StringBuilder();
    string.append(".");
    double myNumber = number * 2;
    while (myNumber > 0) {
      if (myNumber >= 1) {
        string.append(1);
        myNumber = myNumber - 1;
      } else {
        string.append(0);
      }
      if (string.length() == 32)
        return "ERROR";
      
      myNumber = myNumber * 2;
    }
    
    return string.toString();
  }
  
  
  public static void main(String[] args) {
    System.out.println(toBinaryString(0.72));
  }

}
