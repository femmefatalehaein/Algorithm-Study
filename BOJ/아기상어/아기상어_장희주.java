

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 아기상어_장희주 {

	static class Node {
		int x, y, size;
		int dist;

		public Node(int x, int y, int size, int dist) {

			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
		}

	}

	static int N;
	static int[][] space;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static int time;
	static int startX, startY;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 크기
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 공간정보
		space = new int[N][N];
		// 물고기 갯수
		int fishcount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				// 시작위치
				if (space[i][j] == 9) {
					startX = i;
					startY = j;
					space[i][j] = 0;
				}
				// 물고기가 있는 위치일때
				if (space[i][j] >= 1 && space[i][j] <= 6) {
					fishcount++;
				}
			}
		}

		time = 0;
		//물고기가 있을경우
		if (fishcount > 0) {

			bfs(fishcount);
		}
		//결과출력
		System.out.println(time);

	}
	//bfs 수행
	static void bfs(int count) {
		//방문처리
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {

				// 거리가 가까운 순서
				if (o1.dist != o2.dist)
					return o1.dist - o2.dist;
				// x좌표 작은 순서
				if (o1.x != o2.x)
					return o1.x - o2.x;
				// y좌표가 작은 순서
				return o1.y - o2.y;
					
				
				
			}
		});
		pq.offer(new Node(startX, startY, 2, 0));
		//물고기 갯수
		int fishcnt = 0;
		visited[startX][startY] = true;
		//우선순위큐가 비어질때까지
		while (!pq.isEmpty()) {
			Node target = null;
			//우선순위큐가 비어질때까지
			while (!pq.isEmpty()) {
				Node current = pq.poll();
				int cx = current.x;
				int cy = current.y;
				int size = current.size;
				int dist = current.dist;
				//물고기크기가 더 작을때
				 if (space[cx][cy] < size && space[cx][cy] > 0) {
						target = new Node(cx, cy, size, dist);
						space[cx][cy] = 0;
						break;
				//물고기크기가 아기상어보다 클때
				} else if(space[cx][cy] > size) {
					continue;
				}
				//4방향 탐색
				for (int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					// 범위에 벗어나거나 방문처리 되었다면
					if (nx < 0 || ny >= N || nx >= N || ny < 0 || visited[nx][ny]) {
						continue;
					}
					visited[nx][ny] = true;
					pq.offer(new Node(nx, ny, size, dist + 1));
				}
			}
			if (target != null) {
				int tx=target.x;
				int ty=target.y;
				int tsize=target.size;
				int tdist=target.dist;

				//상어위치 갱신
				pq.clear();
				visited=new boolean[N][N];
				pq.add(new Node(tx,ty,tsize,0));
				visited[tx][ty]=true;
				//거리 갱신
				time+=tdist;
				
				//물고기수 증가
				fishcnt++;
				//물고기 수 와 아기상어 크기와 같을때
				if(fishcnt==tsize) {
					//아기상어 크기 증가
					pq.peek().size++;
					//초기화
					fishcnt = 0;
				}
			}
		}

	}

}
