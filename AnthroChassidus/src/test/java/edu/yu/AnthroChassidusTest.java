package edu.yu;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.yu.introtoalgs.AnthroChassidus;

public class AnthroChassidusTest {
    @Test
    public void piazzaQuestionTest() {
        int[] a = {0,0,0};
        int[] b = {1,1,1};
        AnthroChassidus chassid = new AnthroChassidus(50, a, b);
        assertEquals(49, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(2, chassid.nShareSameChassidus(0));
        assertEquals(1, chassid.nShareSameChassidus(23));
    }
    @Test
    public void nShareSameChassidusExceptionTest() {
        int[] a = {0,0,0};
        int[] b = {1,1,1};
        AnthroChassidus chassid = new AnthroChassidus(50, a, b);
        assertEquals(49, chassid.getLowerBoundOnChassidusTypes());
        boolean c = false;
        try {
            chassid.nShareSameChassidus(50);
        } catch (IllegalArgumentException e) {
            c = true;
        }
        assertTrue(c);
        c=false;
        try {
            chassid.nShareSameChassidus(-1);
        } catch (IllegalArgumentException e) {
            c = true;
        }
        assertTrue(c);
        c=false;
        try {
            chassid.nShareSameChassidus(100);
        } catch (IllegalArgumentException e) {
            c = true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest() {
        int[] a = {0,0,0};
        int[] b = {1,1};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(50, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest2() {
        int[] a = {0,0,0};
        int[] b = {1};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(50, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest3() {
        int[] a = {0,0,0};
        int[] b = null;
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(50, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest4() {
        int[] a = {0,0,0};
        int[] b = {1,1,3};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(3, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest5() {
        int[] a = {0,0,3};
        int[] b = {1,1,1};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(3, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest6() {
        int[] a = {0,0,-1};
        int[] b = {1,1,1};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(3, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void exceptionTest7() {
        int[] a = {0,0,1};
        int[] b = {1,1,-1};
        boolean c = false;
        try {
            AnthroChassidus chassid = new AnthroChassidus(3, a, b);
        } catch (IllegalArgumentException e) {
            c=true;
        }
        assertTrue(c);
    }
    @Test
    public void basicTest() {
        int[] a = {1,1,1};
        int[] b = {5,10,15};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(13, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(4, chassid.nShareSameChassidus(1));
        assertEquals(4, chassid.nShareSameChassidus(5)); 
        assertEquals(4, chassid.nShareSameChassidus(15)); 
        assertEquals(4, chassid.nShareSameChassidus(10));
        assertEquals(1, chassid.nShareSameChassidus(2));          
    }
    @Test
    public void basicTest2() {
        int[] a = {3,4,8};
        int[] b = {4,8,12};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(13, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(4, chassid.nShareSameChassidus(3));
        assertEquals(4, chassid.nShareSameChassidus(4)); 
        assertEquals(4, chassid.nShareSameChassidus(8)); 
        assertEquals(4, chassid.nShareSameChassidus(12));
        assertEquals(1, chassid.nShareSameChassidus(2));          
    }
    @Test
    public void basicTest3() {
        int[] a = {2,9,7};
        int[] b = {7,11,9};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(13, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(4, chassid.nShareSameChassidus(2));
        assertEquals(4, chassid.nShareSameChassidus(7)); 
        assertEquals(4, chassid.nShareSameChassidus(9)); 
        assertEquals(4, chassid.nShareSameChassidus(11));
        assertEquals(1, chassid.nShareSameChassidus(1));          
    }
    @Test
    public void basicTestCom1To3() {
        int[] a = {1,1,1,3,4,8,2,9,9,3,2};
        int[] b = {5,10,15,4,8,12,7,11,7,11,1};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(5, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(12, chassid.nShareSameChassidus(2));
        assertEquals(12, chassid.nShareSameChassidus(7)); 
        assertEquals(12, chassid.nShareSameChassidus(9)); 
        assertEquals(12, chassid.nShareSameChassidus(11));
        assertEquals(1, chassid.nShareSameChassidus(6));          
    }
    @Test
    public void basicTestRepeat() {
        int[] a = {1,2};
        int[] b = {1,2};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(16, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(1, chassid.nShareSameChassidus(2));
        assertEquals(1, chassid.nShareSameChassidus(1));   
        assertEquals(1, chassid.nShareSameChassidus(3));       
    }
    @Test
    public void basicTestCanInfer() {
        int[] a = {1,1,1,15,5};
        int[] b = {5,10,15,5,10};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(13, chassid.getLowerBoundOnChassidusTypes());
        assertEquals(4, chassid.nShareSameChassidus(1));
        assertEquals(4, chassid.nShareSameChassidus(5)); 
        assertEquals(4, chassid.nShareSameChassidus(15)); 
        assertEquals(4, chassid.nShareSameChassidus(10));
        assertEquals(1, chassid.nShareSameChassidus(2));      
    }
    @Test
    public void everyonePartOfSameSect() {
        int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] b = {0,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(1, chassid.getLowerBoundOnChassidusTypes());
        for (int i=0; i<16; i++) {
            assertEquals(16, chassid.nShareSameChassidus(i));
        }     
    }
    @Test
    public void everyonePartOfSameSect2() {
        int[] a = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        int[] b = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        AnthroChassidus chassid = new AnthroChassidus(16, a, b);
        assertEquals(1, chassid.getLowerBoundOnChassidusTypes());
        for (int i=0; i<16; i++) {
            assertEquals(16, chassid.nShareSameChassidus(i));
        }     
    }
}
