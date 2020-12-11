/**
 * Source code for the first week homework, WorldPlay part
 *
 * @author Kate Zhang
 * @version 10/12/2020
 */

public class WorldPlay {

    public static void main(String[] args) {
        WorldPlay example = new WorldPlay();

        System.out.println(example.isVowel('o'));

        System.out.println(example.replaceVowels("Hello, world", '*'));

        System.out.println(example.emphasize("Mary Bella Abracadabra", 'a'));
    }


    //    To test a given char is a vowel or not
    public boolean isVowel(char ch) {
        String lowerCaseCh = new String(ch + "").toLowerCase();
        String vowelLetters = "aeiou";
        return vowelLetters.indexOf(lowerCaseCh) != -1;
    }

    //    Replace the vowel letter in a given string as the ch
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sbPhrase = new StringBuilder(phrase);
        int phraseLen = phrase.length();
        int atIndex = 0;
        while (atIndex < phraseLen) {
            char curChar = phrase.charAt(atIndex);
            if (this.isVowel(curChar)) {
                sbPhrase.setCharAt(atIndex, ch);
            }
            atIndex += 1;
        }
        return sbPhrase.toString();
    }

    //    Replace the ch in the phrase with * for ch at even index
//    and + for odd index
    public String emphasize(String phrase, char ch) {
        StringBuilder sbPhrase = new StringBuilder(phrase);
        int atIndex = 0;
        int phraseLen = phrase.length();
        while (atIndex < phraseLen) {
            if (sbPhrase.charAt(atIndex) == ch) {
                if (this.isEvenIndex(atIndex)) {
                    sbPhrase.setCharAt(atIndex, '*');
                } else {
                    sbPhrase.setCharAt(atIndex, '+');
                }
            }
            atIndex += 1;
        }
        return sbPhrase.toString();
    }

    private boolean isEvenIndex(int index) {
        return index % 2 == 0;
    }
}
