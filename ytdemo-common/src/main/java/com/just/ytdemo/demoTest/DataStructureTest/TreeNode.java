package com.just.ytdemo.demoTest.DataStructureTest;

import lombok.Data;

/**
 * @author yutao.yan
 * @date 2018/8/9 17:23
 */
@Data
public class TreeNode {

    // 左节点
    private TreeNode leftTreeNode;
    // 右节点
    private TreeNode rightTreeNode;
    // 数据
    private int value;

    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * 中序 遍历  根 -> 左 -> 右
     *
     * @param treeNode
     */
    public static void inTraverseBTree(TreeNode treeNode) {
        if (treeNode != null ){
            System.out.println(treeNode.value);
            inTraverseBTree(treeNode.leftTreeNode);
            inTraverseBTree(treeNode.rightTreeNode);
        }
    }


    /**
     * 先序 遍历  左 -> 根 -> 右
     *
     * @param treeNode
     */
    public static void preTraverseBTree(TreeNode treeNode) {
        if (treeNode != null ) {
            preTraverseBTree(treeNode.leftTreeNode);
            System.out.println(treeNode.value);
            preTraverseBTree(treeNode.rightTreeNode);
        }

    }


//    /**
//     * 后序 遍历  左 -> 右 -> 根
//     *
//     * @param treeNode
//     */
//    public static void TraverseBTree(TreeNode treeNode) {
//
//        System.out.println(treeNode.value);
//        inTraverseBTree();
//
//    }


    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);


        node1.setLeftTreeNode(node2);
        node1.setRightTreeNode(node3);
        node2.setLeftTreeNode(node4);
        node2.setRightTreeNode(node5);


//        inTraverseBTree(node1);
        preTraverseBTree(node1);



    }














}
