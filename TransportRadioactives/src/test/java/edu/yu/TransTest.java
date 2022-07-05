package edu.yu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.yu.introtoalgs.TransportRadioactives;
import edu.yu.introtoalgs.TransportationState;

public class TransTest {
    @Test
    public void basicTest() {
        List<TransportationState> list = TransportRadioactives.transportIt(2, 2);
        assertEquals(6, list.size());
    }
    @Test
    public void largeTest() {
        List<TransportationState> list = TransportRadioactives.transportIt(3, 3);
        assertEquals(12, list.size());
    }
    @Test
    public void basicTest2() {
        List<TransportationState> list = TransportRadioactives.transportIt(1, 2);
        assertEquals(0, list.size());
    }
    @Test
    public void illegalArgTest() {
        try {
            List<TransportationState> list = TransportRadioactives.transportIt(0, 2);
            assertTrue(false);
        } catch (IllegalArgumentException e) {}
    }
    @Test
    public void longTest() {
        long time = System.nanoTime();
        List<TransportationState> list = TransportRadioactives.transportIt(1, 2);
        long elapsedTime = System.nanoTime()-time;
        assertTrue(elapsedTime<Math.pow(10,10));
    }
}
