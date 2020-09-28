package cs5004.animator.view;

import java.io.FileNotFoundException;


import cs5004.animator.model.IShape;

/**
 * This is an IView interface. It contains render(for textual view), getViewType,
 * draw(for animation view) and refresh(which will call the repaint()).
 */
public interface IView {

  /**
   * Render the model state to the output file or System.out.
   * @throws FileNotFoundException if cannot find the file
   */
  void render() throws FileNotFoundException;

  /**
   * Return the view type.
   * @return the view type
   */
  String getViewType();

  /**
   * Draw the current shape.
   * @param shape the given shape being drawn
   */
  void draw(IShape shape);

  /**
   * Refresh the canvas.
   */
  void refresh();

  /**
   * Return a textual description of the animation with real time.
   * @return a textual description of the animation with real time
   */
  String createOutputTxt();

}
