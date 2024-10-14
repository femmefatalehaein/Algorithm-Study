import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int x;
	int y;
	int cnt; // 벽을 부순 횟수
		
	public Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

	@Override 
	public int compareTo(Node o) {
		// 오름차순(가장 작은 cnt 먼저)
		return this.cnt - o.cnt;
	}
}
	
public class 백준_1261_알고스팟_곽승미 {
	
	static int N,M;
	static int[][] map;
	
	static int[] dx = {-1,1,0,0}; // 상,하,좌,우
	static int[] dy = {0,0,-1 ,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		
		map = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			String[] line = br.readLine().split("");
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(line[j]);
				map[i][j] = num;
			}
		}
		
		int res = bfs(0,0);
		System.out.println(res);
	}
	
	
	
	static int bfs(int x, int y) {
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐 사용
		boolean[][] visited = new boolean[M][N];
		
		visited[y][x] = true;
		pq.add(new Node(x,y,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int px = node.x;
			int py = node.y;
			int pcnt = node.cnt;
            
			// 도착
			if(px == N-1 && py == M-1) { // 마지막 도착지에 도달하면 벽 부순 횟수 return
				return pcnt;
			}
			
			for(int i=0; i<4 ; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				// 좌표가 범위를 벗어났는지 확인
				if(nx <0 || ny <0 || nx > N-1 || ny > M-1) continue;
				
				// 방문했던 곳인지 확인
				if(visited[ny][nx]) continue;
				
				visited[ny][nx] =true; // 방문 체크
				
				if(map[ny][nx]==0) { // map[nx][ny]의 값이 0이라면 벽이 아님 -> 그냥 cnt 유지
					pq.add(new Node(nx,ny, pcnt));
				}else {
					pq.add(new Node(nx,ny, pcnt+1)); // map[nx][ny]의 값이 1이라면 벽 -> cnt+1
				}
			}
			
		}
		return 0;
	}
}