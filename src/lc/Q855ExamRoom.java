package lc;

import java.util.*;

public class Q855ExamRoom {

    TreeSet<int[]> slots;
    Map<Integer, List<int[]>> slotByLocMap;
    int N;
    public Q855ExamRoom(int N) {
        slots = new TreeSet<>((e1, e2) -> {
            int s1 = getSlotSize(e1);
            int s2 = getSlotSize(e2);
            int res = Integer.compare(s2, s1);
            if (res == 0) {
                res = Integer.compare(e1[0], e2[0]);
            }
            return res;
        });
        int[] initialSlot = new int[]{-1, N};
        slotByLocMap.computeIfAbsent(-1, k -> new ArrayList<>()).add(null);
        slotByLocMap.get(-1).add(initialSlot);
        slotByLocMap.computeIfAbsent(N, k -> new ArrayList<>()).add(initialSlot);
        slotByLocMap.get(N).add(null);
        slots.add(initialSlot);
        this.N = N;
    }

    private int getSlotSize(int[] s) {
        if (s[0] == -1) {
            return s[1];
        } else if (s[1] == N) {
            return N - s[0] - 1;
        } else {
            return (s[1] - s[0]) / 2;
        }
    }

    public int seat() {
        int[] slot = this.slots.pollFirst();
        int split;
        if (slot[0] == -1) {
            split = 0;
        } else if (slot[1] == N) {
            split = N - 1;
        } else if (slot[1] - slot[0] == 1){
            return -1;
        } else {
            split = (slot[0] + slot[1]) / 2;
        }
        int[] s1 = new int[]{slot[0], split};
        int[] s2 = new int[]{split, slot[1]};
        slotByLocMap.get(s1[0]).set(1, s1);
        slotByLocMap.get(s2[1]).set(0, s2);
        slotByLocMap.computeIfAbsent(split, k -> new ArrayList<>()).add(s1);
        slotByLocMap.get(split).add(s2);
        slots.addAll(slotByLocMap.get(split));
        //System.out.println(slots);
        //System.out.println(slotByLocMap);
        //System.out.println(split);
        return split;
    }

    public void leave(int p) {

        List<int[]> slot = slotByLocMap.get(p);
        slotByLocMap.remove(p);
        slots.removeAll(slot);
        int[] newSlot = new int[]{slot.get(0)[0], slot.get(1)[1]};
        slots.add(newSlot);
        slotByLocMap.get(slot.get(0)[0]).set(1, newSlot);
        slotByLocMap.get(slot.get(1)[1]).set(0, newSlot);
        //System.out.println("after seat");
        //System.out.println(slots);
        //System.out.println(slotByLocMap);
    }

}


