import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Source code for the third week, Parsing Weather Data
 */
public class ParsingWeatherData {
    public static void main(String[] args) {
        DirectoryResource dr = new DirectoryResource();
        Humidity humidity = new Humidity(dr);
//        Weather weather = new Weather();
        humidity.fileWithLowestHumidity(dr);
    }

    private void coldestHourInManyFiles() {
    
    }


}
