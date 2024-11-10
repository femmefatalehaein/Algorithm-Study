import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16234_인구이동_곽승미 {
	
	static int N,L,R;
	static int map[][], union[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int total = 0;
		
		while(true) {
			union = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					union[i][j] = -1;
				}
			}
			
			int idx = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(union[i][j] == -1) {
						bfs(i,j,idx);
						idx++;
					}
				}
			}
			if(idx == N*N) {
				break;
			}
			total++;
		}
		System.out.println(total);
	}
	static void bfs(int x, int y, int index) {
		Queue<int[]> q = new LinkedList<>();
		LinkedList<int[]> list = new LinkedList<>();
		
		q.offer(new int[] {x,y});
		list.add(new int[] {x,y});
		
		union[x][y] = index;
		int people = map[x][y];
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + dx[d];
				int ny = curY + dy[d];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && union[nx][ny] == -1) {
					int diff = Math.abs(map[curX][curY] - map[nx][ny]);
					if(diff >= L && diff <= R) {
						union[nx][ny] = index;
						q.offer(new int[] {nx,ny});
						list.add(new int[] {nx,ny});
						people += map[nx][ny];
						cnt++;
					}
				}
			}
		}
		int newpeople = Math.round(people / cnt);
		for(int[] p : list) {
			map[p[0]][p[1]] = newpeople;
		}
	}
}