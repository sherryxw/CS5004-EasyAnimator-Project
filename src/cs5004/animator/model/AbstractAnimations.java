package cs5004.animator.model;


import java.util.Map;

/**
 * This is an abstract class of Animation, it implements all the functions mandated by IAnimation.
 * This class is extended by Move, Color, Scale.
 */
public class AbstractAnimations implements IAnimation {
  protected int startTime;
  protected int endTime;
  protected String name;
  protected AnimationsType animationsType;
  protected Map shapeList;

  /**
   * Constructor an AbstractAnimations. It takes shapeList, animationsType, shape name,
   * animation startTime, animation endTime as parameters.
   * @param shapeList the map of all the shapes
   * @param animationsType the animation's type
   * @param name the name of the shape that requests animation
   * @param startTime the start time of this animation
   * @param endTime the end time of this animation
   * @throws IllegalArgumentException if input is invalid or out of range
   */
  protected AbstractAnimations(Map shapeList, AnimationsType animationsType, String name,
                            int startTime, int endTime)
          throws IllegalArgumentException {
    if (shapeList == null || animationsType == null || name.equals("") || startTime < 0
            || endTime < 0) {
      throw new IllegalArgumentException("Invalid input, cannot create animation");
    }
    this.shapeList = shapeList;
    this.animationsType = animationsType;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Return the name of the shape request for animation.
   * @return the name of the shape request for animation.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Return the type of this animation.
   * @return the type of this animation.
   */
  @Override
  public AnimationsType getType() {
    return this.animationsType;
  }

  /**
   * Return the start time of this animation.
   * @return the start time of this animation
   */
  @Override
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Return the start time of this animation.
   * @return the end time of this animation
   */
  @Override
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Perform the animation.
   */
  @Override
  public void performAction(int frame) throws IllegalArgumentException {
    return;
  }

  /**
   * Tween helper, return the value in current frame.
   * @param start start value
   * @param end end value
   * @param startTime the animation start time
   * @param endTime the animation end time
   * @param frame the current frame
   * @return the value in current frame
   */
  @Override
  public double tweenHelper(double start, double end, int startTime, int endTime, double frame) {
    return (start * (endTime - frame) / (endTime - startTime))
            + (end * (frame - startTime) / (endTime - startTime));
  }

}
