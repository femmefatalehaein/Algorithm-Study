package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_등산로조성 {
    private static int N, K;
    private static int [][] map;
    private static List<int[]> highest;
    public static void main(String[] args) throws IOException {

        System.setIn(Files.newInputStream(Paths.get("./src/res/input_2117.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            highest = new ArrayList<>(); // 가장 높은 곳의 좌표를 담아줄 리스트
            int max = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }
            // 가장 높은 곳 좌표 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) {
                        highest.add(new int[] {i, j});
                    }
                }
            }




            sb.append("#").append(tc).append(" ").append("answer").append("\n");
        }
        System.out.println(sb.toString());
    }
}
