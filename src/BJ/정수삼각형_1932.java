package backjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형_1932 {

    static int N ;
    static Integer[][] arr;
    static Integer[][] dp;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());;

        arr = new Integer[N][N];
        dp = new Integer[N][N];

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j <= i ;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = arr[0][0]; // 맨 첫 인덱스의 최대는 자기자신

        for(int i = 0 ; i < N ; i++)
            findMax(N-1,i);

        int max_value = 0;
        for(int i = 0 ; i < N ;i++)
            max_value = Math.max(max_value, dp[N-1][i]);

        System.out.println(max_value);
    }
    public static int findMax(int r, int c) { // 탑따운

        if(dp[r][c] == null) {
            if(c==0) {
                dp[r][c] = findMax(r-1,c) + arr[r][c];
            }
            else if(r==c) {
                dp[r][c] = findMax(r-1,c-1) + arr[r][c];
            }
            else
                dp[r][c] = Math.max(findMax(r-1,c), findMax(r-1,c-1)) + arr[r][c];
        }

        return dp[r][c];
    }
}



