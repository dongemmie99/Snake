
public class SnakeGameTest {
    public static void main(String[] args) {
        
        // did the snake run into its tail or the walls?
        boolean gameOver = false;
        
        boolean firstTime = true;
        
        
        
        PennDraw.enableAnimation(10);
        
        
        // run the game
        while (!gameOver) {
            PennDraw.text(0.5, 0.5,"press any key");
            System.out.println("hi");
            if (firstTime) {
                
                if (PennDraw.hasNextKeyTyped()) {
                    char key = PennDraw.nextKeyTyped();
                    PennDraw.clear();
                    firstTime = false;
                    System.out.println("cleared");
                }
            } else {
                //PennDraw.clear();
                System.out.println("running");
                    // draw snake using array 
                    // advance snake one box - if key is pressed, advance snake in that direction, if not, keep going straight 
                    //PennDraw.advance(); 
            }
        }
    }
}