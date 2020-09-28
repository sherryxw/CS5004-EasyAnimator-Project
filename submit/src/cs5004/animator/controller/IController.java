package cs5004.animator.controller;

import java.io.FileNotFoundException;


/**
 * This is a controller interface, it purpose is to mediate the interactions between the view and
 * the model.
 * It includes an animate methods to run the model.
 */
public interface IController {


  /**
   * Perform an animation using the provided model.
   */
  void animate() throws FileNotFoundException;
}
