package edu.yu.introtoalgs;

import java.util.*;

public class GraphsAndMazes {

  /** A immutable coordinate in 2D space.
   *
   * Students must NOT modify the constructor (or its semantics) in any way,
   * but can ADD whatever they choose.
   */
  public static class Coordinate { 
    public final int x, y;
    
    /** Constructor, defines an immutable coordinate in 2D space.
     *
     * @param x specifies x coordinate
     * @param y specifies x coordinate
     */
    public Coordinate(final int x, final int y) {
        if (x<0||y<0) {
            throw new IllegalArgumentException();
        }
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals (Object o) {
        if (o==null) {
            return false;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate c = (Coordinate)o;
        if (this.x==c.x&&this.y==c.y) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    public List<Coordinate> getAdjacentCoordinates() {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(this.x+1, this.y));
        if (this.x!=0) {
            list.add(new Coordinate(this.x-1, this.y));
        }
        list.add(new Coordinate(this.x, this.y+1));
        if (this.y!=0) {
            list.add(new Coordinate(this.x, this.y-1));
        }
        return list;
    }
    /** Add any methods, instance variables, static variables that you choose
     */
  } // Coordinate class

  public static class RecursiveFunction {
    boolean[][] boolArr;
    final int[][] maze;
    Coordinate start;
    final Coordinate end;
    Coordinate[][] parent;
    Stack<Coordinate> stack;
    public RecursiveFunction(final int[][] maze, Coordinate start, final Coordinate end) {
        this.maze=maze;
        this.start=start;
        this.end=end;
        this.boolArr = new boolean[this.maze.length][];
        for (int i=0; i<maze.length; i++) {
            this.boolArr[i] = new boolean[this.maze[i].length];
        }
        this.parent = new Coordinate[this.maze.length][];
        for (int i=0; i<maze.length; i++) {
            this.parent[i] = new Coordinate[this.maze[i].length];
        }
        this.stack = new Stack<>();
    }
    public List<Coordinate> run() {
        repeat(this.start);
        List<Coordinate> list = new ArrayList<>();
        if (this.start.equals(this.end)) {
            list.add(this.end);
            return list;
        }
        Coordinate c = this.parent[end.x][end.y];
        if (c==null) {
            return list;
        } else {
            list.add(0, end);
            while (c!=null) {
                list.add(0, c);
                c = this.parent[c.x][c.y];
            }
            return list;
        }
    }
    private void repeat(Coordinate startHere) {
        //add to list
        stack.push(startHere);
        //set boolean array marked as true
        boolArr[startHere.x][startHere.y]=true;
        if (startHere.equals(end)) {
            return;
        }
        while (!stack.isEmpty()) {
            Coordinate coord = stack.pop();
            if (coord.equals(end)) {
                return;
            }
            for (Coordinate c : coord.getAdjacentCoordinates()) {
                if (isInRange(c.x, c.y)&&!boolArr[c.x][c.y]&&this.maze[c.x][c.y]==0) {
                    stack.push(c);
                    boolArr[c.x][c.y]=true;
                    this.parent[c.x][c.y]=coord;
                }
            }
        }
    }
    private boolean isInRange(int x, int y) {
        return !(x<0 || x>=maze.length || y<0 || y>=maze[x].length);
    }
  }
  /** Given a maze (specified by a 2D integer array, and start and end
   * Coordinate instances), return a path (beginning with the start
   * coordinate, and terminating wih the end coordinate), that legally
   * traverses the maze from the start to end coordinates.  If no such
   * path exists, returns an empty list.  The path need need not be a
   * "shortest path".
   *
   * @param maze 2D int array whose "0" entries are interpreted as
   * "coordinates that can be navigated to in a maze traversal (can be
   * part of a maze path)" and "1" entries are interpreted as
   * "coordinates that cannot be navigated to (part of a maze wall)".
   * @param start maze navigation must begin here, must have a value
   * of "0"
   * @param end maze navigation must terminate here, must have a value
   * of "0"
   * @return a path, beginning with the start coordinate, terminating
   * with the end coordinate, and intervening elements represent a
   * legal navigation from maze start to maze end.  If no such path
   * exists, returns an empty list.  A legal navigation may only
   * traverse maze coordinates, may not contain coordinates whose
   * value is "1", may only traverse from a coordinate to one of its
   * immediate neighbors using one of the standard four compass
   * directions (no diagonal movement allowed).  A legal path may not
   * contain a cycle.  It is legal for a path to contain only the
   * start coordinate, if the start coordinate is equal to the end
   * coordinate.
   */
  public static
    List<Coordinate> searchMaze
    (final int[][] maze, final Coordinate start, final Coordinate end) {
    if (maze==null||start==null||end==null||start.x<0||start.y<0||end.x<0||end.y<0||start.x>=maze.length||end.x>=maze.length||start.y>=maze[start.x].length||end.y>=maze[end.x].length||maze[start.x][start.y]==1||maze[end.x][end.y]==1||maze.length==0) {
        throw new IllegalArgumentException();
    }
    RecursiveFunction r = new RecursiveFunction(maze, start, end);
    return r.run();
  }

  /** minimal main() demonstrates use of APIs
   */
  public static void main (final String[] args) {
        final int[][] exampleMaze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };

        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        final List<Coordinate> path = searchMaze(exampleMaze, start, end);
        System.out.println("path="+path);
    }   
}