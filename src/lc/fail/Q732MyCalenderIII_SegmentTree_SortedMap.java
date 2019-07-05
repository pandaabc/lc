package lc.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Q732MyCalenderIII_SegmentTree_SortedMap {
    private RangedSTNode parent;
    public Q732MyCalenderIII_SegmentTree_SortedMap() {
        this.parent = new RangedSTNode(0, 1000000000, 0, 1000000000);
    }

    public int book(int start, int end) {



    }

    private void constructNode(RangedSTNode parentNode, int start, int end) {

        int mid = (parentNode.expectedStart + parentNode.expectedEnd) / 2;
        // same as parent
        if (start == parentNode.start && end == parentNode.end) {
            parentNode.maxBooked ++;
        }

    }






    // The below sorted map algo seems to not work and miss 1 or 2 counts easily...
    private SortedMap<Integer, List<Integer>> sortedMap = new TreeMap<>();

    public int bookSM(int start, int end) {
        sortedMap.computeIfAbsent(start, k -> new ArrayList<>()).add(end - 1);
        sortedMap.get(start).sort(Integer::compareTo);
        return findMaxBookedRange();
    }

    private int findMaxBookedRange() {
        int max = 0;
        int cnt = 0;
        for (int s : sortedMap.keySet()) {

            //System.out.println(s);
            cnt = sortedMap.get(s).size();
            for (int e : sortedMap.get(s)) {
                System.out.println(e);
                SortedMap<Integer, List<Integer>> subMap = sortedMap.subMap(s + 1, e + 1);
                if (!subMap.isEmpty()) {
                    int fk = subMap.firstKey();
                    List<Integer> allE = subMap.values().stream().flatMap(List::stream).filter(e0 -> e0 >= fk).collect(Collectors.toList());

                    max = Math.max(max, cnt + allE.size());
                }
                cnt --;
            }
        }
        return max;
    }

}

class RangedSTNode {
    RangedSTNode left;
    RangedSTNode right;
    int start;
    int end;
    int expectedStart;
    int expectedEnd;
    int maxBooked;
    public RangedSTNode(int start, int end, int expectedStart, int expectedEnd) {
        this.start = start;
        this.end = end;
        this.expectedStart = expectedStart;
        this.expectedEnd = expectedEnd;
    }

}
