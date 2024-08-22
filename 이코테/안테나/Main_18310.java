package 안테나;


import java.io.*;
import java.util.*;


public class Main_18310 {

/*
4
5 1 7 9
*/
	static int N,sum,min, minIdx;	
	static int[] map, diff;
	
	public static void main(String [] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		
		st = new StringTokenizer(br.readLine());
		
		map = new int[N];
		diff = new int[N];
		
		for(int n = 0; n<N; n++) {
			map[n] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		minIdx = 0;
		  Arrays.sort(map);
		
//		for(int n=0; n<N; n++) {
//			sum = 0;
//			for(int k=0; k<N; k++) {
//				sum+= Math.abs(map[n]-map[k]);
//				if(sum>=min) {
//					break;
//				}
//			}
//			if(min>sum) {git
//				min = sum;
//				minIdx = n;
//			}
//		}
//		
//		System.out.println(map[minIdx]);
	        // 중앙값 출력
	        System.out.println(map[(N - 1) / 2]);
		
	}
	

}
