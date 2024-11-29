import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1226_미로1_곽승미 {

	static int[] dx = {-1,1,0,0}; // 상,하,좌,우
	static int[] dy = {0,0,-1,1};
	static char[][] map; // 맵
	static Queue<int[]> q; //큐
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_1226_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			StringBuilder sb = new StringBuilder();
			int t = Integer.parseInt(br.readLine());
			map = new char[16][16];
			q = new LinkedList<>();
			// 맵 입력
			for (int i = 0; i < 16; i++) {
				String str = br.readLine();
				for (int j = 0; j < 16; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			int cnt = bfs(1,1);
			sb.append("#").append(tc).append(" ").append(cnt);
			System.out.println(sb.toString());
		}
	}
	
	public static int bfs(int x, int y) {
		q.offer(new int[] {x,y});
		map[x][y] = '1';
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			// 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				// 길이 있으면 큐에 추가 후 방문 처리
				if(map[nx][ny] == '0') {
					q.offer(new int[] {nx,ny});
					map[nx][ny] = '1';
				}
				
				// 도착점 도달한 경우
				if(map[nx][ny] == '3') {
					return 1;
				}
			}
		}
		return 0;
	}

}