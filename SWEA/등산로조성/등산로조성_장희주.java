package 등산로조성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조성_장희주 {

	static class Node {
		int x, y, value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

	}

	static int N, K;
	static int[][] map;
	static int result;
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { -0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			// 지도의 한변길이
			N = Integer.parseInt(st.nextToken());
			// 최대 공사 가능 깊이
			K = Integer.parseInt(st.nextToken());
			int maxValue = 0;
			// 지도정보
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > maxValue) {
						maxValue = map[i][j];
					}
				}
			}
			// 결과값
			result = 0;
			// 최대값 가지고 있는 위치 리스트에 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxValue) {
						//방문초기화
						visited = new boolean[N][N];
						//함수실행
						dfs(i, j, 1,1);
					}
				}
			}
			// 결과 출력
			System.out.println("#" + test_case + " " + result);

		}

	}

	static void dfs(int x, int y, int length,int cnt) {
		//방문한 위치 방문처리
		visited[x][y] = true;
		
		//4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			//범위에 벗어난다면
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			//방문하지않았다면
			if (!visited[nx][ny]) {
				//현재 위치가 전 위치보다 작을경우
				if (map[nx][ny] < map[x][y]) {
					//이동
					dfs(nx, ny, length + 1,cnt);
				//현재 위치가 전위치보다 같거나 작을경우
				} else {
					//깍지않았고 지금 위치에서 K깊이 빼준 값보다 전 위치값이 크다면
					if (cnt>0 && map[nx][ny] - K < map[x][y]) {
						//임시값
						int temp=map[nx][ny];
						//전위치보다 1작게 만들기
						map[nx][ny]=map[x][y]-1;
						//깍았다는 값 넣어줌
						//그리고 이동
						dfs(nx, ny, length + 1,cnt-1);
						//백트래킹->다시 값 원상태로 바꿔주기
						map[nx][ny]=temp;
					}
				}
			}
		}
		//백트래킹
		//방문처리 원상태로
		visited[x][y]=false;
		//최대값 갱신
		result=Math.max(result, length);
	}
}
