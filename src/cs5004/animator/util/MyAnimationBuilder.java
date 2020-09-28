package cs5004.animator.util;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

/**
 * This is MyAnimationBuilder which implements AnimationBuilder. It contains all the
 * functions defined in AnimationBuilder interface.
 */
public class MyAnimationBuilder implements AnimationBuilder<IModel> {
  private final IModel model;

  /**
   * Construct a MyAnimationBuilder.
   * @param model the given model
   */
  public MyAnimationBuilder(IModel model) {
    this.model = model;
  }

  /**
   * Constructs a final document.
   *
   * @return the newly constructed document
   */
  @Override
  public IModel build() {
    return this.model;
  }

  /**
   * Specify the bounding box to be used for the animation.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the bounding box
   * @param height The height of the bounding box
   * @return This {@link AnimationBuilder}
   */
  @Override
  public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
    this.model.setAnimationBoundsW(width);
    this.model.setAnimationBoundsH(height);
    return this;
  }

  /**
   * Adds a new shape to the growing document.
   *
   * @param name The unique name of the shape to be added. No shape with this name should already
   *             exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
   *             shapes is unspecified, but should include "ellipse" and "rectangle" as a minimum.
   * @return This {@link AnimationBuilder}
   */
  @Override
  public AnimationBuilder<IModel> declareShape(String name, String type) {

    if (type.equalsIgnoreCase("rectangle")) {
      model.addShape(name, ShapeType.RECTANGLE, 0,0,0,0,0,0,
              0,0,0);
    }
    else if (type.equalsIgnoreCase("ellipse")) {
      model.addShape(name, ShapeType.ELLIPSE, 0,0,0,0,0,0,0,
              0,0);
    }

    return this;
  }

  /**
   * Adds a transformation to the growing document.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return This {@link AnimationBuilder}
   */
  @Override
  public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                            int r1, int g1, int b1, int t2, int x2, int y2, int w2,
                                            int h2, int r2, int g2, int b2) {
    if (model.getShapeList().get(name) != null) {
      IShape shape = (IShape) model.getShapeList().get(name);
      //haven't set up before
      if (shape.getAppears() == 0 && shape.getDisappears() == 0) {
        shape.setRed(r1);
        shape.setGreen(g1);
        shape.setBlue(b1);
        shape.setHeight(h1);
        shape.setWidth(w1);
        shape.setPosition2D(x1, y1);
        shape.setAppearance(t1);
        shape.setDisappearance(t2);
      }
      if (t2 > shape.getDisappears()) {
        shape.setDisappearance(t2);
      }

      //add motion
      //move
      if (x1 != x2 || y1 != y2) {
        model.move(name, t1, t2, x1, y1, x2, y2);
      }
      //scale change
      else if (w1 != w2 || h1 != h2) {
        model.scalePerform(name, t1, t2, w1, h1, w2, h2);
      }
      //color change
      else if (r1 != r2 || g1 != g2 || b1 != b2) {
        model.changeColor(name, t1, t2, r1, g1, b1, r2, g2, b2);
      }
    }
    return this;
  }
}
