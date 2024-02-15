package examples.binarytree.breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements BinaryTreeSearch {
    @Override
    public <T> void search(BinaryTree<T> tree) {
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            BinaryTree<T> t = queue.poll();
            System.out.println(t);

            t.getLeft().ifPresent(queue::add);
            t.getRight().ifPresent(queue::add);
        }
    }

}
