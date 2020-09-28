package cs5004.animator.model;

/**
 * This class represents the Ellipse shape type. It extends abstractShape class.
 * It contains name, position, width, height, RGB color, appearance time, disappearance time as
 * parameters.
 */
public class Ellipse extends AbstractShape {

  /**
   * Construct an Ellipse shape. It includes name, position, width, height, RGB color,
   * appearance time, disappearance time.
   * @param name the name of this shape
   * @param position2D the position of this shape
   * @param width the width of this shape
   * @param height the height of this shape
   * @param red the current red intensity for this shape's color
   * @param green the current green intensity for this shape's color
   * @param blue the current blue intensity for this shape's color
   * @param appearsT the shape appearance time
   * @param disappearsT the shape disappearance time
   */
  public Ellipse(String name, Position2D position2D, double width, double height, double red,
                 double green, double blue, int appearsT, int disappearsT) {
    super(name, position2D, width, height, red, green, blue, appearsT, disappearsT);
  }

  /**
   * Return a string of the shape info.
   * @return a string of the shape info
   */
  @Override
  public String toString() {
    //For example:
    //Name: C
    //Type: Ellipse
    //Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
    return "Name: " + this.getName() + "\nType: Ellipse\nCenter: (" + this.position2D.getX()
            + "," + this.position2D.getY() + "), X radius: " + this.getWidth() + ", Y radius: "
            + this.getHeight() + ", Color: (" + this.getRed() + "," + this.getGreen() + ","
            + this.getBlue() + ")\n";
  }


  /**
   * Return the shape type.
   * @return the shape type
   */
  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }

}
