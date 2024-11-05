import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_17472_다리만들기2 {
	static int N,M,map[][],bridges[][],minLength[],cnt;
	static boolean visited[][],ischeck[];
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<int[]> mq = new LinkedList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static class Node implements Comparable<Node>{
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
		// 섬 나누기(섬 번호 부여)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					setNum(i,j);
				}
			}
		}
//		 맵 체크
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] +" ");
//			}
//			System.out.println();
//		}
		
		// 다리 놓기 ( cnt : 섬 개수(번호) )
		bridges = new int[cnt+1][cnt+1];
		ischeck = new boolean[cnt+1]; // 섬에 대한 방문 체크
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && !ischeck[map[i][j]]) 
					make(i, j); 
			}
		}
		
		// 프림 알고리즘을 통해 섬 연결하기
		Arrays.fill(ischeck, false);
		int total = 0, nodeCnt = 0; // 다리길이 합, 연결된 섬 개수
		minLength = new int[cnt+1];
		Arrays.fill(minLength, Integer.MAX_VALUE);
		minLength[1] = 0;
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			if(ischeck[node.to]) continue; // 이미 연결 시 패스
			
			total += node.weight;
			ischeck[node.to] = true;
			
			if(++nodeCnt == cnt) break; // 모든 섬 연결됐으면 중단
			
			for (int i = 1; i <= cnt; i++) {
				if(!ischeck[i] && bridges[node.to][i] !=0 && minLength[i] > bridges[node.to][i]) {
					minLength[i] = bridges[node.to][i];
					pq.offer(new Node(i, bridges[node.to][i]));
				}
			}
		}
		if(nodeCnt < cnt) {
			System.out.println(-1);
		}else {
			System.out.println(total);
		}
		
		
	}
	// 섬 번호 부여하기
	static void setNum(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y});
		++cnt; // 섬 번호 증가(새로운 섬)
		map[x][y] = cnt;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + dx[d];
				int ny = curY + dy[d];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					map[nx][ny] = cnt;
					q.offer(new int[] {nx,ny});
				}
			} 
		}
	}
	// 다리 만들기
	static void make(int x, int y) {
		int curNum = map[x][y]; // 현재 섬 번호
		boolean check[][] = new boolean[N][M];
		mq.offer(new int[] {x,y});
		check[x][y] = true;
		
		while(!mq.isEmpty()) {
			int cur[] = mq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) {continue;} // 범위 벗어나면 패스
				
				int length = 1; // 다리 길이
				
				int sx = nx, sy = ny;
				
				if(map[sx][sy] != map[x][y]) { // 다리 놓을 곳이면
					
					while(true) {
						// 방향 그대로 계속 이동
						sx += dx[d];
						sy += dy[d];
						
						if(sx < 0 || sx >= N || sy < 0 || sy >= M || map[sx][sy] == map[x][y]) {
							length = 0;
							break;
						}
						
						if(map[sx][sy] != 0) break; // 다른 섬 도달
						
						length++; // 다리 길이 증가
					}
					if(length > 1) { // 다리 길이 2 이상일때
						if(bridges[curNum][map[sx][sy]] == 0) {// 다리 처음 생성 시
							bridges[curNum][map[sx][sy]] = length; // 다리 길이 저장
						}else {
							// 작은 값으로 갱신
							bridges[curNum][map[sx][sy]] = Math.min(length, bridges[curNum][map[sx][sy]]);
						}
					}
				}
				if(map[nx][ny] == map[x][y] && !check[nx][ny]) {
					mq.offer(new int[] {nx,ny});
					check[nx][ny] = true;
				}
			}
		}
		// 하나의 섬 모두 탐색했으면 방문 체크하기
		ischeck[map[x][y]] = true;
	}
}