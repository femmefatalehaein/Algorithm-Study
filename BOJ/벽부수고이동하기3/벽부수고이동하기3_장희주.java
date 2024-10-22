package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기3_장희주 {

	static class Node {

		int x, y, dist, k;
		// 낮:0 밤: 1
		int status;

		public Node(int x, int y, int dist, int k, int status) {

			this.x = x;
			this.y = y;
			this.dist = dist;
			this.k = k;
			this.status = status;

		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, K;
	static int[][] array;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 세로,가로크기, 벽갯수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 맵정보
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j) - '0';
			}
		}
		int result = 0;
		result = bfs();
		System.out.println(result);

	}

	static int bfs() {

		Queue<Node> q = new ArrayDeque<>();

		// 낮과 밤움직임?
		boolean[][][] visited = new boolean[N][M][K + 1];
		q.offer(new Node(0, 0, 1, K, 0));
		while (!q.isEmpty()) {
			Node c = q.poll();
			int cx = c.x;
			int cy = c.y;
			int cdist = c.dist;
			int ck = c.k;
			int status = c.status;

			// N-1, M-1위치에 왔을때
			if (N - 1 == cx && M - 1 == cy) {

				return cdist;
			}
//			if (status == 0) {
//				if (array[cx][cy] == 1) {
//					q.add(new Node(cx, cy, cdist + 1, ck, 1));
//
//					continue;
//				}
//			}
			// 4방향 돌기
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				// 범위벗어나면
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][ck]) {
					continue;
				}
				// 낮일때
				if (status == 0) {
					// 벽일때
					if (array[nx][ny] == 1) {
						// 부실수있을때
						if (ck > 0) {
							q.offer(new Node(nx, ny, cdist + 1, ck - 1, 1));
							visited[nx][ny][ck] = true;
							// 부실수없을때
						} else {
							continue;
						}
						// 벽이 아닐때
					} else {
						q.offer(new Node(nx, ny, cdist + 1, ck, 1));
						visited[nx][ny][ck] = true;
					}

				}
				if(status==1) {
					// 벽일때
					if (array[nx][ny] == 1) {
						q.offer(new Node(cx,cy,cdist+1,ck,0));
						visited[cx][cy][ck]=true;
						// 벽이 아닐때
					} else {
						q.offer(new Node(nx, ny, cdist + 1, ck, 0));
						visited[nx][ny][ck] = true;
					}
				}
			}
		}

		return -1;
	}
}
