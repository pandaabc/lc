package algo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */


/**
 * remember to call clean() to clean...
 */
public class Q489RobotRoomCleaner_DFS {

    public void cleanRoom(Robot robot) {
        Set<String> tried = new HashSet<>();
        tried.add("0_0");
        robot.clean();
        action(robot, tried, 0, 0, 0);
        //System.out.println(tried);
    }
    // direction 0 up, 1 left, 2 down, 3 right
    public void action(Robot robot, Set<String> tried, int i, int j, int dir) {

        for (int k = 0; k < 4; k ++) {
            int locDir = (dir + k) % 4;
            String nextPos = getI(i, locDir) +"_"+ getJ(j, locDir);
            if (!tried.contains(nextPos) && robot.move()) {
                robot.clean();
                tried.add(nextPos);
                action(robot, tried, getI(i, locDir), getJ(j, locDir), locDir);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }

    }

    private int getI (int i, int dir) {
        if (dir == 0) {
            return i - 1;
        }
        if (dir == 2) {
            return i + 1;
        }
        return i;
    }

    private int getJ (int j, int dir) {
        if (dir == 1) {
            return j - 1;
        }
        if (dir == 3) {
            return j + 1;
        }
        return j;
    }


}

interface Robot{
    public boolean move();
    public void turnLeft();
    public void turnRight();
    public void clean();
}
