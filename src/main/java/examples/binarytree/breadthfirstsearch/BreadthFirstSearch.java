package examples.binarytree.breadthfirstsearch;

import examples.binarytree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        BinaryTree<Integer> tree =
                new BinaryTree<>(1,
                    new BinaryTree<>(2,
                            new BinaryTree<>(3),
                            new BinaryTree<>(4,
                                    new BinaryTree<>(7),
                                    new BinaryTree<>(8))),
                    new BinaryTree<>(5,
                            new BinaryTree<>(6),
                            null));
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        System.out.printf("The size is: %d\n", bfs.computeSize(tree));
        bfs.search(tree);
    }

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

    private <T> int computeSize(BinaryTree<T> tree) {
        int leftSize = tree.getLeft().map(this::computeSize).orElse(0);
        int rightSize = tree.getRight().map(this::computeSize).orElse(0);

        return 1 + leftSize + rightSize;
    }

}
