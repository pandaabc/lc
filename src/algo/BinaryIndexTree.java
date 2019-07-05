package algo;

public class BinaryIndexTree {

    int[] binaryIndexTree;
    int[] numbers;

    public void updateBIT(int index, int val) {
        int diff = val - numbers[index];
        // update the number array
        numbers[index] = val;
        // update the binaryIndexTree
        index ++;
        while (index < binaryIndexTree.length) {
            binaryIndexTree[index] += diff;
            index = index + index & (-index);
        }
    }

    public int getSum(int index) {
        int sum = 0;
        index ++;
        while (index > 0) {
            sum += binaryIndexTree[index];
            index = index - index & (-index);
        }
        return sum;
    }

    public void initializeBIT(int[] numbers) {
        this.numbers = numbers;
        this.binaryIndexTree = new int[numbers.length + 1];
        for (int i = 0; i < numbers.length; i ++) {
            updateBIT(i, numbers[i]);
        }
    }

}
