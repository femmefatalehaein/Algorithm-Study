import java.io.*;
import java.util.*;

public class 방향전환_문서빈 {
    static int T, startR, startC, endR, endC, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());
            endR = Integer.parseInt(st.nextToken());
            endC = Integer.parseInt(st.nextToken());

            // 두 좌표 간의 가로 및 세로 거리 차이 계산
            int rowDiff = Math.abs(startR - endR);
            int colDiff = Math.abs(startC - endC);
            int rowcolDiff = Math.abs(rowDiff-colDiff);

         // 1. 대각선 이동으로 최대한 가까운 위치까지 이동합니다.
            ans = Math.min(rowDiff, colDiff) * 2;

            // 2. 남은 거리(수직 또는 수평 이동) 처리
            if (rowcolDiff % 2 == 0) {
                ans += (rowcolDiff * 2);
            } else {
                ans += (rowcolDiff * 2) - 1;
            }


            System.out.println("#" + tc + " " + ans);
        }
    }
}
