import java.io.*;
import java.util.*;

public class Solution_2117 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
//			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N; // 도시의 크기
	static int M; // 하나의 집이 지불할 수 있는 비용
	static int[][] map; // 도시 정보
	static int[] profit = new int[22]; // 도시의 최대 크기만큼 배열 생성
	static Point[] dir = { new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0) };
	static int maxHome, home;
	// 서비스를 제공받는 가장 많은 집의 수
	// 총 집의 개수를 통해 해당되지 않는 범위는 자르기 //??

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int k = 1; k < 22; k++)
			profit[k] = k * k + (k - 1) * (k - 1);
		// 이익을 저장한다
		// N이 가질 수 있는 최대값 + 1 그리고 1부터 시작한다고 가정하여 이익을 저장함

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			maxHome = home = 0;
//			boolean visited[] = new boolean[N * N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						home++;
					// 집의 개수
					// 전체 집의 개수가 필요
					// 모든 집의 이익을 더해도 손해가 나는지 확인하기 위함
				}
			}
			for (int k = 1; k < 22; k++) {
				// 범위
				if (home * M - profit[k] < 0)
					break;
				// 모든 집의 이익을 더해도 손해가 나는 상황이면 이 이상 가지 않는다
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						BFS(i, j, k);
					}
				}
			}

			System.out.println("#" + tc + " " + maxHome);
		}

	}

	// 1. 이동 : BFS
	static void BFS(int x, int y, int k) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int depth = 0;
		int cnt = 0;

		q.add(new Point(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int size = q.size();

			if (depth == k)
				break; // 해당 depth까지 오면 중단

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				if (map[cur.x][cur.y] == 1)
					cnt++;

				for (Point d : dir) {
					int nx = cur.x + d.x;
					int ny = cur.y + d.y;

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;
					if (visited[nx][ny])
						continue;

					visited[nx][ny] = true;
					q.add(new Point(nx, ny));
				}
			}

			depth++;
		}

		if (cnt * M - profit[k] >= 0)
			maxHome = Math.max(cnt, maxHome);
		// 가장 많은 집의 개수를 업데이트
		// 이윤이 나는 경우에만 업데이트 한다
		// 이윤이 큰 경우가 아니라 적자가 아닌경우에 업데이트 한다
	}

}
