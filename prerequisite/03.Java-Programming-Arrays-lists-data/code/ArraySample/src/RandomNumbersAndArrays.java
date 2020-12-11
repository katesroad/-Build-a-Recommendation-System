import java.util.Random;

public class RandomNumbersAndArrays {
    public static void main(String[] args) {
        RandomNumbersAndArrays example = new RandomNumbersAndArrays();
        example.simulate(20000);
    }

    public int[] simulate(int rolls) {
        int[] result = new int[13];
        Random random = new Random();
        double rollsDouble = new Double(rolls);

        double[] result2 = new double[13];

        int curRoll = 1;
        while (curRoll < rolls) {
            int d1 = random.nextInt(6);
            int d2 = random.nextInt(6);
            result[d1 + d2] += 1;
            result2[d1 + d2] = result[d1+d2] / (double)rolls;
            curRoll += 1;
        }

        int curIndex = 0;
        while (curIndex < 13) {
            System.out.println(curIndex+ "=" + result2[curIndex] * 100);
            System.out.println(result[curIndex]);
            curIndex +=1;
        }
        return result;
    }
}
