
import org.junit.Before;
import org.junit.Test;
import cs5004.animator.model.IModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ShapeType;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * This is a test for textual view class. Need to check whether the output shows correct shapes
 * and motions in real time (not frame).
 */
public class TextualViewTest {
  private IView view;
  private IModel model;


  @Before
  public void setUp() throws Exception {
    model = new ModelImpl();
    model.addShape("R", ShapeType.RECTANGLE, 0,0,100,100,
            20,20,0,100,100);
    String out = "";
    int speed = 20;
    view = new TextualView(model, out, speed);
  }

  @Test
  public void testRender0() {
    //actual time, not tick, speed = 20
    assertEquals("Shapes: \n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 100.0, Height: 100.0, Color: (20.0,20.0,0.0)\n" +
            "Appears at t=5, Disappears at t=5\n", view.createOutputTxt());

  }

  @Test
  public void testRender1() {
    //actual time, not tick, speed = 10
    int speed = 10;
    view = new TextualView(model, "", speed);
    assertEquals("Shapes: \n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 100.0, Height: 100.0, Color: (20.0,20.0,0.0)\n"
                    + "Appears at t=10, Disappears at t=10\n",
            view.createOutputTxt());

  }

  @Test
  public void testRender2() {
    //actual time, not tick
    model.move("R", 3,10,0,0,200,200);
    assertEquals("Shapes: \n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 100.0, Height: 100.0, Color: (20.0,20.0,0.0)\n" +
            "Appears at t=5, Disappears at t=5\n" +
            "Shape R moves from (0.0,0.0) to (200.0,200.0), from time t=0 to t=0\n",
            view.createOutputTxt());

  }




}