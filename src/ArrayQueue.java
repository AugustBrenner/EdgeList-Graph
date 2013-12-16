/******************************************************************************
 *
 * ArrayQueue
 *  a Queue data structure implementing FIFO functionality with an array
 *  based implementation.
 *
 * @see
 *   <A HREF="https://github.com/AugustBrenner">
 *       Checkout my GitHub</A>
 *
 * @author
 * August Brenner
 * G00682282
 *
 * @version
 *   December 18th, 2013
 ******************************************************************************/

public class ArrayQueue 
{
  private Object[] queue;           // Array that holds queue elements
  private int capacity;             // size of the array (capacity of the queue)
  private int numItems = 0;         // number of items on the queue
  private int front = 0;           // index of front of queue
  private int rear = -1;             // index of rear of queue

  // Constructors
  public ArrayQueue() 
  {
    queue = new Object[100];
    capacity = 100;
  }

  public ArrayQueue(int maxSize) 
  {
    queue = new Object[maxSize];
    capacity = maxSize;
  }

  public void enqueue(Object item)
  // Adds an element to the front of this queue
  {      
    rear = (rear + 1) % capacity;
    queue[rear] = item;
    numItems = numItems + 1;
  }

  public Object dequeue()
  // Removes an element from the rear of this queue
  {       
    Object toReturn = queue[front];
    queue[front] = null;
    front = (front + 1) % capacity;
    numItems = numItems - 1;
    return toReturn;
  }

  public boolean isEmpty()
  // Checks if this queue is empty
  {              
    return (numItems == 0);
  }

  public boolean isFull()
  // Checks if this queue is full
  {              
    return (numItems == capacity);
  }
}