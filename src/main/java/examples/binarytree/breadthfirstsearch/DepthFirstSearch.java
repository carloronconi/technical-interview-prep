package examples.binarytree.breadthfirstsearch;

import java.util.Stack;

public class DepthFirstSearch implements BinaryTreeSearch {
    @Override
    public <T> void search(BinaryTree<T> tree) {
        Stack<BinaryTree<T>> stack = new Stack<>();
        stack.push(tree);

        while(!stack.empty()) {
            BinaryTree<T> t = stack.pop();
            System.out.println(t);

            t.getRight().ifPresent(stack::push);
            t.getLeft().ifPresent(stack::push);
        }
    }

    // Can be done recursively (as function calls are LIFO)
    public <T> void searchRec(BinaryTree<T> tree) {
        System.out.println(tree);

        tree.getLeft().ifPresent(this::searchRec);
        tree.getRight().ifPresent(this::searchRec);
    }
}
