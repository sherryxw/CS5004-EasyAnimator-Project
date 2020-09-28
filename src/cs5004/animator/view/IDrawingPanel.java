package cs5004.animator.view;


import cs5004.animator.model.IShape;

/**
 * This is an IDrawingPanel interface. It contains a drawShapes function.
 */
public interface IDrawingPanel {

  /**
   * Draw the current shape.
   * @param shape the shaped being drawn
   */
  void drawShapes(IShape shape);
}
