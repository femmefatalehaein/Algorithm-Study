package 벽부수고이동하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기_장희주 {

    static class Node {
        int x, y, dist, wall;

        public Node(int x, int y, int dist, int wall) {
            this.x = x;
            this.y = y;
            this.dist = dist;
         // 벽을 부쉈는지 여부 (0: 부수지 않음, 1: 부숨)
            this.wall = wall; 
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
     // 0: 벽을 부수지 않은 상태, 1: 벽을 부순 상태
        visited = new boolean[N][M][2]; 

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 1, 0));
     // 벽을 부수지 않은 상태에서의 방문
        visited[0][0][0] = true; 

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            int dist = current.dist;
            int wall = current.wall;

            // 도착 지점에 도달하면 거리 출력
            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나면 
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 벽이 아니고 방문하지 않은 경우
                if (map[nx][ny] == 0 && !visited[nx][ny][wall]) {
                    visited[nx][ny][wall] = true;
                    queue.offer(new Node(nx, ny, dist + 1, wall));
                }

                // 벽이고 아직 벽을 부순 적이 없는 경우 
                if (map[nx][ny] == 1 && wall == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    //벽부수기
                    queue.offer(new Node(nx, ny, dist + 1, 1));
                }
            }
        }

        // 도착할 수 없는 경우
        return -1;
    }
}
