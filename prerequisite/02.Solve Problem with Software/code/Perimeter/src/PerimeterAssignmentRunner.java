import edu.duke.*;

import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalperim = 0
        double totalperim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalperim by currDist
            totalperim = totalperim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalperim is the answer
        return totalperim;
    }

    public int getNumPoints(Shape s) {
        // Put code here
        int totalPoints = 0;

        for (Point pt : s.getPoints()) {
            totalPoints += 1;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerm = getPerimeter(s);
        int pointSize = this.getNumPoints((s));
        return totalPerm / pointSize;
    }

    public double getLargestSide(Shape s) {
        double lgSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point curPt : s.getPoints()) {
            double curDist = prevPt.distance(curPt);
            lgSide = Math.max(lgSide, curDist);
        }
        return lgSide;
    }

    public double getLargestX(Shape s) {

        Point prevPt = s.getLastPoint();
        double maxX = prevPt.getX();
        for(Point curPt: s.getPoints()) {
            double curX = curPt.getX();
            maxX = Math.max(maxX, curX);
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        return 0.0;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);

        int numOfPts = getNumPoints(s);
        System.out.println("Number of points = " + numOfPts);

        double avgLen = getAverageLength(s);
        System.out.println("Average length = " + avgLen);

        double lgSide = getLargestSide(s);
        System.out.println("Largest side = " + lgSide);

        double lgX = getLargestX(s);
        System.out.println("Largest x = " + lgX);
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
