package codingTest;
import java.io.*;
import java.util.*;

public class Solution_2805_박해인 {

	public static int input [][];
	
	public static void main(String []  args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			//-------------------------------------
			int N = Integer.parseInt(br.readLine());
			input = new int[N][N];

			
			// 입력받기
			for(int n=0; n<N; n++) {
				String str = br.readLine();
				for(int c =0; c<N; c++) {
					int x = Integer.parseInt(str.split("")[c]);
					input[n][c] = x;
				}
			}
			// 입력받기 끝 ~~
			
			// 나누어서 생각하기 . . .
			int idx = 0;
			int sum = 0;
			// 1. 첫부분
			for(int r=0; r<N/2; r++) {
				for(int c = (N/2-idx); c<=(N/2+idx); c++) {
					sum+=input[r][c];
					
				}
				idx++;
			}
			//System.out.println("-----------");
			// 2. 가운데 줄
			for(int c = 0; c<N; c++) {
				sum+=input[N/2][c];
			}
			
			//System.out.println("-----------");
			// 3. 마지막 부분
			idx--;
			for(int r=N/2+1; r<N; r++) {
				for(int c = (N/2-idx); c<=(N/2+idx); c++) {
					sum+=input[r][c];
				}
				idx--;
			}
			
			
			System.out.println("#"+(t+1)+" "+sum);

		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
