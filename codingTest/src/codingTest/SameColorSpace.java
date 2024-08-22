package codingTest;

import java.io.*;
import java.util.*;


public class SameColorSpace {
/*
8
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
0 0 0 0 1 1 0 0
0 0 0 0 1 1 0 0
1 0 0 0 1 1 1 1 
0 1 0 0 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1
*/
	static int N, map[][];
	static int white, green;
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for( int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();	
			}
		}

		cut(0,0,N);
		System.out.println(white);
		System.out.println(green);
	}
	
	//시작점이 (r,c) 에서 size만큼 크기의 공간을 확인한다.
	static void cut(int r, int c, int size) {
		
		// 주어진 공간이 모든 같은 색으로 이루어졌는지 체크
		// 같은 색으로 이루어져 있지 않으면 4분할
		// 같은 색이며 분할하지 않음
		int sum = 0;
		for(int i =r, rEnd = r+size; i<rEnd; i++) {
			for(int j = c, cEnd = c+size; j<cEnd; j++) {	
				sum += map[i][j];
			}
		}
		//같은색이면 분할하지 않음
		if(sum == 0) {
			white++;
			return;
		}else if(sum == size*size) {
			green++;
			return;
		}else {
			// 같은 색으로 이루어져 있지 않으면 4분할
			int half = size/2;
			//#1
			cut(r,c,half);
			//#2
			cut(r,c+half,half);
			//#3
			cut(r+half,c,half);
			//#4
			cut(r+half,c+half,half);
		}
	}
	
	
	
}
