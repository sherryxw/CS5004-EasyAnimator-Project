package cs5004.animator.model;

/**
 * This is an abstract class for Shapes. This class will be extended by Rectangle and Oval.
 * This abstract class implements all the functions defined in IShape.
 */
public class AbstractShape implements IShape {
  protected String name;
  protected Position2D position2D;
  protected double width;
  protected double height;
  protected double red;
  protected double green;
  protected double blue;
  protected int appearsT;
  protected int disappearsT;

  /**
   * Construct an abstractShape. Take name, position, width, height, red, green, blue, appearsTime,
   * disappearsTime as parameters.
   * @param name the name of this shape
   * @param position2D the current position of this shape
   * @param width the current width of this shape
   * @param height the current height of this shape
   * @param red the current red intensity for this shape's color
   * @param green the current green intensity for this shape's color
   * @param blue the current blue intensity for this shape's color
   * @param appearsT the shape appearance time
   * @param disappearsT the shape disappearance time
   * @throws IllegalArgumentException if the input is invalid or out of range
   */
  protected AbstractShape(String name, Position2D position2D, double width, double height,
                          double red, double green, double blue, int appearsT, int disappearsT)
          throws IllegalArgumentException {

    if (position2D == null || name.equals("") || width < 0 || height < 0 || red < 0
            || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Input data is invalid, cannot build a shape");
    }

    this.name = name;
    this.position2D = position2D;
    this.width = width;
    this.height = height;
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.appearsT = appearsT;
    this.disappearsT = disappearsT;
  }

  /**
   * Return this shape name.
   * @return this shape name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Return the current position of the shape.
   * @return the current position of the shape
   */
  @Override
  public Position2D getPosition() {
    return this.position2D;
  }


  /**
   * Return the width of the shape.
   * @return the width of the shape
   */
  @Override
  public double getWidth() {
    return this.width;
  }


  /**
   * Return the height of the shape.
   * @return the height of the shape
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Return the red portion of this shape's color.
   * @return the red portion of this shape's color
   */
  @Override
  public double getRed() {
    return this.red;
  }

  /**
   * Return the blue portion of this shape's color.
   * @return the blue portion of this shape's color
   */
  @Override
  public double getBlue() {
    return this.blue;
  }

  /**
   * Return the green portion of this shape's color.
   * @return the green portion of this shape's color
   */
  @Override
  public double getGreen() {
    return this.green;
  }

  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }

  /**
   * Return this shape appearance time.
   * @return this shape appearance time
   */
  @Override
  public int getAppears() {
    return this.appearsT;
  }

  /**
   * Return this shape disappearance time.
   * @return this shape disappearance time
   */
  @Override
  public int getDisappears() {
    return this.disappearsT;
  }

  /**
   * Update the red intensity of this shape color.
   * @param newRed the updated red
   */
  public void setRed(double newRed) {
    this.red = newRed;
  }

  /**
   * Update the green intensity of this shape color.
   * @param newGreen the updated green
   */
  public void setGreen(double newGreen) {
    this.green = newGreen;
  }

  /**
   * Update the blue intensity of this shape color.
   * @param newBlue the updated blue
   */
  public void setBlue(double newBlue) {
    this.blue = newBlue;
  }

  /**
   * Update the width of this shape.
   * @param newWidth the updated width
   */
  public void setWidth(double newWidth) {
    this.width = newWidth;
  }

  /**
   * Update the height of this shape.
   * @param newHeight the updated height
   */
  public void setHeight(double newHeight) {
    this.height = newHeight;
  }

  /**
   * Update the position of this shape.
   * @param newX the new x-coordinates of this shape
   * @param newY the new y-coordinates of this shape
   */
  public void setPosition2D(double newX, double newY) {
    this.position2D.setPosition(newX, newY);
  }

  /**
   * Set the appearance time.
   * @param t the appearance time
   */
  public void setAppearance(int t) {
    this.appearsT = t;
  }

  /**
   * Set the disappearance time.
   * @param t the disappearance time
   */
  public void setDisappearance(int t) {
    this.disappearsT = t;
  }

}
