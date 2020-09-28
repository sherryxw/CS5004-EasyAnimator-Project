package cs5004.animator.model;

import java.util.Objects;

/**
 * This class represents a 2D position.
 */
public final class Position2D {

  private double x;
  private double y;


  /**
   * Initialize this object to the specified position.
   *
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return x coordinate.
   *
   * @return x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Return y coordinate.
   *
   * @return y coordinate
   */
  public double getY() {
    return y;
  }


  public void setPosition(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * Copy constructor.
   */
  public Position2D(Position2D v) {
    this(v.x, v.y);
  }

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }



}
