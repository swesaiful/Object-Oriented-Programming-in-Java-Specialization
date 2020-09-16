import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {

    /**
     * The getPerimeter method has one parameter s of type Shape. Given a shape, 
     * this method returns the perimeter of the shape.
     */

    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    /**
     * The getNumPoints that has one parameter s that is of type Shape. 
     * This method returns an integer that is the number of points in Shape s.
     */

    public int getNumPoints (Shape s) {
        int numPt = 0;
        for(Point currPt : s.getPoints()){
            numPt = numPt + 1;
        }
        return numPt;
    }

    /**
     * The getAverageLength that has one parameter s that is of type Shape. 
     * This method returns a number of type double that is the calculated 
     * average of all the sides’ lengths in the Shape S.
     */

    public double getAverageLength(Shape s) {
        double avgLength = getPerimeter(s)/ getNumPoints(s);
        return avgLength;
    }

    /**
     * The getLargestSide that has one parameter s that is of type Shape. 
     * This method returns a number of type double that is the longest side in the Shape S.
     */

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(currDist > largestSide){
                largestSide = currDist;
                prevPt = currPt;
            }
        }
        return largestSide;
    }

    /**
     * The getLargestX that has one parameter s that is of type Shape. 
     * This method returns a number of type double that is the largest x 
     * value over all the points in the Shape s.
     */

    public double getLargestX(Shape s) {
        double largestX = 0;
        for(Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if(currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    /**
     * The getLargestPerimeterMultipleFiles method has no parameters. 
     * This method creates a DirectoryResource (so you can select multiple files) 
     * and then iterates over these files. 
     */

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(currPerim > largestPerim){
                largestPerim = currPerim;
            }
            
        }
        return largestPerim;
    }

    /**
     * The getFileWithLargestPerimeter method has no parameters. 
     * This method should, like the getLargestPerimeterMultipleFiles method, 
     * create its own Directory Resource, except that this new method returns 
     * the File that has the largest such perimeter, so it has return type File.
     */

    public String getFileWithLargestPerimeter() {
        File largestFile = null;    // replace this code
        double largestPerim = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(currPerim > largestPerim){
                largestPerim = currPerim;
                largestFile = f;
            }
            
        }
        
        return largestFile.getName();
    }

    /**
     * The testPerimeter method has no return value, hence its return type is void. 
     * This method is used to select a data file by using the FileResource class, 
     * create a shape based on the points from that data file, and then calculate 
     * the perimeter of the shape and output its value.
     */

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPt = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPt);
        System.out.println("average side length = " + avgLength);
        System.out.println("longest side = " + largestSide);
        System.out.println("largest X value = " + largestX);
        
        //testPerimeterMultipleFiles();
        //testFileWithLargestPerimeter();
    }
    
    public void testPerimeterMultipleFiles() {
        double maxPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + maxPerim);
    }

    public void testFileWithLargestPerimeter() {
        String file =  getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + file);
    }

    /**
     * The triangle method has no return value and creates a triangle that you 
     * can use to test the methods you will create in this assignment.
     */
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This printFileNames() method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
