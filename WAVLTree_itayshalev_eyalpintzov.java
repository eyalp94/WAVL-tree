
public class WAVLTree_itayshalev_eyalpintzov {
	

	/**
	 *
	 * WAVLTree
	 *
	 * An implementation of a WAVL Tree.
	 * (Haupler, Sen & Tarajan ‘15)
	 *
	 */

	public class WAVLTree {
		
		private WAVLNode root;
		private WAVLNode externalNode;
		private WAVLNode min;
		private WAVLNode max;
		
		public WAVLTree()
		{
			this.root = null;
			this.min = null;
			this.max = null;
			this.externalNode = new WAVLNode(-1, null, null);
			this.externalNode.rank = -1;
			this.externalNode.isExternal = true;
			this.externalNode.subTreeSize = 0;
		}
		
	  /**
	   * public boolean empty()
	   *
	   * returns true if and only if the tree is empty
	   *
	   */
	  public boolean empty() {
		  return(this.root == null);
	  }

	 /**
	   * public String search(int k)
	   *
	   * returns the info of an item with key k if it exists in the tree
	   * otherwise, returns null
	   */
	  public String search(int k)
	  {
		  WAVLNode currentNode = this.root;
		  while(currentNode.isInnerNode())
		  {
			  if(currentNode.getKey() == k)
			  {
				  return currentNode.getValue();
			  }
			  else if(currentNode.getKey() > k)
			  {
				  currentNode = currentNode.getLeft();
			  }
			  else
			  {
				  currentNode = currentNode.getRight();
			  }
		  }
		  return null;
	  }

	  /**
	   * public int insert(int k, String i)
	   *
	   * inserts an item with key k and info i to the WAVL tree.
	   * the tree must remain valid (keep its invariants).
	   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
	   * returns -1 if an item with key k already exists in the tree.
	   */
	   public int insert(int k, String i) {
		  WAVLNode parentToInsert;
		  if(this.root == null)
		  {
			  this.root = new WAVLNode (k,i,this.getExternalNode());
			  return 0;
		  }
		  else
		  {
			  parentToInsert = this.root.placeToInsert(k);
			  //System.out.println(parentToInsert.getKey());
			  WAVLNode nodeToInsert = new WAVLNode(k,i,this.getExternalNode());
			  if(parentToInsert == null)
			  {
				  return -1;
			  }
			  if(this.min == null || k < this.min.getKey())
			  {
				  this.min = nodeToInsert;
			  }
			  if(this.max == null || k > this.max.getKey())
			  {
				  this.max = nodeToInsert;
			  }
			  if(parentToInsert.getKey() > k)
			  {
				  parentToInsert.setLeft(nodeToInsert);
				  nodeToInsert.setParent(parentToInsert);
			  }
			  else
			  {
				  parentToInsert.setRight(nodeToInsert);
				  nodeToInsert.setParent(parentToInsert);
			  }
			  
			  WAVLNode currentNode = nodeToInsert;
			  while(currentNode.getParent() != null)
			  {
				  currentNode = currentNode.getParent();
				  currentNode.setSubTreeSize(currentNode.getSubTreeSize()+1);
				  //System.out.println("Changed " +currentNode.getKey()+ " To " + (currentNode.getSubTreeSize()));
			  }
			  
			  int numRebalance = parentToInsert.rebalanceInsert();
			  while(this.root.getParent() != null)
			  {
				  //System.out.println(this.root.getKey());
				  this.setRoot(this.root.getParent());
			  }
			  return numRebalance;
		  }
	   }
	  
	   /**
	   * public int delete(int k)
	   *
	   * deletes an item with key k from the binary tree, if it is there;
	   * the tree must remain valid (keep its invariants).
	   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
	   * returns -1 if an item with key k was not found in the tree.
	   */
	   public int delete(int k)
	   {
		   return 42;   // to be replaced by student code
	   }

	   /**
	    * public String min()
	    *
	    * Returns the info of the item with the smallest key in the tree,
	    * or null if the tree is empty
	    */
	   public String min()
	   {
		   if(this.min != null)
		   {
			   return this.min.getValue(); 
		   }
		   return null;
	   }

	   /**
	    * public String max()
	    *
	    * Returns the info of the item with the largest key in the tree,
	    * or null if the tree is empty
	    */
	   public String max()
	   {
		   if(this.max != null)
		   {
			   return this.max.getValue(); 
		   }
		   return null;
	   }

	   /**
	   * public int[] keysToArray()
	   *
	   * Returns a sorted array which contains all keys in the tree,
	   * or an empty array if the tree is empty.
	   */
	   public int[] keysToArray()
	   {
		   if(this.size() == 0)
		   {
			   return new int[0];
		   }
		   int[] arr = new int[this.size()]; 
		   return this.root.keysToArray(arr, 0);
	   }

	   /**
	   * public String[] infoToArray()
	   *
	   * Returns an array which contains all info in the tree,
	   * sorted by their respective keys,
	   * or an empty array if the tree is empty.
	   */
	   public String[] infoToArray()//changed
	   {
		   if(this.size() == 0)
		   {
			   return new String[0];
		   }
		   String[] arr = new String[this.size()];
		   int[] count = new int[1];
		   count[0] = 0;
		   return this.root.infoToArray(arr, count); 
	   }

	   /**
	    * public int size()
	    *
	    * Returns the number of nodes in the tree.
	    *
	    */
	   public int size()
	   {
		  if(root == null)
		  {
			  return 0;
		  }
		  return root.getSubTreeSize();
	   }
	   
	     /**
	    * public WAVLNode getRoot()
	    *
	    * Returns the root WAVL node, or null if the tree is empty
	    *
	    */
	   public WAVLNode getRoot()
	   {
		   return this.root;
	   }
	   
	   /**
	    * public WAVLNode getExternalNode()
	    *
	    * Returns the External WAVL node.
	    *
	    */
	   public WAVLNode getExternalNode()
	   {
		   return this.externalNode;
	   }
	   

	     /**
	    * public WAVLNode setRoot()
	    *
	    * Change the tree's root to the new root.
	    *
	    */
	   public void setRoot(WAVLNode root)
	   {
		   this.root = root;
	   }
	   
	     /**
	    * public int select(int i)
	    *
	    * Returns the value of the i'th smallest key (return -1 if tree is empty)
	    * Example 1: select(1) returns the value of the node with minimal key 
	        * Example 2: select(size()) returns the value of the node with maximal key 
	        * Example 3: select(2) returns the value 2nd smallest minimal node, i.e the value of the node minimal node's successor  
	    *
	    */   
	   public String select(int i)
	   {		   
		   String[] arr = this.infoToArray();
		   if(i > arr.length)
		   {
			   return "-1";
		   }
		   return arr[i-1];
	   }

	   /**
	   * public class WAVLNode
	   */
	   public class WAVLNode{
		   private int key;
		   private String value;
		   private int rank;
		   private WAVLNode parent;
		   private WAVLNode leftSon;
		   private WAVLNode rightSon;
		   private boolean isExternal;
		   private int subTreeSize;
		   
		   public WAVLNode(int key, String value ,WAVLNode externalNode)
		   {
			   this.key = key;
			   this.value = value;
			   this.rank = 0;
			   this.leftSon = externalNode;
			   this.rightSon = externalNode;
			   this.isExternal = false;
			   this.subTreeSize = 1;
		   }
		   
		   public int getKey()
		   {
			   return this.key; 
		   }
		   
		   public String getValue()
		   {
			   return this.value;
		   }
		   
		   public WAVLNode getLeft()
		   {
			   return this.leftSon;
		   }
		   
		   public WAVLNode getRight()
		   {
			   return this.rightSon;
		   }
		   
		   public boolean getIsExternal()
		   {
			   return this.isExternal;
		   }
		   
		   public WAVLNode getParent()
		   {
			   return this.parent;
		   }
		   
		   public int getRank()
		   {
			   return this.rank;
		   }
		   
		   public int getSubTreeSize()
		   {
			   return this.subTreeSize;
		   }
		   
		   public void setValue(String value)
		   {
			   if(!this.isExternal)
			   {
				   this.value = value;
			   }               
		   }
		   
		   public void setRank(int rank)
		   {
			   if(!this.isExternal)
			   {
				   this.rank = rank;
			   }
		   }
		   
		   public void setParent(WAVLNode parent)
		   {
			   this.parent = parent;
		   }
		   
		   public void setRight(WAVLNode rightSon)
		   {
			   if(!this.isExternal)
			   {
				   this.rightSon = rightSon;
			   }
		   }
		   
		   public void setLeft(WAVLNode leftSon)
		   {
			   if(!this.isExternal)
			   {
				   this.leftSon = leftSon;
			   }
		   }
		   
		   public void setSubTreeSize(int size)
		   {
			   if(!this.isExternal)
			   {
				   this.subTreeSize = size;
			   }
		   }
		   
		   public boolean isInnerNode()
		   {
			   return !isExternal;
		   }
		   
		   public String toString()
		   {
			   String st = "";
			   st += "key = " + key + ", value = " + value + ", rank " + rank +", parentKey = " + "parent.getKey()" + ", leftKey = " + "leftSon.getKey()" + ", rightKey = " + "rightSon.getKey()" + ", isExternal = " + isExternal + ", size = " + this.subTreeSize; 
			   return st;
		   }
		   
		   public int[] keysToArray(int[] arr, int currentIndex)
		   {
			   if(this.getLeft().isExternal && this.getRight().isExternal)
			   {
				   arr[currentIndex] = this.getKey();
				   return arr;
			   }
			   this.getLeft().keysToArray(arr, currentIndex);
			   currentIndex += this.getLeft().getSubTreeSize();
			   arr[currentIndex] = this.getKey();
			   currentIndex++;
			   this.getRight().keysToArray(arr, currentIndex);
			   return arr;
		   }
		   
		   public String[] infoToArray(String[] arr, int[] currentIndex)//changed
		   {
			   if(this.getLeft().isExternal && this.getRight().isExternal)
			   {
				   arr[currentIndex[0]] = this.getValue();
				   currentIndex[0]++;
				   return arr;
			   }
			   if(!this.getLeft().isExternal)
			   {
				   this.getLeft().infoToArray(arr, currentIndex);
				   //currentIndex += this.getLeft().getSubTreeSize();
			   }
			   arr[currentIndex[0]] = this.getValue();
			   currentIndex[0]++;
			   if(!this.getRight().isExternal)
			   {
				   this.getRight().infoToArray(arr, currentIndex);
			   }
			   return arr;
		   }
		   
		   public WAVLNode placeToInsert(int key)//tiud
		   {
			   if(key == this.getKey())
			   {
				   return null;
			   }
			   if(key < this.getKey())
			   {
				   if(this.getLeft().isExternal)
				   {
					   return this;
				   }
				   return this.getLeft().placeToInsert(key);
			   }
			   else
			   {
				   if(this.getRight().isExternal)
				   {
					   return this;
				   }
				   return this.getRight().placeToInsert(key);   
			   }
		   }
		   
		   public int rebalanceInsert()
		   {
			   int thisRank = this.getRank();
			   WAVLNode left = this.getLeft();
			   WAVLNode right = this.getRight();	
			   WAVLNode parent = this.getParent();
			   int leftDiffRank = thisRank - left.getRank();
			   int rightDiffRank = thisRank - right.getRank();
			   
			   if(((leftDiffRank <= 2) && (leftDiffRank > 0)) && ((rightDiffRank <= 2) && (rightDiffRank > 0)))
			   {
				   return 0;
			   }
			   if((leftDiffRank == 0 && rightDiffRank==1) || (leftDiffRank == 1 && rightDiffRank==0))
			   {//promote
				   this.setRank(thisRank + 1);
				   if(parent == null)
				   {
					   return 1;
				   }
				   return 1 + parent.rebalanceInsert();
			   }
			   
			   if(leftDiffRank == 0)
			   { 
				   WAVLNode leftLeft = left.getLeft();
				   WAVLNode leftRight = left.getRight();
				   if(left.getRank() - leftLeft.getRank() == 1)// one rotate - case2
				   {
					   if(parent != null)
					   {
						   left.setParent(parent);
						   if(parent.getKey() < this.getKey())
						   {
							   parent.setRight(left);
						   }
						   else 
						   {
							   parent.setLeft(left);
						   }
					   }
					   else 
					   {
						   left.setParent(null);
					   }
					   this.setParent(left);
					   left.setRight(this);
					   this.setRank(thisRank - 1);
					   this.setLeft(leftRight);
					   leftRight.setParent(this);
					   
					   //System.out.println("Changed " +this.getKey()+ " To " + (this.getSubTreeSize() - left.getSubTreeSize() + leftRight.getSubTreeSize()));
					   this.setSubTreeSize(this.getSubTreeSize() - left.getSubTreeSize() + leftRight.getSubTreeSize()); //z
					   //System.out.println("Changed " +left.getKey()+ " To " + (left.getSubTreeSize() - leftRight.getSubTreeSize() + this.getSubTreeSize()));
					   left.setSubTreeSize(left.getSubTreeSize() - leftRight.getSubTreeSize() + this.getSubTreeSize()); //x
					   
					   
					   return 1;
				   }
				   else//double rotate - case3
				   {
					   //System.out.println("here: " + leftLeft);
					   WAVLNode leftRightLeft = leftRight.getLeft();
					   WAVLNode leftRightRight = leftRight.getRight();
					   if(parent != null)
					   {
						   leftRight.setParent(parent);
						   if(parent.getKey() < this.getKey())
						   {
							   parent.setRight(leftRight);
						   }
						   else 
						   {
							   parent.setLeft(leftRight);
						   }
					   }
					   else 
					   {
						   leftRight.setParent(null);
					   }
					   this.setParent(leftRight);
					   leftRight.setRight(this);
					   this.setLeft(leftRightRight);
					   leftRightRight.setParent(this);
					   leftRight.setLeft(left);
					   left.setParent(leftRight);
					   left.setRight(leftRightLeft);
					   leftRightLeft.setParent(left);
					   left.setRank(left.getRank() - 1);
					   this.setRank(thisRank - 1);
					   leftRight.setRank(leftRight.getRank() + 1);
					   
					   //System.out.println("Changed " +this.getKey()+ " To " + (this.getSubTreeSize() - left.getSubTreeSize() + leftRightRight.getSubTreeSize()));
					   this.setSubTreeSize(this.getSubTreeSize() - left.getSubTreeSize() + leftRightRight.getSubTreeSize()); //z
					   //System.out.println("Changed " +left.getKey()+ " To " + (left.getSubTreeSize() - leftRight.getSubTreeSize() + leftRightLeft.getSubTreeSize()));
					   left.setSubTreeSize(left.getSubTreeSize() - leftRight.getSubTreeSize() + leftRightLeft.getSubTreeSize()); //x
					   //System.out.println("Changed " +leftRight.getKey()+ " To " + (left.getSubTreeSize() + this.getSubTreeSize() + 1));
					   leftRight.setSubTreeSize(left.getSubTreeSize() + this.getSubTreeSize() + 1); //b
					   					   
					   return 2;
				   }
				   
			   }
			   
			   else
			   { 
				   WAVLNode rightLeft = right.getLeft();
				   WAVLNode rightRight = right.getRight();
				   if(right.getRank() - rightRight.getRank() == 1)//one rotate - case2 sim
				   {
					   if(parent != null)
					   {
						   right.setParent(parent);
						   if(parent.getKey() < this.getKey())
						   {
							   parent.setRight(right);
						   }
						   else 
						   {
							   parent.setLeft(right);
						   }
					   }
					   else
					   {
						   right.setParent(null);
					   }   
					   this.setParent(right);
					   right.setLeft(this);
					   this.setRank(thisRank - 1);
					   this.setRight(rightLeft);
					   rightLeft.setParent(this);
					   
					   //System.out.println("Changed " +this.getKey()+ " To " + (this.getSubTreeSize() - right.getSubTreeSize() + rightLeft.getSubTreeSize()));
					   this.setSubTreeSize(this.getSubTreeSize() - right.getSubTreeSize() + rightLeft.getSubTreeSize()); //z
					   //System.out.println("Changed " +right.getKey()+ " To " + (right.getSubTreeSize() - rightLeft.getSubTreeSize() + this.getSubTreeSize()));
					   right.setSubTreeSize(right.getSubTreeSize() - rightLeft.getSubTreeSize() + this.getSubTreeSize()); //y
					   
					   return 1;
				   }
				   
				   else// double rotation - case3 sim
				   {
					   WAVLNode rightLeftLeft = rightLeft.getLeft();
					   WAVLNode rightLeftRight = rightLeft.getRight();
					   if(parent != null)
					   {
						   rightLeft.setParent(parent);
						   if(parent.getKey() < this.getKey())
						   {
							   parent.setRight(rightLeft);
						   }
						   else 
						   {
							   parent.setLeft(rightLeft);
						   }
					   }
					   else
					   {
						   rightLeft.setParent(null);
					   }
					   this.setParent(rightLeft);
					   rightLeft.setLeft(this);
					   this.setRight(rightLeftLeft);
					   rightLeftLeft.setParent(this);
					   rightLeft.setRight(right);
					   right.setParent(rightLeft);
					   right.setLeft(rightLeftRight);
					   rightLeftRight.setParent(right);
					   right.setRank(right.getRank() - 1);
					   this.setRank(thisRank - 1);
					   rightLeft.setRank(rightLeft.getRank() + 1);
					   
					   //System.out.println("Changed " +this.getKey()+ " To " + (this.getSubTreeSize() - right.getSubTreeSize() + rightLeftLeft.getSubTreeSize()));
					   this.setSubTreeSize(this.getSubTreeSize() - right.getSubTreeSize() + rightLeftLeft.getSubTreeSize()); //z
					   //System.out.println("Changed " +left.getKey()+ " To " + (right.getSubTreeSize() - rightLeft.getSubTreeSize() + rightLeftRight.getSubTreeSize()));
					   right.setSubTreeSize(right.getSubTreeSize() - rightLeft.getSubTreeSize() + rightLeftRight.getSubTreeSize()); //y
					   //System.out.println("Changed " +rightLeft.getKey()+ " To " + (right.getSubTreeSize() + this.getSubTreeSize() + 1));
					   rightLeft.setSubTreeSize(right.getSubTreeSize() + this.getSubTreeSize() + 1); //a
					   
					   return 2;
				   }
				   
			   }
			   
		   }
		   
	   }
	}

	public static void main(String[] args)
	{
		WAVLTree_itayshalev_eyalpintzov forest = new WAVLTree_itayshalev_eyalpintzov();
		WAVLTree tree = forest.new WAVLTree();
		/*tree.insert(4, "4");
		tree.insert(5, "5");
		tree.insert(1, "1");
		tree.insert(2, "2");
		tree.insert(0, "0");
		tree.insert(3, "3");
		*/ // double rotate case 3
		
		/*tree.insert(3, "3");
		tree.insert(2, "2");
		tree.insert(5, "5");
		tree.insert(5, "5");
		tree.insert(4, "4");
		tree.insert(6, "6");
		tree.insert(7, "7");
		*/ // rotate case 2 sim
		
		tree.insert(100, "100");
		tree.insert(1, "1");
		tree.insert(8, "8");
		tree.insert(5, "5");
		tree.insert(25, "25");
		tree.insert(9, "9");
		tree.insert(4, "4");
		tree.insert(3, "3");
		tree.insert(19, "19");
		// double rotate case 3 - sim
		
		//System.out.println(tree.getRoot());
		
		/*System.out.println(tree.getRoot());
		System.out.println(tree.getRoot().getLeft());
		System.out.println(tree.getRoot().getRight());
		System.out.println(tree.getRoot().getLeft().getLeft());
		System.out.println(tree.getRoot().getLeft().getRight());
		System.out.println(tree.getRoot().getRight().getLeft());
		System.out.println(tree.getRoot().getRight().getRight());
		System.out.println();
		*/
		//System.out.println(tree.getRoot().getLeft().getLeft().getLeft());
		//System.out.println(tree.getRoot().getLeft().getLeft().getRight());
		//System.out.println(tree.min());
		//System.out.println(tree.max());
		/*String[] arr = tree.infoToArray();
		for(int i=0; i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}*/
		//System.out.println(tree.select(9));
		//tree.select(6);
		/*System.out.println(tree.root.getSubTreeSize());
		System.out.println(tree.getRoot().getLeft().getSubTreeSize());
		System.out.println(tree.getRoot().getRight().getSubTreeSize());
		System.out.println(tree.getRoot().getLeft().getLeft().getSubTreeSize());
		System.out.println(tree.getRoot().getLeft().getRight().getSubTreeSize());
		System.out.println(tree.getRoot().getRight().getLeft().getSubTreeSize());
		System.out.println(tree.getRoot().getRight().getRight().getSubTreeSize());
		*/
		
		/*tree.insert(5, "5");
		tree.insert(3, "3");
		tree.insert(6, "6");
		tree.insert(4, "4");
		tree.insert(2, "2");
		tree.insert(1, "1");
		*/ // rotate case 2
		
		/*System.out.println("Root - the key: "+ tree.root.getKey() +" , the size: "+ tree.root.getSubTreeSize());
		System.out.println("Left - the key: "+ tree.root.getLeft().getKey() +" , the size: "+ tree.root.getLeft().getSubTreeSize());
		System.out.println("Right - the key: "+ tree.root.getRight().getKey() +" , the size: "+ tree.root.getRight().getSubTreeSize());
		System.out.println("RightLeft - the key: "+ tree.root.getRight().getLeft().getKey() +" , the size: "+ tree.root.getRight().getLeft().getSubTreeSize());
		System.out.println("RightRight - the key: "+ tree.root.getRight().getRight().getKey() +" , the size: "+ tree.root.getRight().getRight().getSubTreeSize());
		System.out.println("LeftLeft - the key: "+ tree.root.getLeft().getLeft().getKey() +" , the size: "+ tree.root.getLeft().getLeft().getSubTreeSize());
		System.out.println("LeftRight - the key: "+ tree.root.getLeft().getRight().getKey() +" , the size: "+ tree.root.getLeft().getRight().getSubTreeSize());
		//System.out.println(tree.root.getLeft().getRight().isExternal);
		 */
		
		String[] arr = tree.infoToArray();
		for(int i=0; i<arr.length; i++)
		{
			System.out.println(arr[i]);
		}
	}
}
