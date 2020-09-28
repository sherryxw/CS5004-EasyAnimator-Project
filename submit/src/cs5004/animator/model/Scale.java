package cs5004.animator.model;

import java.util.Map;

/**
 * This class represents one of the animations: Scale. It extends from AbstractAnimation.
 * The constructor contains a shapeList, shapeName, animation startTime, animation endTime,
 * before height, before width, and after height, after width.
 */
public class Scale extends AbstractAnimations {

  private final double beforeHeight;
  private final double beforeWidth;
  private final double afterHeight;
  private final double afterWidth;

  /**
   * Constructor a Scale animation. It takes shapeList, shape name, animation startTime,
   * animation endTime, beforeWidth, beforeHeight, afterWidth, afterHeight as parameters.
   * @param shapeList the map of all the shapes
   * @param name the name of the shape that requests animation
   * @param startTime the start time of this animation
   * @param endTime the end time of this animation
   * @param beforeHeight before height of this shape
   * @param beforeWidth before width of this shape
   * @param afterHeight after height of this shape
   * @param afterWidth after width of this shape
   * @throws IllegalArgumentException if before/after width/height is invalid
   */
  public Scale(Map shapeList, String name, int startTime, int endTime,
               double beforeWidth, double beforeHeight, double afterWidth, double afterHeight)
          throws IllegalArgumentException {
    super(shapeList, AnimationsType.SCALE, name, startTime, endTime);

    if (afterHeight <= 0 || afterWidth <= 0 || beforeHeight < 0 || beforeWidth < 0) {
      throw new IllegalArgumentException("Height/width is invalid, cannot build scale animation");
    }

    this.beforeHeight = beforeHeight;
    this.beforeWidth = beforeWidth;
    this.afterHeight = afterHeight;
    this.afterWidth = afterWidth;

  }

  /**
   * Return true if can perform scale, false otherwise.
   * @return true if can perform scale, false otherwise
   */
  private boolean canAction() {
    //same size -> cannot reshape
    if (this.beforeWidth == this.afterWidth && this.beforeWidth == this.afterWidth) {
      return false;
    }
    IShape shape = (IShape) shapeList.get(name);
    return startTime >= shape.getAppears() && endTime <= shape.getDisappears();
  }

  /**
   * Perform scale if can do.
   */
  @Override
  public void performAction(int frame) {
    if (canAction()) {
      IShape shape = (IShape) shapeList.get(name);
      shape.setHeight(tweenHelper(this.beforeHeight, this.afterHeight, this.startTime,
              this.endTime, frame)) ;
      shape.setWidth(tweenHelper(this.beforeWidth, this.afterWidth, this.startTime,
              this.endTime, frame));
    }
  }

  /**
   * Return a string of the animation info.
   * @return a string of the animation info
   */
  @Override
  public String toString() {
    // For example:
    // Shape A scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0
    return "Shape " + this.name + " scales from Width: " + this.beforeWidth  + ", Height: "
            + this.beforeHeight + " to Width: " + this.afterWidth + ", Height: "
            + this.afterHeight;
  }


}
