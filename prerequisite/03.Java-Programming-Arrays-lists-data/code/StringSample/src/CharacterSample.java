/**
 * Source code for the first week's lecture, using Character class
 *
 * @author kate Zhang
 * @version 10/12/2020
 **/
public class CharacterSample {

    public static void main(String[] args) {
        char letter = "hello".charAt(0);
        String test = "ABCabc0123456789';#!";
        int testStrLen = test.length();
        int atIndex = 0;
        while (atIndex < testStrLen) {
            char ch = test.charAt(atIndex);
            if (Character.isDigit(ch)) {
                System.out.println(ch + "is a digit.");
            }
            if (Character.isAlphabetic(ch)) {
                System.out.println(ch + "is alphabetic.");
            }
            if (ch == '#') {
                System.out.println(ch + "#hashtag");
            }
            atIndex += 1;
        }
    }
}
