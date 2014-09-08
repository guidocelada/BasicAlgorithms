package tree;

/**
 * 
 * @author Guido Celada (celadaguido@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
	BinaryNode<Integer> binaryNode = new BinaryNode<Integer>(10);
	binaryNode.add(new BinaryNode<Integer>(5));
	binaryNode.add(new BinaryNode<Integer>(18));
	binaryNode.add(new BinaryNode<Integer>(3));
	binaryNode.add(new BinaryNode<Integer>(6));
	binaryNode.add(new BinaryNode<Integer>(20));
	binaryNode.add(new BinaryNode<Integer>(21));
	System.out.println(BinaryNode.height(binaryNode) + "\n");
	BinaryNode.preorderTraversalPrint(binaryNode);
	System.out.println();
	BinaryNode.preorderTraversalPrintNoRecursion(binaryNode);
	System.out.println();
	System.out.println(BinaryNode.lowestCommonAncestor(binaryNode,
		binaryNode.getLeftChild().getLeftChild(),
		binaryNode.getLeftChild().getRightChild()).getValue());

    }

}
