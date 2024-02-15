package examples.binarytree.breadthfirstsearch;

public class BinaryTreeApp {
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
        BinaryTreeSearch bfs = new BreadthFirstSearch();
        DepthFirstSearch dfs = new DepthFirstSearch();

        System.out.printf("The size is: %d\n", tree.computeSize());
        System.out.println("Performing BFS:");
        bfs.search(tree);
        System.out.println("Performing DFS:");
        dfs.search(tree);

        System.out.println("Performing DFS recursively:");
        dfs.searchRec(tree);
    }
}
