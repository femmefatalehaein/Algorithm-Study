package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_게리맨더링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] population = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        int[][] graph = new int[N + 1][N + 1];
        // 연결 상태 표시할 때 더 편한게 뭔지?
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int rotation = Integer.parseInt(st.nextToken());
            for (int j = 0; j < rotation; j++) {
                int connected = Integer.parseInt(st.nextToken());
                graph[i][connected] = 1;
            }
        }
        
        // 탐색을 어떻게 돌려야되지?
        // 1. 연결관계 확인 방법?
        // 2.

    }
}
