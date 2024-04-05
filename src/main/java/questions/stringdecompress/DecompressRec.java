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
public class DecompressRec implements Runnable {

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isAlpha(char c) {
        return c>= 'a' && c <= 'z';
    }

    private boolean isOpen(char c) {
        return c == '[';
    }

    private boolean isClose(char c) {
        return c == ']';
    }

    private record RecResult(String text, Integer idx) {}

    private RecResult decRec(char[] chars, int idx) {
        StringBuilder sb = new StringBuilder();

        int sum = 0;
        int digits = 0;
        int skipTo = 0;
        for (int i = idx; i < chars.length; i++) {
            if (skipTo > i) continue;
            char c = chars[i];
            if (isAlpha(c)) {
                sb.append(c);
                continue;
            }
            if (isDigit(c)) {
                sum += (int) (Integer.parseInt(String.valueOf(c)) * Math.pow(10, digits));
                digits++;
                continue;
            }
            if (isOpen(c)) {
                RecResult innerResult = decRec(chars, i + 1);
                sb.append(innerResult.text().repeat(sum));
                sum = 0;
                digits = 0;
                skipTo = innerResult.idx() + 1;
                continue;
            }
            if (isClose(c)) return new RecResult(sb.toString(), i);
        }

        return new RecResult(sb.toString(), -1);
    }

    @Override
    public void run() {
        String s = "3[c11[ab]d]zz4[x]";
        System.out.printf("Decompressing: %s\n", s);

        char[] chars = s.toCharArray();

        String decompressed = decRec(chars, 0).text();
        System.out.printf("Decompressed: %s\n", decompressed);
    }

    public static void main(String[] args) {
        DecompressRec decompress = new DecompressRec();
        decompress.run();
    }
}
