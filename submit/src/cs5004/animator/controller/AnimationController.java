package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;

/**
 * This is an AnimationController class implements IController.
 */
public class AnimationController implements IController {
  private final Timer timer;


  /**
   * Construct an AnimationController.
   * @param model the model
   * @param animationView the animation view
   * @param speed the speed
   */
  public AnimationController(IModel model, IView animationView, int speed) {

    this.timer = new Timer(1000 / speed, new ActionListener() {
      int currentFrame = 0;
      @Override
      public void actionPerformed(ActionEvent e) {

        if (currentFrame == model.getLength()) {
          timer.stop();
          return;
        }
        for (IShape renderShape : model.getAllShapesAtFrame(currentFrame++)) {
          animationView.draw(renderShape);
        }
        animationView.refresh();
      }
      });

  }

  /**
   * Perform an animation.
   */
  @Override
  public void animate() {
    timer.start();
  }
}
