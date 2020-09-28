package cs5004.animator.model;

import java.util.Map;

/**
 * This class represents one of the animations: Color change. It extends from AbstractAnimation.
 * The constructor contains a shapeList, shapeName, animation startTime, animation endTime,
 * and RGB color.
 */
public class ChangeColor extends AbstractAnimations {

  private final double beforeRed;
  private final double beforeGreen;
  private final double beforeBlue;
  private final double afterRed;
  private final double afterGreen;
  private final double afterBlue;


  /**
   * Constructor a colorChange animation. It takes shapeList, shape name, animation startTime,
   * animation endTime, before RGB, after RGB as parameters.
   * @param shapeList the map of all the shapes
   * @param name the name of the shape that requests animation
   * @param startTime the start time of this animation
   * @param endTime the end time of this animation
   * @param beforeRed the red intensity before change
   * @param beforeGreen the green intensity before change
   * @param beforeBlue the blue intensity before change
   * @param afterRed the red intensity after change
   * @param afterGreen the green intensity before change
   * @param afterBlue the blue intensity before change
   * @throws IllegalArgumentException if RGB input is invalid
   */
  public ChangeColor(Map shapeList, String name, int startTime, int endTime,
                     double beforeRed, double beforeGreen, double beforeBlue, double afterRed,
                     double afterGreen, double afterBlue)
          throws IllegalArgumentException {
    super(shapeList, AnimationsType.COLOR, name, startTime, endTime);

    if (afterRed < 0 || afterBlue < 0 || afterGreen < 0
            || afterRed > 255 || afterBlue > 255 || afterGreen > 255 || beforeRed < 0
            || beforeRed > 255 || beforeBlue < 0 || beforeBlue > 255 || beforeGreen < 0
            || beforeGreen > 255) {
      throw new IllegalArgumentException("Invalid RGB input, cannot build color animation");
    }

    this.shapeList = shapeList;
    this.name = name;
    this.beforeRed = beforeRed;
    this.beforeGreen = beforeGreen;
    this.beforeBlue = beforeBlue;
    this.afterRed = afterRed;
    this.afterGreen = afterGreen;
    this.afterBlue = afterBlue;
  }

  /**
   * Return true if can perform color change, false otherwise.
   * @return true if can perform color change, false otherwise
   */
  private boolean canAction() {

    IShape shape = (IShape) this.shapeList.get(this.name);

    //same color -> cannot change color
    if ((this.beforeRed == this.afterRed && this.beforeBlue == this.afterBlue
            && this.beforeGreen == this.afterGreen)) {
      return false;
    }
    //cannot perform before shape appear or after shape disappear (time out of range)
    return this.getStartTime() >= shape.getAppears() && this.getEndTime() <= shape.getDisappears();
  }

  /**
   * Perform color change if can color change.
   */
  @Override
  public void performAction(int frame) {
    if (canAction()) {
      //perform color change
      IShape shape = (IShape) this.shapeList.get(this.name);
      shape.setGreen(tweenHelper(this.beforeGreen, this.afterGreen, this.startTime,
              this.endTime, frame));
      shape.setBlue(tweenHelper(this.beforeBlue, this.afterBlue, this.startTime, this.endTime,
              frame));
      shape.setRed(tweenHelper(this.beforeRed, this.afterRed, this.startTime, this.endTime, frame));
    }
  }

  /**
   * Return a string of the animation info.
   * @return a string of the animation info
   */
  @Override
  public String toString() {
    // For example:
    // Shape A changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0)
    return "Shape " + this.name + " changes color from (" + this.beforeRed
            + "," + this.beforeGreen + "," + this.beforeBlue
            + ") to (" + this.afterRed + "," + this.afterGreen + "," + this.afterBlue
            + ")";
  }



}
