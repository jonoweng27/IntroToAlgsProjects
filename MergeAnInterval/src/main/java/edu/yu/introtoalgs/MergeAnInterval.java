package edu.yu.introtoalgs;

import java.util.*;

/** Implements the "Add an Interval To a Set of Intervals" semantics defined in
 * the requirements document.
 * 
 * @author Avraham Leff 
 */

public class MergeAnInterval {

  /** An immutable class, holds a left and right integer-valued pair that
   * defines a closed interval
   *
   * IMPORTANT: students may not modify the semantics of the "left", "right"
   * instance variables, nor may they use any other constructor signature.
   * Students may (are encouraged to) add any other methods that they choose,
   * bearing in mind that my tests will ONLY DIRECTLY INVOKE the constructor
   * and the "merge" method.
   */
  public static class Interval implements Comparable<Interval>{
    public final int left;
    public final int right;

    /** Constructor
     * 
     * @param left the left endpoint of the interval, may be negative
     * @param right the right endpoint of the interval, may be negative
     * @throws IllegalArgumentException if left is >= right
     */
    public Interval(int l, int r) {
      if (l>=r) {
          throw new IllegalArgumentException();
      }
      this.left = l;
      this.right = r;
    }

    //Sort in ascending order by left, and if the left values are the same, then descending order by right
    @Override
    public int compareTo(Interval o) {
        if (o==null) {
            throw new NullPointerException();
        }
        if (((Integer)this.left).compareTo(o.left)==0) {
            return ((Integer)o.right).compareTo(this.right);
        }
        return ((Integer)this.left).compareTo(o.left);
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) {
            return false;
        }
        if (!(o instanceof Interval)) {
            return false;
        }
        Interval i = (Interval)o;
        return (((Integer)this.left).equals(i.left))&&(((Integer)this.right).equals(i.right));
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }

  } // Interval class

  /** Merges the new interval into an existing set of disjoint intervals.
   *
   * @param intervals an set of disjoint intervals (may be empty)
   * @param newInterval the interval to be added
   * @return a new set of disjoint intervals containing the original intervals
   * and the new interval, merging the new interval if necessary into existing
   * interval(s), to preseve the "disjointedness" property.
   * @throws IllegalArgumentException if either parameter is null
   */
  public static Set<Interval> merge (final Set<Interval> intervals, Interval newInterval) {
      if (intervals==null||newInterval==null) {
          throw new IllegalArgumentException();
      }
      //add newInterval to list and sort in ascending order
      intervals.add(newInterval);
      List<Interval> list = new ArrayList<>(intervals);
      Collections.sort(list);
      for (int i=0; i<list.size()-1; i++) {
        //if the list has overlap, then remove all of the overlap, keeping the value of the minimum (which is the rightmost of the two values) and updating the maximum every time an overlap is removed
        if (list.get(i).right>=list.get(i+1).left) {
            int minimum = Math.min(list.get(i).left, list.get(i+1).left);
            int maximum = Math.max(list.get(i).right, list.get(i+1).right);
            Interval largeSpanning = new Interval(minimum, maximum);
            list.remove(i);
            list.remove(i);
            while ((!list.isEmpty())&&(i<list.size())&&overlaps(list.get(i), largeSpanning)) {
                maximum = Math.max(maximum, list.get(i).right);
                list.remove(i);
            }
            //add the new interval which includes the saved minimum and maximum
            list.add(new Interval(minimum, maximum));
            break;
        }
      }
      return new HashSet<>(list);
  }
  private static boolean overlaps (Interval a, Interval b) {
    return Math.max(a.left, b.left) <= Math.min(a.right, b.right);
  }
}