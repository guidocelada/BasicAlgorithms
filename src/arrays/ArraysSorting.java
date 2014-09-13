package arrays;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class ArraysSorting {

    /**
     * Find the min element every time, and place it in the right position.
     * O(n^2)
     */
    public static int[] selectionSort(int[] array) {
	for (int i = 0; i < array.length; i++) {
	    int aux = array[i];
	    int minPosition = findMinimumPosition(array, i, array.length - 1);

	    array[i] = array[minPosition];
	    array[minPosition] = aux;
	}
	return array;
    }

    /**
     * Helper method for selectionSort
     * 
     * @returns the position of the minimun element of the array
     */
    public static int findMinimumPosition(int[] array, int from, int to) {
	if (from < 0 | to >= array.length) // out of bounds
	    return -1;

	int min = Integer.MAX_VALUE;
	int minPosition = -1;

	for (int i = from; i <= to; i++) {
	    if (array[i] < min) {
		min = array[i];
		minPosition = i;
	    }
	}

	return minPosition;
    }

    // //////////////////////////////////////////////////////////////////////////

    /**
     * Compares adjacent elements and exchanges their values. O(n^2).
     */
    public static int[] bubbleSort(int[] array) {
	boolean unsorted = true;

	while (unsorted) {
	    unsorted = false;
	    int length = array.length - 1;

	    for (int i = length; i > 0; i--) {
		if (array[i - 1] > array[i]) {
		    unsorted = true;
		    int aux = array[i];
		    array[i] = array[i - 1];
		    array[i - 1] = aux;
		}
	    }
	}
	return array;
    }

    // //////////////////////////////////////////////////////////////////////////

    /**
     * Divides an array and then merges it. O(n log n). Memory O(2N) Note:
     * Better for large sets that dont fit in memory and need access to hard
     * drive. Highly parallelizable.
     */
    static int[] aux; // so that you create this only once and reuse it

    public static void mergeSort(int[] array) {
	aux = new int[array.length];
	sort(array, 0, array.length - 1);
    }

    /**
     * Helper recursive function that divides and merges the array
     */
    private static void sort(int[] array, int lo, int hi) {
	if (hi <= lo)
	    return;
	int mid = lo + (hi - lo) / 2;
	sort(array, lo, mid);
	sort(array, mid + 1, hi);
	merge(array, lo, mid, hi);
    }

    /**
     * Merges two ordered arrays (one array is lo to mi, and the other is mid+1
     * to hi)
     * 
     * @returns an ordered merge of the 2 arrays
     */
    private static void merge(int[] array, int lo, int mid, int hi) {
	for (int i = lo; i <= hi; i++)
	    // aux copy so we can order on the original array
	    aux[i] = array[i];

	int i = lo; // pointer for the first array
	int j = mid + 1; // pointer for the second array

	for (int k = lo; k <= hi; k++) { // for every element that needs to be
					 // sorted (both arrays)
	    if (i > mid)
		array[k] = aux[j++]; // first array runned out of elements
	    else if (j > hi)
		array[k] = aux[i++]; // second array runned out of elements

	    else if (aux[j] < aux[i])
		array[k] = aux[j++]; // pick the minimum element of both arrays
				     // and advance pointer
	    else
		array[k] = aux[i++];
	}
    }

    // //////////////////////////////////////////////////////////////////////////

    public static void heapSort(int[] array) {
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

	for (int element : array) {
	    minHeap.add(element);
	}

	for (int i = 0; i < array.length; i++) {
	    array[i] = minHeap.remove();
	}
    }

    public static void heapSortInPlace(int[] array) {
	
    }

    // //////////////////////////////////////////////////////////////////////////

    /**
     * 3-way quicksort
     * 
     * Choose a value an put the greater values to the right, lowers to the
     * left, and equals in the center. recursive. O(n^2) but avg O(n log n). Memory: In place
     */
    public static void quickSort(int[] array) {
	shuffleArray(array);
	quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int lo, int hi) {
	if (hi <= lo)
	    return;

	int lowerIndex = lo;
	int greaterIndex = hi;
	int element = array[lo];
	int i = lo;

	while (i <= greaterIndex) {
	    if (array[i] < element)
		swap(array, lowerIndex++, i++);
	    else if (array[i] > element)
		swap(array, i, greaterIndex--);
	    else
		i++;
	}

	quickSort(array, lo, lowerIndex - 1);
	quickSort(array, greaterIndex + 1, hi);
    }

    // O(n)
    private static void shuffleArray(int[] ar) {
	Random rnd = new Random();
	for (int i = ar.length - 1; i > 0; i--) {
	    int index = rnd.nextInt(i + 1); // random between 0 and i
	    swap(ar, i, index);
	}
    }

    private static void swap(int[] array, int i, int j) {
	int a = array[i];
	array[i] = array[j];
	array[j] = a;
    }

    // //////////////////////////////////////////////////////////////////////////

    public static int binarySearch(int[] array, int element) {
	return binarySearch(array, element, 0, array.length - 1);
    }

    private static int binarySearch(int[] array, int element, int low, int high) {
	if (low > high)
	    return -1; // ERROR

	int mid = (low + high) / 2;

	if (array[mid] == element)
	    return mid;
	if (array[mid] < element)
	    return binarySearch(array, element, mid + 1, high);
	if (array[mid] > element)
	    return binarySearch(array, element, low, mid - 1);

	return -1; // will never arrive there
    }

    // //////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

	int[] array = { 2, 2, 6, 3, 6, 4, 2000 };
	System.out.println(Arrays.toString(array));
	int element = 2000;
	System.out.println("The element " + element + " it's in the position "
		+ binarySearch(array, element));
	System.out.println("Please wait...");

	array = new int[10000];
	long startTime, endTime;
	randomFillArray(array);

	startTime = System.currentTimeMillis();
	selectionSort(array.clone());
	endTime = System.currentTimeMillis();
	System.out.println("selectionSort: " + (endTime - startTime) + " ms");

	startTime = System.currentTimeMillis();
	bubbleSort(array.clone());
	endTime = System.currentTimeMillis();
	System.out.println("bubbleSort: " + (endTime - startTime) + " ms");

	startTime = System.currentTimeMillis();
	mergeSort(array.clone());
	endTime = System.currentTimeMillis();
	System.out.println("mergeSort: " + (endTime - startTime) + " ms");

	startTime = System.currentTimeMillis();
	heapSort(array.clone());
	endTime = System.currentTimeMillis();
	System.out.println("heapSort: " + (endTime - startTime) + " ms");

	startTime = System.currentTimeMillis();
	quickSort(array.clone());
	System.out.println(Arrays.toString(array));
	endTime = System.currentTimeMillis();
	System.out.println("quickSort: " + (endTime - startTime) + " ms");

	System.out.println("Done!");
    }

    public static void randomFillArray(int[] array) {
	Random random = new Random();
	for (int i = 0; i < array.length; i++) {
	    array[i] = random.nextInt();
	}
    }

}
