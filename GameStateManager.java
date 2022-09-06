/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A
* Program Description: 
* Central class that manages all the entities action during the game play.
* Sets up and initiates the game and contains the methods that draws and updates
* every entity need in the gameplay
* 
*/

public class GameStateManager {
    private boolean isGameLose;
    private boolean isGameWin;
    private boolean isGamePaused;
    private int width;
    private int height;
    private int score;
    private int life;
    
    private BrickStateManager bsm;
    private Ball ball;
    private Platform platform;
    
    /** Constructor
     * private so that it's harder to access.
     * Initiates the GameStateManager entity with default values
     */
    private GameStateManager() {
        this.isGameLose = false;
        this.isGameWin = false;
        this.isGamePaused = true;
        this.width = 450;
        this.height = 400;
        this.score = 0;
        this.life = 3;
        this.bsm = new BrickStateManager();
        this.ball = new Ball();
        this.platform = new Platform();
    }
    
    /**
     * Creates and returns a new GameStateManager object
     * @return new GameStateManager object
     */
    public static GameStateManager setup() {
        GameStateManager gsm = new GameStateManager();
        
        return gsm;
    }
    
    /**
     * getter for isGamePaused
     * @return isGamePaused
     */
    public boolean getIsGamePaused() {
        return isGamePaused;
    }
    
    
    /**
     * Sets the gameplay screen setting.
     */
    public void initiate() {
        PennDraw.setCanvasSize(width, height);
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);
        PennDraw.setFontSize(20);
    }
    
    /**
     * checks whether the player has life left.
     * @return true if life <= 0, false otherwise
     */
    private boolean isNoLifeLeft() {
        if (life <= 0) {
            return true;
        }
        return false;
    }
    
    /**
     * if there is no life left, set isGameLose to true.
     * @return boolean value isGameLose
     */
    public boolean checkGameLost() {
        if (isNoLifeLeft()) {
            isGameLose = true;
        }
        return isGameLose;
    }
    
    /**
     * check if all the bricks have been broken.
     * if so set isGameWin to true.
     * @return boolean value isGameWin.
     */
    public boolean checkGameWin() {
        if (bsm.isNoBricksLeft()) {
            isGameWin = true;
        }
        return isGameWin;
    }
    
    /**
     * Start refers to when the game start with the ball stationary 
     * on the platform
     * 
     * When the user clicks, set isGamePaused to false.
     * @param user input of mouse click
     */
    public void startGame(boolean mouseClick) {
        if (mouseClick) {
            isGamePaused = false;
        }
        
    }
    
    /**
     * Updates the every elements in the game : ball, bricks, platform, score, life.
     * When the ball goes off screen, set isGamePaused to true and reset the 
     * ball and the platform.
     * @param x-coordinate of the mouse
     */
    public void update(double mouseX) {
        ball.update();
        score = bsm.update(ball, score);
        platform.update(mouseX, ball);
        
        if (ball.offTheScreen()) {
            life--;
            
            /*
             * we create the new instance here to make sure we have the ball and
             * the platform positioned and drawn at the initial position
             * */ 
            ball = new Ball();
            platform = new Platform();

            isGamePaused = true;
        }
    }
    
    /**
     * Draw the every elements in the game : ball, bricks, platform, score, life.
     * When game is won or lost, display according screen.
     */
    public void draw() {
        // when game lost
        if (isGameLose) {
            drawWhenLost();
            return;
        }
        
        // when game won
        if (isGameWin) {
            drawWhenWin();
            return;
        }
        
        // call various draw() function from entities
        bsm.draw();
        PennDraw.setFontSize(12);
        PennDraw.text(width - 100, 20, "Score : " + score);
        PennDraw.text(width - 30, 20, "Life : " + life);
        ball.draw();
        platform.draw();
    }
    
    /**
     * Draw the screen when player lost.
     * Display score and instruction.
     */
    private void drawWhenLost() {
        PennDraw.clear(PennDraw.BLACK);
        PennDraw.setFontSize(20);
        PennDraw.text(width / 2, height / 2 - 25 , 
                      "You lose. Click to start new game");
        PennDraw.text(width / 2, height / 2 + 25, "Score : " + score);
    }
    
    /**
     * Draw the screen when player won.
     * Display congratuation and instruction.
     */
    private void drawWhenWin() {
        PennDraw.clear(PennDraw.BLACK);
        PennDraw.setFontSize(20);
        PennDraw.text(width / 2, height / 2, "You Win. Click to start new game");
    }
}