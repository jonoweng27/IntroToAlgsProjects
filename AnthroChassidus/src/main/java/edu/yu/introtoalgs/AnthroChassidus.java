package edu.yu.introtoalgs;

/** Defines and implements the AnthroChassidus API per the requirements
 * documentation.
 *
 * @author Avraham Leff
 */

public class AnthroChassidus {

  int numPeople;
  int numComponents;
  int[] underlyingArray;
  int[] sizeArray;
  /** Constructor.  When the constructor completes, ALL necessary processing
   * for subsequent API calls have been made such that any subsequent call will
   * incur an O(1) cost.
   *
   * @param n the size of the underlying population that we're investigating:
   * need not correspond in any way to the number of people actually
   * interviewed (i.e., the number of elements in the "a" and "b" parameters).
   * Must be greater than 2.
   * @param a interviewed people, element value corresponds to a unique "person
   * id" in the range 0..n-1
   * @param b interviewed people, element value corresponds to a unique "person
   * id" in the range 0..n-1.  Pairs of a_i and b_i entries represent the fact
   * that the corresponding people follow the same Chassidus (without
   * specifying what that Chassidus is).
   */
  public AnthroChassidus(final int n, final int[] a, final int[] b) {
    if (n<=2||a==null||b==null||(a.length!=b.length)) {
      throw new IllegalArgumentException();
    }
    
    this.numPeople=n;
    this.numComponents=n;
    this.underlyingArray=new int[n];
    this.sizeArray=new int[n];
    //to start, iterate through array and give each id its own unique chassidus (before anything else) and start with the size of each thing being 1 - this takes O(n) time
    for (int i=0; i<n; i++) {
      this.underlyingArray[i]=i;
      this.sizeArray[i]=1;
    }
    //Takes O(n) to iterate through array, and union and find are NEARLY O(1) operations, so this whole process is O(2n), which is O(n)
    for (int i=0; i<a.length; i++) {
      if (outOfRange(a[i])||outOfRange(b[i])) {
        throw new IllegalArgumentException();
      }
      union(a[i], b[i]);
    }
  }

  /** Return the tightest value less than or equal to "n" specifying how many
   * types of Chassidus exist in the population: this answer is inferred from
   * the interviewers data supplied to the constructor
   *
   * @return tightest possible lower bound on the number of Chassidus in the
   * underlying population.
   */
  public int getLowerBoundOnChassidusTypes() {
    return this.numComponents;
  }

  /** Return the number of interviewed people who follow the same Chassidus as
   * this person.
   *
   * @param id uniquely identifies the interviewed person
   * @return the number of interviewed people who follow the same Chassidus as
   * this person.
   */
  public int nShareSameChassidus(final int id) {
    if (outOfRange(id)) {
      throw new IllegalArgumentException();
    }
    return sizeArray[find(id)];
  }

  private int find (int p) {
    while (p != underlyingArray[p]) {
      underlyingArray[p]=underlyingArray[underlyingArray[p]];
      p = underlyingArray[p];
    }
    return p;
  }

  private void union (int p, int q) {
    int i = find(p);
    int j = find(q);
    if (i == j) {
      return;
    }
    // Make smaller root point to larger one.
    if (sizeArray[i] < sizeArray[j]) { 
      underlyingArray[i] = j;
      sizeArray[j] += sizeArray[i];
    } else { 
      underlyingArray[j] = i;
      sizeArray[i] += sizeArray[j]; 
    }
    numComponents--;
  }

  private boolean outOfRange(int x) {
    return (x<0||x>=this.numPeople);
  }

} // class