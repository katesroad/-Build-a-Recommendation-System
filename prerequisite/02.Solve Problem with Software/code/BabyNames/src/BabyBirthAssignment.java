import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;

/**
 * Source code for BabyNames project
 *
 * @author Kate Zhang
 * @version 09/12/2020
 */

public class BabyBirthAssignment {

    public static void main(String[] args) {
        BabyBirthAssignment example = new BabyBirthAssignment();

        example.yearOfHighestRank("Alice", "M");
//
//        System.out.println("totalBirth:");
////        example.totalBirths();
//        System.out.println("\n");
//
//        example.getRank(2012, "Mason", "F");
//        System.out.println("\n");
//
//        example.getName(2012, 2, "M");
//        System.out.println("\n");
//
//        example.whatIsNameInYear("Isabella", 2012, "F", 2014);

    }

    /**
     * Modify the totalBirths to print the number of girls names,
     * the number of boys names and the total names in this file
     */
    public void totalBirths() {
        FileResource fr = new FileResource("data/example-small.csv");
        CSVParser parser = fr.getCSVParser();

        int totalBoys = 0;
        int totalGirls = 0;
        int totalBorn = 0;
        for (CSVRecord curRecord : parser) {
            if (curRecord != null) {
                int births = Integer.parseInt(curRecord.get(2));
                totalBorn += births;
                if (curRecord.get(1).equals("M")) totalBoys += births;
                else totalGirls += births;
            }
        }

        System.out.println("total births = " + totalBorn);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys= " + totalBoys);
    }

    /**
     * Get the rank for a given name and gender for a specified year
     *
     * @{int} year
     * @{String} name
     * @{String} gender
     */
    public int getRank(int year, String name, String gender) {
        String fileName = this.getFileNameByYear(year);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int curRowIndex = 0;
        boolean nameIsExist = false;
        for (CSVRecord curRecord : parser) {
            String curName = curRecord.get(0);
            String curGender = curRecord.get(1);
            if (curGender.equals(gender)) {
                curRowIndex += 1;
                if (curName.equals(name)) {
                    nameIsExist = true;
                    break;
                }
            }
        }

        if (nameIsExist) {
            System.out.println("For year=" + year
                    + ", name=" + name + " and gender="
                    + gender + ", the rank=" + curRowIndex + "\n");
            return curRowIndex + 1;
        } else {
            System.out.println("For year=" + year
                    + "and gender=" + gender
                    + "and name =" + name
                    + ". Did not find this person.");
            return -1;
        }
    }

    /**
     * @{int} year
     * @{String} name
     * @{String} gender gender F for female, M for male
     */
    public String getName(int year, int rank, String gender) {
        String fileName = this.getFileNameByYear(year);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        String name = "NO NAME";
        int curRank = 0;
        for (CSVRecord curRecord : parser) {
            String curGender = curRecord.get(1);
            String curRecordsName = curRecord.get(0);
            if (curGender.equals(gender)) {
                curRank += 1;
                if (curRank == rank) {
                    name = curRecordsName;
                    break;
                }
            }
        }

        System.out.println("year=" + year + ", rank=" + rank + ", gender=" + gender + ", name=" + name);

        return name;
    }

    /**
     * For a given user with born year and gender, get its name in another year
     * with the  same name's rank.
     *
     * @parma{string} name current name
     * @param{int} year string
     * @param{string} gender user's gender
     * @param{int} newYear
     */
    public String whatIsNameInYear(String name, int year, String gender, int newYear) {
        String newName = "No Name";

        int rank = this.getRank(year, name, gender);

        System.out.println(name + " rank at " + rank + " at year " + year);

        if (rank == -1) return newName;
        else newName = this.getName(year, newYear, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");

        return newName;
    }

    /**
     * Selects a range of file, and returns the year which current user
     * has the highest rank in this files
     *
     * @param{name} name the user's name
     * @param{string} gender the user's gender
     */
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int year = -1;
        int rank = -1;
        for (File curF : dr.selectedFiles()) {
            System.out.println(curF.getName());
        }


        return year;
    }

    /**
     * Get the average rank for a given user's name and gender
     *
     * @param{string} name, the user's name
     * @param{gender} gender, the user's gender
     */
    public int getAverageRank(String name, String gender) {
        int rank = 0;
        return rank;
    }

    public int getTotalRankedHigher(int year, String name, String gender) {
        int ranks = 0;
        return ranks;
    }

    private String getFileNameByYear(int year) {
        return "data/yob" + year + ".csv";
    }
}
