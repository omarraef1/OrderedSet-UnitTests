
// Omar R. Gebril 	SID 23323978 	CSC 210

/**
 * This collection class maintains a set of values in their natural order as
 * defined by compareTo. The only type that can be stored in this collection
 * must implement interface Comparable<T>
 * 
 * @author Rick Mercer
 * 
 * @param <E>
 *          The type argument when constructed can be any type that implements
 *          Comparable or another interface that extends Comparable
 */
public class OrderedSet<E extends Comparable<E>> {

  // A private class that stores one node in a Binary Search Tree
  private class TreeNode {

    private TreeNode right;
    private E data;
    private TreeNode left;

    public TreeNode(E element) {
      left = null;
      data = element;
      right = null;
    }
  } // end class TreeNode

  private TreeNode root;

  // Create an empty OrderedSet
  public OrderedSet() {
    root = null;
  }

  // 1) Insert element into this OrderedSet and return true keeping this an
  // OrderedSet. If element exists, do not change this OrderedSet, return false.
  // A set must have unique elements. This algorithm should be O(log n)
  public boolean insert(E element) {
	  wasInserted = false;
	  root = insert(root, element);
	  return wasInserted;
  }
  
  private  boolean wasInserted;
  
  private TreeNode insert(TreeNode t, E el) {
	  if (t==null) {
		  t = new TreeNode(el);
		  wasInserted = true;
	  }
	  else if ( el.compareTo(t.data)< 0) {
		  t.left = insert(t.left, el);
		//  wasInserted = true;
	  }
	  else if (el.compareTo(t.data)>0) {
		  t.right = insert(t.right, el);
		//  wasInserted = true;
	  }
	  return t;
  }

  // 2) The number of elements in this OrderedSet, which should be 0 when first
  // constructed. This may run O(n) or O(1)--your choice.
  public int size() {
    return size(root);
  }
  
  private int size(TreeNode t) {
	  if (t==null) {
		  return 0;
	  }
	  else {
		  return size(t.left) +1 + size(t.right);
	  }
  }

  // 3) Return one string that concatenates all elements in this OrderedSet as
  // they are visited in order. Elements are separated by spaces as in "1 4 9"
  // from this OrderedSet.
  // ___ 4
  // _ / _ \
  // _1 ___ 9
  // This algorithm may run O(n)
  public String toStringInorder() {
	  return infix(root).trim();
  }
  
  private String infix(TreeNode t) {
	  if (t==null) {
		  return "";
	  }
	  else {
		  return infix(t.left)+t.data+" " +infix(t.right);
	  }
	  
  }

  boolean contained = false;
  
  // 4) Return true if search equals an element in this OrderedSet.
  public boolean contains(E search) {
	 contains(root, search);
    return contained;
  }
  
  private TreeNode contains(TreeNode t, E search) {
	  if (t!=null) {
		  if (t.data.compareTo(search)==0) {
			  contained = true;
			  return t;
		  }
		  else {
			  TreeNode foundNode = contains(t.left,search);
			  if (foundNode == null) {
				  foundNode = contains(t.right,search);
			  }
			  return foundNode;
		  }
	  }
	  else {
		  contained = false;
		  return null;
	  }
  }

  // 5) Return the element in this OrderedSet that is greater than all other
  // elements using the comapreTo method of E. If this is empty, return null.
  public E max() {
	  return max(root);
  }
  
  private E max(TreeNode t) {
	  if (t==null) {
		  return null;
	  }
	  if (t.right==null) {
		  return t.data;
	  }
	  else {
		  return max(t.right);
	  }
  }

  // 6) Return how many nodes are at the given level. If level > the height of the
  // tree, return 0. Remember that an empty tree has a height of -1 (page 252).
  //
  // ____ 4 _ There is 1 node on level 0
  // ___ / \
  // __ 3 __ 9 _ There are 2 nodes on level 1
  // _ / \ __ \
  // 1 __ 5 __ 11 _ There are 3 nodes in level 2 and 0 nodes on levels >= 3)
//  private int count;
  public int nodesAtLevel(int level) {
	  return nodesAtLevel(root, 0, level);
  }
  
  private int nodesAtLevel(TreeNode t, int curr, int level){
	  int count = 0;
	  if (t==null) {
		  return 0;
	  }
	  if (curr==level) {
		  count++;
	  }
	  else {
		  return nodesAtLevel (t.left, curr+1, level)+nodesAtLevel(t.right, curr+1, level);
	  }
	  return count;
  }

  // 7) Return the intersection of this OrderedSet and the other OrderedSet as
  // a new OrderedSet. Do not modify this OrderedSet or the other OrderedSet.
  // The intersection of two sets is the set of elements that are in both sets.
  // The intersection of {2, 4, 5, 6} and {2, 5, 6, 9} is {2, 5, 6}
  public OrderedSet<E> intersection(OrderedSet<E> other) {
	  OrderedSet<E> result = new OrderedSet<E>();
	  intersection(result, root, other);
	  return result;
  }
  
  private void intersection(OrderedSet<E> result, TreeNode p, OrderedSet<E> other) {
	  if (p==null) {
		  return;
	  }
	  if(other.contains(p.data)) {
		  result.insert(p.data);
		  intersection(result, p.left, other);
		  intersection(result, p.right, other);
	  }
  }

  // 8) Return the union of this OrderedSet and the other OrderedSet as
  // a new OrderedSet. Do not modify this OrderedSet or the other OrderedSet.
  // The union of two sets is the set all distinct elements in the collection.[
  // The union of {2, 4, 6} and {2, 5, 9} is {2, 4, 5, 6, 9}
 // private TreeNode t= root;
 // private TreeNode s=root;
  public OrderedSet<E> union(OrderedSet<E> other) {
	  OrderedSet<E> result = new OrderedSet<E>();
	  TreeNode t = root;
	  TreeNode s = other.root;
	  result = union(t, result);
	  result = union(s, result);
	  return result;
  }
  
  private OrderedSet<E> union(TreeNode t, OrderedSet<E> result){
	  if (t==null) {
		  return result;
	  }
	  else {
		  result.insert(t.data);
		  union(t.left, result);
		  union(t.right,result);
	  }
	return result;
  }

  // 9) Return an OrderedSet that contains all elements that are greater than or
  // equal to the first parameter inclusive and strictly less than the second
  // parameter exclusive.
  public OrderedSet<E> subset(E inclusive, E exclusive) {
	  OrderedSet<E> result = new OrderedSet<E>();
	  subset(root, inclusive, exclusive, result);
	  return result;
  }
  
  private void subset(TreeNode t, E inclusive, E exclusive, OrderedSet<E> result) {
	  if (t==null) {
		  return;
	  }
	  if(inclusive.compareTo(t.data)<0) {
		  subset(t.left, inclusive, exclusive, result);
	  }
	  if (inclusive.compareTo(t.data)<=0 && exclusive.compareTo(t.data)>0) {
		  result.insert(t.data);
	  }
	  if (exclusive.compareTo(t.data)>0) {
		  subset(t.right, inclusive, exclusive, result);
	  }
  }

  // 10) Return true if two different OrderedSet objects have the same exact
  // structure. Each node must have the same number of nodes on every level,
  // the same height, the same size, the same number of leaves, and the same
  // number of internal nodes. Each corresponding node must also have the same
  // number of children (0, 1, or 2) in the same place. The data need NOT be the
  // same. Do not compare corresponding elements.
  //
  // Please see the specification for more examples
  // Here is one example that must return true:
  //
  // ... M ......... P
  // ../...\ ..... /...\
  // .B ... R ... F.....Q
  // ..\ ... \.....\ ....\
  // ...F.... Z ....J.....R
  //
  // Here is one example that must return false
  // ....M...........M
  // ../...\ ..... /...\
  // .B.....R.....B.....R
  // ..\.....\ ...\..../
  // ...F.....Z....F..Z
  // Precondition: E is the same for both OrderedSets
  public boolean sameStructure(OrderedSet<E> other) {
	  TreeNode t = root;
	  TreeNode s = other.root;
    return sameStructure(t, s);
  }
  
  private boolean sameStructure(TreeNode t, TreeNode s) {
	  if(t==null && s==null) {
		  return true;
	  }
	  if (t==null || s==null) {
		  return false;
	  }
	  else return sameStructure(t.left, s.left) && sameStructure(t.right, s.right);
  }

  // 11) If element equals an element in this OrderedSet, remove it and return
  // true. Return false whenever element is not found. In all cases, this
  // OrderedSet must remain a true OrderedSet. Here is one recommended algorithm
  // http://www.cs.arizona.edu/~mercer/Projects/BSTRemoveGeneric.pdf
  //
  // This algorithm should be O(log n)
  //
  public boolean remove(E element) {
	int size1 = size();
	remove(element, root);
	int size2 = size();
    return size1>size2;
  }
  
  private TreeNode remove(E element, TreeNode t) {
	  if (t == null) {
		  return t;
	  }
	  if (size() <3) {
		  t=root;
		  if(t.data.compareTo(element)<0) {
			  t.left=remove(element,t.left);
		  }
		  else if(element.compareTo(t.data)>0) {
			  t.right = remove(element, t.right);
		  }
	  }
	  if (element.compareTo(t.data)<0) {
		  t.left = remove(element, t.left);
	  }
	  else if(element.compareTo(t.data)>0) {
		  t.right = remove(element, t.right);
	  }
	  else {
		  if(t.left == null && t.right == null) {
			  return null;
		  }
		  else if (t.left == null) {
			  return t.right;
		  }
		  else if(t.right == null) {
			  return t.left;
		  }
		  else {
			  TreeNode minValue = findMin(t.right);
			  t.data=minValue.data;
			  t.right = remove(minValue.data, t.right);
		  }
	  }
	  return t;
}

private TreeNode findMin(OrderedSet<E>.TreeNode t) {
	if (t.left!=null) {
		return findMin(t.left);
	}
	return t;
}
}