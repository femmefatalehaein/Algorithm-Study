package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프로세서연결하기2_장희주 {
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int N;
	static int[][] array;
	static int result;
	static List<Node>list;
	static int maxCoreCnt;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st;
			array=new int[N][N];
			list=new ArrayList<>();
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					array[i][j]=Integer.parseInt(st.nextToken());
					if(array[i][j]==1) {
						list.add(new Node(i,j));
					}
				}
			}
			maxCoreCnt=0;
			result=0;
			dfs(0,0,0,new boolean[N][N]);
			System.out.println("#"+test_case+" "+result);
		}
	}
	static void dfs(int cnt,int coreCnt,int length,boolean[][] visited) {
		//조건문
		if(cnt==list.size()) {
			if(maxCoreCnt<coreCnt) {
				maxCoreCnt=coreCnt;
				result=length;
			}
			else if(maxCoreCnt==coreCnt) {
				if(result>length) {
					result=length;
				}
			}
			return;
		}
		
		//선택인했을때
		dfs(cnt+1, coreCnt,length,visited);
		//선택했을때
		int x=list.get(cnt).x;
		int y=list.get(cnt).y;
		visited[x][y]=true;
		for(int i=0;i<4;i++) {
			int nx=x+dx[i];
			int ny=y+dy[i];
			while(nx>=0 && ny>=0 && nx<N && ny<N && array[nx][ny]==0 && !visited[nx][ny]) {
				visited[nx][ny]=true;
				length++;
				nx+=dx[i];
				ny+=dy[i];
				
			}
			if(nx<0 || ny<0 || nx>=N || ny>=N) {
				dfs(cnt+1, coreCnt+1,length,visited);
				
			}
			nx-=dx[i];
			ny-=dy[i];
			while(nx!=x || ny!=y) {
				visited[nx][ny]=false;
				length--;
				nx-=dx[i];
				ny-=dy[i];
			}
		}

	}

}
