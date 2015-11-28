package com.leetcode.oj.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 
 * @author flu
 * 
 */
public class RebuildFromInorderPreorder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return build(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode build(int[] preorder, int preorderIndex, int[] inorder, int start, int end,
                    Map<Integer, Integer> map) {
        if (start > end || preorderIndex >= preorder.length)
            return null;

        TreeNode root = new TreeNode(preorder[preorderIndex]);
        int mid = map.get(preorder[preorderIndex]);
        System.out.println("mid is " + mid);

        root.left = build(preorder, preorderIndex + 1, inorder, start, mid - 1, map);

        // preorderIndex + 1 plus mid - start is the starting point for right subtree for preporder array
        root.right = build(preorder, preorderIndex + 1 + mid - start, inorder, mid + 1, end, map);
        return root;
    }

}
