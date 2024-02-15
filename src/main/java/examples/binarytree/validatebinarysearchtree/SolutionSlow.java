package examples.binarytree.validatebinarysearchtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

// my solution: O(n^2)
class SolutionSlow {

    public boolean isValidBST(TreeNode root) {
        // base case
        if (root == null || root.left == null && root.right == null) return true;

        try{
            if (root.val <= Collections.max(visitTree(root.left))) {return false;}
        } catch (java.util.NoSuchElementException e) {} // ignore when list is empty

        try{
            if (root.val >= Collections.min(visitTree(root.right))) {return false;}
        } catch (java.util.NoSuchElementException e) {} // ignore when list is empty

        return isValidBST(root.left) && isValidBST(root.right);
    }

    public List<Integer> visitTree(TreeNode tree) {
        List<Integer> values = new ArrayList<>();
        Stack<TreeNode> rest = new Stack<>();
        if (tree == null) return values;

        rest.push(tree);

        while (!rest.isEmpty()) {
            TreeNode t = rest.pop();
            if (t != null) {
                values.add(t.val);
                if (t.left != null) rest.push(t.left);
                if (t.right != null) rest.push(t.right);
            }
        }

        return values;
    }
}
