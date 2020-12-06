
/**
 * Code for homework week2, the part4
 * resouces: https://www.dukelearntoprogram.com/course2/doc/javadoc/edu/duke/URLResource.html
 */

import edu.duke.*;

public class Part4 {
    public void findYoutube() {
        String keywords = "youtube.com";

        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String line : ur.lines()) {
            boolean isYoutubeLink = line.indexOf(keywords) != -1;
            if (isYoutubeLink) {
                int startIndex = line.indexOf("http");
                int stopIndex = line.indexOf("\"", startIndex);
                try {
                    String youtubeLink = line.substring(startIndex, stopIndex);
                    if (youtubeLink.indexOf(keywords) > -1) {
                        System.out.println(youtubeLink);
                    }
                } catch(Exception e) {
                    System.out.println(line);
                    System.out.println(startIndex);
                    System.out.println(stopIndex);
                }
            }
        }

    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.findYoutube();
    }

}
