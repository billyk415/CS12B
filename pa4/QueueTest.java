// Billy Kwong
// bqkwong
// 12B/12M
// 2/19/2018
// QueueTest.java
// pa4
// Test Client for the Queue class

import java.io.*;
import java.util.Scanner;

public class QueueTest{
   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

   public static void main(String[] args) throws IOException{                       
       Queue Q = new Queue();       
       Job j1 = new Job(3, 4);                                                                                           
       Job j2 = new Job(1, 1);                                                                                           
       Job j3 = new Job(2, 12);
       Q.enqueue(j1);                                                                                                     
       Q.enqueue(j2);          
       Q.enqueue(j3);
       System.out.println("Is Queue Empty? "+Q.isEmpty());                                                                
       System.out.println("Queue Length = "+Q.length());                                                                  
       System.out.println("Queue elements: "+ Q.toString());                  
       System.out.println("First element in current Q: "+Q.peek().toString());                                            
       Q.dequeue();                                                                                                       
       System.out.println("After first dq: "+Q.toString());                                                             
       Q.dequeueAll();
       System.out.println("Is Queue Empty? "+Q.isEmpty());     
       System.out.println("Queue Length = "+Q.length());
       
   }    
}