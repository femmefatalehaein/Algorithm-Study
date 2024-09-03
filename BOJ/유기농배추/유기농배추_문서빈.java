package algo_Java;

import java.util.*;
import java.io.*;

public class BOJ_1012 {
	static int[][] field; //배추밭을 표현할  2차원 배열
	static boolean[][] visited; // 해당 위치를 방문했는지 확인할 2차원 배열
	// 한 배추에서 시작해서 연결된 모든 배추를 방문한 후에는 해당 그룹에 속한 배추들은 더 이상 탐색할 필요가 없다!
	static int M, N; // 배추밭의 가로, 세로 길이
	static int[] dx = {-1, 1, 0, 0}; // x좌표의 상하좌우 이동을 위한 배열
	static int[] dy = {0, 0, -1, 1}; // y좌표의 상하좌우 이동을 위한 배열
	// dx, dy 배열을 통해 현재 위치에서 주변 위치로의 이동을 쉽게 계산할 수 있음 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken()); // 배추의 위치의 개수 
			
			field = new int[N][M]; // 생성 및 초기화 
			visited = new boolean[N][M];
			
			// 배추의 위치를 입력 받아 field 배열에 표시
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				field[x][y] = 1; // (x, y) 위치에 배추가 있음
			}
			
			int cnt = 0; // 연결된 배추 그룹의 수를 저장할 변수 (인접한 배추들은 하나의 그룹으로 간주)
			// 배추가 인접해 있는 기준 = 상하좌우로 연결된 경우
			
			// 배추밭의 모든 위치 검사 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 배추가 있고, 아직 방문하지 않은 위치라면 DFS 시작
					if (field[i][j] == 1 && !visited[i][j]) {
						DFS(i, j);
						cnt ++; // DFS가 끝나면 연결된 그룹을 하나 찾은 것이므로 cnt 1 증가 					
					}
				}
			}
			System.out.println(cnt);
		}
		
	}
	
	// DFS 함수
	static void DFS(int x, int y) {
		visited[x][y] = true; // 현재 위치를 방문했음을 표시 
		
		// 상하좌우 방향으로 이동
		for (int i = 0; i < 4; i++) {
			// (x,y)좌표에서 상,하,좌,우로 각각 이동 
			int nx = x + dx[i]; 
			int ny = y + dy[i]; 
			
			// nx, ny가 배추밭의 범위 내에 있고
			if (nx >= 0 && nx < N && ny >= 0 && ny < M) { // 설정 잘 해주기!
				// 해당 위치에 배추가 있고, 아직 방문하지 않았다면 DFS 재귀 호출
				if (field[nx][ny] == 1 && !visited[nx][ny]) {
					DFS(nx, ny);
				}
			} 
		} 
		
	}
}
