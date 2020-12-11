/**
 * The source code for the first week homework CaesarCipher.
 */

public class CaesarCipher {
    protected final static String alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected final static String alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        CaesarCipher example = new CaesarCipher();
        String content = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        example.encrypt(content, 15);
        example.encryptTwoKeys(content, 8, 21);
    }

    /**
     * Given a source message, encrypt it
     *
     * @param{String} source the source message
     * @param{int} key letter shift amount
     * @return{String}
     */
    public void encrypt(String source, int key) {
        String upperCaseLetters = this.getShiftedAlphabet(key, false);
        String lowerCaseLetters = this.getShiftedAlphabet(key, true);
        StringBuilder result = new StringBuilder(source);

        int curIndex = 0;
        int sourceLen = source.length();
        while (curIndex < sourceLen) {
            char letter = source.charAt(curIndex);
            char newLetter = letter;
            boolean isLowerCase = Character.isLowerCase(letter);
            int newIndex = isLowerCase ? alphabetLowerCase.indexOf(letter) : alphabetUpperCase.indexOf(letter);
            String targetString = isLowerCase ? lowerCaseLetters : upperCaseLetters;
            if (newIndex != -1) {
                newLetter = targetString.charAt(newIndex);
            }
            result.setCharAt(curIndex, newLetter);
            curIndex += 1;
        }

        System.out.println("For source=\n" + source
                + "\nafter encryption, the result = \n"
                + result.toString() + "\n");
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder outputSb = new StringBuilder(input);
        String key1UpperLetters = this.getShiftedAlphabet(key1, false);
        String key1LowerLetters = this.getShiftedAlphabet(key1, true);
        String key2UpperLetters = this.getShiftedAlphabet(key2, false);
        String key2LowerLetters = this.getShiftedAlphabet(key2, true);

        int curIndex = 0;
        int inputLen = input.length();

        while (curIndex < inputLen) {
            char curLetter = outputSb.charAt(curIndex);
            char newLetter = curLetter;
            boolean isLowerCase = Character.isLowerCase(curLetter);
            boolean isEvenIndex = curIndex % 2 == 0;
            int newIndex = isLowerCase ? alphabetLowerCase.indexOf(curLetter) : alphabetUpperCase.indexOf(curLetter);
            if (newIndex != -1) {
                if (isEvenIndex) {
                    newLetter = isLowerCase ? key1LowerLetters.charAt(newIndex) : key1UpperLetters.charAt(newIndex);
                } else {
                    newLetter = isLowerCase ? key2LowerLetters.charAt(newIndex) : key2UpperLetters.charAt(newIndex);
                }
            }
            outputSb.setCharAt(curIndex, newLetter);
            curIndex += 1;
        }


        System.out.println("For source =" + input
                + "\n, after two keys, the output is \n"
                + outputSb.toString());

        return outputSb.toString();
    }


    /**
     * Given a key, get the shifted alphabet
     *
     * @param{int} key
     * @return{StringBuildder}
     */
    public String getShiftedAlphabet(int key, boolean useLowerCase) {
        int len = alphabetLowerCase.length();
        int validKey = (key + len) % len;
        if (useLowerCase) {
            return alphabetLowerCase.substring(validKey) + alphabetLowerCase.substring(0, validKey);
        }
        return alphabetUpperCase.substring(validKey) + alphabetUpperCase.substring(0, validKey);
    }

}
