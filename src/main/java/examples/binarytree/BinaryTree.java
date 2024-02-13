package examples.binarytree;

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

    @Override
    public String toString() {
        return value.toString();
    }
}
