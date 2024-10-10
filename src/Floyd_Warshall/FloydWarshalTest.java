package Floyd_Warshall;
import java.io.*;
import java.util.*;

public class FloydWarshalTest {
/*
입력
5
9
1 2 10
1 3 5
2 3 2
3 1 1
3 2 13
4 1 8
4 5 3
5 4 9
5 2 31

출력 결과
0 10 5 0 0 
3 0 2 0 0 
1 11 0 0 0 
8 18 13 0 3 
17 27 22 9 0 
*/
	//INF vs INF2 
	static final int INF = 1000000000;
	static final int INF2 = Integer.MAX_VALUE;
	
	public static void floyd(int[][] graph, int n) {

		//경유지 선택!
		for(int k=1; k<=n; k++) {
			// 출발지
			for(int i =1; i<=n; i++) {
				//도착지
				for(int j = 1; j<=n; j++) {
					
					graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
				}
			}
		}	
		
		//출력
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(graph[i][j] == INF2) System.out.print(0+" ");
				else System.out.print(graph[i][j]+" ");		
			}
			System.out.println();
		}
	}
	
	public static void main(String args []) throws IOException{
		
		//입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//1. 정점의 갯수 받기
		int N = Integer.parseInt(br.readLine());
		//2. 간선의 갯수 받기
		int M = Integer.parseInt(br.readLine());
		
		int[][] graph  = new int[N+1][N+1];
		
		for(int i=0; i<graph.length; i++) {
			for(int j=0; j<graph.length; j++) {
				// 그니까 나 → 나로 가는 것은 0이니까 얘를 제외하고!!!
				if(i==j) continue;
				graph[i][j] =  INF2;
			}
		}
		
		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			//1. 시작점
			int v = Integer.parseInt(st.nextToken());
			//2. 끝점
			int w = Integer.parseInt(st.nextToken());
			//3. 가중치
			int cost = Integer.parseInt(st.nextToken());
			graph[v][w] = cost;
		}
		
		System.out.println(INF2);
		floyd(graph,N);
		
	}
}
