package examples.bitwise;

public class BitwiseOperators {
    public static void main(String[] args) {
        // count ones
        int x = 12;
        int count = 0;
        while (x != 0) {
            count += (x & 1);
            x = x >> 1;
        }
        System.out.println(count);
    }
}
