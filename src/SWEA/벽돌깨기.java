package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {
    private static int N, W, H, maxTotalBricks, countBricks;
    private static int[][] originalMap;

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/res/input_벽돌깨기.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            originalMap = new int[H][W];
            int[][] simulationMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    originalMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            maxTotalBricks = 0;

            for (int i = 0; i < W; i++) {
                simulationMap = arrayCopy(originalMap, simulationMap);
                breakBricks(N, simulationMap, 0, i);
            }
            sb.append("#").append(tc).append(' ').append(maxTotalBricks).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void breakBricks(int depth, int[][] map, int brokenBricks, int w) {
        if (depth == N) {
            maxTotalBricks = Math.max(maxTotalBricks, brokenBricks);
            return;
        }
        // 들어왔으면 height 부터 돌려주기
        for (int h = 0; h < H; h++) {
            if (map[h][w] == 0) continue;
            countBricks =0;
            breakRange(map[h][w], h, w, map); // 요 안에서 countBrick 계산됨
            map = moveArray(map); // map  이동한 다음
            for (int i = 0; i < W; i++) {
                breakBricks(depth + 1, map, brokenBricks + countBricks, i);
            }

        }


    }
    // 배열 이동을 위한 메서드
    private static int[][] moveArray(int[][] map) {
        for (int w = 0; w < W; w++) {
            boolean flag = true;
            while (flag) {
                int changeCnt = 0;
                for (int i = 0; i < H - 1; i++) { // 세로로만 보니까 H=r
                    int temp = map[i][0];
                    int tempNext = map[i + 1][0];
                    if (temp != 0 && tempNext == 0) {
                        map[i][0] = tempNext;
                        map[i + 1][0] = temp;
                        changeCnt += 1;
                    }
                    if (changeCnt == 0) flag = false;
                }
            }
        }
        return map;
    }

    // 배열 복사하기 위한 메소드
    private static int[][] arrayCopy(int[][] origin, int[][] clone) {
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[0].length; j++) {
                clone[i][j] = origin[i][j];
            }
        }
        return clone;
    }

    // 파괴할 범위와 파괴할 벽돌의 개수를 세기 위한 메서드
    private static void breakRange(int range, int x, int y, int[][] map) {
        for (int i = 0; i < range; i++) {
            // 상
            if (x - i >= 0) {
                if (map[x - i][y] > 0) {
                    map[x][y] = 0;
                    breakRange(map[x - i][y], x - i, y, map);
                    countBricks +=1;
                    map[x - i][y] = 0;
                }
            }
            // 하
            if (x + i < H) {
                if (map[x + i][y] > 0) {
                    map[x][y] = 0;
                    breakRange(map[x + i][y], x + i, y, map);
                    countBricks +=1;
                    map[x + i][y] = 0;
                }
            }

            // 좌
            if (y - i >= 0) {
                if (map[x][y - i] > 0) {
                    map[x][y] = 0;
                    breakRange(map[x][y - i], x, y - i, map);
                    countBricks +=1;
                    map[x][y - i] = 0;
                }
            }
            // 우
            if (y + i < H) {
                if (map[x][y + i] > 0) {
                    map[x][y] = 0;
                    breakRange(map[x][y + i], x, y + i, map);
                    countBricks +=1;
                    map[x][y + i] = 0;
                }
            }
        }
    }


}
