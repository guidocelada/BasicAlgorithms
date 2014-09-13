package tree;

/**
 * Max Heap: complete binary tree in which the parent its >= childs
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class MaxHeap {
    
    private int[] heap;
    private int size; //Amount of elements
    
    public MaxHeap(int capacity) {
	heap = new int[capacity];
	size = 0;
    }
    
    public MaxHeap(int[] array) {
	heap = array;
	size = array.length;
	
	for(int i = parent(size-1); i >= 0; i--) {
	    sinkDown(i);
	}
    }
    
    /**
     * O(log N)
     */
    public void insert(int n) throws Exception {
	if (size + 1 <= heap.length)
	    size++;
	else
	    throw new Exception("You exceeded the heap size");
	
	int last = size - 1;
	
	heap[last] = n;
	swimUp(last);
    }
    
    /**
     * O(log N)
     */
    public int deleteMax() throws Exception {
	if (size > 0)
	    size--;
	else
	    throw new Exception("There are no more elements");
	int max = heap[0];
	swap(size,0);
	sinkDown(0);
	return max;
    }
    
    
    private void swimUp(int k) {	
	while (k > 0 && heap[parent(k)] < heap[k]) {  // k/2 = parent of k
	    swap(k, parent(k));
	    k = parent(k);
	}
    }
    
    private void sinkDown(int k) {
	
	while (left(k) < size) {
	    int largestChild = left(k);
	    if (left(k) < size - 1 && heap[left(k)] < heap[right(k)] ) 
		largestChild = right(k);
	    
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
    
    /**
     * left child position of node in position k
     */
    private int left(int k) {
	return (k*2)+1;
    }
    
    /**
     * right child position of node in position k
     */
    private int right(int k) {
	return left(k)+1;
    }
    
    /**
     * parent position of node in position k
     */
    private int parent(int k) {
	return k/2;
    }
    
    public static void main(String[] args) {
	MaxHeap pq = new MaxHeap(5);
	try {
        	pq.insert(2);
        	pq.insert(4);
        	pq.insert(1);
        	pq.insert(5);
        	pq.insert(8);
        	assert (pq.deleteMax() == 8);
        	assert (pq.deleteMax() == 5);
        	assert (pq.deleteMax() == 4);
        	assert (pq.deleteMax() == 2);
        	assert (pq.deleteMax() == 1);
	} catch (Exception e) {
	        e.printStackTrace();
	}
    }
}
