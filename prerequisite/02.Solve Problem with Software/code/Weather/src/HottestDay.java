/**
 * Source code for week3 homework, parsing weather data
 *
 * @author Kate Zhang
 * @version 08/12/2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

class HottestHour {
    public static void main(String[] args) {
        HottestHour example = new HottestHour();
        example.hottestHourInManyDays();
    }

    /**
     * Get the record which contains the hottest temperature
     *
     * @param {CSVParser} parser the CSV parser of a csv file
     * @return {CSVRecord} record the record that contains the highest temperature
     */
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord highestRecord = null;
        for (CSVRecord curRow : parser) {
            highestRecord = this.getLargestOfTwo(curRow, highestRecord);
        }
        return highestRecord;
    }

    /**
     * Find the hottest record in many days
     */
    public void hottestHourInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord hottestSoFar = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curRow = this.hottestHourInFile(parser);
            hottestSoFar = this.getLargestOfTwo(curRow, hottestSoFar);
        }

        int lastIndex = hottestSoFar.size() - 1;
        String result = "The hottest temperature was ";
        result += hottestSoFar.get(1);
        result += " at " + hottestSoFar.get(lastIndex);
        System.out.println(result);
    }

    /**
     * Given a record, to get the temperature in double format
     *
     * @param {CSVRecord} record The CSV record to track
     * @return {double}
     */
    private double getRecordTemperature(CSVRecord record) {
        return Double.parseDouble(record.get("TemperatureF"));
    }

    /**
     * Given two row, return the row that has the highest temperature
     *
     * @param {CSVRecord} curRow the current row to compare with largestRow
     * @param {CSVRecord} largestRow the row with highest temperature so far
     * @return {CSVRecord} return this record with higher temperature
     */
    private CSVRecord getLargestOfTwo(CSVRecord curRow, CSVRecord largestRow) {
        if (largestRow == null) {
            largestRow = curRow;
        } else {
            double curTemp = this.getRecordTemperature(curRow);
            double largestTemp = this.getRecordTemperature(largestRow);
            if (curTemp > largestTemp) {
                largestRow = curRow;
            }
        }
        return largestRow;
    }
}
