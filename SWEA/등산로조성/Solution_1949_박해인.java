package 등산로조성;
import java.util.*;
import java.io.*;


public class Solution_1949_������ {
	
	//�� �ѱ����� -K �� �� �־�...
	static int T,N,K;
	static int[][] map;
	static int [] dx = {-1,1,0,0};
	static int [] dy = {0,0,1,-1};
	static int MaxLength, high;
	static ArrayList<int[]> highs ;
	
	
	static int bfs(int fromX, int fromY) {
		
		boolean visited [][] = new boolean [N][N];
		int depth = 1;
		Queue<int[]> q = new LinkedList<>();	
		
		q.offer(new int[]{fromX,fromY});
		
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			//ť�� ����ִ� �ֵ鸸ŭ
			for(int i =0; i<size; i++) {
			
				int[] curr = q.poll();
				int curX = curr[0];
				int curY = curr[1];
			
				for(int d=0; d<4; d++) {
					
					int nextX = curX+dx[d];
					int nextY = curY+dy[d];		
					//���⼭ �ٰɸ��� �ִµ�>? 
					if(nextX<0||nextY<0||nextX>=N||nextY>=N) continue;
					//else if(visited[nextX][nextY]) continue;
					if(map[nextX][nextY]>=map[curX][curY]) continue;
					q.offer(new int[]{nextX,nextY});
					
				}

			}	
			depth++;
		}
		MaxLength = Math.max(depth, MaxLength);
		return MaxLength;
	} 

	//i,j�� �����Ѵ�. 
	static void getMap(int i, int j){
		
		for(int k=0; k<=K; k++) {
				//*-----------------*//
				// �� ���縦 �����ϴ�...
				map[i][j]-=k;
				getHighs();		
				for(int h = 0; h<highs.size(); h++) {
						int tgtX = highs.get(h)[0];
						int tgtY = highs.get(h)[1];
						MaxLength = Math.max(MaxLength, bfs(tgtX,tgtY));
				}
				map[i][j]+=k;// -> �ٽ� �������ش�.
		}
		return;
	}
	
	static void getHighs(){
		
		highs = new ArrayList<int[]>();

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(high == map[i][j]) {
					highs.add(new int[] {i,j});
				}
			}
		}

	}
	


	public static void main(String [] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		//-----------------------------------------------------------/
		
		for(int t=0; t<T; t++) {		
			//----- input ����.
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			high = map[0][0];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>high) {
					high = map[i][j];
				}
				}
			}			
			
		//-----------------------------------------------------------/
			
			MaxLength = 1;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					//getMap�� k�� �������� ��...
					getMap(i,j);
				}
			}
			System.out.println("#"+(t+1)+" "+(MaxLength-1));

		}

		
		
	}
	
}
