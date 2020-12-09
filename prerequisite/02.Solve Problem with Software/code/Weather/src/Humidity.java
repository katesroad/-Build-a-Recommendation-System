import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

class Humidity {
    private CSVParser parser;

    public static void main(String[] args) {

        DirectoryResource dr = new DirectoryResource();

        Humidity hm = new Humidity();
        hm.fileWithLowestHumidity(dr);
    }


    public void testAvgTemWithHighHumidity(int value) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        this.avgTemperatureWithHighHumidityInFile(parser, value);
    }

    /**
     * Given a directory with many files, get the lowest humidity in these files
     *
     * @param {DirectoryResource}
     */
    public void fileWithLowestHumidity(DirectoryResource dr) {
        CSVRecord lowestHumidityRecord = null;
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord curRecord = this.lowestHumidityInFile(parser);
            lowestHumidityRecord = this.getLowestOfTwoRecords(lowestHumidityRecord, curRecord);
        }

        System.out.println(lowestHumidityRecord.get("DateUTC"));
        System.out.println(lowestHumidityRecord.get("Humidity"));
    }


    /**
     * Given a csv file parser, get the row with lowest humidity
     *
     * @param {CSVParser}
     * @return {CSVRecord}
     */
    private CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestRecord = null;
        for (CSVRecord curRecord : parser) {
            lowestRecord = this.getLowestOfTwoRecords(curRecord, lowestRecord);
        }
        return lowestRecord;
    }

    /**
     * Given two records, get the one with lowest humidity
     *
     * @param {CSVRecord} curRecord current record to compare with the lowestRecord
     * @param {CSVRecord} lowestRecord the lowest record so far
     * @return {CSVRecord}
     */
    private CSVRecord getLowestOfTwoRecords(CSVRecord curRecord, CSVRecord lowestRecord) {
        if (curRecord == null) return lowestRecord;

        if (lowestRecord == null) {
            lowestRecord = curRecord;
        } else {
            double lowestTemp = Double.parseDouble(lowestRecord.get("Humidity"));
            double curTemp = Double.parseDouble(curRecord.get("Humidity"));
            if (curTemp < lowestTemp) lowestRecord = curRecord;
        }
        return lowestRecord;
    }

    //    Write a method named averageTemperatureWithHighHumidityInFile
    public double avgTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int recordCount = 0;
        double totalTemp = 0;

        for (CSVRecord curRecord : parser) {
            if (curRecord == null) continue;
            double humidity = Double.parseDouble(curRecord.get("Humidity"));
            if (humidity > value) {
                totalTemp = Double.parseDouble(curRecord.get("TemperatureF"));
                recordCount += 1;
            }
        }

        return totalTemp / recordCount;
    }
}
