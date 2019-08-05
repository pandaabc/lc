package lc;

import java.util.Arrays;
import java.util.Comparator;

public class Q853CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {

        if (position == null || position.length == 0) {
            return 0;
        }

        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i ++) {
            cars[i] = new Car(position[i], speed[i], (target - position[i]) * 1. / speed[i]);
        }

        Arrays.sort(cars, Comparator.comparingInt(Car::getPos).reversed());
        for (int i = 0; i < position.length; i ++) {
            cars[i].rank = i;
        }

        Arrays.sort(cars, Comparator.comparingDouble(Car::getTime).reversed());

        int r = position.length + 2;
        int cnt = 0;
        for (int i = 0; i < cars.length; i ++) {
            int temp_r = cars[i].rank;
            if (temp_r < r) {
                r = temp_r;
                cnt ++;
            }
        }
        return cnt;
    }


}

class Car{
    int pos;
    int spd;
    double time;
    int rank;
    Car(int pos, int spd, double time) {
        this.pos = pos;
        this.spd = spd;
        this.time = time;
    }
    public int getPos() {
        return pos;
    }
    public double getTime() {
        return time;
    }
}