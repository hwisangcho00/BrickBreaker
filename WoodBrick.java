/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A 
* Program Description: 
* Creates a WoodBrick class that implements the Brick interface.
* Overrides all method specified in the Brick interface.
* When the ball entity hits the brick, it loses one strength, and when strength
* becomes 0, it breaks. The ball changes direction in Y after colliding with
* the ball. The y acceleration of the ball is also affected after collision.
* 
*/

public class WoodBrick implements Brick {
    private int strength;
    private double xPosition;
    private double yPosition;
    private double topBoundary;
    private double leftBoundary;
    private double rightBoundary;
    private double bottomBoundary;
    private boolean isBroken;
    
    /** Constructor
     * Initiates the WoodBrick entity with default values
     */
    public WoodBrick(double xPosition, double yPosition) {
        //weakest with 1 strength
        this.strength = 1;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.topBoundary = yPosition + HALF_HEIGHT;
        this.leftBoundary = xPosition - HALF_WIDTH;
        this.rightBoundary = xPosition + HALF_WIDTH;
        this.bottomBoundary = yPosition - HALF_HEIGHT;
        this.isBroken = false;
    }
    
    /**
     * When the brick is not broken, draws the rectangle and the remaining strength
     */
    @Override
    public void draw() {
        if (!isBroken) {
            PennDraw.setPenColor(155, 103, 60);
            PennDraw.filledRectangle(xPosition, yPosition, HALF_WIDTH, HALF_HEIGHT);
            PennDraw.setPenColor(PennDraw.YELLOW);
            PennDraw.text(xPosition, yPosition, "" + strength);
        }
    }
    
    /**
     * Draws a star shape at the ball's position
     * @param ball entity for its position
     */
    @Override
    public void drawWhenCollide(Ball ball) {
        PennDraw.setPenColor(PennDraw.YELLOW);
        PennDraw.filledSquare(ball.getXPosition(), ball.getYPosition(), 10);
        PennDraw.filledSquare(ball.getXPosition(), ball.getYPosition(), 10, 45);
    }
    
    /**
     * decrements the strength by 1. When strength is zero or lower, 
     * set isBroken to true
     * @return the boolean value isBroken
     */
    @Override
    public boolean takeDamage() {
        strength--;
        
        if (strength <= 0) {
            isBroken = true;
        }
        
        return isBroken;
        
    }
    
    /**
     * Checks if the Brick collided with the ball.
     * If the two collided, affects the ball's velocity and acceleration
     * @param ball entity
     * @return whether the brick has broke after the collision
     */
    @Override
    public boolean collide(Ball ball) {
        
        // check if the brick and the ball is in contact
        boolean hasCollided = ball.getRight() >= leftBoundary && 
            ball.getLeft() <= rightBoundary && 
            ball.getTop() >= bottomBoundary && 
            ball.getBottom() <= topBoundary;
        
        if (hasCollided) {
            
            // show visual effect
            drawWhenCollide(ball);
            
            /*
             * we reverse the position of the ball with its velocity to make up
             * for the precision error when in the contact
             * */ 
            ball.setXPosition(ball.getXPosition() - ball.getVX() * 2);
            ball.setYPosition(ball.getYPosition() - ball.getVY() * 2);
            
            // change y velocity of the ball
            ball.negateVY();
            
            // create random acceleration that goes from -1 to 1
            double ay = (Math.random() - 0.5) * 2;
            
            // change the ball's y velocity with the acceleration
            ball.affectY(ay);
            
            // take damage and return if the brick has been broken or not
            return takeDamage();
        }
        
        return false;
        
    }
    
}