package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a modelImpl. It implements all the functions mandated by IModel interface.
 * It contains three list to store the related information of the model.
 * shapeList: record all the shapes being added to the model.
 *            Key: shape name, Value: IShape object.
 * currAnimationList: record all animations for each shape.
 *                    Key: shape name, Value: ArrayList of this shape animations.
 * sortedAnimationList: get a sorted animations list based on animations start time.
 */
public final class ModelImpl implements IModel {
  //Record all the shapes being added
  //R: rectangle, V: oval, O: rectangle ...A: oval...
  private final Map<String, IShape> shapeList;

  //Record new animation valid or not for each shape
  //For example:   Red Green Blue            width height
  //R: [color("R", 0.1, 1.0 , 2.0); scale("R",  5,  10)]
  //P: [color("P", time:5, time:10, 1.0, 1.1, 2.1); scale("P", 10, 10),
  // color("P", time 20, time 30, 1.0, 2.0, 2.1)]
  private final Map<String, ArrayList<IAnimation>> currAnimationLst;

  //Sort the animation list based on start time
  //[color("R",t1= 0 ), scale("O", t1=10), move("C", t1=20)]
  private final ArrayList<IAnimation> sortedAnimationList;

  private int x;
  private int y;
  private int w;
  private int h;

  /**
   * Construct a modelImpl. Contains three list.
   */
  public ModelImpl() {
    this.shapeList = new LinkedHashMap<>();
    this.currAnimationLst = new HashMap<>();
    this.sortedAnimationList = new ArrayList<>();
  }


  /**
   * Return the IShape object of this shape name.
   * @param name the name of the shape
   * @return the IShape object of this shape name
   */
  @Override
  public IShape getIShape(String name) {
    return shapeList.get(name);
  }

  /**
   * Return a copy of shapeList.
   * @return the copy of shapeList
   */
  @Override
  public Map getShapeList() {
    Map<String, IShape> newShapeList = new LinkedHashMap<>();
    for (String name: shapeList.keySet()) {
      newShapeList.put(name, shapeList.get(name));
    }
    return newShapeList;
  }

  /**
   * Return the copy of sortedList.
   * @return the copy of sortedList
   */
  @Override
  public ArrayList getSortedList() {
    ArrayList<IAnimation> newSortedList = new ArrayList<>();
    newSortedList.addAll(this.sortedAnimationList);
    return newSortedList;
  }

  /**
   * Return the animation list.
   * @return the animation list
   */
  @Override
  public Map getCurrAnimationList() {
    Map<String, ArrayList<IAnimation>> newAnimationList = new HashMap<>();
    for (String name: this.currAnimationLst.keySet()) {
      newAnimationList.put(name, this.currAnimationLst.get(name));
    }
    return newAnimationList;
  }

  /**
   * Return true if this shape is already exist in the model, false otherwise.
   * @param name the name of this shape
   * @return true if this shape is already exist in the model, false otherwise
   */
  private boolean checkShapeExist(String name) {
    //check whether this name is already used
    for (String existShape: shapeList.keySet()) {
      if (existShape.equals(name)) {
        return true;
      }
    }
    return false;
  }

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
  @Override
  public void addShape(String name, ShapeType type, double x, double y, double width, double height,
                       double red, double green, double blue, int startTime, int endTime)
          throws IllegalArgumentException {
    if (checkShapeExist(name)) {
      return;
    }
    //add new shape to the shapeList
    IShape shape;
    Position2D position2D = new Position2D(x, y);
    switch (type) {
      case ELLIPSE:
        shape = new Ellipse(name, position2D, width, height, red, green, blue, startTime, endTime);
        shapeList.put(name, shape);
        break;
      case RECTANGLE:
        shape = new Rectangle(name, position2D, width, height, red,
                green, blue, startTime, endTime);
        shapeList.put(name, shape);
        break;
      default:
        throw new IllegalArgumentException("Cannot find the shape pattern");
    }
  }

  /**
   * Delete a shape from this model.
   * @param name the name of the shape
   * @param type the type of the shape
   * @param deleteTime the delete Time of the shape
   * @throws IllegalArgumentException if input is invalid or cannot find the shape
   */
  @Override
  public void removeShape(String name, ShapeType type, int deleteTime)
          throws IllegalArgumentException {
    if (name.equals("") || type == null) {
      throw new IllegalArgumentException("Invalid input, cannot delete shape");
    }
    if (shapeList.get(name) == null) {
      throw new IllegalArgumentException("Cannot find this shape, cannot delete");
    }
    //remove from shapeList
    shapeList.remove(name);
    //remove all its related animation from currAnimation map
    currAnimationLst.remove(name);
    //remove all animation that after deleteTime
    sortedAnimationList.removeIf(animation -> animation.getName().equals(name)
            && (deleteTime < animation.getEndTime()));
  }


  /**
   * Create a change color animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param beforeR the afterR intensity of this shape
   * @param beforeG the afterG intensity of this shape
   * @param beforeB the afterB intensity of this shape
   * @param afterRed the new red intensity of this shape
   * @param afterGreen the new green intensity of this shape
   * @param afterB the new blue intensity of this shape
   * @throws IllegalArgumentException if cannot find the shape or time overlap
   */
  @Override
  public void changeColor(String name, int timeStart, int timeEnd, double beforeR, double beforeG,
                          double beforeB, double afterRed, double afterGreen, double afterB)
          throws IllegalArgumentException {
    //check whether this shape exist or not
    if (!checkShapeExist(name)) {
      throw new IllegalArgumentException("Cannot find the shape");
    }

    boolean timeOverlap = checkTimeOverlap(AnimationsType.COLOR, name, timeStart, timeEnd);
    if (!timeOverlap) {
      IAnimation newColor = new ChangeColor(shapeList, name, timeStart, timeEnd,
              beforeR, beforeG, beforeB, afterRed, afterGreen, afterB);
      updateAllRelatedList(newColor, timeStart, name);
    }

  }

  /**
   * Create a scale animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param fromW the afterWidth of the shape
   * @param fromH the afterHeight of the shape
   * @param afterWidth the afterWidth of the shape
   * @param afterHeight the afterHeight of the shape
   * @throws IllegalArgumentException if cannot find the shape or time overlap
   */
  @Override
  public void scalePerform(String name, int timeStart, int timeEnd, double fromW, double fromH,
                           double afterWidth, double afterHeight) throws IllegalArgumentException {
    //check whether this shape exist or not
    if (!checkShapeExist(name)) {
      throw new IllegalArgumentException("Cannot find the shape");
    }

    boolean timeOverlap = checkTimeOverlap(AnimationsType.SCALE, name, timeStart, timeEnd);

    if (!timeOverlap) {
      IAnimation newScale = new Scale(shapeList, name, timeStart, timeEnd, fromW, fromH,
              afterWidth, afterHeight);
      updateAllRelatedList(newScale, timeStart, name);
    }

  }

  /**
   * Create a move animation.
   * @param name the name of the shape
   * @param timeStart the start time of this animation
   * @param timeEnd the end time of this animation
   * @param toX the new x-coordinate of the shape
   * @param toY the new y-coordinate of the shape
   * @throws IllegalArgumentException if cannot find the shape, or time overlap
   */
  @Override
  public void move(String name, int timeStart, int timeEnd, double fromX, double fromY,
                   double toX, double toY)
          throws IllegalArgumentException {
    //check whether this shape exist or not
    if (!checkShapeExist(name)) {
      throw new IllegalArgumentException("Cannot find the shape");
    }

    boolean timeOverlap = checkTimeOverlap(AnimationsType.MOVE, name, timeStart, timeEnd);
    if (!timeOverlap) {
      IAnimation newMove = new Move(shapeList, name, timeStart, timeEnd, fromX, fromY, toX, toY);
      updateAllRelatedList(newMove, timeStart, name);
    }

  }

  /**
   * Return a description of all the animations.
   * @return a description of all the animations
   */
  @Override
  public String modelState() {
    StringBuilder output = new StringBuilder();
    output.append("Shapes: \n");
    //add all the shape info
    //Name: C
    //Type: oval
    //Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0, 1.0, 1.2)
    //Appears at t=10
    //Disappears at t=100
    for (String shape: shapeList.keySet()) {
      output.append(shapeList.get(shape).toString()); //Name, type, min corner, color, etc ...
      output.append("Appears at t=").append(shapeList.get(shape).getAppears()).
              append(", Disappears at t=").append(shapeList.get(shape).getDisappears()).
              append("\n");
    }
    //add all animations
    //Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=10 to t=50
    for (IAnimation animation: sortedAnimationList) {
      output.append(animation.toString()).append(", from time t=").
              append(animation.getStartTime()).
              append(" to t=").append(animation.getEndTime());
    }
    return String.valueOf(output);

  }


  /**
   * Perform animations at this frame.
   * @param frame the current frame
   */
  private void performAnimations(int frame) {

    for (IAnimation animation: sortedAnimationList) {
      if (frame >= animation.getStartTime() && frame <= animation.getEndTime()) {
        animation.performAction(frame);
      }
    }
  }

  /**
   * Return a list of all the shapes at this frame.
   * @param frame current frame
   * @return a list of all the shapes at this frame
   */
  @Override
  public List<IShape> getAllShapesAtFrame(int frame) {
    performAnimations(frame);
    List<IShape> shapesAtFrame = new ArrayList<>();
    //check new shape appear
    for (String shape: shapeList.keySet()) {
      if (frame >= shapeList.get(shape).getAppears()
              && frame <= shapeList.get(shape).getDisappears()) {
        shapesAtFrame.add(shapeList.get(shape));
      }
    }
    return shapesAtFrame;
  }

  /**
   * Return true if there is a time conflict between new animation and the exist animation,
   * false otherwise.
   * @param animationsType the new animation type
   * @param name the name of the shape
   * @param startT the start time of the new animation
   * @param endT the end time of the new animation
   * @return true if there is a time conflict between new animation and the exist animation,
   *        false otherwise
   */
  private boolean checkTimeOverlap(AnimationsType animationsType, String name, int startT,
                                   int endT) {
    //check whether this animation is already request(time conflict or not)
    for (String shape: currAnimationLst.keySet()) {
      if (shape.equals(name)) { //find the shape
        ArrayList<IAnimation> shapeAnimationList
                = currAnimationLst.get(name); //current shape animation list
        for (IAnimation eachAnimation : shapeAnimationList) {
          if (eachAnimation.getType().equals(animationsType)) { //find out color animation
            //means this shape already request color change, we check time period
            if (startT < eachAnimation.getEndTime() && endT > eachAnimation.getStartTime()) {
              return true; //time conflict
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * Add new animation to the sortedAnimationList.
   * @param startTime the start time of the new animation
   * @param newAnimation the new animation being added
   */
  private void addAnimationInSortedList(int startTime, IAnimation newAnimation) {
    // compare the startTime with the current list, sort the list in order
    // added directly if list is null
    if (sortedAnimationList.size() == 0) {
      sortedAnimationList.add(newAnimation);
      return;
    }
    //list != null
    int i  = 0;
    boolean added = false;
    //compare the time
    for (IAnimation currAnimation: sortedAnimationList) {
      if (startTime <= currAnimation.getStartTime()) {
        sortedAnimationList.add(i, newAnimation);
        added = true;
        break;
      }
      i ++;
    }
    //add at last
    if (!added) {
      sortedAnimationList.add(newAnimation);
    }
  }


  /**
   * Update new animation to all related lists.
   * @param newAnimation the new animation
   * @param timeStart the start time of the new animation
   * @param name the name of the shape
   */
  private void updateAllRelatedList(IAnimation newAnimation, int timeStart, String name) {
    //add new move to sorted animation list
    addAnimationInSortedList(timeStart, newAnimation);
    //add new move to currAnimation map
    ArrayList<IAnimation> animationArrayList = currAnimationLst.get(name);
    if (animationArrayList == null) { //no this shape animation before
      currAnimationLst.put(name, new ArrayList<>()); //create one
      ArrayList<IAnimation> animationList = currAnimationLst.get(name);
      animationList.add(newAnimation);
    }
    else {
      animationArrayList.add(newAnimation);
    }
  }

  /**
   * Return the largest ending time from all the shapes and animations.
   * @return the largest ending time from all the shapes and animations
   */
  public int getLength() {
    int length = 0;
    //maximum frame is from the transformations
    //largest end time
    for (String name: shapeList.keySet()) {
      if (shapeList.get(name).getDisappears() > length) {
        length = shapeList.get(name).getDisappears();
      }
    }
    return length;
  }

  /**
   * Set animation bound w.
   * @param setW the given w
   */
  @Override
  public void setAnimationBoundsW(int setW) {
    this.w = setW;
  }

  /**
   * Set animation bound h.
   * @param setH the given h
   */
  @Override
  public void setAnimationBoundsH(int setH) {
    this.h = setH;
  }

  /**
   * Get animation bound w.
   */
  @Override
  public int getAnimationBoundsW() {
    return this.w;
  }

  /**
   * Get animation bound h.
   */
  @Override
  public int getAnimationBoundsH() {
    return this.h;
  }



}


