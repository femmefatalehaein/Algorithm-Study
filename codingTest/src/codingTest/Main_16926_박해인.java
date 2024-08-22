package codingTest;

import java.io.*;
import java.util.*;
		
public class Main_16926_박해인 {

	static int N,M,R;
	static int[][] input;
	static int min;
	
	static int[] dx = {0, 1, 0, -1}; // 왼쪽으로 넣는, 위로 넣는, 오른쪽으로 넣는, 아래로 넣는
	static int[] dy = {1, 0, -1, 0};
	
    // 회전 시키는 메소드
    static void rotate() {
    	
    	// 둘중 작은 값 /2만큼 회전시켜야 해!
    	for(int t=0; t<min/2; t++) { // 회전 시킬 그룹의 갯수 구하기
    		
    		int x = t;
    		int y = t;
    		System.out.println("t는"+t);
    		//나올 수 있는 조합 ? (0,0) (1,1)   
    		
    		int temp = input[x][y]; // 마지막에 넣을 값 미리 빼놓음
    		
    		System.out.println("temp는 : "+temp);
    		
    		int idx = 0; // 우, 하, 좌, 상 방향으로 이동하며 반시계 방향으로 값 넣을 인덱스
    		
    		// idx 0, 1, 2, 3
    		
    		while(idx < 4) { // 왼쪽으로 넣는, 위로 넣는, 오른쪽으로 넣는, 아래로 넣는 연산을 차례로 수행
    						 // 오른쪽 접근, 아래쪽 접근, 왼쪽 접근, 위로 접근
    			int nx = x + dx[idx];
    			int ny = y + dy[idx];
    			
    			// 범위 안이라면
    			if(nx < N-t && ny < M-t && nx >= t && ny >= t) {
    				input[x][y] = input[nx][ny];
    				x = nx;
    				y = ny;
    			} 
    			
    			// 범위를 벗어났다면 다음 방향으로 어감
    			else {
    				idx++;
    			}
    		}
    		
    		input[t+1][t] = temp; // 빼 놓은 값 넣어 줌
    	}
    }
    
	public static void main(String [] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		input = new int [N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		min = Math.min(N, M);
		
		
		for(int i=1; i<=R; i++) { // 회전 횟수만큼 회전시킴
    		rotate();
    	}
		
		
		for(int i=0; i<N; i++) {
    		for(int j=0; j<M; j++) {
    			System.out.print(input[i][j] + " ");
    		}
    		System.out.println();
    	}
		
	}
	
}

