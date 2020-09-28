import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import cs5004.animator.model.AnimationsType;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Move;
import cs5004.animator.model.Position2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Scale;

import static org.junit.Assert.assertEquals;

/**
 * This is a test for IAnimation interface. It tests all the functions in IAnimation interfaces.
 */
public class IAnimationTest {
  private IAnimation move;
  private IAnimation scale;
  private IAnimation color;
  private Map shapeList;

  @Before
  public void setUp() throws Exception {
    shapeList = new HashMap<String, IShape>();
    move = new Move(shapeList, "R", 0, 10, 0,0,100,100);
    color = new ChangeColor(shapeList, "L", 20,40,1.0,
            1.0,1.0,2.0,2.0,2.0);
    scale = new Scale(shapeList, "Q", 30,50, 100,
            200,10,20);
  }

  @Test
  public void getName() {
    assertEquals("R", move.getName());
    assertEquals("L", color.getName());
    assertEquals("Q", scale.getName());
  }

  @Test
  public void getType() {
    Assert.assertEquals(AnimationsType.MOVE, move.getType());
    assertEquals(AnimationsType.SCALE, scale.getType());
    assertEquals(AnimationsType.COLOR, color.getType());
  }

  @Test
  public void getStartTime() {
    assertEquals(0, move.getStartTime());
    assertEquals(20, color.getStartTime());
    assertEquals(30, scale.getStartTime());
  }

  @Test
  public void getEndTime() {
    assertEquals(10, move.getEndTime());
    assertEquals(40, color.getEndTime());
    assertEquals(50, scale.getEndTime());
  }

  @Test
  public void performAction1() {
    //move = new Move(shapeList, "R", 0, 10, 0,0,100,100);
    //success move from (0,0) to (100,100)
    IShape shapeR = new Rectangle("R", new Position2D(0,0), 10,20,
            0.1,0.1,0.1, 0, 100);
    shapeList.put("R", shapeR);
    move.performAction(5);
    assertEquals(50, shapeR.getPosition().getX(), 0.01);
    assertEquals(50, shapeR.getPosition().getY(), 0.01);
    move.performAction(2);
    assertEquals(20, shapeR.getPosition().getX(), 0.01);
    assertEquals(20, shapeR.getPosition().getY(), 0.01);
  }



  @Test
  public void performAction2() {
    //color = new Color(shapeList, "L", 20,40,1.0,
    //            1.0,1.0,2.0,2.0,2.0);
    IShape shapeL = new Rectangle("L", new Position2D(0,0), 10,20,
            1.0,1.0,1.0, 0, 100);
    shapeList.put("L", shapeL);
    color.performAction(1);
    assertEquals(0.05, shapeL.getRed(), 0.01);
    assertEquals(0.05, shapeL.getGreen(), 0.01);
    assertEquals(0.05, shapeL.getBlue(), 0.01);
    color.performAction(2);
    assertEquals(0.1, shapeL.getRed(), 0.01);
    assertEquals(0.1, shapeL.getGreen(), 0.01);
    assertEquals(0.1, shapeL.getBlue(), 0.01);
  }

  @Test
  public void performAction3() {
    //scale = new Scale(shapeList, "Q", 30,50, 100,
    //            200,10,20);
    IShape shapeQ = new Rectangle("Q", new Position2D(0,0), 100,200,
            1.0,1.0,1.0, 0, 100);
    shapeList.put("Q", shapeQ);
    scale.performAction(1);
    assertEquals(461, shapeQ.getHeight(), 0.01);
    assertEquals(230.5, shapeQ.getWidth(), 0.01);
    scale.performAction(10);
    assertEquals(380, shapeQ.getHeight(), 0.01);
    assertEquals(190, shapeQ.getWidth(), 0.01);

  }


  @Test
  public void testToString() {
    assertEquals("Shape R moves from (0.0,0.0) to (100.0,100.0)",
            move.toString());
    assertEquals("Shape L changes color from (1.0,1.0,1.0) to (2.0,2.0,2.0)",
            color.toString());
    assertEquals("Shape Q scales from Width: 100.0, Height: 200.0 to Width: 10.0, " +
            "Height: 20.0", scale.toString());
  }
}