package 혁진이의프로그램검증;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 혁진이의프로그램검증_장희주 {

	static class Node {
		int x, y, direction,memory;

		public Node(int x, int y, int direction,int memory) {

			this.x = x;
			this.y = y;
			this.direction = direction;
			this.memory=memory;
		}

	}

	static int sum;
	static int R, C;
	static char[][] array;
	// 우좌상하
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int direction;
	static int memory;
	static Queue<Node>q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			array = new char[R][C];
			memory = 0;
			direction = 0;
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					array[i][j] = str.charAt(j);
				}
			}
			sum = 0;
			String result = null;
			result = simul(0, 0);
			System.out.println("#" + test_case + " " + result);
		}

	}

	static String simul(int x, int y) {

		q=new ArrayDeque<>();
		q.offer(new Node(x, y, 0,0));
		boolean visited[][][][] = new boolean[R][C][16][4];
		while (!q.isEmpty()) {
			Node c = q.poll();
			int cx = c.x;
			int cy = c.y;
			direction = c.direction;
			memory=c.memory;
			if (array[cx][cy] == '@') {
				return "YES";
			} else if (array[cx][cy] == '?') {
				
				for(int i=0;i<4;i++) {
					int nx=cx+dx[i];
					int ny=cy+dy[i];
					if(nx<0 || ny<0 || nx>=R || ny>=C || visited[nx][ny][memory][i]) {
						continue;
					}
					q.add(new Node(nx,ny,i,memory));
					visited[nx][ny][memory][i]=true;
					continue;
				}
			} 
				dir(array[cx][cy]);
				int nx = cx + dx[direction];
				int ny = cy + dy[direction];
				if (nx < 0) {
					nx = R - 1;
				} else if (ny < 0) {
					ny = C - 1;
				} else if (nx >= R) {
					nx = 0;
				} else if (ny >= C) {
					ny = 0;
				}
				if (!visited[nx][ny][memory][direction]) {
					q.add(new Node(nx, ny, direction,memory));
					visited[nx][ny][memory][direction] = true;
				}

		}
		return "NO";
	}

	static void dir(char move) {
		if (move == '<') {
			direction = 1;

		} else if (move == '>') {
			direction = 0;

		} else if (move == '^') {
			direction = 2;
		} else if (move == 'v') {
			direction = 3;
		} else if (move == '_') {

			if (memory == 0) {
				direction = 0;
			} else {
				direction = 1;
			}

		} else if (move == '|') {

			if (memory == 0) {
				direction = 3;
			} else {
				direction = 2;
			}

		} else if (0 <= (move - '0') && (move - '0') <= 9) {
			memory = move - '0';
		} else if (move == '+') {
			if (memory == 15) {
				memory = 0;
			} else {
				memory += 1;
			}
		} else if (move == '-') {
			if (memory == 0) {
				memory = 15;
			} else {
				memory -= 1;
			}
		}
	}


}