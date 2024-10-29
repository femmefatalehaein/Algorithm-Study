
import java.io.*;
import java.util.*;



//아니 이거 입력 왜이래 ;;;;;
class Main {

	static int N,T;
	static Point map [][];
	
	static int dx [] = {-1,1,0,0};
	static int dy [] = {0,0,1,-1};
	
	
	
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int cost;
		
		public Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		//해당 값이 작은걸 출력한다..
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	static int Dijkstra(Point from, Point to) {
		
		boolean visited [][] = new boolean[N][N];
		int dist [][] = new int [N][N];
		int INF = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dist[i][j] = INF;
			}
		}
		
		dist[from.x][from.y] = from.cost;
		//우선순위큐 만들기
		PriorityQueue<Point> q = new PriorityQueue<>();
		//아무튼 처음 값은 0 이니까..
		
		q.offer(from);
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			int curX = curr.x;
			int curY = curr.y;
			
			if(visited[curX][curY]) continue;///////->이거 안해도 되지 않나?
			visited[curX][curY] = true;
			
			//posX,posY 다음으로 갈 수 있는거
			for(int i=0; i<4; i++) {
				int posX = curX+dx[i];
				int posY = curY+dy[i];
				
				if(posX>=N||posY>=N||posX<0||posY<0)continue;
				//이제 여기서 방문처리를 할지 말지를 구하는거지
				if(visited[posX][posY]) continue;
				
				int newDist = dist[curX][curY] + map[posX][posY].cost;
				// 더 작다면
				 if (newDist < dist[posX][posY]) {
	                    dist[posX][posY] = newDist;
	                    q.offer(new Point(posX, posY, newDist));
	                }
			}
			
		}
		
		return(dist[to.x][to.y]);
		
	}
	

	
	public static void main(String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;
		while(true) {
		
		N = Integer.parseInt(br.readLine());
		if(N==0)break;
		
		map = new Point[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = new Point(i,j,Integer.parseInt(st.nextToken()));
				
			}
			
		}
		
		System.out.println("Problem "+(t+1)+": "+Dijkstra(map[0][0],map[N-1][N-1]));
	
		t++;
		}
	}
	

}
