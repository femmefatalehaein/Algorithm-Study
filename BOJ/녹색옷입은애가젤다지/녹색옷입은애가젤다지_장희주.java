import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라 알고리즘
public class 녹색옷입은애가젤다지_장희주 {
	//위치좌표, 거리
	static class Node {
		int x, y, distance;

		public Node(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}

	}

	static int N;
	static int[][] cave;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//테스트 케이스 번호
		int cnt = 0;
		//N이 0으로 입력되기전까지
		while ((N  = Integer.parseInt(br.readLine())) != 0) {
			cnt++;
			//N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			//동굴정보
			cave = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());

				}
			}

			System.out.printf("Problem %d: %d\n", cnt, getMinDistance(0, 0, N - 1, N - 1));
		}

	}

	static int getMinDistance(int sx, int sy, int ex, int ey) {
		boolean[][] visited = new boolean[N][N];
		int[][] minDistance = new int[N][N];
		final int INF = Integer.MAX_VALUE;
		//우선순위 큐 생성
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
		//무한대로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minDistance[i][j] = INF;
			}
		}
		//처음 좌표값 넣어주기
		minDistance[sx][sy] = cave[sx][sy];
		pq.offer(new Node(sx, sy, minDistance[sx][sy]));
		//우선순위큐 비어질때까지
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			int y = node.y;
			int distance = node.distance;
			//방문처리되었다면 패스
			if (visited[x][y])
				continue;
			//방문 처리 해주기
			visited[x][y]=true;
			//끝 좌표라면
			if (x == ex && y == ey)
				return distance;
			//4방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				//범위 벗어나면 패스
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				//방문처리 안되었고 전 값보다 작다면
				if (minDistance[nx][ny] > distance + cave[nx][ny] && !visited[nx][ny]) {
					//최소값을 갱신
					minDistance[nx][ny] = distance + cave[nx][ny];
					pq.offer(new Node(nx,ny,minDistance[nx][ny]));
				}
			}

		}
		//마지막 좌표으로 리턴
		return minDistance[ex][ey];
	}
}
