package D_1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1953_김가연 {
	
	static int n;
	static int m;
	
	static boolean ifmap(int x, int y) {
		if (((0 <= x) && (x < n)) && ((0 <= y) && (y < m))) {
			return true;
		} else {
			return false;
		}
	}
	
	static int[] ifgo(int n) {
		if (n == 1) {
			return new int[] {0, 1, 2, 3};
		} else if (n == 2) {
			return new int[] {0, 1};
		} else if (n == 3) {
			return new int[] {2, 3};
		} else if (n == 4) {
			return new int[] {0, 3};
		} else if (n == 5) {
			return new int[] {1, 3};
		} else if (n == 6) {
			return new int[] {1, 2};
		} else {
			return new int[] {0, 2};
		}
	}
	
	static boolean ifcon(int w, int d) {
		
		if (d == 0) {
			return false;
		}
		
		if (w == 0) {
			if ((d == 2) || (d == 5) || (d == 6) || (d == 1)) {
				return true;
			}
		} else if (w == 1) {
			if ((d == 2) || (d == 4) || (d == 7) || (d == 1)) {
				return true;
			}
		} else if (w == 2) {
			if ((d == 3) || (d == 4) || (d == 5) || (d == 1)) {
				return true;
			}
		} else {
			if ((d == 3) || (d == 6) || (d == 7) || (d == 1)) {
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= tc ; test_case ++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			int [][] prison = new int [n][m];
			char [][] draw = new char [n][m];
			boolean [][] visit = new boolean [n][m];
			
			int px = Integer.parseInt(st.nextToken());
			int py = Integer.parseInt(st.nextToken());
			
			int time = Integer.parseInt(st.nextToken());
			
			for (int i = 0 ; i < n ; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0 ; j < m ; j++) {
					prison[i][j] = Integer.parseInt(st.nextToken());
					
					if (prison[i][j] == 0) {
						
					} else if (prison[i][j] == 1) {
						draw[i][j] = '+';
					} else if (prison[i][j] == 2) {
						draw[i][j] = 'ㅣ';
					} else if (prison[i][j] == 3) {
						draw[i][j] = 'ㅡ';
					} else if (prison[i][j] == 4) {
						draw[i][j] = '└';
					} else if (prison[i][j] == 5) {
						draw[i][j] = '┏';
					} else if (prison[i][j] == 6) {
						draw[i][j] = '┐';
					} else {
						draw[i][j] = '┘';
					}
				}
			}
			
			int [][] dir = new int [][] {{-1 , 0}, {1, 0}, {0, -1}, {0, 1}};
			
			int answer = 1;
			
			Queue<int []> where = new LinkedList<>();
			where.add(new int[] {px, py, prison[px][py], 1});
			visit[px][py] = true;
			
			while(!where.isEmpty()) {
				
				int [] w = where.poll();
				if (w[3] < time) {
					
					
					int wx = w[0];
					int wy = w[1];
					
					int [] wroad = ifgo(w[2]);
					
					
					for (int i = 0 ; i < wroad.length ; i++) {
						int dx = dir[wroad[i]][0] + wx;
						int dy = dir[wroad[i]][1] + wy;
						
						
						if (ifmap(dx, dy)) {
							if ((ifcon(wroad[i], prison[dx][dy])) && (!visit[dx][dy])) {
								
								
								visit[dx][dy] = true;
								if ((w[3] + 1) <= time) {
									answer += 1;
								}
								where.add(new int[] {dx, dy, prison[dx][dy], w[3] + 1});
							}
						}
					}
					
				}

			}
			
			System.out.printf("#%d %d \n", test_case, answer);
			
		}

	}

}
