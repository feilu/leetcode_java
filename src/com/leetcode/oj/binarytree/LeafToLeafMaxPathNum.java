package com.leetcode.oj.binarytree;


public class LeafToLeafMaxPathNum {

    public int findMaxPathNum(TreeNode root) {
        if (root == null)
            return 0;
        
        SubSolution sol = findMaxRec(root);
        return Math.max(sol.maxInSubTree, sol.maxLeefToRoot);
    }
    
    private SubSolution findMaxRec(TreeNode node) {
        if (node == null)
            return new SubSolution(-1, -1);

        if (node.left == null && node.right == null)
            return new SubSolution(node.val, node.val);
        
        SubSolution left = findMaxRec(node.left);
        SubSolution right = findMaxRec(node.right);

        // num that passed subtree
        int maxNumPassNode = combine(left.maxLeefToRoot, right.maxLeefToRoot, node.val);                                        
        return new SubSolution(maxNumPassNode, Math.max(combine(left.maxLeefToRoot, -1, node.val), combine(-1,
                        right.maxLeefToRoot, node.val)));
    }

    private int combine(int left, int right, int root) {
        if (left > 0) {
            if (right > 0) { 
                // compare leftRootRight vs rightRootLeft. need to reverse the second half
                int tmp1 = Integer.parseInt(Integer.toString(left) + Integer.toString(root)
                                + new StringBuilder().append(right).reverse().toString());
                int tmp2 = Integer.parseInt(Integer.toString(right) + Integer.toString(root)
                                + new StringBuilder().append(left).reverse().toString());
                return Math.max(tmp1, tmp2);
            } else {
                return Integer.parseInt(Integer.toString(left) + Integer.toString(root));
            }
        } else if (right > 0) {
            return Integer.parseInt(Integer.toString(right) + Integer.toString(root));
        } else {
            return root;
        }
    }

    private class SubSolution {
        int maxInSubTree;
        int maxLeefToRoot;
        
        public SubSolution(int subtree, int leafToRoot) {
            this.maxInSubTree = subtree;
            this.maxLeefToRoot = leafToRoot;
        }
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(1);
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(9);

        n.left = null;
        n.right = b;
        b.left = c;
        b.right = d;
        System.out.println(new LeafToLeafMaxPathNum().findMaxPathNum(n));
    }
}
