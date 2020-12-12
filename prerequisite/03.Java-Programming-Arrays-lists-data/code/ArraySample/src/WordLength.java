import edu.duke.*;

/**
 * Source code for the first week homework, WordLength class
 *
 * @author Kate Zhang
 * @version 11/12/2020
 */

public class WordLength {
    public static void main(String[] args) {
        WordLength example = new WordLength();
        FileResource fr = new FileResource();
        int[] counts = new int[31];

        example.countWordLengths(fr, counts);
        example.indexOfMax(counts);
    }

    /**
     * Given a file resource, group word's length
     *
     * @return {int[]}
     * @param{FileResource}
     * @param{int[}}
     */
    public int[] countWordLengths(FileResource fr, int[] counts) {
        int countsLen = counts.length;
        int lastIndex = countsLen - 1;
        for (String curWord : fr.words()) {
            int wordLen = this.wordLength(curWord);
            if (wordLen < lastIndex) {
                counts[wordLen] += 1;
            } else {
                counts[lastIndex] += 1;
            }
        }
        for (int i = 0, len = countsLen; i < countsLen; i++) {
            System.out.println(i + ",=" + counts[i] + "");
        }
        return counts;
    }

    /**
     * Given an integer array, return the index where the max elements locates
     *
     * @param {int[]}
     * @return {int}
     */
    public int indexOfMax(int[] values) {
        int indexOfMax = 0;
        int valuesLen = values.length;
        int curIndex = 0;
        int maxValue = values[curIndex];
        while (curIndex < valuesLen) {
            if (maxValue < values[curIndex]) {
                indexOfMax = curIndex;
                maxValue = values[curIndex];
            }
            curIndex += 1;
        }
        System.out.println("The index of max value=" + indexOfMax);
        return indexOfMax;
    }

    /**
     * Count the letter length of a word
     * If a word has a non-letter as the first or last character, it should not be counted as part of the word length.
     * For example, the word And, would be considered of length 3 (the comma is not counted), the word “blue-jeans”
     * would be considered of length 10 (the double quotes are not counted, but the hyphen is). Note that we will
     * miscount some words, such as “Hello,” which will be counted as 6 since we don’t count the double quotes but
     * will count the comma, but that is OK as there should not be many words in that category.
     *
     * @param {String} word the word to count length
     * @return {int} word length
     */
    private int wordLength(String word) {
        int wordLen = word.length();
        char firstLetter = word.charAt(0);
        char lastLetter = word.charAt(wordLen - 1);
        if (Character.isLetter(lastLetter) == false) {
            wordLen -= 1;
        }
        if (Character.isLetter(firstLetter) == false) {
            wordLen -= 1;
        }
        return wordLen;
    }
}
