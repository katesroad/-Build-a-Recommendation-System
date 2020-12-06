/**
 * The start codeon is atg, the stop code on can be tga, tag and taa
 * The valid dna has a distance of multiple 3 between the start codeon and the end codon.
 *
 * @author Kate Zhang
 */

public class Part5 {
    /**
     * Given a string, to find the index of a stop codon
     *
     * @param {String} dna The dna string
     * @param {int}    curIndex the index of start codon in a DNA string
     * @param {String} stopCodon the stop codon symbol
     * @return {int} the index of a stop codon in given string
     */
    public int findStopCodon(String dna, int curIndex, String stopCodon) {
        return dna.indexOf(stopCodon, curIndex + 3);
    }

    /**
     * Given the index of start codon and stop codon, to test if a
     * string is a valid dna
     *
     * @param {int} startIndex The index of start codon
     * @param {int} stopIndex The index of stop codon
     * @return {Boolean}
     **/
    public boolean isValidGene(int startIndex, int stopIndex) {
        if (stopIndex == -1) return false;
        return (stopIndex - startIndex) % 3 == 0;
    }

    /**
     * Given a dna string, find and print gene if there are any
     *
     * @param {string} dna the dna string
     */
    public void findGene(String dna) {
        final String startCodon = "atg";
        int curIndex = dna.indexOf(startCodon);
        if (curIndex == -1) {
            System.out.println("Did not find gene in DNA " + dna);
        }

        final String taaCodon = "taa";
        final String tagCodon = "tag";
        final String tgaCodon = "tga";
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
                System.out.println("Found gene:");
                System.out.println(dna.substring(curIndex, stopIndex + 3));
            } else {
                System.out.println("Did not find gene in DNA " + dna + " this round.");
            }
            curIndex = dna.indexOf(startCodon, stopIndex + 3);
        }
    }

    public static void main(String[] args) {
        Part5 part5 = new Part5();
        String DNA = "AATGCTAACTAGCTGACTAAT";
        part5.findGene(DNA.toLowerCase());
    }
}