package cs5004.animator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This interface represents an animationModel. It contains 4 get functions that get shape,
 * get shapeList, getSortedList, getCurrAnimationList.
 * It also contains changeColor, addShape, deleteShape, scalePerform, move and modelState functions.
 */
public interface IModel {

  /**
   * Return the IShape object of the name.
   * @param name the name of the shape
   * @return the IShape object of the name
   */
  IShape getIShape(String name);

  /**
   * Return a copy of shapeList.
   * @return the copy of shapeList
   */
  Map getShapeList();

  /**
   * Return the copy of sorted animation list.
   * @return the copy of sorted animation list
   */
  ArrayList getSortedList();

  /**
   * Return the copy of list of each shape's animation.
   * @return the copy of list of each shape's animation.
   */
  Map getCurrAnimationList();

  /**
   * Add a new shape to the model.
   * @param name the name of the shape
   * @param type  the shape type
   * @param x the x-coordinate of the shape
   * @param y the y-coordinate of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param red the red intensity of this shape
   * @param green the green intensity of this shape
   * @param blue the blue intensity of this shape
   * @param startTime the start time of this animation
   * @param endTime the end time of this animation
   * @throws IllegalArgumentException if cannot add new shape to the model
   */
  void addShape(String name, ShapeType type, double x, double y, double width, double height,
                double red, double green, double blue, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * Delete a shape from this model.
   * @param name the name of the shape
   * @param type the type of the shape
   * @param deleteTime the delete Time of the shape
   * @throws IllegalArgumentException if input is invalid
   */
  void removeShape(String name, ShapeType type, int deleteTime) throws IllegalArgumentException;

  /**
   * Create a change color animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param beforeR the afterR intensity of this shape
   * @param beforeG the afterG intensity of this shape
   * @param beforeB the afterB intensity of this shape
   * @param afterR the afterR intensity of this shape
   * @param afterG the afterG intensity of this shape
   * @param afterB the afterB intensity of this shape
   * @throws IllegalArgumentException if cannot find the shape
   */
  void changeColor(String name, int timeStart, int timeEnd, double beforeR, double beforeG,
                   double beforeB, double afterR, double afterG, double afterB)
          throws IllegalArgumentException;

  /**
   * Create a scale animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param fromW the afterWidth of the shape
   * @param fromH the afterHeight of the shape
   * @param afterWidth the afterWidth of the shape
   * @param afterHeight the afterHeight of the shape
   * @throws IllegalArgumentException if cannot find the shape
   */
  void scalePerform(String name, int timeStart, int timeEnd, double fromW, double fromH,
                    double afterWidth, double afterHeight) throws IllegalArgumentException;

  /**
   * Create a move animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param fromX the new x-coordinate of the shape
   * @param fromY the new y-coordinate of the shape
   * @param toX the new x-coordinate of the shape
   * @param toY the new y-coordinate of the shape
   * @throws IllegalArgumentException if cannot find the shape
   */
  void move(String name, int timeStart, int timeEnd, double fromX, double fromY,
            double toX, double toY)
          throws IllegalArgumentException;

  /**
   * Return a string of the description of animation.
   * @return a string of the description of animation.
   */
  String modelState();


  /**
   * Return a list of all the shapes at this frame.
   * @param frame current frame
   * @return a list of all the shapes at this frame
   */
  List<IShape> getAllShapesAtFrame(int frame);  //list of shapes at frame (helper)


  /**
   * Set animation bound w.
   * @param w the given w
   */
  void setAnimationBoundsW(int w);

  /**
   * Set animation bound h.
   * @param h the given h
   */
  void setAnimationBoundsH(int h);


  /**
   * Get animation bound w.
   */
  int getAnimationBoundsW();

  /**
   * Get animation bound h.
   */
  int getAnimationBoundsH();

  /**
   * Return the largest ending time from all the shapes and animations.
   * @return the largest ending time from all the shapes and animations
   */
  int getLength();



}
