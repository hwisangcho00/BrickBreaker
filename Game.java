/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: java Game
* Program Description: 
* Functions as the main method for the game.Functions
* Uses the GameStateManager entity to control the gameplay.
* Logic flow that checks for user input (click) when game is paused or ended
* both won and lost.
* 
*/

public class Game {
    
    public static void main(String[] args) {
        
        GameStateManager gsm = GameStateManager.setup();
        gsm.initiate();
        gsm.draw();
        
        PennDraw.enableAnimation(45);
        
        // outer loop allows the game to run endlessly
        while (true) {
            // when the game is not lost and not won
            while (!gsm.checkGameLost() && !gsm.checkGameWin()) {
                
                PennDraw.clear();
                
                gsm.draw();

                // when game is not paused, we update the gsm
                if (!gsm.getIsGamePaused()) {
                    gsm.update(PennDraw.mouseX());
                } else {
                    // game is paused
                    
                    // break when game is lost
                    if (gsm.checkGameLost()) {
                        break;
                    }

                    // break when game is won
                    if (gsm.checkGameWin()) {
                        break;
                    }
                    
                    // we unpause/start the game when mouse is pressed
                    gsm.startGame(PennDraw.mousePressed());
                    
                }
                
                PennDraw.advance();
                
            } // end of inner while loop
            
            // we reach here when game is lost or won
            
            // check if the game is lost
            if (gsm.checkGameLost()) {
                gsm.draw();
                // start new game by calling setup()
                if (PennDraw.mousePressed()) {
                    gsm = gsm.setup();
                }
            }
            
            // check if the game is lost
            if (gsm.checkGameWin()) {
                gsm.draw();
                // start new game by calling setup()
                if (PennDraw.mousePressed()) {
                    gsm = gsm.setup();
                }
            } 
            
            PennDraw.advance();
        } // end of outer while loop
    }
    
}