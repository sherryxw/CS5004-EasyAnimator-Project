package cs5004.animator.model;

import java.util.Map;

/**
 * This class represents one of the animations: Move. It extends from AbstractAnimation.
 * The constructor contains a shapeList, shapeName, animation startTime, animation endTime,
 * position before move, and position after move.
 */
public class Move extends AbstractAnimations {
  private final double fromX;
  private final double fromY;
  private final double toX;
  private final double toY;

  /**
   * Constructor a Move animation. It takes shapeList, shape name, animation startTime,
   * animation endTime, before position, after position as parameters.
   * @param shapeList the map of all the shapes
   * @param name the name of the shape that requests animation
   * @param startTime the start time of this animation
   * @param endTime the end time of this animation
   * @param fromX the x-coordinate before move
   * @param fromY the y-coordinate before move
   * @param toX the x-coordinate after move
   * @param toY the y-coordinate after move
   * @throws IllegalArgumentException if before or after position is invalid
   */
  public Move(Map shapeList, String name, int startTime, int endTime, double fromX, double fromY,
              double toX, double toY) throws IllegalArgumentException {
    super(shapeList, AnimationsType.MOVE, name, startTime, endTime);

    //if (fromX < 0 || fromY < 0 || toX < 0 || toY < 0) {
    //  throw new IllegalArgumentException("Invalid position input, cannot build move animation");
    //}
    this.fromX = fromX;
    this.fromY = fromY;
    this.toX = toX;
    this.toY = toY;

  }

  /**
   * Return true if can perform move, false otherwise.
   * @return true if can perform move, false otherwise
   */
  private boolean canAction() {
    //cannot move from same position to same position
    if (this.fromX == this.toX && this.fromY == this.toY) {
      return false;
    }
    IShape shape = (IShape) this.shapeList.get(this.name);
    return startTime >= shape.getAppears() && endTime <= shape.getDisappears();

  }

  /**
   * Perform move position if can do.
   */
  @Override
  public void performAction(int frame) {

    if (canAction()) {
      IShape shape = (IShape) this.shapeList.get(this.name);
      double x = tweenHelper(this.fromX, this.toX, this.startTime, this.endTime, frame);
      double y = tweenHelper(this.fromY, this.toY, this.startTime, this.endTime, frame);
      shape.setPosition2D(x, y);
    }
    
  }


  /**
   * Return a string of the animation info.
   * @return a string of the animation info
   */
  @Override
  public String toString() {
    // For example:
    // Shape A moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50
    return "Shape " + this.name + " moves from (" + this.fromX  + "," + this.fromY + ") to ("
            + this.toX + "," + this.toY + ")";
  }





}
