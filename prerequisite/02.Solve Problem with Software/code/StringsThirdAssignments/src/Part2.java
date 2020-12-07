/**
 * Source code for the homework of week2.
 * Please refer: https://www.coursera.org/learn/java-programming/supplement/ct8gA/programming-exercise-storing-all-genes
 *
 * @author Kate Zhang
 * @version 06/12/2020
 */

import edu.duke.*;

import java.util.Locale;

public class Part2 {

    /**
     * Given the dna string, current start codon position and the symbol of
     * stop codon, return the index of the stop codon.
     *
     * @param {string} dna the dna string
     * @param {int}    the index of the start codon in given dna string
     * @param {string} stopCodon the symbol of the stopCodon
     * @return {int}
     */
    private int findStopCodon(String dna, int curIndex, String stopCodon) {
        try {
            return dna.indexOf(stopCodon, curIndex + 3);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Given the index of the start codon and the index of stop codon
     * to test if a substring of a dna is a valid gene
     *
     * @param {int} startCodonIndex
     * @param {int} stopCodonIndex
     */
    private boolean isValidGene(int startCodonIndex, int stopCodonIndex) {
        if (stopCodonIndex == -1) return false;
        return (stopCodonIndex - startCodonIndex) % 3 == 0;
    }


    /**
     * Return all the gene contained in the given dna string
     *
     * @param {string} dna the given dna string
     * @return {StorageResource}
     */
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();

        final String startCodon = "atg";
        final String taaCodon = "taa";
        final String tagCodon = "tag";
        final String tgaCodon = "tga";
        int curIndex = dna.indexOf(startCodon);
        final int dnaLen = dna.length();

        while (curIndex != -1) {
            int stopIndex = dnaLen;

            int taaStopIndex = this.findStopCodon(dna, curIndex, taaCodon);
            int tagStopIndex = this.findStopCodon(dna, curIndex, tagCodon);
            int tgaStopIndex = this.findStopCodon(dna, curIndex, tgaCodon);

            if (this.isValidGene(curIndex, taaStopIndex)) {
                stopIndex = Math.min(stopIndex, taaStopIndex);
            }
            if (this.isValidGene(curIndex, tagStopIndex)) {
                stopIndex = Math.min(stopIndex, tagStopIndex);
            }
            if (this.isValidGene(curIndex, tgaStopIndex)) {
                stopIndex = Math.min(stopIndex, tgaStopIndex);
            }

            if (stopIndex != dnaLen) {
                final String gene = dna.substring(curIndex, stopIndex);
                sr.add(gene);
            }
            try {
                curIndex = dna.indexOf(startCodon, stopIndex + 3);
            } catch (Exception e) {
                break;
            }
        }
        return sr;
    }

    /**
     * Given a DNA string, get its cgRatio
     *
     * @param {string} dna the DNA string
     * @return {float}
     */
    public double cgRatio(String dna) {
        final float dnaLen = dna.length();
        final String dnaInUppercase = dna.toUpperCase();
        int count = 0;
        for (int i = 0; i < dnaLen; i++) {
            char letter = dnaInUppercase.charAt(i);
            if (letter == 'G' || letter == 'C') {
                count += 1;
            }
        }
        return count / dnaLen;
    }

    /**
     * Count the how many times the CTG codon appears in a
     * given DNA string
     *
     * @param {string} dna The DNA String
     * @return {int}
     */
    public int countCTG(String dna) {
        final String dnaInUppercase = dna.toUpperCase();
        final String ctgCodon = "CTG";
        int count = 0;
        int curIndex = dnaInUppercase.indexOf(ctgCodon);
        while (curIndex != -1) {
            count += 1;
            try {
                curIndex = dnaInUppercase.indexOf(ctgCodon, curIndex + 3);
            } catch (Exception e) {
                break;
            }
        }
        return count;
    }

    public static void main(String args[]) {
        Part2 part2 = new Part2();
        final String dna = "AATGCTAACTAGCTGACTAAT";
        StorageResource sr = part2.getAllGenes(dna.toLowerCase());
        int count = 0;
        for (String s : sr.data()) {
            System.out.println(s);
            count += 1;
        }
        if (count > 0) {
            System.out.println("Found " + count + " gene");
        } else {
            System.out.println("Did not find genes.");
        }

        System.out.println("CTG appears " + part2.countCTG(dna) + " time/s.");
        System.out.println("cgRatio:" + part2.cgRatio(dna));
    }
}
