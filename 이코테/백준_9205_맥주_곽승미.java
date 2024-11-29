import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_9205_맥주_곽승미 {
    static int T, N;
    static int hx, hy, fx, fy;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine()); // 편의점 수

            StringTokenizer st = new StringTokenizer(br.readLine());
            hx = Integer.parseInt(st.nextToken());
            hy = Integer.parseInt(st.nextToken());

            graph = new int[N][2]; // 편의점 좌표를 저장할 배열
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                graph[j][0] = Integer.parseInt(st.nextToken()); // x 좌표
                graph[j][1] = Integer.parseInt(st.nextToken()); // y 좌표
            }

            st = new StringTokenizer(br.readLine());
            fx = Integer.parseInt(st.nextToken()); // 축제 x 좌표
            fy = Integer.parseInt(st.nextToken()); // 축제 y 좌표

            visited = new boolean[N]; // 방문 체크 배열
            bfs();
        }
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{hx, hy});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            // 축제 장소에 도달 가능한지 확인
            if (Math.abs(x - fx) + Math.abs(y - fy) <= 1000) {
                System.out.println("happy"); // 도달 가능하면 'happy' 출력
                return;
            }

            // 편의점들을 확인
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    int nx = graph[i][0];
                    int ny = graph[i][1];

                    // 편의점까지의 거리가 1000 미터 이하일 때
                    if (Math.abs(x - nx) + Math.abs(y - ny) <= 1000) {
                        visited[i] = true; // 방문 체크
                        q.add(new int[]{nx, ny}); // 큐에 추가
                    }
                }
            }
        }

        // 축제에 도달할 수 없으면 'sad' 출력
        System.out.println("sad");
    }
}