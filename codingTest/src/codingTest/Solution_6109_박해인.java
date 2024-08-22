package codingTest;

import java.util.*;
import java.io.*;

public class Solution_6109_박해인 {
/*
2
5 up
4 8 2 4 0
4 4 2 0 8
8 0 2 4 4
2 2 2 2 8
0 2 2 0 0
2 down
16 2
0 2
*/
	static int [][] map;
	static boolean [][] tmp;
	static int N;
	static int dx,dy,posX,posY;
	
	static void move(String keyword) {
		
		switch(keyword) {
		case "up":
			dx=-1; dy=0;
			break;
		case "down":
			dx=1; dy=0;
			break;
		case "left":
			dx=0; dy=-1;
			break;
		case "right":
			dx=0; dy=1;
			break;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {		
				posX = i+dx;
				posY =j+dy;
				
				if(posX<N&&posY<N) {
					
					tmp[posX][posY] = true;
					
				}
			
						
						
						
			}
		}
	
		
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {		
				System.out.print(map[i][j]);
			}
		System.out.println();
		}
	}
	
	public static void main(String [] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			//---------------테케시작
			
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String keyword = st.nextToken();
			map = new int[N][N];
			tmp = new boolean[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			move(keyword);
			print();
			
			
			
			//---------------테케끝
		}
		
	}
}
