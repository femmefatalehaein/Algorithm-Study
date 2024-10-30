package 활주로건설;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 활주로건설_장희주 {

	static int N, X;
	static int[][] map;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 지형크기
			N = Integer.parseInt(st.nextToken());
			// 경사로의 길이
			X = Integer.parseInt(st.nextToken());
			// 지형정보
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			count = 0;
			for(int i = 0; i < N; i++) {
				count += check(map[i]);
				count += check(transform(map,i));
			}
			// 결과출력 
			System.out.println("#" + test_case + " " + count);
		}

	}
	static int[] transform(int[][] array, int index) {
		
		int[] newArray=new int[N];
		for(int i=0;i<N;i++) {
			newArray[i]=array[i][index];
		}
		return newArray;
	}

	static int check(int[] array) {
		
		int cnt=1;
		for(int i=0;i<N-1;i++) {
			if(array[i]==array[i+1]) {
				cnt++;
			}
			//내림막길
			else if(array[i]-array[i+1]==1) {
				
				if(cnt>=0) {
					cnt=-X+1;
					
				}else {
					return 0;
				}
			}
			//오름막길
			else if(array[i]-array[i+1]==-1) {
				
				if(cnt>=X) {
					cnt=1;
				}else {
					return 0;
				}
			}else {
				return 0;
			}
		}
		if(cnt<0) {
			return 0;
		}else {
			return 1;
		}
	}
}