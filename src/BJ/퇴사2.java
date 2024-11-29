import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {
    private static int N;
    private static int[] T, P, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        T = new int[N + 1];  // 상담 기간 저장
        P = new int[N + 1];  // 상담 금액 저장
        dp = new int[N + 2]; // 최대 수익 저장, dp[N+1]도 필요

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (i + T[i] <= N + 1) {  // 상담 종료일이 퇴사일을 넘지 않으면
                dp[i] = Math.max(P[i] + dp[i + T[i]], dp[i + 1]); // 역으로 세주기.
            } else {
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[1]); // 1에 들어온 값이 최대비용
    }
}
