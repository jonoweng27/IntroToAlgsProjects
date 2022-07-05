package edu.yu;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.yu.introtoalgs.GraphsAndMazes;
import edu.yu.introtoalgs.GraphsAndMazes.Coordinate;;

public class GraphTest {
    @Test
    public void leffTest() {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(2, 0);
          final Coordinate end = new Coordinate(0, 2);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          List<Coordinate> list = new ArrayList<>();
          list.add(new Coordinate(2,0));
          list.add(new Coordinate(1,0));
          list.add(new Coordinate(0,0));
          list.add(new Coordinate(0,1));
          list.add(new Coordinate(0,2));
          assertEquals(list, path);
    }

    @Test
    public void outOfBoundsTest() {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(3, 0);
          final Coordinate end = new Coordinate(0, 2);
          try {
            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
            assertTrue(false);
          } catch (IllegalArgumentException e) {}
    }
    @Test
    public void outOfBoundsTest2() {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(0, 15);
          final Coordinate end = new Coordinate(0, 2);
          try {
            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
            assertTrue(false);
          } catch (IllegalArgumentException e) {}
    }
    @Test
    public void sameOutOfBoundsVal() {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(3, 3);
          final Coordinate end = new Coordinate(3, 3);
          try {
            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
            assertTrue(false);
          } catch (IllegalArgumentException e) {}
    }
    @Test
    public void jaggedArrayTest() {
        final int[][] exampleMaze = {
            {0,1},
            {0},
            {0,0,0}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(2, 2);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          List<Coordinate> list = new ArrayList<>();
          list.add(new Coordinate(0,0));
          list.add(new Coordinate(1,0));
          list.add(new Coordinate(2,0));
          list.add(new Coordinate(2,1));
          list.add(new Coordinate(2,2));
          assertEquals(list, path);
    }
    @Test
    public void jaggedArrayTest2() {
        final int[][] exampleMaze = {
            {0,1},
            {1},
            {0,0,0}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(2, 2);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          assertEquals(new ArrayList<>(), path);
    }
    @Test
    public void singleArrayTest() {
        final int[][] exampleMaze = {
            {0}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(0, 0);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          List<Coordinate> list = new ArrayList<>();
          list.add(new Coordinate(0,0));
          assertEquals(list, path);
    }
    @Test
    public void singleArrayTest2() {
        final int[][] exampleMaze = {
            {1}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(0, 0);
          try {
            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
            assertTrue(false);
          } catch (IllegalArgumentException e){}
    }
    @Test
    public void mazeToItself() {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(0, 0);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          List<Coordinate> list = new ArrayList<>();
          list.add(new Coordinate(0,0));
          assertEquals(list, path);
    }
    @Test
    public void mazeToItself2() {
        final int[][] exampleMaze = {
            {1, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(0, 0);
          final Coordinate end = new Coordinate(0, 0);
          try {
            final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
            assertTrue(false);
          } catch (IllegalArgumentException e){}
    }
    @Test
    public void noSolution() {
        final int[][] exampleMaze = {
            {1, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(2, 0);
          final Coordinate end = new Coordinate(0, 2);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          assertEquals(new ArrayList<>(), path);
    }
    @Test
    public void noSolution2() {
        final int[][] exampleMaze = {
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
          };
      
          final Coordinate start = new Coordinate(2, 0);
          final Coordinate end = new Coordinate(0, 2);
          try {
             List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
             assertTrue(false);
          } catch (IllegalArgumentException e){}
    }
    @Test
    public void windyPath() {
        final int[][] exampleMaze = {
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0}, 
            {0, 1, 0, 0}
          };
      
          final Coordinate start = new Coordinate(3, 0);
          final Coordinate end = new Coordinate(3, 2);
          final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
          List<Coordinate> list = new ArrayList<>();
          list.add(new Coordinate(3,0));
          list.add(new Coordinate(2,0));
          list.add(new Coordinate(1,0));
          list.add(new Coordinate(0,0));
          list.add(new Coordinate(0,1));
          list.add(new Coordinate(0,2));
          list.add(new Coordinate(0,3));
          list.add(new Coordinate(1,3));
          list.add(new Coordinate(2,3));
          list.add(new Coordinate(3,3));
          list.add(new Coordinate(3,2));
          assertEquals(list, path);
    }
    @Test
    public void hugeTest() {
      int[][] exampleMaze = new int[5000][5000];
      for (int i=1; i<exampleMaze.length; i++) {
        for (int j=1; j<exampleMaze[0].length; j++) {
          exampleMaze[i][j]=1;
        }
      }
      final Coordinate start = new Coordinate(4000, 0);
      final Coordinate end = new Coordinate(0, 4000);
      final List<Coordinate> path = GraphsAndMazes.searchMaze(exampleMaze, start, end);
    }
}
