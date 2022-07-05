package edu.yu.introtoalgs;

public class IntersectRectangles {

  /** This constant represents the fact that two rectangles don't intersect.
   *
   * @see #intersectRectangle
   * @warn you may not modify this constant in any way
   */
  public final static Rectangle NO_INTERSECTION =
    new Rectangle(0, 0, -1, -1);

  /** An immutable class that represents a 2D Rectangle.
   *
   * @warn you may not modify the instance variables in any way, you are
   * encouraged to add to the current set of variables and methods as you feel
   * necesssary.
   */
  public static class Rectangle {
    // safe to make instance variables public because they are final, now no
    // need to make getters
    public final int x;
    public final int y;
    public final int width;
    public final int height;

    /** Constructor: see the requirements doc for the precise semantics.
     *
     * @warn you may not modify the currently defined semantics in any way, you
     * may add more code if you so choose.
     */
    public Rectangle
      (final int x, final int y, final int width, final int height)
    {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }
    @Override
    public boolean equals (Object o) {
      if (o==null) {
        return false;
      }
      if (!(o instanceof Rectangle)) {
        return false;
      }
      Rectangle rect = (Rectangle)o;
      return (!(this.x!=rect.x||this.y!=rect.y||this.width!=rect.width||this.height!=rect.height));
    }
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + this.x;
      result = prime * result + this.y;
      result = prime * result + this.width;
      result = prime * result + this.height;
      return result;
    }
    @Override
    public String toString() {
      return (((Integer)this.x).toString())+this.y+this.width+this.height;
    }
  }

  /** If the two rectangles intersect, returns the rectangle formed by their
   * intersection; otherwise, returns the NO_INTERSECTION Rectangle constant.
   *
   * @param r1 one rectangle
   * @param r2 the other rectangle
   * @param a rectangle representing the intersection of the input parameters
   * if they intersect, NO_INTERSECTION otherwise.  See the requirements doc
   * for precise definition of "rectangle intersection"
   * * @throws IllegalArgumentException if either parameter is null.
   */
  public static Rectangle intersect (final Rectangle r1, final Rectangle r2)
  {
    if (r1==null||r2==null) {
      throw new IllegalArgumentException();
    }

    int r1WidthMin = r1.x;
    int r1WidthMax = r1.x+r1.width;
    int r2WidthMin = r2.x;
    int r2WidthMax = r2.x+r2.width;

    int r1HeightMin = r1.y;
    int r1HeightMax = r1.y+r1.height;
    int r2HeightMin = r2.y;
    int r2HeightMax = r2.y + r2.height;

    if ((!areInRange(r1WidthMin, r1WidthMax, r2WidthMin, r2WidthMax)||(!areInRange(r1HeightMin, r1HeightMax, r2HeightMin, r2HeightMax)))) {
      return NO_INTERSECTION;
    }

    //find bottom left point
    //find width
    int newRectMinX = Math.max(r1WidthMin, r2WidthMin);
    //find height
    int newRectMinY = Math.max(r1HeightMin, r2HeightMin);
    //find top right point
    //find width
    int newRectMaxX = Math.min(r1WidthMax, r2WidthMax);
    //find height
    int newRectMaxY = Math.min(r1HeightMax, r2HeightMax);
    //return
    return new Rectangle(newRectMinX, newRectMinY, newRectMaxX-newRectMinX, newRectMaxY-newRectMinY);

  }
  private static boolean areInRange(int oneMin, int oneMax, int twoMin, int twoMax) {
      return Math.max(oneMin, twoMin) <= Math.min(oneMax, twoMax);
  }


} // class