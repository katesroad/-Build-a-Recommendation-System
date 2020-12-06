import java.awt.*;

/**
 * Part 3: Problem Solving with Strings
 * @author Kate Zhang
 */

public class Part3 {
    /**
     * Test if stringA appears two times in stringB
     * @return {boolean}
     */
    public boolean twoOccurrences(String stringA, String stringB) {
        int times = this.howMany(stringA, stringB);
        return times >= 2;
    }

    /**
     * How many times stringA appears in stringB
     * @return {int}
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

    public void testing() {
        String string1 = "aaaa";
        String string2 = "bbbb";
        System.out.println(this.twoOccurrences(string1, string2));

        String string12 = "abaababababa";
        String string11 = "ab";
        System.out.println(this.twoOccurrences(string11, string12));

        String stringA = "an";
        String stringB = "banana";
        String result1 =  this.lastPart(stringA, stringB);
        System.out.println(result1);

        String stringA1 = "zoo";
        String stringA2 = "forest";
        String result2 = this.lastPart(stringA1, stringA2);
        System.out.println(result2);
    }

    public String lastPart(String stringA, String stringB) {
        int startIndex = stringB.indexOf(stringA);
        if(startIndex ==-1) {
            return stringB;
        }
        int stringALen = stringA.length();
        return stringB.substring(startIndex + stringALen);
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }

}
