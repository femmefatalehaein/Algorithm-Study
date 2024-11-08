package 물놀이가자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이가자2_장희주 {

	static class Node {
		int x, y, dist;

		public Node(int x, int y, int dist) {

			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

	static int N, M;
	static char[][] array;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Node>q;


	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			array = new char[N][M];
			q=new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					array[i][j]=str.charAt(j);
					if(array[i][j]=='W') {
						q.offer(new Node(i,j,0));
					}
				}
			}
			int sum = 0;
			sum=bfs();
			System.out.println("#" + test_case + " " + sum);
		}

	}

	static int bfs() {
		int sum=0;

		boolean[][] visited = new boolean[N][M];
		
			while (!q.isEmpty()) {
				Node c = q.poll();
				int cx = c.x;
				int cy = c.y;
				int dist = c.dist;

				if (array[cx][cy]=='L') {
					sum+=dist;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
						continue;
					}
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny, dist + 1));
				}
		
		}
		return sum;
	}
}
