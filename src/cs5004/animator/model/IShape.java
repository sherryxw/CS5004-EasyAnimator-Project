package cs5004.animator.model;

/**
 * This interface represents the Shape methods offered.
 * Includes 7 get functions that return this shape appearsTime, disappearsTime, name, position,
 * width, height, red, green, blue.
 * Includes 6 set functions that return updated position, width, height, red, green, blue.
 */
public interface IShape {

  /**
   * Return the shape type.
   * @return the shape type
   */
  ShapeType getType();

  /**
   * Return this shape appearance time.
   * @return this shape appearance time
   */
  int getAppears();

  /**
   * Return this shape disappearance time.
   * @return this shape disappearance time
   */
  int getDisappears();

  /**
   * Return this shape name.
   * @return this shape name
   */
  String getName();

  /**
   * Return the current position of the shape.
   * @return the current position of the shape
   */
  Position2D getPosition();

  /**
   * Return the width of the shape.
   * @return the width of the shape
   */
  double getWidth();

  /**
   * Return the height of the shape.
   * @return the height of the shape
   */
  double getHeight();

  /**
   * Return the red portion of this shape's color.
   * @return the red portion of this shape's color
   */
  double getRed();

  /**
   * Return the blue portion of this shape's color.
   * @return the blue portion of this shape's color
   */
  double getBlue();

  /**
   * Return the green portion of this shape's color.
   * @return the green portion of this shape's color
   */
  double getGreen();

  /**
   * Update the red intensity of this shape color.
   * @param newRed the updated red
   */
  void setRed(double newRed);

  /**
   * Update the green intensity of this shape color.
   * @param newGreen the updated green
   */
  void setGreen(double newGreen);

  /**
   * Update the blue intensity of this shape color.
   * @param newBlue the updated blue
   */
  void setBlue(double newBlue);

  /**
   * Update the width of this shape.
   * @param newWidth the updated width
   */
  void setWidth(double newWidth);

  /**
   * Update the height of this shape.
   * @param newHeight the updated height
   */
  void setHeight(double newHeight);

  /**
   * Update the position of this shape.
   * @param newX the new x-coordinates of this shape
   * @param newY the new y-coordinates of this shape
   */
  void setPosition2D(double newX, double newY);

  /**
   * Set the shape appearance time.
   * @param t the appearance time
   */
  void setAppearance(int t);

  /**
   * Set the shape disappearance time.
   * @param t the disappearance time
   */
  void setDisappearance(int t);


}
