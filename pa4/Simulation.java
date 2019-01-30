// Billy Kwong
// bqkwong
// 12B/12M
// 2/19/2018
// Simulation.java
// pa4

import java.io.*;
import java.util.Scanner;

public class Simulation{
	
//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
	}

	public static void main(String[] args) throws IOException{

		if ( args.length != 1 ){
			System.err.println("Usage: Simulation input_file");
			System.exit(1);
		}
	
		Scanner in;
		PrintWriter report;
		PrintWriter trace;
       
		   try{
		   // open input file
		   in = new Scanner(new File(args[0]));
		   }
		   catch(IOException e){
		   throw new IOException("Failed to open specified input file");
		   }

		   try{
			// write files
			report = new PrintWriter(new FileWriter(args[0]+".rpt"));
			trace  = new PrintWriter(new FileWriter(args[0]+".trc"));
		   }
		   catch(IOException e){
		   throw new IOException("Failed to create report and/or trace file(s)");
		   }
		   
		   int m = in.nextInt();
		   Queue Q1  = new Queue();
		   Queue Q2 = new Queue();       

		   in.nextLine();
		   
		   for (int i = 0; i < m; i++){
		   Job j = new Job(in.nextInt(), in.nextInt());
		   Q1.enqueue(j);
		   Q2.enqueue(j);
		   }

		   outFiles(trace, report, args[0], m, Q2);

		   for(int n = 1; n < m; n++){

		   trace.println("*****************************");
		   if ( n == 1 ){
			   trace.println(n+" processor:");
		   }
		   else{
			   trace.println(n+" processors:");
		   }
		   trace.println("*****************************");	   

		   int time = 0;
		   int aT = 0;
		   int fT = 0;

		   int finish  = 0;
		   int jobsProcess = 0;

		   Queue[] ProcessorA = new Queue[n];

		   for(int i = 0; i < n; i++){
			   ProcessorA[i] = new Queue();
		   }
		   
		   if( Q2.isEmpty() ){
			   for(int i = 0; i < m; i++){
			   Job J = (Job) Q1.dequeue();
			   J.resetFinishTime();
			   Q1.enqueue(J);
			   Q2.enqueue(J);
			   }
		   }

		   printStatus(trace, time, Q2, ProcessorA);

		   while ( finish < m ){
			   if( jobsProcess < m ){
			   fT  = findfT(ProcessorA);
			   aT = findaT(Q2);
			   if( fT < aT && fT != Job.UNDEF ){
				   finish += finishJobs(trace, ProcessorA, Q2, fT);
				   time = fT;
				   printStatus(trace, time, Q2, ProcessorA);
			   }
			   else if ( fT > aT || fT == Job.UNDEF ){
				   time = aT;
				   jobsProcess += AddNextProcA(trace, ProcessorA, Q2, aT);
				   printStatus(trace, time, Q2, ProcessorA);
			   }
			   else if ( fT == aT ){
				   finish += finishJobs(trace, ProcessorA, Q2, fT);
				   jobsProcess += AddNextProcA(trace, ProcessorA, Q2, aT);
				   time = fT;
				   printStatus(trace, time, Q2, ProcessorA);
			   }
			   }
			   else{
			   fT  = findfT(ProcessorA);
			   finish += finishJobs(trace, ProcessorA, Q2, fT);
			   time = fT;
				   printStatus(trace, time, Q2, ProcessorA);
			   }
		   }
		   calcStatsAndReport(report, Q2, n);
		   }
			   in.close();
			   report.close();
		   trace.close();
	   }
		private static int  findfT(Queue[] ProcessorA){
		int fT = Job.UNDEF;

		for(int i = 0; i < ProcessorA.length; i++){
			if ( !ProcessorA[i].isEmpty() ){
			Job J = (Job)ProcessorA[i].peek();
			if ( fT == Job.UNDEF ){
				fT = J.getFinish();
			}
			else if ( fT > J.getFinish() ){
				fT = J.getFinish();
			}
			}
		}
		return (fT);
		}
		private static int  findaT(Queue Q2){
		return (((Job)Q2.peek()).getArrival());
		}
		
		private static int finishJobs(PrintWriter trace, Queue[] ProcessorA, Queue Q2, int fT){
		int finish = 0;
		for(int i =0; i < ProcessorA.length; i++){
			if ( !ProcessorA[i].isEmpty() ){
			if ( ((Job)ProcessorA[i].peek()).getFinish() ==  fT ){
				Job J = (Job)ProcessorA[i].dequeue();
				finish++;
				Q2.enqueue(J);
				if ( !ProcessorA[i].isEmpty() ){
				((Job)ProcessorA[i].peek()).computeFinishTime(fT);
				}
			}
			}
		}
		return (finish);
		}

		private static int AddNextProcA(PrintWriter trace, Queue[] ProcessorA, Queue Q2, int aT){
		
		int jobsProcess = 0;
		while( true ){
			if( Q2.isEmpty() || ((Job)Q2.peek()).getArrival() != aT ){
			break;
			}
			Job J = (Job)Q2.dequeue();
			int index = findQueue(ProcessorA);
			if( ProcessorA[index].isEmpty() ){
			J.computeFinishTime(aT);
			}
			ProcessorA[index].enqueue(J);
			jobsProcess++;
		}
		return jobsProcess;
		}
		private static int findQueue(Queue[] ProcessorA){

		int queueIndex = -1;
		int queueLength = -1;
		int qIndex = 0;
		boolean found = false;
		while ( !found && qIndex < ProcessorA.length ){
			if ( ProcessorA[qIndex].isEmpty() ){
			queueIndex = qIndex;
			found = true;
			}
			else{
			if( queueIndex == -1 ){
				queueIndex = qIndex;
				queueLength = ProcessorA[qIndex].length();
			}
			else if ( ProcessorA[qIndex].length() < queueLength ){
				queueIndex = qIndex;
				queueLength = ProcessorA[qIndex].length();
			}
			}
			qIndex++;
		}
		return (queueIndex);
		}

		private static void printStatus(PrintWriter trace, int time, Queue Q2, Queue[] ProcessorA){
		trace.println("time="+time);
		trace.println("0: "+Q2.toString());
		for(int i = 1; i <= ProcessorA.length ; i++){
			trace.println(i+": "+ProcessorA[i-1].toString());
		}
		trace.println();
		}
		private static void outFiles(PrintWriter trace, PrintWriter report, String fileName, int m, Queue Q2){
		   trace.println("Trace file: "+fileName+".trc");
		   trace.println(m+" Jobs:");
		   trace.println(Q2.toString());
		   trace.println();
		   report.println("Report file: "+fileName+".rpt");
		   report.println(m+" Jobs:");
		   report.println(Q2.toString());
		   report.println();
		   report.println("***********************************************************");
		}
		private static void calcStatsAndReport(PrintWriter report, Queue Q2, int n){
		int   totalWait   = 0;
		int   maxWait     = 0;
		int   numJobs     = Q2.length();
		float averageWait = 0;
		for(int i = 0; i < numJobs; i++){
			Job J = (Job)Q2.dequeue();
			totalWait += J.getWaitTime();
			if( J.getWaitTime() > maxWait ){
			maxWait = J.getWaitTime();
			}
		}
		averageWait = (float)totalWait/(float)numJobs;
		if( n == 1 ){
			report.println(n+" processor: totalWait="+totalWait+", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
		}
		else{
			report.println(n+" processors: totalWait="+totalWait+", maxWait="+maxWait+", averageWait="+String.format("%.2f", averageWait));
		}
		}
}