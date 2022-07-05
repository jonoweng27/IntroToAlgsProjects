package edu.yu;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.yu.introtoalgs.*;
import edu.yu.introtoalgs.MergeAnInterval.*;

public class MergeAnIntTests {
    @Test
    public void leffTest1() {
        Interval a = new Interval(0, 2);
        Interval b = new Interval(3, 4);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, b);
        assertEquals(2, finalSet.size());
        assertTrue(finalSet.contains(a));
        assertTrue(finalSet.contains(b));
    }
    @Test
    public void leffTest2() {
        Interval a = new Interval(0, 2);
        Interval b = new Interval(1, 4);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, b);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
        assertTrue(finalSet.contains(new Interval(0, 4)));
    }
    @Test
    public void leffTest3() {
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(0,3);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        set1.add(b);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, c);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
        assertFalse(finalSet.contains(c));
        assertTrue(finalSet.contains(new Interval(0, 4)));
    }
    @Test
    public void myTest1() {
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(2,3);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        set1.add(b);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, c);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
        assertFalse(finalSet.contains(c));
        assertTrue(finalSet.contains(new Interval(1, 4)));
    }
    @Test
    public void myTest2() {
        Interval a = new Interval(1, 2);
        Interval b = new Interval(1, 3);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, b);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertTrue(finalSet.contains(b));
    }
    @Test
    public void myTest3() {
        Interval a = new Interval(1, 3);
        Interval b = new Interval(1, 2);
        HashSet<Interval> set1 = new HashSet<>();
        set1.add(a);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, b);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(b));
        assertTrue(finalSet.contains(a));
    }
    @Test
    public void emptySet() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 3);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, a);
        assertEquals(1, finalSet.size());
        assertTrue(finalSet.contains(a));
    }
    @Test
    public void intervalThatSpansAll() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(5,6);
        Interval d = new Interval (1,5);
        set1.add(a);
        set1.add(b);
        set1.add(c);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, d);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
        assertFalse(finalSet.contains(c));
        assertFalse(finalSet.contains(d));
        assertTrue(finalSet.contains(new Interval(1,6)));
    }
    @Test
    public void intervalThatSpansAllButOne() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(5,6);
        Interval d = new Interval (1,6);
        set1.add(a);
        set1.add(b);
        set1.add(c);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, d);
        assertEquals(1, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
        assertFalse(finalSet.contains(c));
        assertTrue(finalSet.contains(d));
    }
    @Test
    public void mergeThatDoesNothing() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 100);
        Interval b = new Interval(3, 4);
        set1.add(a);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, b);
        assertEquals(1, finalSet.size());
        assertTrue(finalSet.contains(a));
        assertFalse(finalSet.contains(b));
    }
    @Test
    public void mergeThatDoesntMerge() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(5,6);
        Interval d = new Interval (101,104);
        set1.add(a);
        set1.add(b);
        set1.add(c);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, d);
        assertEquals(4, finalSet.size());
        assertTrue(finalSet.contains(a));
        assertTrue(finalSet.contains(b));
        assertTrue(finalSet.contains(c));
        assertTrue(finalSet.contains(d));
    }
    @Test
    public void mergeWithSameElementInSet() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(1, 2);
        Interval b = new Interval(3, 4);
        Interval c = new Interval(5,6);
        Interval d = new Interval (1,2);
        set1.add(a);
        set1.add(b);
        set1.add(c);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, d);
        assertEquals(set1, finalSet);
        assertEquals(3, finalSet.size());
        assertTrue(finalSet.contains(a));
        assertTrue(finalSet.contains(b));
        assertTrue(finalSet.contains(c));
    }
    @Test
    public void testNegative() {
        HashSet<Interval> set1 = new HashSet<>();
        Interval a = new Interval(-1, 1);
        Interval b = new Interval(-5, -3);
        Interval c = new Interval(0,2);
        set1.add(a);
        set1.add(b);
        Set<Interval> finalSet = MergeAnInterval.merge(set1, c);
        assertEquals(2, finalSet.size());
        assertFalse(finalSet.contains(a));
        assertTrue(finalSet.contains(b));
        assertFalse(finalSet.contains(c));
        assertTrue(finalSet.contains(new Interval(-1,2)));
    }
}
