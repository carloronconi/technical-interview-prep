package examples.queueinterface;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueApp {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        ArrayDeque<Integer> dequeQueue = new ArrayDeque<>();
        ArrayDeque<Integer> dequeStack = new ArrayDeque<>();


        for (int i = 0; i < 5; i++) {
            queue.add(i);
            stack.push(i);
            dequeQueue.addLast(i);
            dequeStack.addLast(i);
        }

        System.out.println("Queue");
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.remove());
            System.out.println(dequeQueue.removeFirst());
        }

        System.out.println("Stack");
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
            System.out.println(dequeStack.removeLast());
        }
    }
}
