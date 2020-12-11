/**
 * The source code for the first week homework CaesarCipher.
 * */

public class CaesarCipher {
    protected final static String alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected final static String alphabetLowerCase = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        CaesarCipher example = new CaesarCipher();
        String content = "Hello, world";
        example.encrypt(content, 97);
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


    /**
     * Given a key, get the shifted alphabet
     *
     * @param{int} key
     * @return{StringBuildder}
     */
    public String getShiftedAlphabet(int key, boolean useLowerCase) {
        int len = alphabetLowerCase.length();
        int validKey = key % len;
        if (useLowerCase) {
            return alphabetLowerCase.substring(validKey) + alphabetLowerCase.substring(0, validKey);
        }
        return alphabetUpperCase.substring(validKey) + alphabetUpperCase.substring(0, validKey);
    }
}
