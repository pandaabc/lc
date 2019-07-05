package lc;

public class Q165CompareVersionNumber {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        for (int i = 0; i < Math.min(v1.length, v2.length); i ++) {
            if (v1[i].equals(v2[i])) {
                continue;
            }
            return Integer.compare(Integer.valueOf(v1[i]), Integer.valueOf(v2[i]));
        }
        if (v1.length == v2.length) {
            return 0;
        }
        for (int i = Math.min(v1.length, v2.length); i < Math.max(v1.length, v2.length); i ++) {
            if (i < v1.length && Integer.valueOf(v1[i]) != 0) {
                return 1;
            }
            if (i < v2.length && Integer.valueOf(v2[i]) != 0) {
                return -1;
            }
        }
        return 0;
    }

}
