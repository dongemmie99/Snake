/* Name: Emmie Dong
 * PennKey: emdee
 * Recitation: 207
 * 
 * Execution: java SnakeGame
 * 
 * Game of Snake
 * 
 */   
public class SnakeGame {
    public static void main(String[] args) {
        // did the snake run into its tail or the walls?
        boolean gameOver = false;
        // is this the start?
        boolean start = true;
        // create snake
        Snake snakey = new Snake();
        PennDraw.enableAnimation(15);
        
        while (true) {
            if (start) {
                PennDraw.clear();
                PennDraw.text(0.5, 0.55, "SNAKE"); 
                PennDraw.text(0.5, 0.45, "press any key to start");  
                if (PennDraw.hasNextKeyTyped()) {
                    start = false;
                    PennDraw.nextKeyTyped();
                    snakey.generateFood();
                }
            }
            
            // if its not start of game, run game
            if (!start && !gameOver) { 
                PennDraw.clear();
                PennDraw.rectangle(0.49, 0.51, 0.48, 0.48);
                PennDraw.text(0.5, -0.02, "Score: " + snakey.getScore());
                snakey.draw();
                snakey.drawFood();
                if (PennDraw.hasNextKeyTyped()) { 
                    char key = PennDraw.nextKeyTyped();
                    if (key == 'w' || key == 'a' || 
                        key == 's' || key == 'd') {
                        snakey.turn(key); 
                    }
                } else {
                    snakey.forward();     
                }
                
                // test if game over
                snakey.tail();
                gameOver = snakey.getGameOver();
                
                // test if it has eaten a food 
                if (snakey.eat()) {
                    snakey.generateFood();
                }
            }
            
            // starts over if game is over
            if (gameOver) { 
                snakey.drawHighScores(); 
                if (PennDraw.hasNextKeyTyped()) {
                    start = true;
                    gameOver = false;
                    PennDraw.nextKeyTyped();
                    PennDraw.clear();
                    snakey.reset();
                }
            }
            PennDraw.advance();
        }
    }
}
