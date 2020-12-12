/**
 * Source code for the first week homework, breaking the caesar cipher part
 *
 * @author kate Zhang
 * @version 12/12/2020
 */

public class BreakCaesarCipher {
    String alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String hello = "Hello, world";
        BreakCaesarCipher example = new BreakCaesarCipher();

        example.encrypt(hello, 12);
        example.encryptTwoKeys(hello, 12, 12);
    }

    public String encrypt(String input, int key) {
        int inputLen = input.length();
        int curIndex = 0;
        int validKey = this.getValidKey(key);
        StringBuilder resSb = new StringBuilder(input);

        while (curIndex < inputLen) {
            char letter = input.charAt(curIndex);
            boolean isLowerCase = Character.isLowerCase(letter);
            int atIndex = isLowerCase ? alphabetLowerCase.indexOf(letter) : alphabetUpperCase.indexOf(letter);
            if (atIndex != -1) {
                int newIndex = (atIndex + validKey) % 26;
                char newLetter = isLowerCase ? alphabetLowerCase.charAt(newIndex) : alphabetUpperCase.charAt(newIndex);
                resSb.setCharAt(curIndex, newLetter);
            }
            curIndex += 1;
        }

        System.out.println("Before encryption, source=" + input);
        System.out.println("After encryption, result=" + resSb.toString());

        return resSb.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder resSb = new StringBuilder();
        int validKey1 = this.getValidKey(key1);
        int validKey2 = this.getValidKey(key2);
        int inputLen = input.length();
        int curIndex = 0;

        StringBuilder evenSb = new StringBuilder();
        StringBuilder oddSb = new StringBuilder();

        while (curIndex < inputLen) {
            char curLetter = input.charAt(curIndex);
            if (curIndex % 2 == 0) {
                evenSb.append(curLetter);
            } else {
                oddSb.append(curLetter);
            }
            curIndex += 1;
        }

        String evenIndexStr = this.encrypt(evenSb.toString(), validKey1);
        String oddIndexStr = this.encrypt(oddSb.toString(), validKey2);

        curIndex = 0;
        while (curIndex < inputLen) {
            int atIndex = curIndex / 2;
            if (curIndex % 2 == 0) {
                resSb.append(evenIndexStr.charAt(atIndex));
            } else {
                resSb.append(oddIndexStr.charAt(atIndex));
            }
            curIndex += 1;
        }

        System.out.println("\nBefore encryption with two keys, source=" + input);
        System.out.println("After encryption with two keys, result=" + resSb.toString());

        return resSb.toString();
    }

    private int getValidKey(int key) {
        return (Math.abs(key)) % 26;
    }

}