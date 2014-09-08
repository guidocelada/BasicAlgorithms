package tree;

import java.util.ArrayList;
import java.util.Stack;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BinaryNode<T extends Comparable<T>> extends Node<T> {
	private int height;
	
	public BinaryNode(T value, ArrayList<Node> childs) throws Exception  {
		this.setValue(value);
		this.setHeight(0);
		if (childs.size() <= 2)
			this.setChildrens(childs);
		else
			throw new Exception("Max size of childrens for the BinaryNode is 2");
	}

	public BinaryNode(T value) {
		super(value);
	}
	
	public void add(BinaryNode nodeToAdd) {
		if (nodeToAdd.isLessThan(this)) { //nodeToAdd is less than this.value
			if (this.getLeftChild() == null)
				this.setLeftChild(nodeToAdd);
			else
				this.getLeftChild().add(nodeToAdd);
		} else if (nodeToAdd.isGreaterThan(this)) { //nodeToAdd is greater than this.value
			if (this.getRightChild() == null)
				this.setRightChild(nodeToAdd);
			else
				this.getRightChild().add(nodeToAdd);
		}
	}
	
	public BinaryNode getLeftChild() {
		return (BinaryNode) this.getChildren(0);
	}
	
	public BinaryNode getRightChild() {
		return (BinaryNode) this.getChildren(1);
	}
	
	public void setLeftChild(BinaryNode value) {
		this.setChildren(0, value);
		((BinaryNode) this.getChildren(0)).setHeight(height+1);
	}
	
	public void setRightChild(BinaryNode value) {
		this.setChildren(1, value);
		((BinaryNode) this.getChildren(0)).setHeight(height+1);
	}

	static public void preorderTraversalPrintNoRecursion(BinaryNode root) {
		if (root != null) {
			Stack<BinaryNode> stack = new Stack<BinaryNode>();
			stack.push(root);
			while (!stack.empty()) {
				BinaryNode n = stack.pop();
				System.out.println(n.getValue());
				if (n.getRightChild() != null)
					stack.push(n.getRightChild());
				if (n.getLeftChild() != null)
					stack.push(n.getLeftChild());
			}
		}
	}
	
	static public Node lowestCommonAncestor(BinaryNode root, BinaryNode n1, BinaryNode n2) {
		while (root != null) {
			if (n1.isLessThan(root) & n2.isLessThan(root)) //both are less than root, so go left
				root = root.getLeftChild();
			else if (n1.isGreaterThan(root) & n2.isGreaterThan(root)) //both are greater than root, so go right
				root = root.getRightChild();
			else 
				return root;
		}
		return null;
	}
	
	public boolean isGreaterThan(BinaryNode n1) {
		return (this.getValue().compareTo((T) n1.getValue()) > 0);
	}
	
	public boolean isLessThan(BinaryNode n1) {
		return (this.getValue().compareTo((T) n1.getValue()) < 0);
	}
	
	public boolean isBalanced() {
	  if(this.isLeaf())
	    return true;
	  
	  if (Math.abs(this.getLeftChild().getHeight() - this.getRightChild().getHeight()) >= 2)
	    return false;
	  
	  return (this.getLeftChild().isBalanced() && this.getRightChild().isBalanced());
	    
	}
	
	private int getHeight() {
	  return height;
	}
	
	private void setHeight(int height) {
	  this.height = height;
	}
}
