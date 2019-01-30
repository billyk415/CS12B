// Billy Kwong
// bqkwong
// 12B/12M
// 1/25/18
// Search.java
// pa2
// Determines whether or not the target word is amongst the words in the input file, 
// print a message to stdout stating whether or not the target was found, 
// and (optionally) state the line on which the target was found, if it is found. 

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Search {
	
	// mergeSort()
    // sort subarray A[p...r]
    public static void mergeSort(String[] A, int[] B, int p, int r){
      int q;
      if(p < r) {
         q = (p+r)/2;
         // System.out.println(p+" "+q+" "+r);
         mergeSort(A, B, p, q);
         mergeSort(A, B, q+1, r);
         merge(A, B, p, q, r);
      }
    }
	
	// merge()
    // merges sorted subarrays A[p..q] and A[q+1..r]
    public static void merge(String[] A, int[] C, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1];
      String[] R = new String[n2];
	  int[] Ln = new int[n1];
	  int[] Rn = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = A[p+i];
		 Ln[i] = C[p+i];
      }
      for(j=0; j<n2; j++){ 
         R[j] = A[q+j+1];
		 Rn[j] = C[q+j+1];
      }

      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if( i<n1 && j<n2 ){
            if( L[i].compareTo(R[j]) < 0 ){
               A[k] = L[i];
			   C[k] = Ln[i];
               i++;
            }else{
               A[k] = R[j];
			   C[k] = Rn[j];
               j++;
            }
         }else if( i<n1 ){
            A[k] = L[i];
			C[k] = Ln[i];
            i++;
         }else{ // j<n2
            A[k] = R[j];
			C[k] = Rn[j];
            j++;
         }
      }
    }

    // binarySearch()
    // pre: Array A[p..r] is sorted
    static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p > r) {
		 System.out.println(target + " not found");
         return -1;
      }else{
         q = (p+r)/2;
         if(target.compareTo(A[q]) == 0){
            return q;
         }else if(target.compareTo(A[q]) < 0){
            return binarySearch(A, p, q-1, target);
         }else{ // target > A[q]
            return binarySearch(A, q+1, r, target);
         }
      }
    }
	
	public static void main(String[] args) throws IOException{
		
		Scanner in = null;
		String[] sort = null;
		ArrayList<String> list = null; 
		int[] lineNumber = null;
		int match = 0;
		
		// if less than 2 arguments are given, print instructions and exit
		if(args.length<2){
			System.out.println("Usage: Search file target1 [target2 ..]");
			System.exit(1);
		}
		
		// Places words in file into an array list to find line numbers
		list = new ArrayList<String>();
		in = new Scanner(new File(args[0]));
		while(in.hasNextLine()){
			list.add(in.nextLine());
		}
		
		// converts to array
		sort = new String[list.size()];
		sort = list.toArray(sort);
		
		// create array for line numbers
		lineNumber = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			lineNumber[i] = i+1;
		}
		
		// calls mergeSort
		mergeSort(sort,lineNumber,0,sort.length-1);
		
		// loop that calls binarySearch and prints out line number if found
		for (int i = 1; i <args.length; i++){
			match = binarySearch(sort,0,sort.length-1,args[i]);
			if(match>=0){
				System.out.println(args[i]+" found on line "+lineNumber[match]);
			}	
		}	
	}
}