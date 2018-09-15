/* Name: Emmie Dong
 * PennKey: emdee
 * Recitation: 207
 * 
 * Execution: java Board
 * 
 * All possible positions for the snake 
 * 
 */   
public class Board {
    private double[][] xCoords; 
    private double[][] yCoords; 
    
    // constructor, no inputs
    public Board() {
        xCoords = new double[50][50];
        yCoords = new double[50][50];
        // go through each row
        for (int i = 0; i < 50; i++) {
            // go through each column
            for (int j = 0; j < 50; j++) {
                xCoords[i][j] = j * 0.02;
                yCoords[i][j] = 1 - i * 0.02;
            } 
        }
    }
    // returns x coordinate given the index value, int i and int j
    public double getXCoord(int i, int j) {
        return xCoords[i][j];
    }
    // returns y coordinate given the index value, int i and int j
    public double getYCoord(int i, int j) {
        return yCoords[i][j];
    }
  
    // remove a coordinate, inputs: int i int j, the coordinate index
    public void removeCoord(int i, int j) {
        xCoords[i][j] = -5;
        yCoords[i][j] = -5;
    }
    
    // add a coordinate, inputs: int i int j, the coordinate index
    // and double x double y, the coordinate values
    public void addCoord(int i, int j, double x, double y) {
        xCoords[i][j] = x;
        yCoords[i][j] = y;
    }
}