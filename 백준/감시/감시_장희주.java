package 감시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 감시_장희주 {
	
	static int N,M;
	static int[][] office;
	//상하좌우
	static int[] dx= {-1,1,0,0};
	static int[] dy= {-0,0,-1,1};
	
	static int blindCnt;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//세로크기
		N=Integer.parseInt(st.nextToken());
		//가로크기
		M=Integer.parseInt(st.nextToken());
		//사무실 정보
		office=new int[N][M];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				office[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//입력 끝
		//결과값
		blindCnt=0;
		
		//결과출력
		System.out.println(blindCnt);
		
	
	}

}
