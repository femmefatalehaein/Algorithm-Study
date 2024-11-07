import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_10966_물놀이를가자 {
	
	static int N,M;
	static char map[][];
	static int visited[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			visited = new int[N][M];
			Queue<int[]> q = new LinkedList<int[]>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = -1;
				}
			}
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					
					if(map[i][j] == 'W') {
						q.offer(new int[] {i,j});
						visited[i][j] = 0;
					}
				}
			}
			while(!q.isEmpty()) {
				int cur[] = q.poll();
				int x = cur[0];
				int y = cur[1];
				
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == -1) {
						q.offer(new int[] {nx,ny});
						visited[nx][ny] = visited[x][y] + 1; 
					}
				}	
			}
			int res = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					res += visited[i][j];
				}
			}
			System.out.println("#"+tc+" "+res);
		}
	}
}