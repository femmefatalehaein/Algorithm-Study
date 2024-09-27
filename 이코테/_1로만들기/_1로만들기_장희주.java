package _1로만들기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1463번 1로 만들기
public class _1로만들기_장희주 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(br.readLine());

        int dp[] = new int[x + 1];

        dp[0] = dp[1] = 0;

        for (int i = 2; i <= x; i++) {
            dp[i] = dp[i - 1] + 1; // 먼저 1을 빼준다 
         // 1을 뺀 값과 2로 나눈 값 중 최솟값을 dp[i]에 저장한다 
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1); 
         // 1을 뺀 값과 2로 나눈 값 중 최소값인 dp[i] 와 3으로 나눈 값 중 최솟값을 dp[i]에 저장
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1); 
        }

        System.out.println(dp[x]);
    }
}