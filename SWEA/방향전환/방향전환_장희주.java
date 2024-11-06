package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 방향전환_장희주 {

	static class Node {
		int x, y,dist,direction;

		public Node(int x, int y,int dist,int direction) {

			this.x = x;
			this.y = y;
			this.dist=dist;
			this.direction=direction;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Node> list;
	static int result;
	static int x1, x2, y1, y2;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			list = new ArrayList<>();

			x1 = Integer.parseInt(st.nextToken())+100;
			y1 = Integer.parseInt(st.nextToken())+100;
			x2 = Integer.parseInt(st.nextToken())+100;
			y2 = Integer.parseInt(st.nextToken())+100;
			result=bfs(x1, y1, new boolean[201][201][2]);
			
			System.out.println("#" + test_case + " " + result);
		}

	}

	static int bfs(int x, int y, boolean[][][] visited) {
		
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(x, y,0,0));
		q.offer(new Node(x, y,0,2));
		while (!q.isEmpty()) {
			Node c=q.poll();
			int cx=c.x;
			int cy=c.y;
			int dist=c.dist;
			int direction=c.direction;
			
			if(cx==x2 && cy==y2) {
				return dist;
			}
			//가로일때
			if(direction ==0 || direction ==1) {
				for(int i=2;i<4;i++) {
					int ny=cy+dy[i];
					int nx=cx;
					//세로일때
					if(nx>=0 && ny>=0 && nx<201 && ny<201 && !visited[nx][ny][0]) {
						visited[nx][ny][0]=true;
						q.offer(new Node(nx,ny,dist+1,i));
					}
				}
			}
			//세로일때 	
			else {
				for(int i=0;i<2;i++) {
					int nx=cx+dx[i];
					int ny=cy;
					if(nx>=0 && ny>=0 && nx<201 && ny<201 && !visited[nx][ny][1]) {
						visited[nx][ny][1]=true;
						q.offer(new Node(nx,ny,dist+1,i));
					}
				}
			
			}
			
		}
		return -1;
	}

}
