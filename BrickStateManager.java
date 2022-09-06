/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A
* Program Description: 
* This entity controlls the Brick array that will be used in the GameStateManager.
* It defines the brick alignment and create blocks accordingly.It
* Also draws and updates the Brick entities.
* 
*/

public class BrickStateManager {
    
    private Brick[][] bricks;
    private boolean[][] level1 = {
            {true, true, true, true, true, true},
            {true, true, false, false, true, true},
            {true, true, false, false, true, true},
            {true, true, true, true, true, true},
            {true, true, true, true, true, true},
            {true, true, false, false, true, true},
            {true, true, false, false, true, true},
            {true, true, false, false, true, true}
        };
    
    private boolean[][] level0 = {
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, true, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
        };
    
    private final  int ROW = 8;
    private final  int COL = 6;
    
    /** Constructor
     * Initiates the BrickStateManager entity.
     * Create bricks 2d array and call createBricks()
     */
    public BrickStateManager() {
        this.bricks = new Brick[ROW][COL];
        createBricks(level1);
    }
    
    /**
     * Creates the brick alignment based on the brickMap parameter
     * There is a 50 percent chance of WoodBrick, 35 percent of StoneBrick
     * and 15 percent of IronBrick forming where the value of brickMap[i][j] is true
     * The x,yPosition of the Brick is determined and given as parameter for the
     * Brick constructor.
     * @param boolean 2d array that specifies where the brick will be located
     */
    private void createBricks(boolean[][] brickMap) {
        
        Brick refBrick = new WoodBrick(0, 0);
        
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (brickMap[i][j]) {
                    double random = Math.random();
                    double xPosition = refBrick.HALF_WIDTH + 
                        refBrick.HALF_WIDTH * 2 * j;
                    double yPosition = 400 - refBrick.HALF_HEIGHT - 
                        refBrick.HALF_HEIGHT * 2 * i;
                    if (random < 0.5) {
                        bricks[i][j] = new WoodBrick(xPosition, yPosition);
                    } else if (random < 0.85) {
                        bricks[i][j] = new StoneBrick(xPosition, yPosition);
                    } else {
                        bricks[i][j] = new IronBrick(xPosition, yPosition);
                    }
                }
            }
        }
        
    }
    
    /**
     * Draw the Brick entities in bricks array.
     * Uses Brick entities' draw() method.
     */
    public void draw() {
        PennDraw.clear(PennDraw.BLACK);
         for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
                    bricks[i][j].draw();
                }
            }
         }
    }
    
    /**
     * Checks if the bricks 2d array is filled with only null values.
     * @return true when every slot in bricks array is null. false is there's Brick
     * remaining
     */
    public boolean isNoBricksLeft() {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Checks if the Brick inside bricks array collided with the ball.
     * When the brick is broken after the collision, add 50 points to the score.
     * @param current score of the game
     * @return updated score
     */
    public int update(Ball b, int score) {
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                if (bricks[i][j] != null) {
                    if (bricks[i][j].collide(b)) {
                        bricks[i][j] = null;
                        // add score when collision broke the brick
                        score += 50;
                    }
                }
            }
        }
        return score;
    }
    
    
}