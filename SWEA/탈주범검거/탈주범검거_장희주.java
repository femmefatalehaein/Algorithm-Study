package 탈주범검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거_장희주 {

	static class Node {
		int x, y,dist;

		public Node(int x, int y,int dist) {
			this.x = x;
			this.y = y;
			this.dist=dist;
		}

	}

	static int N, M, R, C, L;
	static int[][] map;
	static int Cnt;
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { -0, 0, -1, 1 };
	//파이프 타입별
	static boolean[][] type = { { false, false, false, false }, { true, true, true, true },
			{ true, true, false, false }, { false, false, true, true }, { true, false, false, true },
			{ false, true, false, true }, { false, true, true, false }, { true, false, true, false } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 세로크기
			N = Integer.parseInt(st.nextToken());
			// 가로크기
			M = Integer.parseInt(st.nextToken());
			// 맨홀뚜껑이 위치한 장소의 세로 위치
			R = Integer.parseInt(st.nextToken());
			// 가로위치
			C = Integer.parseInt(st.nextToken());
			// 탈출후 소요시간
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			// 0: 터널이 없는 장소 1~7:해당위치의 터널 구조물 타입
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//장소개수
			Cnt = 0;
			bfs();

			// 결과 출력
			System.out.println("#" + test_case + " " + Cnt);

		}

	}

	static void bfs() {
		//방문처리
		boolean[][] visited = new boolean[N][M];
		//큐실행
		Queue<Node> q = new ArrayDeque<>();
		//처음 시작 위치 ,거리
		q.add(new Node(R, C, 1));
		//방문처리
		visited[R][C] = true;
		//갯수 증가
		Cnt = 1;
		//큐가 비어질때까지 계속진행
		while (!q.isEmpty()) {
			//꺼냄
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int dist=node.dist;
			//거리값과 소요시간이 같을경우
			if(dist==L) {
				break;
			}
			//4방향돌기
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				//범위 벗어나면
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				//x,y위치와 nx,ny위치에 연결이 된다면
				//방향 반대인거 잊지말기!
				if (type[map[nx][ny]][revserD(d)] && type[map[x][y]][d]) {
					//방문하지않았다면
					if (!visited[nx][ny]) {
						q.add(new Node(nx, ny,dist+1));
						visited[nx][ny] = true;
						Cnt++;

					}
				}
			}
		}

	}
	//방향 인덱스 바꾸기
	static int revserD(int direction) {

		switch (direction) {
		case 1:
			return 0;
		case 0:
			return 1;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return -1;
	}

}
