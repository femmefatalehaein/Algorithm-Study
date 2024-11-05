package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설 {

    private static int N, X, map[][];

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/res/input_활주로.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= 1; tc++) {
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
            answer += toRow(map, X);
            toCol();

            sb.append('#').append(tc).append(' ').append(answer).append("\n");
        }
        System.out.println(sb.toString());

    }

    private static int toRow(int[][] map, int x) {
        int countLoads = 0;
        // i는 전체를 돌아야되니까 N만큼 전부 돌기
        for (int i = 0; i < N; i++) {
            // j는 바로 앞과 비교해주기 때문에
            for (int j = 0; j < N - 1; j++) {
                boolean isAbleToBuild = true;
                if (Math.abs(map[i][j] - map[i][j + 1]) > 1) break;
                else if (Math.abs(map[i][j] - map[i][j + 1]) == 0) continue;
                // 1 차이 날 떄
                else {
                    if (map[i][j] > map[i][j + 1] && j+x < N) {
                        boolean isSameHeight = true;
                        for (int k = j+1; k < j+x; k++) { // j 앞부터 X까지 같은 높이인지?
                            if (map[i][k] != map[i][k+1]) {
                                isSameHeight=false;
                                break;
                            }
                        }
                        if (!isSameHeight) {
                            isAbleToBuild = false;
                            break;
                        }else { // 경사로 설치 가능한경우
                            j = j+x;
                        }
                    } else if (map[i][j] < map[i][j + 1] && j-x >= 0) {
                        boolean isSameHeight = true;
                        for (int k = j-1; k > j-x ; k--) {
                            if (map[i][k] != map[i][k - 1]) {
                                isSameHeight = false;
                                break;
                            }
                        }
                        if (!isSameHeight) {
                            isAbleToBuild = false;
                            break;
                        }
                    }
                }
                if (isAbleToBuild)
                    countLoads ++;
            }
        }
        return countLoads;
    }

    private static void toCol() {

    }
}
