//-----------------------------------------------------------------------------
//  ProbeSequence.java
//  This is the solution to problem 8b on the 12B final review sheet.  The
//  program can be used to generate the solutions to problems 8a and 7ac.
//
//  Compile:  javac ProbeSequence.java
//  
//-----------------------------------------------------------------------------

public class ProbeSequence {

   static final int tableSize = 11;

   // h1()
   static int h1(int k){ 
      return k%tableSize; 
   }

   // h2()
   static int h2(int k){ 
      return 1+k%(tableSize-1);
      // return 5; 
   }

   // h()
   static int h(int k, int i){
      return ( h1(k) +  h2(k)*i )%tableSize;  
   }


   public static void main(String[] args) {
   
      int[] keys = {34, 25, 79, 56, 6};

      for(int k : keys){
         System.out.print(k+": ");
         for(int i = 0; i<tableSize; i++){
            System.out.print(h(k, i)+" ");
         }
         System.out.println();
      }
   }
}