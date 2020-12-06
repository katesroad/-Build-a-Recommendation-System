/**
 * Homework Week2, the debug code for part1, part2 and part 3
 * More information please refer: https://www.coursera.org/learn/java-programming/quiz/qFeyj/debugging-part-2
 *
 * @author Kate Zhang
 * @version 06/12/2020
 */
public class Debugging {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        int inputsLen = input.length();
        int round = 0;
        while (true) {
            if (index == -1 || inputsLen - 4 < index) {
                break;
            }
            round += 1;
            System.out.println("Round " + round);
            System.out.println("Before updating, the index=" + index);
            String found = input.substring(index + 1, index + 4);
            System.out.println(found);

//            the version after revising
            index = input.indexOf("abc", index + 3);
            System.out.println("After updating, the index=" + index);
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Debugging debug = new Debugging();
        debug.findAbc("abcabcabcabca");
    }
}
