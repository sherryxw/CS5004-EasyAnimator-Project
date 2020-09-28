import cs5004.animator.model.IShape;
import cs5004.animator.model.Ellipse;
import cs5004.animator.model.Position2D;
import cs5004.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * This is a test for IShape interface. It tests all the functions in IShape interface.
 */
public class IShapeTest {
  private IShape rectangle;
  private IShape oval;

  @org.junit.Before
  public void setUp() throws Exception {
    rectangle = new Rectangle("R", new Position2D(200,200), 50,100,
            1.0, 0.0,0.0 , 10, 50);
    oval = new Ellipse("C", new Position2D(500,100), 60,30,0.0,
            0.0,1.0, 0 ,100);
  }

  @org.junit.Test
  public void testToString() {
    assertEquals("Name: R\n" +
            "Type: rectangle\n" +
            "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n",
            rectangle.toString());

    assertEquals("Name: C\n" +
            "Type: Ellipse\n" +
            "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n",
            oval.toString());
  }

  @org.junit.Test
  public void testGetAppears() {
    assertEquals(10, rectangle.getAppears());
    assertEquals(0, oval.getAppears());
  }


  @org.junit.Test
  public void testGetDisappears() {
    assertEquals(50, rectangle.getDisappears());
    assertEquals(100, oval.getDisappears());
  }

  @org.junit.Test
  public void testGetName() {
    assertEquals("R", rectangle.getName());
    assertEquals("C", oval.getName());
  }

  @org.junit.Test
  public void testGetPosition() {
    assertEquals(new Position2D(200,200).toString(), rectangle.getPosition().toString());
    assertEquals(new Position2D(500,100).toString(), oval.getPosition().toString());
  }

  @org.junit.Test
  public void testGetWidth() {
    assertEquals(50, rectangle.getWidth(), 0.01);
    assertEquals(60, oval.getWidth(), 0.01);
  }

  @org.junit.Test
  public void testGetHeight() {
    assertEquals(100, rectangle.getHeight(), 0.01);
    assertEquals(30, oval.getHeight(), 0.01);
  }

  @org.junit.Test
  public void testGetRed() {
    assertEquals(1.0, rectangle.getRed(), 0.01);
    assertEquals(0.0, oval.getRed(), 0.01);
  }

  @org.junit.Test
  public void testGetGreen() {
    assertEquals(0.0, rectangle.getGreen(), 0.01);
    assertEquals(0.0, oval.getGreen(), 0.01);
  }

  @org.junit.Test
  public void testGetBlue() {
    assertEquals(0.0, rectangle.getBlue(), 0.01);
    assertEquals(1.0, oval.getBlue(), 0.01);
  }

  @org.junit.Test
  public void testSetRed() {
    rectangle.setRed(5.0);
    oval.setRed(5.0);
    assertEquals(5.0, rectangle.getRed(), 0.01);
    assertEquals(5.0, oval.getRed(), 0.01);
  }

  @org.junit.Test
  public void testSetG() {
    rectangle.setGreen(5.0);
    oval.setGreen(5.0);
    assertEquals(5.0, rectangle.getGreen(), 0.01);
    assertEquals(5.0, oval.getGreen(), 0.01);
  }

  @org.junit.Test
  public void testSetB() {
    rectangle.setBlue(5.0);
    oval.setBlue(5.0);
    assertEquals(5.0, rectangle.getBlue(), 0.01);
    assertEquals(5.0, oval.getBlue(), 0.01);
  }

  @org.junit.Test
  public void testSetW() {
    rectangle.setWidth(5.0);
    oval.setWidth(5.0);
    assertEquals(5.0, rectangle.getWidth(), 0.01);
    assertEquals(5.0, oval.getWidth(), 0.01);
  }


  @org.junit.Test
  public void testSetH() {
    rectangle.setHeight(5.0);
    oval.setHeight(5.0);
    assertEquals(5.0, rectangle.getHeight(), 0.01);
    assertEquals(5.0, oval.getHeight(), 0.01);
  }


  @org.junit.Test
  public void testSetPosition() {
    rectangle.setPosition2D(0,0);
    oval.setPosition2D(0,0);
    assertEquals(new Position2D(0,0).toString(), rectangle.getPosition().toString());
    assertEquals(new Position2D(0,0).toString(), oval.getPosition().toString());
  }





}
