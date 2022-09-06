/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A
* Program Description: 
* Ball entity that bounces off walls, bricks, and platform.
* Moves with velocity distinctively in x and y direction, and the velocity is 
* affected by acceleration when it collides with a brick or the platform.
* 
*/

public class Ball {
    
    private double radius;
    private double xPosition;
    private double yPosition;
    private final double INITIALX = 225;
    private final double INITIALY = 50;
    private final double MAX_VX = 5;
    private final double MIN_VX = -5;
    private final double MAX_VY = 3.5;
    private final double MIN_VY = 2.5;
    private double vx;
    private double vy;
    
    /** Constructor
     * Initiates the Ball entity with default values
     */
    public Ball() {
        this.radius = 5;
        this.xPosition = INITIALX;
        this.yPosition = INITIALY;
        this.vx = -0.5;
        this.vy = 3;
    }
    
    /**
     * Getters and setters for the Ball properties
     */
    public double getXPosition() {
        return this.xPosition;
    }
    
    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }
    
    public double getYPosition() {
        return this.yPosition;
    }
    
    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }
    
    public double getVX() {
        return this.vx;
    }
    
    public double getVY() {
        return this.vy;
    }
    
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Getters and setters for the boundaries of Ball
     */
    public double getTop() {
        return yPosition + radius;
    }
    
    public double getLeft() {
        return xPosition - radius;
    }
    
    public double getRight() {
        return xPosition + radius;
    }
    
    public double getBottom() {
        return yPosition - radius;
    }
    
    /**
     * negate the vx value
     */
    public void negateVX() {
        this.vx *= -1;
    }
    
    /**
     * negate the vy value
     */
    public void negateVY() {
        this.vy *= -1;
    }
    
    /**
     * Draw the ball with the respective position and radius
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledCircle(xPosition, yPosition, radius);
    }
    
    /**
     * Update the position of the ball using the vx and vy.
     * If the ball bounces to the left, right, or top of the screen,
     * change the vx and vy respectively.
     */
    public void update() {
        
        // when bounce against left or right wall
        if (getLeft() + vx <= 0 || getRight() + vx >= 450) {
            negateVX();
        }
        
        // when bounce against top wall
        if (getTop() + vy >= 400) {
            negateVY();
        }
        
        // chance the x,y position accordingly with the velocity
        xPosition += vx;
        yPosition += vy;
    }
    
    /**
     * Check if the yPosition is negative or not.
     * @return true if the yPosition of the ball is negative. True otherwise
     */
    public boolean offTheScreen() {
        
        if (yPosition <= 0) {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * Change the velocity of x (vx) by the acceleration.
     * There is a maximum and minimum value for vx
     * -5 < vx < 5
     * @param acceleration of x
     */
    public void affectX(double ax) {
        
        double possibleVx = vx + ax;
        
        if (possibleVx > MAX_VX) {
            vx = MAX_VX;
        } else if (possibleVx < MIN_VX) {
            vx = MIN_VX;
        } else {
            vx += ax;
        }
    }
    
    /**
     * Change the velocity of y (vy) by the acceleration.
     * There is a limit for vy
     * -3.5 < vy < -2.5 or 2.5 < vy < 3.5
     * @param acceleration of y
     */
    public void affectY(double ay) {
        
        double possibleVy = vy + ay;
        
        if (vy > 0) {
            if (possibleVy > MAX_VY) {
                vy = MAX_VY;
            } else if (possibleVy < MIN_VY) {
                vy = MIN_VY;
            } else {
                vy += ay;
            }
        } else {
            if (possibleVy < -1 * MAX_VY) {
                vy = -1 * MAX_VY;
            } else if (possibleVy > -1 * MIN_VY) {
                vy = -1 * MIN_VY;
            } else {
                vy += ay;
            }
        }
    }
}