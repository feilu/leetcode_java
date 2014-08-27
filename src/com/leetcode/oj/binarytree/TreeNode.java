package com.leetcode.oj.binarytree;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v) {
        this.val = v;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
