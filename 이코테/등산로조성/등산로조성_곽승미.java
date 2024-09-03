package 등산로조성;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산로조성_곽승미 {
    static int N, K, result;
    static int[][] mountain, visited;
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 이동 방향 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            mountain = new int[N][N];
            visited = new int[N][N];

            int top = 0; // 최고 높이 초기화

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    mountain[i][j] = Integer.parseInt(st.nextToken());
                    top = Math.max(top, mountain[i][j]); // 최고봉 찾기
                }
            }

            result = 0;

            // 최대 높이에서 시작하는 DFS 호출
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mountain[i][j] == top) {
                        dfs(i, j, 1, K);
                    }
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    static void dfs(int x, int y, int dis, int K) {
    	result = Math.max(result, dis); // 최대 거리 갱신

        visited[x][y] = 1; // 방문 표시

        for (int[] d : deltas) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0) {
                if (mountain[nx][ny] < mountain[x][y]) {
                    dfs(nx, ny, dis + 1, K); // 산을 깎지 않고 이동
                } 
                else if (K > 0 && mountain[nx][ny] - mountain[x][y] < K) {
                    int height = mountain[nx][ny];
                    mountain[nx][ny] = mountain[x][y] - 1; // 산 깎기
                    dfs(nx, ny, dis + 1, 0); // 산을 깎은 후 이동
                    mountain[nx][ny] = height; // 원상태로 복구
                }
            }
        }

        visited[x][y] = 0; // 방문 해제
    }
}
