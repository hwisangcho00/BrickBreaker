/*
* Name: Hwi-sang Cho
* Pennkey: hwisang
* Execution: N/A
* Program Description: 
* Interface that defines the characteristics of a brick entity.
*/

public interface Brick {
    
    //Width and height is defined for all class that implements Brick
    double HALF_WIDTH = 37.5;
    double HALF_HEIGHT = 15;
    
    void draw();
    void drawWhenCollide(Ball ball);
    boolean takeDamage();
    boolean collide(Ball ball);
   
}