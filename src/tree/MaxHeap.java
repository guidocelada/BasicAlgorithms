package tree;

/**
 * Max Heap: complete binary tree in which the parent its >= childs
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class MaxHeap {
    
    private Integer[] heap; //start at index 1
    private int size;
    
    public MaxHeap(int n) {
	heap = new Integer[n+1];
	size = 0;
    }
    
    /**
     * O(log N)
     * @throws Exception 
     */
    public void insert(int n) throws Exception {
	if (size + 1 < heap.length)
	    size++;
	else
	    throw new Exception("You exceeded the heap size");
	
	heap[size] = n;
	swimUp(size);
    }
    
    /**
     * O(log N)
     * @throws Exception 
     */
    public int deleteMax() throws Exception {
	if (size > 0)
	    size--;
	else
	    throw new Exception("There are no more elements");
	
	int max = heap[size];
	swap(size,1);
	sinkDown(1);
	return max;
    }
    
    private void swimUp(int k) {
	int parent = k/2;
	
	while (k > 1 && heap[parent] < heap[k]) {
	    swap(k, parent);
	    parent = k/2;
	}
    }
    
    private void sinkDown(int k) {
	int left = k*2;
	
	while (left <= size) {
	    int right = left + 1;
	    int largestChild = left;
	    
	    if (left < size && heap[left] < heap[right] ) 
		largestChild = right;
	    
	    if (heap[k] >= heap[largestChild])
		break;
	    
	    swap(k, largestChild);
	    k = largestChild;
	}
    }
    
    private void swap(int i, int j) {
	int temp = heap[i];
	heap[i] = heap[j];
	heap[j] = temp;
    }
}
