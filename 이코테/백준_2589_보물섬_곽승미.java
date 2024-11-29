import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2589_보물섬_곽승미 {
	static int N,M,answer;
	static char[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static Queue<int[]> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i<N; i++) {
			String line = br.readLine();
			for(int j = 0; j<M; j++) {
				map[i][j] = line.charAt(j);
//				System.out.println(map[i][j]);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'L') {
					bfs(i,j);
				}
			}		
		}
		System.out.println(answer);
		
	}
	static void bfs(int x, int y) {
		int[][] visited = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = -1;
			}		
		}
		visited[x][y] = 0;
		q.offer(new int[]{x,y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if(map[nx][ny] == 'L' && visited[nx][ny] == -1) {
					visited[nx][ny] = visited[curX][curY] + 1;
					q.offer(new int[] {nx, ny});
					answer = Math.max(answer, visited[nx][ny]);
				}
			}
		}
	}
}