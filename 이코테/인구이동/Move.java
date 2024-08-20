package D_0819;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Move {
	
	static boolean ifcoun(int x, int y, int n) {
		if (((0 <= x) && (x < n)) &&((0 <= y) &&(y < n))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		Queue<int[]> line = new LinkedList<>();
		Queue<int[]> one = new LinkedList<>();
		
		int [][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		int [][] coun = new int [n][n];
		for (int i = 0 ; i < n ; i ++) {
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < n ; j ++) {
				coun[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		int b = 0;
		int cnt = 0;
		int day = 0;
		int [][] visit;
		
		
		while (true) {
			int ss  = 0;
			visit = new int [n][n];
			for (int i = 0 ; i < n ; i ++) {
				for (int j = 0 ; j < n ; j ++) {
					b = 0;
					cnt = 0;
					one = new LinkedList<>();
					line.add(new int[] {i, j});
					while(!line.isEmpty()) {
						int [] arr = line.poll();
						if (visit[arr[0]][arr[1]] != 1) {
							visit[arr[0]][arr[1]] = 1;
							one.add(new int[] {arr[0], arr[1]});
							cnt += coun[arr[0]][arr[1]];
							for (int [] a : dir) {
								int dx = arr[0] + a[0];
								int dy = arr[1] + a[1];
								if (ifcoun(dx, dy, n)) {
									int minus = Math.abs(coun[arr[0]][arr[1]] - coun[dx][dy]);
									if ((visit[dx][dy] != 1) &&((l <= minus) && (minus <= r))) {
										if (visit[arr[0]][arr[1]] != 1) {
											visit[arr[0]][arr[1]] = 1;
										}
										line.add(new int[] {dx, dy});
									}
								}
							}
						}
					}				
					
					if (one.size() > 1) {
						b = one.size();
						cnt = cnt / b; 
						while(!one.isEmpty()) {
							int [] arr = one.poll();
							coun[arr[0]][arr[1]] = cnt;
						}
						ss++;
					}
				}
				
			}
			if (ss == 0) {
				break;
			} else {
				day++;
			}
		}
		System.out.println(day);

	}

}
