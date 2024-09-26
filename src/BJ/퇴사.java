import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사 {

    private static int [] t ; // 소요 시간을 넣어줄 배열
    private static int [] p ; // 이익을 넣어줄 배열
    private static int n ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        n = Integer.parseInt(br.readLine());
        t = new int[n+1];
        p = new int[n+1];

        // 배열 초기화
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];
// dp 값을 담아줄 배열 생성 -> 최대값을 비교해서 넣어줄 것. 시작위치로 부터

    }

    private static void findMax (int idx) {
        // 범위 밖일 때
        if (idx > n) {
            return ;
        }
    }

}