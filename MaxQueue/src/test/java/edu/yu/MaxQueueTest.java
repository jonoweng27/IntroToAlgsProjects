package edu.yu;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import edu.yu.introtoalgs.MaxQueue;

public class MaxQueueTest {
    @Test
    public void basicTestExceptions() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        boolean test = false;
        try {
            int i = m.max();
        } catch (NoSuchElementException e) {
            test = true;
        }
        assertTrue(test);
        test = false;
        try {
            int i = m.dequeue();
        } catch (NoSuchElementException e) {
            test = true;
        }
        assertTrue(test);
    }
    @Test
    public void basicTest() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        m.enqueue(1);
        m.enqueue(2);
        m.enqueue(3);
        m.enqueue(4);
        assertEquals(4, m.size());
        assertEquals(4, m.max());
        assertEquals(1, m.dequeue());
        assertEquals(3, m.size());
        assertEquals(4, m.max());
        m.enqueue(100);
        assertEquals(4, m.size());
        assertEquals(100, m.max());
    }
    @Test
    public void basicTest2() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        m.enqueue(5);
        m.enqueue(2);
        m.enqueue(1);
        m.enqueue(0);
        assertEquals(4, m.size());
        assertEquals(5, m.max());
        assertEquals(5, m.dequeue());
        assertEquals(3, m.size());
        assertEquals(2, m.max());
        m.enqueue(100);
        assertEquals(4, m.size());
        assertEquals(100, m.max());
    }
    @Test
    public void basicTest3() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        m.enqueue(5);
        m.enqueue(2);
        m.enqueue(1);
        m.enqueue(4);
        assertEquals(4, m.size());
        assertEquals(5, m.max());
        assertEquals(5, m.dequeue());
        assertEquals(3, m.size());
        assertEquals(4, m.max());
    }
    @Test
    public void basicTest4() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        m.enqueue(5);
        m.enqueue(2);
        m.enqueue(4);
        m.enqueue(3);
        assertEquals(4, m.size());
        assertEquals(5, m.max());
        assertEquals(5, m.dequeue());
        assertEquals(3, m.size());
        assertEquals(4, m.max());
    }
    @Test
    public void test2Or1Elements() {
        MaxQueue m = new MaxQueue();
        assertEquals(0, m.size());
        m.enqueue(5);
        m.enqueue(2);
        assertEquals(2, m.size());
        assertEquals(5, m.max());
        assertEquals(5, m.dequeue());
        assertEquals(1, m.size());
        assertEquals(2, m.max());
        assertEquals(2, m.dequeue());
        assertEquals(0, m.size());
        boolean test = false;
        try {
            int i = m.max();
        } catch (NoSuchElementException e) {
            test = true;
        }
        assertTrue(test);
        test = false;
        try {
            int i = m.dequeue();
        } catch (NoSuchElementException e) {
            test = true;
        }
        assertTrue(test);
    }
}
