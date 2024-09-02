package 물놀이를가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자_장희주 {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int n, m,cnt,result;
	static int totalMin;
	static char[][] map;
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 배열크기
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			// 맵정보 W:물 L:땅
			map = new char[n][m];
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < m; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			cnt=0;
			result=0;
			totalMin=Integer.MAX_VALUE;
			visited=new boolean[n][m];
			// 함수실행
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 'L') {
						bfs(i, j);
						result+=totalMin;
					}
				}
			}
			System.out.println("#" + test_case + " " + result);
		}

	}

	static void bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y));
		visited=new boolean[n][m];
		visited[x][y]=true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int curX = node.x;
			int curY = node.y;
			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
					continue;
				}
				if (!visited[nx][ny] && map[nx][ny]=='W') {
					visited[nx][ny]=true;
					curX = nx;
					curY = ny;
					q.offer(new Node(nx,ny));
					cnt++;
				}
			}

		}
		

	}

}
