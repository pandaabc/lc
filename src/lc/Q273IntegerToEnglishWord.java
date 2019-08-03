package lc;

import java.util.HashMap;
import java.util.Map;

public class Q273IntegerToEnglishWord {

    public static void main (String[] args) {
        Q273IntegerToEnglishWord qq = new Q273IntegerToEnglishWord();
        System.out.println(qq.numberToWords(0));
        System.out.println(qq.numberToWords(5));
        System.out.println(qq.numberToWords(240));
        System.out.println(qq.numberToWords(3521));
        System.out.println(qq.numberToWords(100000));
        System.out.println(qq.numberToWords(1023500152));
    }

    Map<Integer, String> twos = new HashMap<>();
    Map<Integer, String> ones = new HashMap<>();
    Map<Integer, String> levels = new HashMap<>();
    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        num = generate(num, 1_000_000_000, sb);
        num = generate(num, 1_000_000, sb);
        num = generate(num, 1_000, sb);
        sb.append(threes(num));
        if (sb.length() == 0) {
            sb.append("zero");
        }
        return sb.toString();
    }

    private int generate(int num, int level, StringBuilder sb) {
        int nn = num / level;
        if (nn > 0) {
            sb.append(threes(nn));
            sb.append(" ");
            sb.append(levels(level));
            num = num % level;
            if (num > 0) {
                sb.append(" ");
            }
        }
        return num;
    }

    public String levels(int num) {
        if (levels.isEmpty()) {
            levels.put(1_000, "thousand");
            levels.put(1_000_000, "million");
            levels.put(1_000_000_000, "billion");
        }
        return levels.get(num);
    }

    private String threes(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(ones(num / 100));
            sb.append(" ");
            sb.append("hundred");
            num = num % 100;
            if (num > 0) {
                sb.append(" ");
            }
        }
        if (num > 0) {
            sb.append(twos(num));
        }
        return sb.toString();
    }

    private String ones(int num) {
        if (ones.isEmpty()) {
            ones.put(0, "zero");
            ones.put(1, "one");
            ones.put(2, "two");
            ones.put(3, "three");
            ones.put(4, "four");
            ones.put(5, "five");
            ones.put(6, "six");
            ones.put(7, "seven");
            ones.put(8, "eight");
            ones.put(9, "nine");
        }
        return ones.get(num);
    }

    private String twos(int num) {
        if (twos.isEmpty()) {
            twos.put(10, "ten");
            twos.put(11, "eleven");
            twos.put(12, "twelve");
            twos.put(13, "thirteen");
            twos.put(14, "fourteen");
            twos.put(15, "fifteen");
            twos.put(16, "sixteen");
            twos.put(17, "seventeen");
            twos.put(18, "eighteen");
            twos.put(19, "nineteen");
            twos.put(20, "twenty");
            twos.put(30, "thirty");
            twos.put(40, "forty");
            twos.put(50, "fifty");
            twos.put(60, "sixty");
            twos.put(70, "seventy");
            twos.put(80, "eighty");
            twos.put(90, "ninety");
        }

        if (num <= 20 && num >= 10) {
            return twos.get(num);
        }
        StringBuilder sb = new StringBuilder();
        if (num >= 10) {
            sb.append(twos.get(num / 10 * 10));
            num = num % 10;
            if (num > 0) {
                sb.append(" ");
            }
        }
        if (num > 0) {
            sb.append(ones(num));
        }
        return sb.toString();
    }

}
