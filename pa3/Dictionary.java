// Billy Kwong
// bqkwong
// 12B/12M
// 2/7/2018
// Dictionary.java
// Implement a Dictionary ADT based on the linked list data structure.

public class Dictionary implements DictionaryInterface{
	private class Node{
		String key;
		String value;
		Node next;
		
		Node (String k, String v){
			key = k;
			value = v;
			next = null;
		}
	}
	
	// Fields for the Dictionary class
	private Node head;
	private int numItems;
	
	// Dictionary()
	// constructor for the Dictionary class
	public Dictionary(){
		head = null;
		numItems = 0;
	}
	
	// find()
	// returns a reference to the Node at position key in this Dictionary
	private Node findKey(String key){
		Node N = head;
		for (N = head; N != null; N = N.next){
			if (N.key.equals(key)){
				break;
			}
		}
		return N;
	}
	
	// isEmpty()
    // pre: none
    // post: returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
        return(numItems == 0);
    }

    // size()
    // pre: none
    // post: returns the number of elements in this Dictionary
    public int size() {
        return numItems;
    }

	//lookup()
	//pre: none
    //post: returns value associated key, or null reference if no such key exists
	public String lookup(String key){
		Node N = head;
		 while (N!= null){
		 if( N.key.equals(key)){
			return N.value;
		 }
	    
		 N = N.next;
		 }
		 return null;
	}
	
	// insert()
    // inserts newItem into this Dictionary at position key
    // pre: 1 <= index <= size()+1
    // post: !isEmpty(), items to the right of newItem are renumbered
    public void insert(String key, String value) throws DuplicateKeyException{
        if (lookup(key) != null){
			throw new DuplicateKeyException( "cannot insert duplicate keys ");
        }
        if(head == null){
			Node N = new Node(key, value);
			N.next = head;
			head = N;
			numItems++;                                     // new addition to increment size of array when you add first one
		}else{ // adds to the back of the list
			Node N = head;
			while( N != null){ // while the reference value is not empty
			if(N.next == null){  // check if the next one is empty. if it is empty break
            break;
			}
			N = N.next; // will keep going to Next key until there is an empty node 
        }
        N.next = new Node(key,value); // places new key and value
        numItems++; // increments up numitems by one since value is added
      }
    }
	
	// delete()
    // deletes item at position index from this Dictionary
    // pre: 1 <= index <= size()
    // post: items to the right of deleted item are renumbered
	public void delete(String key) throws KeyNotFoundException {
		if (lookup(key) == null) {
			throw new KeyNotFoundException("cannot delete non-existent key");
		} else {
			if (numItems <= 1) {
				Node N = head;
				head = head.next;
				N.next = null;
				//numItems--;
			} else {
				Node N = head;
				if (N.key.equals(key)) {
					head = N.next;
					//numItems--;
				} else {
					while(!N.next.key.equals(key))
						N = N.next;
					N.next = N.next.next;
					//numItems--;
				}
			}
			numItems--;
		}
	}
	
	// makeEmpty()
    // pre: none
    // post: isEmpty()
    public void makeEmpty(){
		head = null;
		numItems = 0;
    }

	// toString
    // pre: none
    // post:  prints current state to stdout
    // Overrides Object's toString() method
	public String toString() {
		//return myString(head);
		String s = "";
		Node N = head;
		while(N != null) {
			s += N.key + " " + N.value + "\n";
			N = N.next;
		}
		return s;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	