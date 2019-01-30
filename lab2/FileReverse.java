//Billy Kwong
//bqkwong
//12B/12M
//1/24/18
//lab2
//FileReverse.java
//takes two command line arguments giving the names of the input and output files. The program will read each line of input, parse the tokens, and then print each token backwards to the output file.

import java.io.*;
import java.util.Scanner;

class FileReverse{
	public static void main(String[] args) throws IOException{
		
		Scanner in = null;
		PrintWriter out = null;
		String line = null;
		String[] token = null;
		int i, n, lineNumber = 0;
		
		// check number of command line is at least 2
		if (args.length < 2){
			System.out.println("Usage: FileCopy <input file> <output file>");
			System.exit(1);
		}
		
		// open files
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		
		// read lines from in, extract and print tokens from each line
		while( in.hasNextLine() ){
			lineNumber++;
			
				// trim leading and trailing spaces, then add one trailing space so
				// split works on blank lines
				line = in.nextLine().trim() + " "; 

				// split line around white space 
				token = line.split("\\s+"); 

				// print out tokens 
				n = token.length;
				for(i=0; i<n; i++){
					out.write(stringReverse(token[i], token[i].length()));
					out.write("\n");
				}
		}
			
		// close files
		in.close();
		out.close();
	}

public static String stringReverse(String s, int n){
	if (n == 1){
		return ""+s.charAt(0);
	} else{ //recursion
		return s.charAt(n-1) + stringReverse(s, n-1);
	}
}
}
