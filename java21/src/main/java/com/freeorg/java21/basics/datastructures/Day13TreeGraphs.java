package com.freeorg.java21.basics.datastructures;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Day13TreeGraphs {
    public static void main(String[] args) {

    }

    TreeNode insertBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertBST(root.left, val);
        } else if (val > root.val) {
            root.right = insertBST(root.right, val);
        }
        // val == root.val: no-op, duplicate ignored (a common, reasonable BST convention)
        return root;
    }

    List<Integer> inorder(TreeNode root) {
        List<Integer> inorderNodes = new ArrayList<>();
        inorder(root, inorderNodes);
        return inorderNodes;
    }

    private void inorder(TreeNode root, List<Integer> inorderNodes) {
        if (null != root) {
            inorder(root.left, inorderNodes);
            inorderNodes.add(root.val);
            inorder(root.right, inorderNodes);
        }
    }

    List<Integer> levelOrder(TreeNode root) {
        List<Integer> levelOrder = new ArrayList<>();
        if (root == null) {
            return levelOrder;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        levelOrder(queue, levelOrder);
        return levelOrder;
    }

    private void levelOrder(Deque<TreeNode> queue, List<Integer> levelOrder) {
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelOrder.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph.computeIfAbsent(a, key -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, key -> new ArrayList<>()).add(a);
        }
        return graph;
    }

    List<Integer> bfsOrder(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> bfsList = new ArrayList<>();
        if (!graph.containsKey(start)) {
            return bfsList;
        }

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsList.add(node);
            for (int neighbor : graph.get(node)) {
                if (visited.add(neighbor)) {   // true only if newly added (wasn't already present)
                    queue.offer(neighbor);
                }
            }
        }
        return bfsList;
    }
}
