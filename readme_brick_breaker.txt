/**********************************************************************
 *  readme template                                                   
 *  Brick Breaker (Final Project)
 **********************************************************************/

Name: Hwi-sang Cho
PennKey: hwisang
Hours to complete assignment (optional):


/**********************************************************************
 *  Please list all help, collaboration, and outside resources
 *  you used here. 
 *
 *  If you did not get any help in outside of TA office hours,
 *  and did not use any materials outside of the standard
 *  course materials and piazza, write the following statement below:
 *  "I did not receive any help outside of TA office hours.  I
 *  did not collaborate with anyone, and I did not use any
 *  resources beyond the standard course materials."
 **********************************************************************/

I did not receive any help outside of TA office hours.
did not collaborate with anyone, and I did not use any
resources beyond the standard course-materials.


 
/**********************************************************************
 *  How to run the program                   
 **********************************************************************/

java Game

to run the brick breaker game.

When you click on the screen, the ball will start moving with initial velocity.
You can move the platform with your mouse.
When the ball fell off, click on the screen to resume.
When you win or lose, click again to start a new game.

/**********************************************************************
 *  Easier ways to test my game                 
 **********************************************************************/
1. When testing for win scenario, go to BrickStateManager.java and look at the 
constructor. Change createBricks(level1) to createBricks(level0).
Then the game will only have one brick.

2. When testing for lose scenario, go to GameStateManager.java and look at the
constructor. Change this.life = 3; to this.life = 1;

/**********************************************************************
 *  Some notes          
 **********************************************************************/
1. Sometimes the visual when the ball collides with the brick won't show up.
I speculate that it's due to the framerate.

2. You can control the x acceleration of the ball by how you move the platform.
If you move in the direction of the ball, the ball will accelerate more in that
direction. If you move the platform opposite to the vx of the ball, the ball will
deaccelerate or even change direction.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
Thank you for the amazing semester.
 
 
 
