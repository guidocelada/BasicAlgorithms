/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package arrays;

import java.util.ArrayList;
import java.util.List;

import utils.Pair;


/**
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class RMQ {
    private static int[] fastLogs;
  
  /* RMQ PreProccess: O(N^2) query: O(1) */
  
  /**
   * @returns the minimum indexes of the array for each query 
   */
    public static List<Integer> rmq(int[] array, List<Pair> queries) {
	int[][] answer = rmqPreProcess(array);
	List<Integer> returnList = new ArrayList<Integer>();
	for (Pair p : queries) {
	    returnList.add(answer[p.x][p.y]);
	}
	return returnList;
    }
  
  /**
   * Range minimum query preprocess O(n^2)
   * @returns a matrix with all the preprocessing done, so that you can answer queries in O(1) 
   */
    private static int[][] rmqPreProcess(int[] array) {
	/* stores indexes, so queryAnswers[1][4] would represent a query answer 
	 * with index from = 1 and to = 4. returns the index of the min element */
	int[][] queryAnswers = new int[array.length][array.length]; 
    
	/* when the indexes are equal (from == to), 
	 * you dont have a range, you just return 
	 * the same index that you are given      */
	for (int i = 0; i < array.length; i++)
	    queryAnswers[i][i] = i; 

	for (int i = 0; i < array.length; i++)
	    for (int j = i + 1; j < array.length; j++) {
		//Last min calculated
		int lastMinIndex = queryAnswers[i][j-1]; 
		
		if (array[lastMinIndex] < array[j])
		    queryAnswers[i][j] = lastMinIndex;
		else
		    queryAnswers[i][j] = j;
	    }

	return queryAnswers;
    }
  
  
  
  
  /**************************************************************************/
  
  
  /* RMQ PreProccess: O(N log N) query: O(1) */
  
  /**
   * @returns the minimum indexes of the array for each query 
   */
    public static List<Integer> rmqSparseTable(int[] array, List<Pair> queries) {
	int[][] queryAnswers = rmqSparseTablePreProcess(array);

	List<Integer> returnList = new ArrayList<Integer>();

	for (Pair p : queries) {
	    int i = p.x; 
	    int j = p.y;
	    
	    int queryLength = j - i + 1; //length of the "subarray" (+1 because its 0 based)
	    int log = log2(queryLength); 
	    int answer1 = queryAnswers[i][log];
	    int answer2 = queryAnswers[j - (int) Math.pow(2, log) + 1][log];
	    
	    if (array[answer1] < array[answer2]) 
		returnList.add(answer1);
	    else 
		returnList.add(answer2);
	}
	return returnList;
    }
  
  /**
   * RMQ preprocess sparse table implementation of O(N log N). queries can then be answered in O(1)
   */
    private static int[][] rmqSparseTablePreProcess(int[] array) {
	/* goes from [0..N-1][0..log N] and queryAnswers[i][j] represents 
	 * the index of the minimum value in the subarray starting at i with length 2^j */
	int[][] queryAnswers = new int[array.length][log2(array.length) + 1]; 

	// Initialize queryAnswers for the intervals of length 1
	for (int i = 0; i < array.length; i++)
	    queryAnswers[i][0] = i;

	// Compute values from smaller to bigger intervals
	for (int j = 1; (1 << j) <= array.length; j++) {
	    for (int i = 0; i + (1 << j) - 1 < array.length; i++) {
		int answer1 = queryAnswers[i][j - 1];
		int answer2 = queryAnswers[i + (1 << (j - 1))][j - 1];
		if (array[answer1] < array[answer2])
		    queryAnswers[i][j] = answer1;
		else
		    queryAnswers[i][j] = answer2;
	    }
	}

	return queryAnswers;
    }
  
  
  
  /**************************************************************************/

  
  
  /* Auxiliary functions */
  
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++){ 
      for (int j = 0; j < matrix[0].length; j++) 
          System.out.print(matrix[i][j] + " ");      
      System.out.print("\n");
    }
  }
    
  private static void populateFastLogs(int length) {
	fastLogs = new int[length + 1];
	int counter = 0;
	int log = 0;
	int num = 1;
	fastLogs[0] = 0;
	for (int i = 1; i < fastLogs.length; i++) {
	    counter++;
	    fastLogs[i] = log;
	    if (counter == num) {
		log++;
		num *= 2;
		counter = 0;
	    }
	}
  }
  
  private static int log2(int n) {
      return fastLogs[n];
  }

  
  
  public static void main(String[] args) {
      int[] array = {5, 10, 1, 3, 8, 15, 1, 29, 2, 4, 59};
      
      populateFastLogs(array.length);
      
      ArrayList<Pair> queries = new ArrayList<Pair>();
      queries.add(new Pair(0,4));
      queries.add(new Pair(1,2));
      queries.add(new Pair(3,10));
      
      for (Integer num : rmqSparseTable(array, queries)) {
  	System.out.println(num);
      }
    }
}
