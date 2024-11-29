package swea.A형대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_활주로 {
    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/res/input_활주로.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int N, X, map[][];
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            // Row - 한 행씩 설치 가능 여부 파악
            for (int i = 0; i < N; i++) {
                if (isAbleToBuild(map[i], N, X)) {
                    answer++;
                }
            }

            // Col - 한 열씩 설치 가능여부 파악
            for (int i = 0; i < N; i++) {
                int[] arr = new int[N];
                for (int j = 0; j < N; j++) {
                    arr[j] = map[j][i];
                }
                if (isAbleToBuild(arr, N, X)) {
                    answer++;
                }
            }
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.println(sb.toString());
    }

    private static boolean isAbleToBuild(int[] arr, int N, int X) {
        // 중복 설치 방지를 위한 배열 선언
        boolean[] installed = new boolean[N];

        // 단계별로 설치 단차 확인하기
        for (int i = 0; i < N - 1; i++) { // 현재랑 그 다음칸이랑 비교해주기 위해 N-1
            // 1. 0일 떄
            if (arr[i] == arr[i + 1]) continue;
                // 2. 1 초과할 떄
            else if (Math.abs(arr[i] - arr[i + 1]) > 1) return false;
                // 3. 1일 때
            else {
                // 3-1) 올라감
                if (arr[i] < arr[i + 1]) {
                    if (i + 1 - X < 0) return false;
                    for (int j = i; j > i - X; j--) {
                        if (arr[j] != arr[i] || installed[j]) return false;
                    }
                    for (int j = i; j > i - X; j--) {
                        installed[j] = true;
                    }

                }
                // 3-2) 내려감
                else {
                    if (i + X >= N) return false;
                    for (int j = i + 1; j <= i + X; j++) {
                        if (arr[j] != arr[i+1] || installed[j]) return false;
                    }
                    for (int j = i + 1; j <= i + X; j++) {
                        installed[j] = true;
                    }
                }

            }
        }
        return true;
    }
}
