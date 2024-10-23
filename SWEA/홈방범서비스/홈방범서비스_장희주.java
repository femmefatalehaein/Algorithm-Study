package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 홈방범서비스_장희주 {
	
	static class Node{
		int x,y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

	static int N,M;
	static int[][] city;
	static int HomeCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			//도시의 크기
			N=Integer.parseInt(st.nextToken());
			//하나의 집이 지불할수있는 비용
			M=Integer.parseInt(st.nextToken());
			city=new int[N][N];
			for(int i=0;i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					city[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//최대 집 갯수
			HomeCnt=0;
			//중심 좌표
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					//k값 경우의 수 모두 돌아보기
					for(int a=1;a<2*N;a++) {
						//최대값 구하기
						HomeCnt=Math.max(profit(a,i,j),HomeCnt);
					}
				}
			}
			//결과 출력
			System.out.println("#"+test_case+" "+HomeCnt);
		}

	}
	//구하고자 하는 좌표
	static int profit(int k, int x, int y) {
		int cnt=0;
		//운영비용
		int area=k * k + (k - 1) * (k - 1);
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				//마름모 범위안
				if(Math.abs(i-x)+Math.abs(j-y)<k) {
					//집일때
					if(city[i][j]==1) {
						//집갯수구하기
						cnt++;
					}
				}
			}
		}
		//값어치 구하기
		//얻는 수익-운영비용
		
		return cnt * M - area >= 0 ? cnt : 0;
		
	}
}