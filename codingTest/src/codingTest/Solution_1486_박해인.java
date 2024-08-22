package codingTest;

import java.io.*;
import java.util.*;

import codingTest.MeetingRoomTest.Meeting;

public class Solution_1486_¹ÚÇØÀÎ {
	
	public static boolean [] visited ;
	public static int [] input;
	public static List<Integer> output; 
	public static int N, B, sum;
	
//	static class Person{
//		
//		int height;
//		
//		public Person(int height) {
//			this.height = height;
//		};
//	}
//	
	public static void subset(int depth) {
		
		if(depth == N) {
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					
					sum+=input[i];
				}
			}
		
		
		if(sum>=B) {
			output.add(sum);
		}
		sum = 0;
		//output.clear();
		
		
		return;
		}

		visited[depth] = true;
		subset(depth+1);
		visited[depth] = false;
		subset(depth+1);

	}
	
	
	
	
	public static void main(String [] args) throws IOException {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
		
			st = new StringTokenizer(br.readLine());
			
			visited = new boolean[N];
			input = new int[N];
			output = new ArrayList<>();
			
			for(int n=0; n<N; n++) {
				int x = Integer.parseInt(st.nextToken());
				input[n] = x;
				//persons[n] = new Person(x);
			}
			sum = 0;
			subset(0);
			
			int min = Integer.MAX_VALUE;
			
			for(int i=0; i<output.size(); i++) {
				if(output.get(i)<min) {
					min = output.get(i);
				}
			}
			
			System.out.println("#"+(t+1)+" "+(min-B));
			
		}
		
	
		
		
		
		
		
	}
}
