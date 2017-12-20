package edu.uopeople.cs1103.unit3;

/**
 * This Program will build a binary tree
 * and then traverse the tree and give
 * the maximum depth
 * and a simple average depth of the leaf nodes
 * <b>Output:</b>
 * node count: 1023
 * max: 30
 * average: 15.5
 * @author Mark Bensch
 */
public class LearningJournal {
	static TreeNode root;

	public static void main( String arg[] ) {
		makeTree( 1023 );
		System.out.println( "node count: " + countNodes( root ) );
		System.out.println( "max: " + maxDepth( root ) );
		System.out.println( "average: " + ( ( (double) maxDepth( root ) + (double) minDepth( root ) ) / 2 ) );
	}

	/**
	 * generate the Binary tree using treeInsert()
	 *
	 * @param size of the tree to make
	 */
	public static void makeTree( int size ) {
		for ( int count = 0; count < size; count++ ) {
			treeInsert( Integer.toString( count ) );
		}
	}

	/**
	 * will return the maximum depth of a binary tree
	 *
	 * @param node root node of a binary tree
	 * @return maximum depth of a binary tree
	 */
	private static int maxDepth( TreeNode node ) {
		if ( node == null ) {
			return 0;
		}
		return 1 + Math.max( maxDepth( node.left ), maxDepth( node.right ) );
	}

	/**
	 * will return the minimum depth of a binary tree
	 *
	 * @param node root node of a binary tree
	 * @return minimum depth of a binary tree
	 */
	private static int minDepth( TreeNode node ) {
		if ( node == null ) {
			return 0;
		}
		return 1 + Math.min( maxDepth( node.left ), maxDepth( node.right ) );
	}

	/**
	 * Add the item to the binary sort tree to which the global variable
	 * "root" refers. (Note that root can’t be passed as a parameter to
	 * this routine because the value of root might change, and a change
	 * in the value of a formal parameter does not change the actual parameter.)
	 */
	public static void treeInsert( String newItem ) {
		if ( root == null ) {
			root = new TreeNode( newItem );
			return;
		}
		TreeNode runner;
		runner = root;
		while ( true ) {
			if ( newItem.compareTo( runner.item ) < 0 ) {
				if ( runner.left == null ) {
					runner.left = new TreeNode( newItem );
					return;
				} else {
					runner = runner.left;
				}
			} else {
				if ( runner.right == null ) {
					runner.right = new TreeNode( newItem );
					return;
				} else {
					runner = runner.right;
				}
			}
		}
	}

	/**
	 * This method will travers a Binary tree
	 * and return the number of nodes in the tree
	 *
	 * @param node
	 * @return the number of nodes in a tree
	 */
	static int countNodes( TreeNode node ) {
		if ( node == null ) {
			return 0;
		} else {
			int count = 1;
			count += countNodes( node.left );
			count += countNodes( node.right );
			return count;
		}
	}

	/**
	 * An object of type TreeNode represents one node in a binary tree of strings.
	 */
	private static class TreeNode {
		String item;
		TreeNode left;
		TreeNode right;

		TreeNode( String str ) {
			item = str;
		}
	}
}
