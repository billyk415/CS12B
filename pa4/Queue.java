// Billy Kwong
// bqkwong
// 12B/12M
// 2/19/2018
// Queue.java
// pa4

public class Queue implements QueueInterface{
	private class Node{
		Object item;
		Node next;
		Node(Object newItem){
			item = newItem;
			next=null;
		}
	}

	// Fields
	private Node head = null;
	private Node tail = null;
	private int numItems = 0;

	// isEmpty()
	// pre: none
	// post: returns true if this Queue is empty, false otherwise
	public boolean isEmpty(){
		return (numItems == 0);
	}

	// length()
	// pre: none
	// post: returns the length of this Queue.
	public int length(){
		return numItems;
	}

	// enqueue()
	// adds newItem to back of this Queue
	// pre: none
	// post: !isEmpty()
	public void enqueue(Object newItem){
		Node N = new Node(newItem);
		if (isEmpty()){
			head = tail = N;
		}
		else{
			tail.next = N;
			tail = N;
		}
		numItems++;
	}

	// dequeue()
	// deletes and returns item from front of this Queue
	// pre: !isEmpty()
	// post: this Queue will have one fewer element
	public Object dequeue() throws QueueEmptyException{
		if (isEmpty()){
			throw new QueueEmptyException("cannot dequeue() empty queue");
		}
		Object x = head.item;
		if (head == tail){
			head = tail = null;
		}
		else{
			head = head.next;
		}
		numItems--;
		return x;
    }
		

	// peek()
	// pre: !isEmpty()
	// post: returns item at front of Queue
	public Object peek() throws QueueEmptyException{
		if (isEmpty()){
				throw new QueueEmptyException("cannot peek() empty queue");
			}
		return head.item;
    }
			
		

	// dequeueAll()
	// sets this Queue to the empty state
	// pre: !isEmpty()
	// post: isEmpty()
	public void dequeueAll() throws QueueEmptyException{
		if (isEmpty()){
				throw new QueueEmptyException("cannot dequeue() empty queue");
			}
		head = tail = null;
		numItems = 0;
    }

	// toString()
	// overrides Object's toString() method
	public String toString(){
	    String s = "";
        Node n = head;
        for (; n != null ; n = n.next){
            s += n.item.toString() + " ";
        }
        return s;
    }
}