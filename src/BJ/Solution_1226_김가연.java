package D_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1226_김가연 {
	
	static boolean ifmap(int x, int y) {
		
		if (((0 <= x) && (x < 16)) && ((0 <= y) && (y < 16))) {
			return true;
		} else {
			return false;
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0 ; i < 10 ; i ++) {
			
			int [][] miro = new int [16][16];
			boolean [][] visit = new boolean [16][16];
			
			int [][] dir = new int [][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
			
			int sx = 0, sy = 0;
			int test_case = Integer.parseInt(bf.readLine());
			
			for (int j = 0 ; j < 16 ; j++) {
				
				String line = bf.readLine();
				for (int k = 0 ; k < 16 ; k++) {
					miro[j][k] = line.charAt(k) - 48;
					
					if (miro[j][k] == 2) {
						sx = j;
						sy = k;
					}
					
				}
				
			}
			
			Queue<int[]> go = new LinkedList<>();
			visit[sx][sy] = true;
			
			go.add(new int [] {sx, sy});
			
			int answer = 0;
			
			while (!go.isEmpty()) {
				
				int [] g = go.poll();
				
				if ((miro[g[0]][g[1]] == 3)) {
					answer = 1;
					break;
				}
				
				for (int [] d : dir) {
					
					int dx = d[0] + g[0];
					int dy = d[1] + g[1];
					
					if (ifmap(dx, dy)) {
						if ((!visit[dx][dy]) && (miro[dx][dy] != 1)) {
							visit[dx][dy] = true;
							go.add(new int [] {dx, dy});
						}
					}
					
				}
				
			}
			
			System.out.printf("#%d %d \n", test_case, answer);
		}

	}

}
