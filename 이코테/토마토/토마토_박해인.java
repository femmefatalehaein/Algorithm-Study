package 토마토;

import java.util.*;
import java.io.*;

public class 토마토_박해인 {
	
	static int N,M;
	static int [][] map;
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,1,-1};
	static int posX, posY,cnt;
	static Stack<int[]> stk;
	
	public static void howDay() {
		
		boolean check = false;
		//일단 체크해 익은데 어디있는지 체크하기
		
		try {
		for(int i =0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//익은 토마토라면..
				if(map[i][j]==1) {
				stk.add(new int []{i,j});
				}//하나라도 0이면 true..로 바꾸어준다.
				else if(map[i][j]==0){check =true;}	
			}
		}
		
		//물들이기
		while(!stk.isEmpty()) {
		
			int tgt [] = stk.pop();
			for(int k = 0; k<4; k++) {
				posX = tgt[0]+dx[k];
				posY = tgt[1]+dy[k];
				if(posX>=0&&posX<N&&posY>=0&&posY<M) {
					if(map[posX][posY] != -1)
						map[posX][posY] = 1;
				}				
			}	
		}
		
		//하나라도 0이 있다면..check가 true라면
		if(check) {
			cnt++;
			howDay();
			}else {return;}	
		}catch(StackOverflowError e) {
			cnt = -1;
		}
	}
	
	public static void main(String [] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//6
		M = Integer.parseInt(st.nextToken());
		
		//4
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		Scanner sc = new Scanner(System.in);
		
		cnt = 0;
		
		for(int i =0; i<N; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			
			for(int j=0; j<M; j++) {
				
				map[i][j] = Integer.parseInt(st.nextToken());		
			
			}
			
		}
		stk = new Stack<int[]>();
		howDay();
		System.out.println(cnt);
		
		
		
	}
	
	
	
}
