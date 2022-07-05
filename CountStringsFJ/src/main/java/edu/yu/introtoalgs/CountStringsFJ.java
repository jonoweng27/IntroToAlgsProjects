package edu.yu.introtoalgs;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/** Implements the CountStringsFJ semantics specified in the requirements
 * document.
 *
 * @author Avraham Leff
 */

public class CountStringsFJ {

  class ForkJoinSum extends RecursiveTask <Integer> {
    ForkJoinSum (int threshold , String [] array , String str, int low , int high) {
    this.low = low ;
    this.high = high ;
    this.array = array ;
    // If array size is this small ,
    // don ’t process recursively
    this.threshold = threshold ;
    }
    private final int low;
    private final int high;
    private final String [] array;
    private final int threshold;
    public Integer compute () {
        if (high - low <= threshold ) {
          return getEqual(array, str, low, high);
        } // sequential processing
        else {
          ForkJoinSum left = new ForkJoinSum (threshold , array , str, low, (high + low)/2) ;
          ForkJoinSum right = new ForkJoinSum (threshold , array ,str, (high + low)/2 , high);
          left.fork();
          final int rightAnswer = right.compute();
          final int leftAnswer = left.join();
          return leftAnswer+rightAnswer;
      }
    }
  }
  
  final int threshold;
  final String str;
  final String[] arr;
  /** Constructor.
   *
   * @param arr the array to process, can't be null or empty
   * @param str the string to match, can't be null, may be empty
   * @param threshold when the length of arr is less than threshold, processing
   * must be sequential; otherwise, processing must use a fork/join, recursive
   * divide-and-conquer strategy.  The parameter must be greater than 0.
   *
   * IMPORTANT: Students must use this constructor, they MAY NOT add another
   * constructor.
   */
  public CountStringsFJ(final String[] arr, final String str, final int threshold) {
      if (arr==null||arr.length==0||str==null||threshold<=0) {
          throw new IllegalArgumentException();
      }
      this.threshold=threshold;
      this.arr=arr;
      this.str=str;
  }
  /*public static void main (String[] args) {
    long x=0;
    for (int i=0; i<1; i++) {
      x+=run(1048576000, 1000);
    }
    System.out.println("The runtime took " + x + " nanoseconds");
  }
  public static long run(int arraySize, int threshold) {
    String [] arr2 = new String[arraySize];
    for (int i=0; i<arr2.length; i++) {
        arr2[i]="h";
    }
    final String[] arr = arr2;
    final String str="h";
    final CountStringsFJ countStringsFJ = new CountStringsFJ ( arr , str , threshold ) ;
    long time = System.nanoTime();
    final int retval = countStringsFJ.doIt () ;
    return System.nanoTime()-time;
  }*/
  
  /** Returns the number of elements in arr that ".equal" the "str" parameter
   *
   * @return Using a strategy dictated by the relative values of threshold and
   * the size of arr, returns the number of times that str appears in arr
   */
  public int doIt() {
    int parallelism=Runtime.getRuntime().availableProcessors()*1;
    ForkJoinTask < Integer > task = new ForkJoinSum (threshold, arr, str, 0, arr.length );
    final ForkJoinPool fjPool = new ForkJoinPool (parallelism);
    int sum = fjPool.invoke(task);
    fjPool.shutdown () ;
    return sum;
  }

  public static int getEqual(final String[] arr, final String str, final int low, final int high) {
      int result=0;
      for (int i=low; i<high; i++) {
          if (arr[i].equals(str)) {
              result++;
          }
      }
      return result;
  }
}