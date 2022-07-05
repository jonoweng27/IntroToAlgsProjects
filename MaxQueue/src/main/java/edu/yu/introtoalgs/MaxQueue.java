package edu.yu.introtoalgs;

/** Enhances the Queue enqueue() and dequeue() API with a O(1) max()
 * method and O(1) size().  The dequeue() method is O(1), the enqueue
 * is amortized O(1).  The implementation is O(n) in space.
 *
 * @author Avraham Leff
 */

import java.util.NoSuchElementException;

public class MaxQueue {
  public class SinglyLinkedItem {
    public int value;
    public SinglyLinkedItem next;
    public SinglyLinkedItem(int value) {
      this.value=value;
    }
  }

  private SinglyLinkedItem head;
  private SinglyLinkedItem tail;
  private int max;
  private int size;

  /** No-argument constructor: students may not add any other constructor for
   * this class
   */
  public MaxQueue() {
    this.head=null;
    this.tail=null;
    this.max=-1;
    this.size=0;
  }

  /** Insert the element with FIFO semantics
   *
   * @param x the element to be inserted.
   */
  public void enqueue(int x) {
      SinglyLinkedItem item = new SinglyLinkedItem(x);
      //if this is going to be the only item in the list, make it the head
      if (this.size==0) {
        this.head=item;
      //if there is is only one other item in the list, make this item the tail and make the other item (the head)'s next be it
      } else if (this.size==1) {
        this.tail=item;
        this.head.next=this.tail;
      //if there are 2+ items already in the list, make this item the new tail
      } else {
        this.tail.next = item;
        this.tail = item;
      }
      //increase size
      this.size++;
      //check to see if max changed
      if (x>this.max) {
        this.max=x;
      }
  }

  /** Dequeue an element with FIFO semantics.
   *
   * @return the element that satisfies the FIFO semantics if the queue is not
   * empty.
   * @throws NoSuchElementException if the queue is empty
   */
  public int dequeue() {
    //if no items in queue, throw exception
      if (this.size==0) {
        throw new NoSuchElementException();
      }
      //remove item from the head
      SinglyLinkedItem item = this.head;
      this.head = this.head.next;
      //decrease eize by 1
      this.size--;
      //if there is only one item in the list, have that item be at the head and make the tail null
      if (this.size==1) {
        this.tail=null;
      }
      //if there are no items in the list, update the max to be -1 (i.e. nonexistent)
      if (this.size==0) {
        this.max=-1;
      }
      //if there are items still in the list, 
      //check to see if the max was removed
      else if (item.value==this.max) {
        //if it was removed,
        //set max to value of the head (will be changed)
        this.max=this.head.value;
        //iterate through linked list to find the highest value in the listand make it the new max
        SinglyLinkedItem goThrough = this.head;
        while(goThrough!=null) {
          if (goThrough.value>this.max) {
            this.max=goThrough.value;
          }
          goThrough=goThrough.next;
        }
      }
      //return the value of the item that was removed
      return item.value;
  }

  /** Returns the number of elements in the queue
   *
   * @return number of elements in the queue
   */
  public int size() {
      return this.size;
  }


  /** Returns the element with the maximum value
   * 
   * @return the element with the maximum value
   * @throws NoSuchElementException if the queue is empty
   */
  public int max() {
    //check to make sure there is at least one item in the queue
    if (this.size==0) {
      throw new NoSuchElementException();
    }
    return this.max;
  }
  
} // MaxQueue
