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

	static int n, m, result;
	static char[][] map;
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] visited;
	static Queue<Node> q;

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
			//방문처리
			visited = new int[n][m];
			//큐생성
			q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < m; j++) {
					//맵정보 저장
					map[i][j] = str.charAt(j);
					//물일때 위치 좌표 저장
					if (map[i][j] == 'W') {
						q.add(new Node(i, j));
						//방문처리 해줌
						visited[i][j] = 0;
					//물이 아닐때 -1 로 초기화
					} else {
						visited[i][j] = -1;
					}
				}
			}
			//결과값
			result = 0;
			bfs();
			//거리 최소 값 더하기
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					result += visited[i][j];
				}
			}
			// 함수실행
			System.out.println("#" + test_case + " " + result);
		}

	}
	
	static void bfs() {
		
		//큐가 비어질때까지
		while (!q.isEmpty()) {
			//큐에 위치좌표 꺼내기
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			//4방향 돌기
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				//범위에 벗어나면
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					continue;
				}
				//방문하지않았다면
				if (visited[nx][ny] == -1) {
					//전에 있는 값에 +1 더하기
					visited[nx][ny] = visited[x][y] + 1;
					//큐에 삽입
					q.add(new Node(nx, ny));
				}

			}
		}
	}

}
