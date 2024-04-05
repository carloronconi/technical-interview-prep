package questions.stringdecompress;

/**
 * <a href="https://techdevguide.withgoogle.com/resources/former-interview-question-compression-and-decompression/#">
 *     Google Tech Dev Guide</a>!
 * In this exercise, you're going to decompress a compressed string.
 * Your input is a compressed string of the format number[string] and the decompressed output form should be the string written number times. For example:
 * The input
 * 3[abc]4[ab]c
 * Would be output as
 * abcabcabcababababc
 * Other rules
 * Number can have more than one digit. For example, 10[a] is allowed, and just means aaaaaaaaaa
 * One repetition can occur inside another. For example, 2[3[a]b] decompresses into aaabaaab
 * Characters allowed as input include digits, small English letters and brackets [ ].
 * Digits are only to represent amount of repetitions.
 * Letters are just letters.
 * Brackets are only part of syntax of writing repeated substring.
 * Input is always valid, so no need to check its validity.
 */
public class Decompress implements Runnable {
    @Override
    public void run() {
        System.out.println("Decompressing...");
    }

    public static void main(String[] args) {
        Decompress decompress = new Decompress();
        decompress.run();
    }
}
