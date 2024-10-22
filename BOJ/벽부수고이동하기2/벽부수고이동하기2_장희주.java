package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기2_장희주 {
	
	static class Node{
		
		int x,y,dist,k;

		public Node(int x, int y, int dist, int k) {
			
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.k = k;
		
		}
		
	}
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int N,M,K;
	static int[][] array;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//세로,가로크기, 벽갯수
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		//맵정보
		array=new int[N][M];
		for(int i=0;i<N;i++) {
			String str=br.readLine();
			for(int j=0;j<M;j++) {
				array[i][j]=str.charAt(j)-'0';
			}
		}
		int result=0;
		result=bfs();
		System.out.println(result);
		

	}
	static int bfs() {
		
		Queue<Node> q=new ArrayDeque<>();
		
		
		boolean[][][] visited=new boolean[N][M][K+1];
		q.offer(new Node(0,0,1,K));
		while(!q.isEmpty()) {
			Node c=q.poll();
			int cx=c.x;
			int cy=c.y;
			int cdist=c.dist;
			int ck=c.k;
			//N-1, M-1위치에 왔을때
			if(N-1==cx && M-1==cy) {
				
				return cdist;
			}
			//4방향 돌기
			for(int d=0;d<4;d++) {
				int nx=cx+dx[d];
				int ny=cy+dy[d];
				//범위벗어나면
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][ck]) {
					continue;
				}
				//벽부수실수있는 횟수가 있을때
				if(ck>0) {
					//벽일때
					if(array[nx][ny]==1) {
						q.offer(new Node(nx,ny,cdist+1,ck-1));
						visited[nx][ny][ck]=true;
					}
					//벽이 아닐때
					else if(array[nx][ny]==0){
						
						q.offer(new Node(nx,ny,cdist+1,ck));
						visited[nx][ny][ck]=true;
					}
				//벽부실수있는 갯수가 0이하일때
				}else {
					//벽일때
					if(array[nx][ny]==1) {
						continue;
					}
					//벽이 아닐때
					else if(array[nx][ny]==0){
						q.offer(new Node(nx,ny,cdist+1,ck));
						visited[nx][ny][ck]=true;
					}
				}
				
			}
		}
		
		
		return -1;
	}
}
