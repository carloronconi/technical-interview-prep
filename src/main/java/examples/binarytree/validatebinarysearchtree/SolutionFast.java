package examples.binarytree.validatebinarysearchtree;

 /*

}*/

// fast: only O(n) because brings min and max along recursive calls, so pass all nodes only once because it can resolve
// locally
public class SolutionFast {
    boolean flag;
    private void binarySearchTree(TreeNode root, Integer min, Integer max) {
        if(root == null) {
            return;
        }
        if(min != null && min >= root.val) {
            flag = false;
        }

        if(max != null && max <= root.val) {
            flag = false;
        }



        binarySearchTree(root.left, min,root.val);
        binarySearchTree(root.right,root.val,max);
    }
    public boolean isValidBST(TreeNode root) {
        flag = true;
        binarySearchTree(root,null,null);
        return flag;
    }
}