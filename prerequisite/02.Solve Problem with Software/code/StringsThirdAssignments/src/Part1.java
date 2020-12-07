/**
 * Source code for the homework of week2.
 * Please refer: https://www.coursera.org/learn/java-programming/supplement/ct8gA/programming-exercise-storing-all-genes
 *
 * @author Kate Zhang
 * @version 06/12/2020
 */

import edu.duke.*;

import java.util.Locale;

public class Part1 {

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

    public static void main(String args[]) {
        Part1 part1 = new Part1();
        final String dna = "AATGCTAACTAGCTGACTAAT";
        StorageResource sr = part1.getAllGenes(dna.toLowerCase());
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
    }
}
