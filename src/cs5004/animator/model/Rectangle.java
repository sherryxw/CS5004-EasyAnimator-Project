package cs5004.animator.model;

/**
 * This class represents the Rectangle shape type. It extends abstractShape class.
 * It contains name, position, width, height, RGB color, appearance time, disappearance time as
 * parameters.
 */
public class Rectangle extends AbstractShape {

  /**
   * Construct a rectangle shape. It includes name, position, width, height, RGB color,
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
  public Rectangle(String name, Position2D position2D, double width, double height, double red,
                   double green, double blue, int appearsT, int disappearsT) {
    super(name, position2D, width, height, red, green, blue, appearsT, disappearsT);
  }

  /**
   * Return a string of the shape info.
   * @return a string of the shape info
   */
  @Override
  public String toString() {
    // For example:
    // Name: R
    // Type: rectangle
    // Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
    return "Name: " + this.getName() + "\nType: rectangle\nMin corner: (" + this.position2D.getX()
            + "," + this.position2D.getY() + "), Width: " + this.getWidth() + ", Height: "
            + this.getHeight() + ", Color: (" + this.getRed() + "," + this.getGreen() + ","
            + this.getBlue() + ")\n";
  }


  /**
   * Return the shape type.
   * @return the shape type
   */
  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }



}
