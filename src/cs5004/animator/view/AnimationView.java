package cs5004.animator.view;

import java.io.FileNotFoundException;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import cs5004.animator.model.IShape;

/**
 * This class represents an AnimationView. It extends from JFrame and implements IView.
 * It public an animationView with the model and tickPerSec and related bound value.
 * It has render function which transmit the shape to the panel.
 */
public class AnimationView extends JFrame implements IView {

  private final DrawingPanel panel;
  private final String viewType;


  /**
   * Construct an AnimationView. Set related initial value, set scrollPane.
   * @param w the bound - width
   * @param h the bound - height
   */
  public AnimationView(int w, int h) {
    super();
    this.viewType = "animation";

    panel = new DrawingPanel(w,h);  //canvas
    add(panel);

    //current window size
    setSize(w,h);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //Decorator Pattern
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setVisible(true);

    add(scrollPane);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }
  

  /**
   * Nothing to do in the animation view.
   * @throws FileNotFoundException if cannot find the file
   */
  @Override
  public void render() throws FileNotFoundException {
    //Nothing to do in the animation view.
  }

  /**
   * Return the view type.
   * @return the view type
   */
  @Override
  public String getViewType() {
    return this.viewType;
  }

  /**
   * Draw the current shape.
   * @param shape the given shape being drawn
   */
  @Override
  public void draw(IShape shape) {

    panel.drawShapes(shape);
  }

  /**
   * Refresh the canvas.
   */
  @Override
  public void refresh() {
    panel.repaint();
  }

}
