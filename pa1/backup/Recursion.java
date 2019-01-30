//-----------------------------------------------------------------------------
// Billy Kwong
// bqkwong
// 12B/12M
// 1/15/2018
// Recursion.java
// Gain experience implementing recursive methods with headings
//-----------------------------------------------------------------------------

class Recursion {
   
   // reverseArray1()
   // Places the leftmost n elements of X[] into the rightmost n positions in
   // Y[] in reverse order
   static void reverseArray1(int[] X, int n, int[] Y){
	//if (n==0) do nothing
	if(n > 0) {
		Y[X.length - n] = X[n - 1];
		reverseArray1(X, n-1, Y);
	}
   }

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
   static void reverseArray2(int[] X, int n, int[] Y){
	 //if (n==0) do nothing
	 if(n > 0){ 
	 reverseArray2(X, n-1, Y);  
	 Y[n -1] = X[Y.length - n];
	 }
   }
   
   // reverseArray3()
   // Reverses the subarray X[i...j].
   static void reverseArray3(int[] X, int i, int j){
	if(i < j){   
	int temp = X[i];
	X[i] = X[j];
	X[j] = temp;
	i++;
	j--;
	reverseArray3(X, i, j);
	}
   }
   
   // maxArrayIndex()
   // returns the index of the largest value in int array X
   static int maxArrayIndex(int[] X, int p, int r){
        int q = (p+r)/2;
        int max1;
        int max2;

        if(p < r){
            max1 = maxArrayIndex(X, p, q);
            max2 = maxArrayIndex(X, q+1, r);
            if(X[max1] > X[max2]){
                return max1;
            }
            else{
                return max2;
            }
        }
        return r;
    }
   
   // minArrayIndex()
   // returns the index of the smallest value in int array X
    static int minArrayIndex(int[] X, int p, int r){
        int q = (p+r)/2;
        int min1;
        int min2;

        if(p < r){
            min1 = minArrayIndex(X, p, q);
            min2 = minArrayIndex(X, q+1, r);
            if(X[min1] < X[min2]){
                return min1;
            }
            else{
                return min2;
            }
        }
        return r;
    }
   
   // main()
   public static void main(String[] args){
      
      int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
      int[] B = new int[A.length];
      int[] C = new int[A.length];
      int minIndex = minArrayIndex(A, 0, A.length-1);
      int maxIndex = maxArrayIndex(A, 0, A.length-1);
      
      for(int x: A) System.out.print(x+" ");
      System.out.println(); 
      
      System.out.println( "minIndex = " + minIndex );  
      System.out.println( "maxIndex = " + maxIndex );  

      reverseArray1(A, A.length, B);
      for(int x: B) System.out.print(x+" ");
      System.out.println();
      
      reverseArray2(A, A.length, C);
      for(int x: C) System.out.print(x+" ");
      System.out.println();
      
      reverseArray3(A, 0, A.length-1);
      for(int x: A) System.out.print(x+" ");
      System.out.println();  
      
   }
   
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/