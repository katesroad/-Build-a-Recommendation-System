/**
 * The source code for https://www.coursera.org/learn/java-programming/lecture/s4ENY/which-countries-export-developing-an-algorithm
 *
 * @author Kate Zhang
 * @version 06/12/2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVCountries {

    // Get the country name
    public String getRecordCountry(CSVRecord record) {
        return record.get("country");
    }

    /**
     * To test if a record contains a item in a specified row
     *
     * @param {CSVRecord} record the record
     * @param {string}    rowName the rowName
     * @return {boolean}
     * @Param{string} item the being searched item string
     */
    public boolean hasItemInRecord(CSVRecord record, String rowName, String item) {
        String rowValue = record.get(rowName).trim();
        if (rowValue.length() > 0) {
            return rowValue.indexOf(item) != -1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Can run!!");
    }
}
