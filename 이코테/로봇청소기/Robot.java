package D_0827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot {
	
	static boolean ifroom(int x, int y, int row, int col) {
		if (((0 <= x) && (x < row)) && ((0 <= y) && (y < col))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int [][] dir = new int [][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int [][] room = new int [n][m];
		int [][] visit = new int [n][m];
		
		Queue<int []> inrm = new LinkedList<>();
		StringTokenizer rb = new StringTokenizer(bf.readLine());
		int rx = Integer.parseInt(rb.nextToken());
		int ry = Integer.parseInt(rb.nextToken());
		int rd = Integer.parseInt(rb.nextToken());
		
		for (int i = 0 ; i < n ; i++) {
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < m ; j++) {
				room[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		int cnt = 1;
		visit[rx][ry] = 1;
		inrm.add(new int[] {rx, ry, rd});
		while(!inrm.isEmpty()) {
			int [] robot = inrm.poll();
			int d = robot[2] - 1;
			int tmp = 0;
			for (int i = 0 ; i < dir.length ; i++) {
				if (d < 0) {
					d = 3;
				}
				int dx = robot[0] + dir[d][0];
				int dy = robot[1] + dir[d][1];
				if (ifroom(dx, dy, n, m)) {
					if ((room[dx][dy] == 0) && (visit[dx][dy] != 1)) {
						visit[dx][dy] = 1;
						cnt += 1;
						inrm.add(new int[] {dx, dy, d});
						break;
					} else {
						tmp += 1;
					}
				}
				d--;
			}
			
			if (tmp == 4) {
				if (robot[2] == 0) {
					if (room[robot[0] + dir[2][0]][robot[1] + dir[2][1]] == 1) {
						break;
					} else {
						inrm.add(new int[] {robot[0] + dir[2][0], robot[1] + dir[2][1], robot[2]});
					}
				} else if (robot[2] == 1) {
					if (room[robot[0] + dir[3][0]][robot[1] + dir[3][1]] == 1) {
						break;
					} else {
						inrm.add(new int[] {robot[0] + dir[3][0], robot[1] + dir[3][1], robot[2]});
					}
				} else if (robot[2] == 2) {
					if (room[robot[0] + dir[0][0]][robot[1] + dir[0][1]] == 1) {
						break;
					} else {
						inrm.add(new int[] {robot[0] + dir[0][0], robot[1] + dir[0][1], robot[2]});
					}
				} else {
					if (room[robot[0] + dir[1][0]][robot[1] + dir[1][1]] == 1) {
						break;
					} else {
						inrm.add(new int[] {robot[0] + dir[1][0], robot[1] + dir[1][1], robot[2]});
					}
				}
			}
			
		}

		
		System.out.println(cnt);
	}

}
