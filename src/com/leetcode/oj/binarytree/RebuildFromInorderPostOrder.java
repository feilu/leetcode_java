package com.leetcode.oj.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/ Rebuild BT from in order
 * and post order traverse.
 * 
 * @author flu
 * 
 */
public class RebuildFromInorderPostOrder {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // tree value => index in inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return buildRec(inorder, postorder, 0, inorder.length - 1, postorder.length - 1, map);
    }

    private TreeNode buildRec(int[] inorder, int[] postorder, int start, int end, int postIndex,
                    Map<Integer, Integer> map) {
        if (start > end || postIndex < 0)
            return null;

        TreeNode root = new TreeNode(postorder[postIndex]);
        int mid = map.get(postorder[postIndex]);

        // end - mid is # of nodes in right subtree. so postIndex - 1 (exclude last root node)
        // then substract (end - mid) gives # of nodes in left subtree, which is the index for
        // postorder array
        root.left = buildRec(inorder, postorder, start, mid - 1, postIndex - 1 - (end - mid), map);
        root.right = buildRec(inorder, postorder, mid + 1, end, postIndex - 1, map);

        return root;
    }
}
