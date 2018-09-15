/* Name: Emmie Dong
 * PennKey: emdee
 * Recitation: 207
 * 
 * Execution: java Snake
 * 
 * Creates a snake that can be moved
 * 
 */ 
import java.util.ArrayList;
public class Snake {
    // x and y coordinates of snake 
    private ArrayList<Double> snakeX = new ArrayList<Double>(); 
    private ArrayList<Double> snakeY = new ArrayList<Double>(); 
    // all possible positions of snake 
    private Board snakePositions; 
    // index of head of snake
    private int iIndex; // row
    private int jIndex; // column
    // characteristics of the snake
    private String direction; 
    private int size;
    private boolean eat; 
    private boolean gameOver; 
    private int score;
    private int[] highScores;
    // characteristics of food 
    private int iIndexFood; // row
    private int jIndexFood; // column
    private double foodX; // x coord of food
    private double foodY; // y coord of food
    private double lastRemovedX; // for when the snake eats the food
    private double lastRemovedY; // for when the snake eats the food
    
    // constructor, no input
    public Snake() {
        highScores = new int[5];
        reset();
    }
    
    // reset snake to default, when game over and start new game
    // no input/output
    public void reset() {
        snakeX.clear();
        snakeY.clear();
        snakePositions = new Board(); 
        gameOver = false;
        score = 0; 
        // randomly generate starting position
        iIndex = (int) (Math.random() * 46) + 2;
        jIndex = (int) (Math.random() * 46) + 2;
        snakeX.add(snakePositions.getXCoord(iIndex, jIndex));
        snakeY.add(snakePositions.getYCoord(iIndex, jIndex));
        size = snakeX.size();
        // randomly generate starting direction
        int rand = (int) (Math.random() * 4) + 1;
        if (rand == 1) {
            direction = "right";
        }
        if (rand == 2) {
            direction = "left";
        }
        if (rand == 3) {
            direction = "up";
        }
        if (rand == 4) {
            direction = "down";
        }
    }
    
    // make the snake turn, input: char key, no output
    public void turn(char key) {
        // try catch for edge cases that throw exceptions
        try {
            // make sure snake isnt going parallel to key press
            if (!((key == 'a' || key == 'd') && 
                  (direction.equals("left") || direction.equals("right"))) &&
                !((key == 'w' || key == 's') && 
                  (direction.equals("up") || direction.equals("down")))) {
                // left
                if (key == 'a') {
                    snakeX.add(snakePositions.getXCoord(iIndex, jIndex - 1));
                    snakeY.add(snakePositions.getYCoord(iIndex, jIndex - 1));
                    jIndex -= 1;
                    direction = "left"; 
                }
                // right
                if (key == 'd') {
                    snakeX.add(snakePositions.getXCoord(iIndex, jIndex + 1));
                    snakeY.add(snakePositions.getYCoord(iIndex, jIndex + 1));
                    jIndex += 1;
                    direction = "right";
                }
                // up
                if (key == 'w') {
                    snakeX.add(snakePositions.getXCoord(iIndex - 1, jIndex));
                    snakeY.add(snakePositions.getYCoord(iIndex - 1, jIndex));
                    iIndex -= 1;
                    direction = "up";
                }
                // down
                if (key == 's') {
                    snakeX.add(snakePositions.getXCoord(iIndex + 1, jIndex));
                    snakeY.add(snakePositions.getYCoord(iIndex + 1, jIndex));
                    iIndex += 1;
                    direction = "down";
                }  
                lastRemovedX = snakeX.get(0);
                lastRemovedY = snakeY.get(0);
                snakeX.remove(0);
                snakeY.remove(0); 
            }
        } catch (IndexOutOfBoundsException e) {
            gameOver = true;
        }
        // edge cases
        if (iIndex == 0 || iIndex == 49 || jIndex == 0 || jIndex == 49) {
            gameOver = true;
        } 
    }
    
    // make snake move forward, no inputs/outputs
    public void forward() {
        // try catch for edge cases that throw exception
        try {
            if (direction.equals("right")) {
                // add one to head of snake, add to end of array
                snakeX.add(snakePositions.getXCoord(iIndex, jIndex + 1));
                snakeY.add(snakePositions.getYCoord(iIndex, jIndex + 1));
                jIndex += 1;      
            }
            if (direction.equals("left")) {
                // add one to head of snake, add to end of array
                snakeX.add(snakePositions.getXCoord(iIndex, jIndex - 1));
                snakeY.add(snakePositions.getYCoord(iIndex, jIndex - 1));
                jIndex -= 1;    
            }
            if (direction.equals("up")) {
                // add one to head of snake, add to end of array
                snakeX.add(snakePositions.getXCoord(iIndex - 1, jIndex));
                snakeY.add(snakePositions.getYCoord(iIndex - 1, jIndex));
                iIndex -= 1;    
            }
            if (direction.equals("down")) {
                // add one to head of snake, add to end of array
                snakeX.add(snakePositions.getXCoord(iIndex + 1, jIndex));
                snakeY.add(snakePositions.getYCoord(iIndex + 1, jIndex));
                iIndex += 1;
            }
            // remove one to tail of snake, take away from beginning of array
            lastRemovedX = snakeX.get(0);
            lastRemovedY = snakeY.get(0);
            snakeX.remove(0);
            snakeY.remove(0);         
        } catch (IndexOutOfBoundsException e) {
            gameOver = true;
        }
    }
    
    // did the snake eat anything, no input, output true/false
    public boolean eat() {
        if (snakePositions.getXCoord(iIndex, jIndex) == foodX && 
            snakePositions.getYCoord(iIndex, jIndex) == foodY) {
            score += 10;
            snakeX.add(0, lastRemovedX);
            snakeY.add(0, lastRemovedY);
            return true;
        }
        return false;
    }
    
    // test if snake has run into its own tail, no output/input
    public void tail() {
        if (direction.equals("right")) {
            for (int i = 0; i < snakeX.size() - 4; i++) {
                if (snakePositions.getXCoord(iIndex, jIndex - 1) == 
                    snakeX.get(i) &&
                    snakePositions.getYCoord(iIndex, jIndex - 1) == 
                    snakeY.get(i)) {
                    gameOver = true;
                } 
            }
        }
        if (direction.equals("left")) {
            for (int i = 0; i < snakeX.size() - 4; i++) {
                if (snakePositions.getXCoord(iIndex, jIndex + 1) == 
                    snakeX.get(i) && 
                    snakePositions.getYCoord(iIndex, jIndex + 1) == 
                    snakeY.get(i)) {
                    gameOver = true;
                } 
            }
        }
        if (direction.equals("up")) {
            for (int i = 0; i < snakeX.size() - 4; i++) {
                if (snakePositions.getXCoord(iIndex + 1, jIndex) == 
                    snakeX.get(i) && 
                    snakePositions.getYCoord(iIndex + 1, jIndex) == 
                    snakeY.get(i)) {
                    gameOver = true;
                } 
            }
        }
        if (direction.equals("down")) {
            for (int i = 0; i < snakeX.size() - 4; i++) {
                if (snakePositions.getXCoord(iIndex - 1, jIndex) == 
                    snakeX.get(i) && 
                    snakePositions.getYCoord(iIndex - 1, jIndex) == 
                    snakeY.get(i)) {
                    gameOver = true;
                } 
            }
        }
    }
    
    // generate new position of food in an unoccupied space, no output/input
    public void generateFood() {
        boolean empty = false; // assume food spot is not empty at first
        while (!empty) {
            iIndexFood = (int) (Math.random() * 48) + 1;
            jIndexFood = (int) (Math.random() * 48) + 1; 
            // run through snake to check if positions dont overlap 
            // finding an empty spot for food
            for (int i = 0; i < snakeX.size(); i++) {
                if (snakePositions.getXCoord(iIndexFood, jIndexFood) != 
                    snakeX.get(i) && 
                    snakePositions.getYCoord(iIndexFood, jIndexFood) !=
                    snakeY.get(i)) {
                    foodX = snakePositions.getXCoord(iIndexFood, jIndexFood);
                    foodY = snakePositions.getYCoord(iIndexFood, jIndexFood);
                    empty = true;
                }
            }
        }
    }
    
    // draws food, no output/input
    public void drawFood() {
        PennDraw.filledCircle(foodX, foodY, 0.01);
    }
    
    // draw the snake, no output/input
    public void draw() {
        for (int i = 0; i < snakeX.size(); i++) {
            PennDraw.square(snakeX.get(i), snakeY.get(i), 0.01);
        }
    }
    
    // return output score, no input
    public int getScore() {
        return score;
    }
    
    // returns output boolean, if game is over or not, no input
    public boolean getGameOver() {
        return gameOver;
    }
    
    // update the scoreboard, then draw, no inputs/outputs
    public void drawHighScores() {
        PennDraw.clear(PennDraw.BLACK);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(0.5, 0.7, "GAME OVER");
        PennDraw.text(0.5, 0.6, "press any key to continue");
        PennDraw.rectangle(0.5, 0.3, 0.3, 0.2);
        PennDraw.text(0.5, 0.45, "HIGH SCORES");
      
        // find where to insert score, if applicable
        int highIndex = 5;
        if (score != 0) {
            for (int i = 4; i >= 0; i--) {
                if (score > highScores[i]) {
                    highIndex = i;                
                }
            }
        }
        // shift scores over 
        if (highIndex != 5) {
            for (int j = highScores.length - 2; j >= highIndex; j--) {
                highScores[j + 1] = highScores[j];
            }
            highScores[highIndex] = score;
        }
        // draw scoreboard
        for (int i = 0; i < highScores.length; i++) {
            PennDraw.text(0.5, 0.4 - i * 0.05, (i + 1) + ". " + highScores[i]);
        }
        score = 0;
        PennDraw.setPenColor(PennDraw.BLACK);
    }
}