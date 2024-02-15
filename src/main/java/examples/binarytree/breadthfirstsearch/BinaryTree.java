package examples.binarytree.breadthfirstsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

@AllArgsConstructor
public class BinaryTree<T> {
    @Getter
    private T value;
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(@NonNull T value) {
        this(value, null, null);
    }

    public Optional<BinaryTree<T>> getLeft() {
        return Optional.ofNullable(left);
    }

    public Optional<BinaryTree<T>> getRight() {
        return Optional.ofNullable(right);
    }

    public int computeSize() {
        int leftSize = getLeft().map(BinaryTree::computeSize).orElse(0);
        int rightSize = getRight().map(BinaryTree::computeSize).orElse(0);

        return 1 + leftSize + rightSize;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
