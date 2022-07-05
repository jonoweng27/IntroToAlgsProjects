package edu.yu;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.yu.introtoalgs.CountStringsFJ;

public class CountStringFJTest {
   @Test
   public void leffTest1 () {
    final String [] arr = {"h", "ee", "llll", "llll", "oo", "llll"};
    final String str1 = "llll";
    final int threshold1 = 3;
    final CountStringsFJ countStringsFJ1 =
    new CountStringsFJ ( arr , str1 , threshold1 ) ;
    final int retval1 = countStringsFJ1 . doIt ();
    assertEquals(3, retval1);
   }
   
   @Test
   public void leffTest2() {
    final String [] arr = {"h", "ee", "llll", "llll ", "oo", "llll"};
    final String str2 = "h";
    final int threshold2 = 3;
    final CountStringsFJ countStringsFJ2 = new CountStringsFJ ( arr , str2 , threshold2 ) ;
    final int retval2 = countStringsFJ2 . doIt () ;
    assertEquals(1, retval2);
    
   }
   @Test
   public void hugeSameArray() {
       String [] arr2 = new String[1000];
       for (int i=0; i<arr2.length; i++) {
           arr2[i]="h";
       }
       final String[] arr = arr2;
       final String str="h";
       final int threshold = 50;
       final CountStringsFJ countStringsFJ = new CountStringsFJ ( arr , str , threshold ) ;
        final int retval = countStringsFJ.doIt () ;
        assertEquals(1000, retval);
   }
   @Test
   public void NoneSameArray() {
       String [] arr2 = new String[1000];
       for (int i=0; i<arr2.length; i++) {
           arr2[i]="i";
       }
       final String[] arr = arr2;
       final String str="h";
       final int threshold = 50;
       final CountStringsFJ countStringsFJ = new CountStringsFJ ( arr , str , threshold ) ;
        final int retval = countStringsFJ.doIt () ;
        assertEquals(0, retval);
   }
}
