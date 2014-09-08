package graph;

/**
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class UnionFind {

    private int[] root; // root[i] = root of node i
    private int[] size; // size[i] = size of tree i (ammount of nodes)

    /**
     * @param n: capacity
     */
    public UnionFind(int n) {
	root = new int[n];
	for (int i = 0; i < n; i++) 
	    root[i] = i;
	
	size = new int[n];
	for (int i = 0; i < n; i++) 
	    size[i] = 1;
    }

    /**
     * Union operation between nodes i and j
     * 
     * O(log N)
     */
    public void union(int i, int j) {
	int p = findRoot(i);
	int q = findRoot(j);
	
	if (p == q) return;
	
	if (size[p] < size[q]) { //pick the smallest tree and append it to the bigger tree
	    root[p] = q;
	    size[p] += size[q];
	} else {
	    root[q] = p;
	    size[q] += size[p];
	}
    }
    
    /**
     * @returns if the nodes i and j are connected
     * 
     * O(log N)
     */
    public boolean find(int i, int j) {
	return (findRoot(i) == findRoot(j));
    }

    private int findRoot(int i) {
	while (i != root[i]) {
	    /** Keep tree almost flat **/
	    int parent = root[i];
	    int grandParent = root[parent];
	    root[i] = grandParent;

	    i = parent;
	}

	return i;
    }
}
