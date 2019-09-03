package lc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Q387FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        Map<Character, Long> map = IntStream.range(0, s.length()).mapToObj(s::charAt).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return IntStream.range(0, s.length()).filter(e -> map.get(s.charAt(e)) == 1).findFirst().orElse(-1);
    }

}
