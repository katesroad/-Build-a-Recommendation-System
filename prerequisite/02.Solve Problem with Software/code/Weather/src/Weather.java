import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class Weather {

    public static void main(String[] args) {
        DirectoryResource dr = new DirectoryResource();

        Weather w = new Weather();
        w.fileWithColdestTemperature(dr);
    }

    /**
     * Given a directory with many files, get the lowest temperature in these files
     *
     * @param {DirectoryResource}
     */
    public void fileWithColdestTemperature(DirectoryResource dr) {
        CSVRecord coldestRecord = null;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curRecord = this.coldestHourInFile(parser);
            coldestRecord = this.getLowestOfTwoRecords(coldestRecord, curRecord);
        }
        System.out.println(coldestRecord.toString());
    }


    /**
     * Given a csv file parser, get the row with lowest temperature
     *
     * @param {CSVParser}
     * @return {CSVRecord}
     */
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord lowestRecord = null;
        for (CSVRecord curRecord : parser) {
            lowestRecord = this.getLowestOfTwoRecords(curRecord, lowestRecord);
        }
        return lowestRecord;
    }

    /**
     * Given two records, get the one with lowest temperature
     *
     * @param {CSVRecord} curRecord current record to compare with the lowestRecord
     * @param {CSVRecord} lowestRecord the lowest record so far
     * @return {CSVRecord}
     */
    CSVRecord getLowestOfTwoRecords(CSVRecord curRecord, CSVRecord lowestRecord) {
        if (curRecord == null) {
            return lowestRecord;
        }
        if (lowestRecord == null) {
            lowestRecord = curRecord;
        } else {
            double lowestTemp = this.getRecordTemperature(lowestRecord);
            double curTemp = this.getRecordTemperature(curRecord);
            if (curTemp < lowestTemp) lowestRecord = curRecord;
        }
        return lowestRecord;
    }

    /**
     * Get the average temperature of a CSV file parser
     *
     * @param {CSVParser} The CSV file parser
     * @return {double} the average temperature for that file
     */
    double averageTemperatureInFile(CSVParser parser) {
        double totalTemp = 0;
        int totalRows = 0;
        for (CSVRecord curRecord : parser) {
            totalRows += 1;
            totalTemp = this.getRecordTemperature(curRecord);
        }
        return totalTemp / totalRows;
    }

    private double getRecordTemperature(CSVRecord record) {
        return Double.parseDouble(record.get("TemperatureF"));
    }
}
