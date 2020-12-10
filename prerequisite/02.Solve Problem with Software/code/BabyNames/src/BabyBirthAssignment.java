import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Source code for BabyNames project
 *
 * @author Kate Zhang
 * @version 09/12/2020
 */

public class BabyBirthAssignment {

    public static void main(String[] args) {
        BabyBirthAssignment example = new BabyBirthAssignment();


//        example.yearOfHighestRank("Alice", Gender.M);
//        example.getAverageRank("Mason", Gender.M);
        example.getTotalRankedHigher(2012, "Mason", Gender.M);
//
//        System.out.println("totalBirth:");
////        example.totalBirths();
//        System.out.println("\n");
//
//        example.getRank(2012, "Mason", Gender.M);
        System.out.println("\n");
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
                if (curRecord.get(1).equals(Gender.M.name())) totalBoys += births;
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
    public int getRank(int year, String name, Gender gender) {
        String fileName = this.getFileNameByYear(year);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int curRowIndex = 0;
        boolean nameIsExist = false;
        for (CSVRecord curRecord : parser) {
            String curName = curRecord.get(0);
            String curGender = curRecord.get(1);
            if (curGender.equals(gender.name())) {
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
                    + ", gender=" + gender
                    + ", name =" + name
                    + ". Did not find this person.");
            return -1;
        }
    }

    /**
     * @{int} year
     * @{String} name
     * @{String} gender gender F for female, M for male
     */
    public String getName(int year, int rank, Gender gender) {
        String fileName = this.getFileNameByYear(year);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);

        String name = "NO NAME";
        int curRank = 0;
        for (CSVRecord curRecord : parser) {
            String curGender = curRecord.get(1);
            String curRecordsName = curRecord.get(0);
            if (curGender.equals(gender.name())) {
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
    public String whatIsNameInYear(String name, int year, Gender gender, int newYear) {
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
    public int yearOfHighestRank(String name, Gender gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRankYear = -1;
        int highestRank = -1;
        for (File curF : dr.selectedFiles()) {
            String fileName = curF.getName().replace("yob", "");
            try {
                int year = Integer.parseInt(fileName.replace(".csv", ""));
                int yearRankForName = this.getRank(year, name, gender);
                if (yearRankForName > highestRank) {
                    highestRank = yearRankForName;
                    highestRankYear = year;
                }
                System.out.println(year);
            } catch (Exception e) {
                System.out.println("Exception happens for file: " + fileName);
            }

        }

        System.out.println("The highest rank for name=" + name + ",gender=" + gender + "is year=" + highestRankYear);

        return highestRankYear;
    }

    /**
     * Get the average rank for a given user's name and gender
     *
     * @param{string} name, the user's name
     * @param{gender} gender, the user's gender
     */
    public double getAverageRank(String name, Gender gender) {
        double totalRank = 0;
        int totalCount = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName().replace("yob", "").replace(".csv", "");
            try {
                int year = Integer.parseInt(fileName);
                int rankAtThisYear = this.getRank(year, name, gender);
                if (rankAtThisYear != -1) {
                    totalRank += rankAtThisYear;
                    totalCount += 1;
                }
            } catch (Exception e) {
                System.out.println(fileName);
            }
        }

        double avgRank = totalRank / totalCount;

        System.out.println("Average rank for name=" + name + ",gender=" + gender + " is " + avgRank);

        return avgRank;
    }

    /**
     * For a given gender and and name, return the total births that the name has a higher rank
     *
     * @param{int} year
     * @param{String} name
     * @param{Gender} gender
     */
    public int getTotalRankedHigher(int year, String name, Gender gender) {
        int count = 0;
        String fileName = this.getFileNameByYear(year);
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        String genderName = gender.name();
        for (CSVRecord curRecord : parser) {
            String curRecordGender = curRecord.get(1);
            int curRecordBirths = Integer.parseInt(curRecord.get(2));
            if (curRecordGender.equals(genderName)) {
                count += curRecordBirths;
            }
        }
        System.out.println("For name=" + name + ", gender=" + genderName + ", the count is " + count);
        return count;
    }

    private String getFileNameByYear(int year) {
        return "data/yob" + year + ".csv";
    }
}
