import java.util.Locale;

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
     * @param {String} The string
     * @param {int}    the index of start codon
     * @param {String} the stop codon
     * @return {int} the index of a stop codon in given string
     */
    public int findStopCodon(String str, int startIndex, String codon) {
        return str.indexOf(codon, startIndex + 3);
    }

    /**
     * Given two stop codon index, return the min index
     *
     * @return {int}
     */
    public int getMinIndex(int firstIndex, int nextIndex) {
        int minIndex = Math.min(firstIndex, nextIndex);
        if (minIndex == -1) return Math.max(firstIndex, nextIndex);
        return minIndex;
    }

    /**
     * Given the index of start codon and stop codon, to test if a
     * string is a valid dna
     *
     * @param {int} startIndex The index of start codon
     * @param {int} stopIndex The index of stop codon
     * @return {Boolean}
     **/
    public boolean isValidDna(int startIndex, int stopIndex) {
        if (stopIndex == -1) return false;
        return (stopIndex - startIndex) % 3 == 0;
    }

    public void findGene(String str) {
        final String startCodon = "atg";
        int startIndex = str.indexOf(startCodon);
        if (startIndex == -1) {
            System.out.println("Did not find DNA in string " + str);
        }

        final String taaCodon = "taa";
        final String tagCodon = "tag";
        final String tgaCodon = "tga";

        while (startIndex != -1) {
            int taaStopIndex = this.findStopCodon(str, startIndex, taaCodon);
            int tagStopIndex = this.findStopCodon(str, startIndex, tagCodon);
            int tgaStopIndex = this.findStopCodon(str, startIndex, tgaCodon);
            int stopIndex = this.getMinIndex(taaStopIndex, tagStopIndex);
            stopIndex = this.getMinIndex(stopIndex, tgaStopIndex);

            if (this.isValidDna(startIndex, stopIndex)) {
                System.out.println("Found DNA:");
                System.out.println(str.substring(startIndex, stopIndex + 3));
                startIndex = str.indexOf(startCodon, stopIndex + 3);
            } else {
                System.out.println("Did not find DNA in string" + str + "this round.");
                startIndex = str.indexOf(startCodon, startIndex + 3);
            }
        }
    }

    public static void main(String[] args) {
        Part5 part5 = new Part5();
        String DNA = "AAATGCCCTAACTAGATTAAGAAACC";
        part5.findGene(DNA.toLowerCase());
    }
}