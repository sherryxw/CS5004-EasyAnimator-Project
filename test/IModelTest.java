import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.IModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.ShapeType;

import static org.junit.Assert.assertEquals;


/**
 * This is a test for IModel interface. It tests all the functions in IModel interfaces.
 */
public class IModelTest {
  private IModel model;

  @Before
  public void setUp() throws Exception {
    model = new ModelImpl();
    model.addShape("A", ShapeType.RECTANGLE, 0,0, 10, 20,
            1.0, 1.0, 1.0, 0,100);
    model.addShape("B", ShapeType.ELLIPSE, 0,0, 10, 20,
            1.0, 1.0, 1.0, 0,100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetUp() {
    //invalid width
    model.addShape("C", ShapeType.RECTANGLE, 10,10,-1,10,10,10,
            10,10,10);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetUp2() {
    //invalid height
    model.addShape("C", ShapeType.RECTANGLE, 1,1,1,-1,10,10,
            10,10,10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetUp3() {
    //invalid RGB
    model.addShape("C", ShapeType.RECTANGLE, 1,1,1,1,300,10,
            10,10,10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetUp4() {
    //invalid RGB
    model.addShape("C", ShapeType.RECTANGLE, 1,1,1,1,-200,10,
            10,10,10);
  }




  @Test
  public void getShape() {
    assertEquals("Name: A\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n",
            model.getIShape("A").toString());
    assertEquals("Name: B\n" +
            "Type: Ellipse\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n",
            model.getIShape("B").toString());
  }

  @Test
  public void getShapeList() {
    assertEquals("{A=Name: A\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
            ", B=Name: B\n" +
            "Type: Ellipse\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
            "}", model.getShapeList().toString());
  }


  @Test
  public void getAnimationList() {

    assertEquals("{}", model.getCurrAnimationList().toString());
  }

  @Test
  public void getSortedList() {
    model.changeColor("A", 3,6,1,1,
            1,20,20,20);
    model.scalePerform("A", 20,60,10,20,
            30,30);
    model.changeColor("B", 100,200,1,1,1,
            10,10,10);
    model.move("B", 15,45,0,0,100,100);
    assertEquals("[Shape A changes color from (1.0,1.0,1.0) to (20.0,20.0,20.0), " +
            "Shape B moves from (0.0,0.0) to (100.0,100.0), Shape A scales from Width: 10.0, " +
            "Height: 20.0 to Width: 30.0, Height: 30.0, Shape B changes color from (1.0,1.0,1.0) " +
            "to (10.0,10.0,10.0)]", model.getSortedList().toString());
  }


  @Test
  public void changeColor() {
    model.changeColor("A", 10,20, 1.0, 1.0, 1.0,
            20,20,20);
    assertEquals(1, model.getCurrAnimationList().size());
    assertEquals(1, model.getSortedList().size());
    model.changeColor("A", 0,5, 20,20,20,
            30,30,30);
    assertEquals("[Shape A changes color from (1.0,1.0,1.0) to (20.0,20.0,20.0), " +
            "Shape A changes color from (20.0,20.0,20.0) to (30.0,30.0,30.0)]",
            model.getCurrAnimationList().get("A").toString());
    assertEquals("Shapes: \n" +
                    "Name: A\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Name: B\n" +
                    "Type: Ellipse\n" +
                    "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Shape A changes color from (20.0,20.0,20.0) to (30.0,30.0,30.0), " +
                    "from time t=0 to t=5Shape A changes color from (1.0,1.0,1.0) to " +
                    "(20.0,20.0,20.0), from time t=10 to t=20", model.modelState());
    //conflict, cannot be added to animation list, no change for animation list
    model.changeColor("A", 10, 15, 3.0,3.0,3.0,
            0,0,0);
    assertEquals("Shapes: \n" +
                    "Name: A\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Name: B\n" +
                    "Type: Ellipse\n" +
                    "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Shape A changes color from (20.0,20.0,20.0) to (30.0,30.0,30.0), " +
                    "from time t=0 to t=5Shape A changes color from (1.0,1.0,1.0) to " +
                    "(20.0,20.0,20.0), from time t=10 to t=20", model.modelState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColor2() {
    //test cannot find "R"
    model.changeColor("R", 500,500,0,0,
            0,2.0,2.0,2.0);
  }

  @Test
  public void deleteShape() {
    model.move("A", 10,30,0,0,100,100);
    model.changeColor("A", 40,80,0,0,
            0,10,10,10);
    assertEquals("Shapes: \n" +
                    "Name: A\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Name: B\n" +
                    "Type: Ellipse\n" +
                    "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Shape A moves from (0.0,0.0) to (100.0,100.0), from time t=10 to " +
                    "t=30Shape A changes color from (0.0,0.0,0.0) to (10.0,10.0,10.0), " +
                    "from time t=40 to t=80", model.modelState());

    model.removeShape("A", ShapeType.RECTANGLE, 50);
    //change color should be delete
    assertEquals("Shapes: \n" +
                    "Name: B\n" +
                    "Type: Ellipse\n" +
                    "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Shape A moves from (0.0,0.0) to (100.0,100.0), from time t=10 to t=30",
            model.modelState());
  }

  @Test
  public void testDelete() {
    //after delete, cannot find this shape
    model.removeShape("A", ShapeType.RECTANGLE, 0);
    assertEquals("{B=Name: B\n" +
            "Type: Ellipse\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
            "}", model.getShapeList().toString());
    model.getCurrAnimationList().get("A"); //null
  }

  @Test
  public void scalePerform() {
    model.scalePerform("B", 40,50,5,5,
            10,10);
    model.scalePerform("A", 10,20,10,10,
            10,10);
    model.scalePerform("A", 15,30,20,
            20,30,30); //conflict one,cannot be add
    assertEquals("Shapes: \n" +
            "Name: A\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
            "Appears at t=0, Disappears at t=100\n" +
            "Name: B\n" +
            "Type: Ellipse\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
            "Appears at t=0, Disappears at t=100\n" +
            "Shape A scales from Width: 10.0, Height: 10.0 to Width: 10.0, Height: 10.0, " +
            "from time t=10 to t=20Shape B scales from Width: 5.0, Height: 5.0 to Width: 10.0," +
            " Height: 10.0, from time t=40 to t=50", model.modelState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScale2() {
    //test cannot find "R"
    model.scalePerform("R", 500,500,100,100,10,10);
  }


  @Test
  public void move() {
    model.move("A", 10,20, 5,5,10,10);
    model.move("B", 20,40,10,10,10,34);
    model.move("B", 20,30,10,10,100,100); //conflict one ,cannot be added
    assertEquals("Shapes: \n" +
                    "Name: A\n" +
                    "Type: rectangle\n" +
                    "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Name: B\n" +
                    "Type: Ellipse\n" +
                    "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
                    "Appears at t=0, Disappears at t=100\n" +
                    "Shape A moves from (5.0,5.0) to (10.0,10.0), from time t=10 to " +
                    "t=20Shape B moves from (10.0,10.0) to (10.0,34.0), from time t=20 to t=40",
            model.modelState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove2() {
    //test cannot find "R"
    model.move("R", 500,500,100,100,50,50);
  }


  @Test
  public void testState() {
    //sorted by start time
    model.move("A", 0,5, 50, 60, 0,0);
    model.scalePerform("B", 40,50,5,50,
            1,1);
    model.changeColor("A", 10,20, 2.0, 2.0,
            2.0,20,20,20);
    assertEquals("Shapes: \n" +
            "Name: A\n" +
            "Type: rectangle\n" +
            "Min corner: (0.0,0.0), Width: 10.0, Height: 20.0, Color: (1.0,1.0,1.0)\n" +
            "Appears at t=0, Disappears at t=100\n" +
            "Name: B\n" +
            "Type: Ellipse\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 20.0, Color: (1.0,1.0,1.0)\n" +
            "Appears at t=0, Disappears at t=100\n" +
            "Shape A moves from (50.0,60.0) to (0.0,0.0), from time t=0 to t=5Shape A " +
            "changes color from (2.0,2.0,2.0) to (20.0,20.0,20.0), from time t=10 to t=20Shape " +
            "B scales from Width: 5.0, Height: 50.0 to Width: 1.0, Height: 1.0, from time " +
            "t=40 to t=50", model.modelState());
  }
}