import java.awt.*;

/**
 * Code for week2 home work, the part 2
 * @author Kate Zhang
 * */
public class Part2 {

    /**
     * Returns how many times stringA appears in stringB
     */
    public int howMany(String stringA, String stringB) {

        int foundTimes = 0;
        int startIndex = stringB.indexOf(stringA);

        if (startIndex == -1) {
            return foundTimes;
        } else {
            foundTimes = 1;
        }
        int stringALen = stringA.length();
        int lastIndex = stringB.length() - 1;
        while (true) {
            int index = stringB.indexOf(stringA, startIndex + stringALen);
            if (index == -1) {
                break;
            } else {
                foundTimes += 1;
                startIndex = index;
            }
            if (startIndex + stringALen > lastIndex) {
                break;
            }
        }
        return foundTimes;
    }

    public void testHowMany() {
        String string1 = "ABCD";
        String string2 = "ABCDABCDABC";
        int times = this.howMany(string1, string2);
        System.out.println(times);

        String string11 = "ab";
        String string21 = "avbacbcabacbab";
        System.out.print(this.howMany(string11,string21));
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
