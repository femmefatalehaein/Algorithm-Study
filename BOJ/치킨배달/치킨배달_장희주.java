import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달_장희주 {
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	static int n, m;
	static int[][] city;
	static int result;
	static List<Node> chicken,house;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 도시의 크기 N
		n = Integer.parseInt(st.nextToken());
		// 치킨집 최대 개수
		m = Integer.parseInt(st.nextToken());
		city = new int[n + 1][n + 1];
		// 1 : 집 , 2: 치킨집
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j]==2) {
					chicken.add(new Node(i,j));
				}else if(city[i][j]==1) {
					
					house.add(new Node(i,j));
				}
			}
		}
		// 입력값끝
		
		//도시의 치킨 최소거리
		result=Integer.MAX_VALUE;
		dfs(0,0,new int[m]);
		System.out.println(result);
	}

	static void dfs(int cnt,int start,int[] chickenSave) {
		if(cnt==m) {
			int sum = 0;
			for(int i=0;i<house.size();i++) {
				//집 - 치킨 최소거리
				int minDistance = Integer.MAX_VALUE;
				for(int ch : chickenSave) {
					int distance=Math.abs(house.get(i).x-chicken.get(ch).x)+Math.abs(house.get(i).y-chicken.get(ch).y);
					minDistance=Math.min(distance,minDistance);
				}
				 sum += minDistance;
			}
			result=Math.min(sum, result);
			return;
		}
		if(start>=chicken.size()) {
			return;
		}
		//치킨 방문안했을때
		dfs(cnt,start+1,chickenSave);
		//치킨 방문했을때
		chickenSave[cnt]=start;
		dfs(cnt+1,start+1,chickenSave);
		
	
		
	}
}