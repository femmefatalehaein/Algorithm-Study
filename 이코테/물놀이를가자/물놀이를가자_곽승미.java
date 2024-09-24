package 물놀이를가자;
/*
 * SWEA 10966. 물놀이를 가자
 * BFS로 풀이
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 물놀이를가자_곽승미 {
	static int N,M,cnt;
	static char[][] map; 
	static int[][] visited; // 방문배열
	static int[] dx = {-1,1,0,0}; // 상,하,좌,우
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/s_10966_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new int[N][M];
			Queue<int[]> q = new LinkedList<>(); // BFS에 사용할 큐
			
			// 방문 배열 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					visited[i][j] = -1;
				}
			}
			// map 입력 및 초기화
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<M; j++) {
					if(line.charAt(j) == 'W') { // 물이 있는 위치로부터 땅까지 계산
						q.add(new int[] {i,j});
						visited[i][j] = 0; // 시작 위치 0
					}
				}
			}
			// BFS 실행
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				for(int i=0; i<4; i++) {
					int nx = curX + dx[i];
					int ny = curY + dy[i];
					
					// 범위 밖인 경우 continue
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
						continue;
					}
					// 이미 방문한 경우 continue
					if(visited[nx][ny] != -1) {
						continue;
					}
					q.offer(new int[] {nx,ny});
					visited[nx][ny] = visited[curX][curY]+1;
				}
			}
				
			int result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					result += visited[i][j];
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}