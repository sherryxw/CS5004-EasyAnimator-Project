package cs5004.animator.view;

import cs5004.animator.model.IModel;

/**
 * This is a viewFactory class. It contains a makeView method that return different
 * view object based of the view type.
 */
public class ViewFactory {

  /**
   * Return a new View object based on the given view type.
   * @param viewType the view type
   * @param output the output string
   * @param w the bound - width
   * @param h the bound - height
   * @return a new View object based on the given view type
   * @throws IllegalArgumentException if cannot locate the correct view type
   */
  public static IView makeView(IModel model, String viewType, String output, int w,
                               int h, int speed) throws IllegalArgumentException {

    if (viewType.equalsIgnoreCase("text")) {
      return new TextualView(model, output, speed);
    } else if (viewType.equalsIgnoreCase("visual")) {
      return new AnimationView(w, h);
    } else {
      throw new IllegalArgumentException("Currently not support this kind of view");
    }
  }

}
