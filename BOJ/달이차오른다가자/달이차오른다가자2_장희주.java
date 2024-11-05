package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자2_장희주 {
	
	static class Node{
		int x, y,dist,key;

		public Node(int x, int y, int dist,int key) {
		
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.key=key;
		}
		
	}
	
	static int N,M;
	static char[][] array;
	static int minCnt;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		int startX=0;
		int startY=0;
		array=new char[N][M];
		for(int i=0;i<N;i++) {
			String str=br.readLine();
			for(int j=0;j<M;j++) {
				array[i][j]=str.charAt(j);
				if(array[i][j]=='0') {
					startX=i;
					startY=j;
					array[i][j]='.';
				}
			}
		}
		minCnt=bfs(startX,startY);
		
		System.out.println(minCnt);
	}
	static int bfs(int x,int y) {
		boolean[][][] visited=new boolean[N][M][1<<6];
		Queue<Node>q=new ArrayDeque<>();	
		q.add(new Node(x,y,0,0));
		while(!q.isEmpty()) {
			Node c=q.poll();
			int cx=c.x;
			int cy=c.y;
			int ckey=c.key;
			int dist=c.dist;
			visited[cx][cy][ckey]=true;
			for(int i=0;i<4;i++) {
				int nx=cx+dx[i];
				int ny=cy+dy[i];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[nx][ny][ckey]) {
					continue;
				}
				if(array[nx][ny]=='#') {
					continue;
				}
				else if(array[nx][ny]=='.') {
					q.add(new Node(nx,ny,dist+1,ckey));
					visited[nx][ny][ckey]=true;
					
				}
				else if(array[nx][ny]>='a'&& array[nx][ny]<='f') {
					int key=ckey | 1<<array[nx][ny]-'a';
					q.add(new Node(nx,ny,dist+1,key));
				}
				else if(array[nx][ny]>='A'&& array[nx][ny]<='F') {
					if((ckey & 1<<array[nx][ny]-'a')>0){
						visited[nx][ny][ckey]=true;
						q.add(new Node(nx,ny,dist+1,ckey));
					}
				}
				else if(array[nx][ny]=='1') {
					dist+=1;
					return dist;
				}
				
			}
		}
		return -1;
	}
}
