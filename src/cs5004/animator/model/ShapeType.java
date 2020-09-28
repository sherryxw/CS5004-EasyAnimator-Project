package cs5004.animator.model;

/**
 * This enum represents the shape types.
 * Rectangle: a rectangle shape
 * Oval: a oval shape
 */
public enum ShapeType {

  RECTANGLE {
    @Override
    public String toString() {
      return "Rectangle";
    }
  },

  ELLIPSE {
    @Override
    public String toString() {
      return "Ellipse";
    }
  }
}
