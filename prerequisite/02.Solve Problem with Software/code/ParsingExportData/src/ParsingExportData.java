/**
 * The source code for
 * https://www.coursera.org/learn/java-programming/supplement/Qu17T/programming-exercise-parsing-export-data
 *
 * @author kate Zhang
 * @version 07/12/2020
 */

import edu.duke.*;
import org.apache.commons.csv.*;


public class ParsingExportData {

    public static void main(String[] args) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        ParsingExportData example = new ParsingExportData();

        example.listExportersTwoProducts(parser, "fish", "nuts");
        example.numberOfExporters(parser, "sugar");

        String info = example.countryInfo(parser, "Nauru");
        System.out.println(info);

        example.bigExporters(parser, "$999,999,999");
    }

    //    Write a method named tester
    public static void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
    }

    //  Write a method named countryInfo
    public String countryInfo(CSVParser parser, String country) {

        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                String info = country + ":" + record.get("Exports") + ": " + record.get(2);
                return info;
            }
        }

        return "Not Found";
    }

    //    Write a void method named listExportersTwoProducts
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            boolean containItem1 = export.contains(exportItem1);
            boolean containItem2 = export.contains(exportItem2);
            if (containItem1 && containItem2) {
                System.out.println(record.get("Country"));
            }
        }
    }

    //    Write a method named numberOfExporters
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.indexOf(exportItem) != -1) {
                count += 1;
            }
        }
        System.out.println(count);
        return count;
    }


    //    Write a method named bigExporters
    public void bigExporters(CSVParser parser, String amount) {
        float target = parseFloat(amount);

        for (CSVRecord record : parser) {
            String country = record.get(0);
            float value = parseFloat(record.get(2));
            if (value > target) {
                System.out.println(country + ':' + value);
            }
        }
    }

    public static float parseFloat(String amount) {
        String amountStr = amount.replace("$", "");
        amountStr = amountStr.replace(",", "");
        return Float.parseFloat(amountStr);
    }
}
