/**
 * Source code for homework https://www.coursera.org/learn/java-programming/supplement/T8W0j/programming-exercise-finding-a-gene-and-web-links
 * @author Kate Zhang
 * */

public class Part1 {
    public String findSimpleGene(String source) {
        int startIndex = source.indexOf("atg");
        System.out.println("start index = " + startIndex);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = source.indexOf("taa", startIndex + 3);
        System.out.println("stop index = " + stopIndex);
        boolean isValidBand = (stopIndex + 3 - startIndex) % 3 == 0;
        if(isValidBand) {
            return source.substring(startIndex, stopIndex);
        }
        return "";
    }

    public static void main(String[] args) {
        Part1 p1 = new Part1();
        String band = p1.findSimpleGene("cccatggggtttaaataataataggagagagagagagagttt");
        if (band.length() > 0) {
            System.out.println("band = " + band);
        } else {
            System.out.println("No DNA found.");
        }

        String band2 = p1.findSimpleGene("atggggtttaaataataatag");
        if (band2.length() > 0) {
            System.out.println("band2 = " + band2);
        } else {
            System.out.println("No DNA found.");
        }
    }
}
