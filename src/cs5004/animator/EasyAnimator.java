package cs5004.animator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.TextController;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.util.MyAnimationBuilder;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewFactory;


/**
 * Create an animation MVC.
 */
public final class EasyAnimator {


  /**
   * This is the main function, it take inputs as command-line arguments.
   * The inputs contains: -in(mandatory), -out(default: System.out), -view(mandatory),
   * -speed(default: 1).
   * @param args the inputs
   */
  public static void main(String[] args) throws FileNotFoundException {
    IModel model = new ModelImpl();

    //default
    String fileName = "";
    String viewType = "";
    String speed = "1";
    String out = "" ;

    //args
    int i;
    for (i = 0; i < args.length; i ++) {
      if (args[i].equalsIgnoreCase("-in")) {
        fileName = args[i + 1];
      }
      else if (args[i].equalsIgnoreCase("-view")) {
        viewType = args[i + 1];
      }
      else if (args[i].equalsIgnoreCase("-out")) {
        out = args[i + 1];

      }
      else if (args[i].equalsIgnoreCase("-speed")) {
        speed = args[i + 1];
      }

      AnimationBuilder<IModel> myAnimationBuilder = new MyAnimationBuilder(model);
      //model setup
      try {
        model = AnimationReader.parseFile(new FileReader(fileName), myAnimationBuilder);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(new JFrame(), "Cannot read the file");
        System.exit(0);
      }
    }

    //create view
    IView view;
    view = ViewFactory.makeView(model, viewType, out, model.getAnimationBoundsW(),
            model.getAnimationBoundsH(), Integer.parseInt(speed));
    //create controller
    IController controller;
    if (view.getViewType().equalsIgnoreCase("animation")) {
      controller = new AnimationController(model, view, Integer.parseInt(speed));
    } else {
      controller = new TextController(view, model, Integer.parseInt(speed));
    }

    controller.animate();
  }
}
