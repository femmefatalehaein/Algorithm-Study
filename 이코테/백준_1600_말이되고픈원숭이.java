import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_1600_말이되고픈원숭이 {

	static int k,w,h;
	static int[][] map;
	static int[] dx= {1,-1,0,0}, dy= {0,0,1,-1};
	static int[] hx = {1,2,2,1,-1,-2,-2,-1};
	static int[] hy = {2,1,-1,-2,-2,-1,1,2};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
	
		map = new int[h][w];
		
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0,0));
	}
	
	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[h][w][k+1];
		
		check[y][x][0] = true;
		q.add(new int[] {x,y,0,0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0], curY = cur[1];
			int cnt = cur[2], move = cur[3];
            
			if(curX==w-1 && curY==h-1) {
				 return move;
			}
            
			for(int i=0; i<4; i++) {
				int nx = curX + dx[i];
				int ny = curY + dy[i];
				
				if(nx<0 || nx>w-1 || ny<0 || ny>h-1) continue;
				if(check[ny][nx][cnt])  continue;
				
				if(map[ny][nx] != 1) {
					check[ny][nx][cnt] = true;
					q.add(new int[] {nx,ny,cnt, move+1});
				}
			}

			if(cnt<k){
				for(int i=0; i<8; i++) {
					int nx = curX + hx[i];
					int ny = curY + hy[i];
					
					if(nx<0 || nx>w-1 || ny<0 || ny>h-1) continue;
					if(check[ny][nx][cnt+1])  continue;
					
					if(map[ny][nx] != 1) {
						check[ny][nx][cnt+1] = true;
						q.add(new int[] {nx,ny,cnt+1, move+1});
					}
				}
			}
		}
		return -1;
	}
}