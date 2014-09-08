/**
 * 
 */
package tree;

import java.util.ArrayList;

/**
 * 
 * @author Guido J. Celada
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class Node<T extends Comparable<T>> {
	private T value;
	private ArrayList<Node> childrens; 
	
	protected Node() {}
	
	
	public Node(T value, ArrayList<Node> childs) {
		this.setValue(value);
		this.setChildrens(childs);
	}
	
	public Node(T value) {
		this.setValue(value);
		this.setChildrens(new ArrayList<Node>());
	}
	
	public void add(Node node) {
	}
	
	public void remove(Node node) {
	}
	
	public int getChildsLenght() {
		return this.getChildrens().size();
	}
	
	public Node getChildren(int index) {
		if (index < this.getChildsLenght() )
			return this.getChildrens().get(index);
		else 
			return null;
	}
	
	public void setChildren(int index, Node value) {
		if (index < this.getChildsLenght())
			this.getChildrens().set(index, value);
		else {
			while (index > this.getChildsLenght())
				this.getChildrens().add(null);
			this.getChildrens().add(index, value);
		}
		
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Node> getChildrens() {
		return childrens;
	}

	protected void setChildrens(ArrayList<Node> childrens) {
		this.childrens = childrens;
	}
	
	
	static public int height(Node root) {
		if (root == null)
			return 0;
		else {
			int max = 0;
			ArrayList<Node> childs = root.getChildrens();
			for (Node n : childs) 
				max = Math.max(Node.height(n), max);
			return 1 + max;
		}
	}
	
	static public void preorderTraversalPrint(Node root) {
		if (root != null) {
			System.out.println(root.getValue());
			ArrayList<Node> childrens = root.getChildrens();
			for (Node n : childrens) {
				preorderTraversalPrint(n);
			}
		}
	}
	
	public boolean isLeaf() {
	  return (this.getChildrens() == null);
	}
}
