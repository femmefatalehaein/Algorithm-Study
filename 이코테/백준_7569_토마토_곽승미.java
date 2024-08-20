package day0820;
/*
 * 백준 7569. 토마토
 * BFS로 풀이
 * 3차원 배열
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_7569_토마토_곽승미 {
    static int M, N, H;  // M: 상자의 가로 칸 수, N: 상자의 세로 칸 수, H: 쌓아 올려지는 상자의 수
    static int[][][] box; // 상자의 상태를 저장할 3차원 배열
    static int[][][] days; // 각 토마토가 익는데 걸리는 일수를 저장할 3차원 배열
    static int[] dx = {1, -1, 0, 0, 0, 0}; // x축 (좌우)
    static int[] dy = {0, 0, 1, -1, 0, 0}; // y축 (상하)
    static int[] dz = {0, 0, 0, 0, 1, -1}; // z축 (위아래)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];  
        days = new int[H][N][M]; 
        Queue<int[]> queue = new LinkedList<>();

        
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) { // 익은 토마토의 위치를 큐에 저장
                        queue.offer(new int[]{h, n, m});
                    }
                    if (box[h][n][m] == 0) { // 익지 않은 토마토는 -1
                        days[h][n][m] = -1;
                    }
                }
            }
        }

        // BFS 수행
        bfs(queue);

        int result = 0;
        boolean flag = true;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (days[h][n][m] == -1) { // 익지 않은 토마토가 남아 있다면
                        flag = false;
                        break;
                    }
                    result = Math.max(result, days[h][n][m]); //가장 오랜 시간이 걸린 익은 토마토의 일수
                }
            }
        }

        // 결과 출력 (토마토가 다 익었으면 최소 일수를, 아니면 -1 출력)
        if(flag) {
        	System.out.println(result);
        }else {
        	System.out.println(-1);
        }
    }

    // BFS 탐색
    static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int z = cur[0];
            int y = cur[1];
            int x = cur[2];

            // 6방향 탐색
            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M && days[nz][ny][nx] == -1) {
                    days[nz][ny][nx] = days[z][y][x] + 1; // 일수 갱신
                    queue.offer(new int[]{nz, ny, nx}); // 새로운 위치를 큐에 추가
                }
            }
        }
    }
}
