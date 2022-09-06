/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A
* Program Description: 
* Platform that moves only in the x direction.Platform
* It bounces the ball vertically and affects the ball's x acceleration
* when it collides with the ball. The ball's acceleration is affected by
* the movement of the platform.
* 
*/

public class Platform {
    private double halfWidth = 37.5;
    private double halfHeight = 15;
    private double acceleration;
    private double xPosition;
    private double yPosition;
    private boolean movingRight;
    
    
    /** Constructor
     * Initiates the Platform entity with default values
     */
    public Platform() {
        this.halfWidth = 45;
        this.halfHeight = 5;
        this.acceleration = 0.0;
        this.xPosition = 225;
        this.yPosition = 30;
        this.movingRight = false;
    }
    
    /**
     * Draw the platform with the respective position and width/height
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(xPosition, yPosition, halfWidth, halfHeight);
    }
    
    /**
     * Update the platform
     * Calls move() and collide()
     * @param x-coordinate of the mouse and the Ball entity
     */
    public void update(double mouseX, Ball ball) {
        move(mouseX);
        collide(ball);
    }
    
    /**
     * Update the position of the platform using mousePosition.
     * Calculates the acceleration of the platform.
     * @param x-coordinate of the mouse
     */
    private void move(double mouseX) {
        
        // if the mouse goes beyond the screen, limit the platform position
        if (mouseX <= halfWidth || mouseX >= 450 - halfWidth) {
            return;
        }
        
        /*
         * set the acceleration of the platform by how much it moved from the
         * previous position
         * */ 
        acceleration = (mouseX - xPosition) * 0.25;
        xPosition = mouseX;
    }
    
    /**
     * If the ball has collided with the platform, affect the ball's movement
     * with the platform's acceleration
     * @param Ball entity
     */
    private void collide(Ball ball) {
        
        if (ball == null) {
            return;
        }
        
        // check if the ball and the platform is in contact
        boolean hasCollided = ball.getXPosition() >= xPosition - halfWidth && 
                ball.getXPosition() <= xPosition + halfWidth &&
                ball.getBottom() >= yPosition - halfHeight && 
                ball.getBottom() <= yPosition + halfHeight;
        
        /*
         * if collided, change the ball y velocity and affect the ball's x velocity
         * with the platform's acceleration
         * */ 
        if (hasCollided) {
            ball.negateVY();
            ball.affectX(acceleration);
        }
    }
}