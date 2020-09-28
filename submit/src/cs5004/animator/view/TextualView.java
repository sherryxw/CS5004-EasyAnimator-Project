package cs5004.animator.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;

/**
 * This is a TextualView implements IView. It supports all the methods provided by IView.
 */
public class TextualView implements IView {
  private final String out;
  private final String viewType;
  private final IModel model;
  private final int speed;

  /**
   * Construct a textualView. Given the output as a file or System.out.
   * @param out the output
   */
  public TextualView(IModel model, String out, int speed) {
    this.out = out;
    this.viewType = "text";
    this.model = model;
    this.speed = speed;

  }

  /**
   * Render the model state to the output file or System.out.
   * @throws FileNotFoundException if cannot find the file
   */
  @Override
  public void render() throws FileNotFoundException {

    String txt = createOutputTxt();
    if (out.equals("")) {
      System.out.println(txt);
    }
    else {
      try {
        FileOutputStream outputStream = new FileOutputStream(this.out + ".txt");
        byte[] strToBytes = txt.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
      } catch (IOException e) {
        throw new FileNotFoundException("Cannot find the files");
      }
    }
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
   * Nothing to do within the textual view.
   * @param shape the given shape being drawn
   */
  @Override
  public void draw(IShape shape) {
    //Nothing to do within in the textual view
  }

  /**
   * Nothing to do within the textual view.
   */
  @Override
  public void refresh() {
    //Nothing to do within in the textual view
  }


  /**
   * Return a textual description of the animation with real time.
   * @return a textual description of the animation with real time
   */
  public String createOutputTxt() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Shapes: \n");
    for (Object name: model.getShapeList().keySet()) {
      IShape eachShape = (IShape) model.getShapeList().get(name);
      stringBuilder.append(model.getShapeList().get(name).toString());
      stringBuilder.append("Appears at t=").append(eachShape.getAppears() / this.speed)
              .append(", Disappears at t=").append(eachShape.getDisappears() / this.speed)
              .append("\n");
    }
    for (Object animation: model.getSortedList()) {
      IAnimation animation1 = (IAnimation) animation;
      stringBuilder.append(animation.toString()).append(", from time t=")
              .append(animation1.getStartTime() / this.speed)
              .append(" to t=").append(animation1.getEndTime() / this.speed).append("\n");
    }

    return String.valueOf(stringBuilder);
  }


}
