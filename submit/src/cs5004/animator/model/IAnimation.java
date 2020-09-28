package cs5004.animator.model;

/**
 * This interface represents the Animations. Includes 5 functions to getName,
 * getType of the animations, getStartTime of the animation, getEndTime of the animation and
 * perform the animation.
 */
public interface IAnimation {

  /**
   * Return the name of this animation.
   * @return the name of this animation
   */
  String getName();

  /**
   * Return the type of this animation.
   * @return the type of this animation
   */
  AnimationsType getType();

  /**
   * Return the start time of this animation.
   * @return the start time of this animation
   */
  int getStartTime();

  /**
   * Return the end time of this animation.
   * @return Return the start time of this animation
   */
  int getEndTime();

  /**
   * Perform this animation.
   */
  void performAction(int frame) ;

  /**
   * Tween helper, return the value in current frame.
   * @param start start value
   * @param end end value
   * @param startTime the animation start time
   * @param endTime the animation end time
   * @param frame the current frame
   * @return
   */
  double tweenHelper(double start, double end, int startTime, int endTime, double frame);

}
