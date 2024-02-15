package examples.binarytree.validatebinarysearchtree;

// https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBSTApp {
    public static void main(String[] args) {
        TreeNode tree = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));
        SolutionFast solutionFast = new SolutionFast();
        SolutionSlow solutionSlow = new SolutionSlow();
        System.out.println(solutionFast.isValidBST(tree));
        System.out.println(solutionSlow.isValidBST(tree));
    }
}
