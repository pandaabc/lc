package lc;

public class Q292NimGame_GameTheory {

    public boolean canWinNim(int n) {
        // note the simple way is to: return n%4 !=0;
        return canWin(n, true);

    }

    public boolean canWin(int n, boolean isSelf) {
        //System.out.println(n + " " + isSelf);
        if (n <= 0) {
            if (isSelf) {
                return false;
            } else {
                return true;
            }
        }

        for (int i = 1; i <= 3; i ++) {
            int n1 = n - i;
            if (isSelf && canWin(n1, !isSelf)) {
                return true;
            } else if (!isSelf && !canWin(n1, !isSelf)) {
                return false;
            }
        }

        return !isSelf;
    }
}
