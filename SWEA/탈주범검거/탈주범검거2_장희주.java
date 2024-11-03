package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거2_장희주 {

	static class Node {

		int x, y;

		public Node(int x, int y) {

			this.x = x;
			this.y = y;
			
		}

	}

	static int N, M, R, C, L;
	static int[][] cave;
	//상=0, 하=1, 좌=2,우=3
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int locationCnt;

	static boolean[][] types= {{false,false,false,false},{true, true,true,true},{true,true,false,false},
			{false,false,true,true},
			{true,false,false,true},{false,true,false,true},
			{false,true,true,false},{true,false,true,false}};
	

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			cave = new int[N][M];
			locationCnt=1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j< M; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs(R, C, cave[R][C]);
			System.out.println("#"+test_case+" "+locationCnt);
		}

	}

	

	static void bfs(int x, int y, int type) {
		int cnt=L;
		boolean[][] visited = new boolean[N][M];
		Queue<Node> q = new ArrayDeque<>();
		visited[x][y] = true;
		q.offer(new Node(x, y));
		while (cnt > 1) {
			int size = q.size();
			cnt--;
			for(int idx = 0; idx < size; idx++) {
				Node c = q.poll();
				int cx = c.x;
				int cy = c.y;
				//System.out.println(c.x + " " + c.y);
				
				for(int i=0;i<4;i++) {
					int nx=cx+dx[i];
					int ny=cy+dy[i];
					if(nx>=N || ny>=M || nx<0 || ny<0 || visited[nx][ny] || cave[nx][ny]==0) {
						continue;
					}
					if(types[cave[cx][cy]][i] && types[cave[nx][ny]][reverse(i)]) {
						locationCnt++;
						q.offer(new Node(nx,ny));
						visited[nx][ny]=true;
					}
					
				}

				
			}
		}
	}
	static int reverse(int direction) {
		
		if(direction==0) {
			direction=1;
		}else if(direction==1) {
			direction=0;
		}else if(direction==2) {
			direction=3;
		}else if(direction==3) {
			direction=2;
		}
		return direction;
	}

}
