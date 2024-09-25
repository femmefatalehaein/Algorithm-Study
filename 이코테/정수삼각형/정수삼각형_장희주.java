package 정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형_장희주 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=null;
		int[][] dp=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<=i;j++) {
				dp[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
            	//왼쪽위에서 내려오는 경우
                int upLeft = (j == 0) ? 0 : dp[i - 1][j - 1];
                //바로 위에서 내려오는 경우
                int up = (j == i) ? 0 : dp[i - 1][j];
                //최대합 저장
                dp[i][j] += Math.max(upLeft, up);
            }
        } 
		int max = 0;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, dp[n - 1][j]);
        }
        
        System.out.println(max);

	}

}
