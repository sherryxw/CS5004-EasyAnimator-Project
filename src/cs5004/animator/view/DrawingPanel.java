package cs5004.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import cs5004.animator.model.IShape;

/**
 * This is a drawingPanel class which extends JPanel and implements IDrawingPanel.
 */
public class DrawingPanel extends JPanel implements IDrawingPanel {
  private final List<IShape> shapes = new ArrayList<>();

  /**
   * Construct a drawingPanel by given width and height.
   * @param width the given width of the canvas
   * @param height the given height of the canvas
   */
  public DrawingPanel(int width, int height) {
    super();
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(width,height)); //bound model width height

  }

  /**
   * Add the shape to the list, and draw the shape.
   * @param shape the shaped being drawn
   */
  @Override   //animationView call this
  public void drawShapes(IShape shape) {
    shapes.add(shape);
  }

  /**
   * preps our shapes to be painted a specific color.
   *
   * @param g the graphic we are going to be rendering.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (this.shapes != null) {
      for (IShape shape : this.shapes) {  //list
        if (shape != null) {

          Color newColor = new Color((int)shape.getRed(),
                  (int)shape.getGreen() , (int)shape.getBlue() );

          switch (shape.getType().toString()) {
            case "Rectangle":
              g.setColor(newColor);
              g.fillRect((int)shape.getPosition().getX(), (int)shape.getPosition().getY(),
                      (int)shape.getWidth(), (int)shape.getHeight());
              break;
            case "Ellipse":
              g.setColor(newColor);
              g.fillOval((int)shape.getPosition().getX(), (int)shape.getPosition().getY(),
                      (int)shape.getWidth(), (int)shape.getHeight());
              break;
            default:
              throw new IllegalArgumentException("Doesn't support this kind of shape right now");
          }
        }

      }

    }
  }




}


