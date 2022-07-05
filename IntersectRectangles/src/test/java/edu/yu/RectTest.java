package edu.yu;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.yu.introtoalgs.IntersectRectangles;
import edu.yu.introtoalgs.IntersectRectangles.*;

public class RectTest {
    @Test
    public void demo ( ) {
        final Rectangle D = new Rectangle (0 , 0 , 2 , 5) ;
        final Rectangle F = new Rectangle ( 2 , 0 , 2 , 5) ;
        assertEquals ( "side -by-side rectangles do intersect" , new Rectangle ( 2 , 0 , 0 , 5) , IntersectRectangles.intersect (D , F ) ) ;
    }
    @Test
    public void rectEqualTest() {
        final Rectangle D = new Rectangle (0 , 0 , 2 , 5);
        final Rectangle E = new Rectangle (0 , 0 , 2 , 5);
        final Rectangle F = new Rectangle (0 , 0 , 2 , 6);
        final Rectangle G = new Rectangle (0 , 1 , 2 , 5);
        assertEquals(D, E); assertEquals(D.hashCode(), E.hashCode()); assertEquals(D.toString(), E.toString());
        assertNotEquals(D, null);
        assertNotEquals(D, F); assertNotEquals(D.hashCode(), F.hashCode()); assertNotEquals(D.toString(), F.toString());
        assertNotEquals(D, G); assertNotEquals(D.hashCode(), G.hashCode()); assertNotEquals(D.toString(), G.toString());
    }

    @Test
    public void noOverlapTest() {
        final Rectangle D = new Rectangle (0 , 0 , 1 , 1);
        final Rectangle E = new Rectangle (5 , 0 , 5 , 5);
        assertEquals(IntersectRectangles.NO_INTERSECTION, IntersectRectangles.intersect(D, E));
        assertEquals(new Rectangle(0,0,1,1), IntersectRectangles.intersect(D, D));
    }
    @Test
    public void lineOverLapAndTouchTest() {
        final Rectangle D = new Rectangle (0 , 0 , 1 , 1);
        final Rectangle RIGHT_D = new Rectangle(1,0,1,1);
        final Rectangle ABOVE_D = new Rectangle(0, 1, 1, 1);
        assertEquals(new Rectangle(1,0,0,1), IntersectRectangles.intersect(D, RIGHT_D));
        assertEquals(new Rectangle(0,1,1,0), IntersectRectangles.intersect(D, ABOVE_D));
        assertEquals(new Rectangle(1,1,0,0), IntersectRectangles.intersect(RIGHT_D, ABOVE_D));
        assertEquals(new Rectangle(1,0,0,1), IntersectRectangles.intersect(RIGHT_D, D));
        assertEquals(new Rectangle(0,1,1,0), IntersectRectangles.intersect(ABOVE_D, D));
        assertEquals(new Rectangle(1,1,0,0), IntersectRectangles.intersect(ABOVE_D, RIGHT_D));

    }
    @Test
    public void anotherTest() {
        final Rectangle D = new Rectangle (-1 , -1 , 1 , 1);
        final Rectangle E = new Rectangle(0,0,1,1);
        assertEquals(new Rectangle(0,0,0,0), IntersectRectangles.intersect(D, E));
        assertEquals(new Rectangle(0,0,0,0), IntersectRectangles.intersect(E, D));
    }

    @Test
    public void anotherTest2() {
        final Rectangle D = new Rectangle (0,0,5,1);
        final Rectangle E = new Rectangle(3,-5,2,10);
        assertEquals(new Rectangle(3,0,2,1), IntersectRectangles.intersect(D, E));
        assertEquals(new Rectangle(3,0,2,1), IntersectRectangles.intersect(E, D));
    }
    @Test
    public void rectangleOverlappingAnotherTest() {
        final Rectangle D = new Rectangle (-10,-10,20,20);
        final Rectangle E = new Rectangle(-1,-1,2,2);
        assertEquals(new Rectangle(-1,-1,2,2), IntersectRectangles.intersect(D, E));
        assertEquals(new Rectangle(-1,-1,2,2), IntersectRectangles.intersect(E, D));
    }

    @Test
    public void overTest() {
        final Rectangle D = new Rectangle (0,0,3,3);
        final Rectangle E = new Rectangle(0,4,3,3);
        assertEquals(IntersectRectangles.NO_INTERSECTION, IntersectRectangles.intersect(D, E));
        assertEquals(IntersectRectangles.NO_INTERSECTION, IntersectRectangles.intersect(E, D));
    }

    @Test
    public void sideTest() {
        final Rectangle D = new Rectangle (0,0,3,3);
        final Rectangle E = new Rectangle(5,0,3,3);
        assertEquals(IntersectRectangles.NO_INTERSECTION, IntersectRectangles.intersect(D, E));
        assertEquals(IntersectRectangles.NO_INTERSECTION, IntersectRectangles.intersect(E, D));
    }
    @Test
    public void piazzaQuestionTest() {
        final Rectangle D = new Rectangle (2,1,4,4);
        final Rectangle E = new Rectangle(5,2,5,2);
        assertEquals(new Rectangle(5,2,1,2), IntersectRectangles.intersect(D, E));
    }

}
