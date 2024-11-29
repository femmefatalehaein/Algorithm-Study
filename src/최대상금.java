package swea.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 최대상금 {
    static char[] data;
    static int N, MAX;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            data = st.nextToken().toCharArray();
            N = Integer.parseInt(st.nextToken()); // N을 다 돌 필요 엑스
            updateN();
            MAX = 0;
            dfs(0, 0);
            sb.append("#").append(tc).append(" ").append(MAX).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int nth, int k) {
        if (nth == N) {
            checkMax();
            return;
        }

        for (int i = k; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {
                swap(i, j);
                dfs(nth + 1, i);// 자신부터 변경 가능
                swap(i, j);
            }
        }
    }

    private static void checkMax() {
        int num = 0;
        for (int i = 0; i < data.length; i++) {
            num = num * 10 + (data[i] - '0');
        }
        MAX = Math.max(MAX, num);
    }
    // 자리 변경 코드
    private static void swap(int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private static void updateN() {
        if (N > data.length) {
            if (N % 2 != data.length % 2) {
                N = data.length - 1;
            } else {
                N = data.length;
            }
        }
    }


}