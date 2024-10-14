package 그림;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그림_장희주 {
    
    static int n, m;
    static int[][] canvas; 
    static boolean[][] visited; 
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // n: 도화지의 세로 크기, m: 도화지의 가로 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        canvas = new int[n][m];
        visited = new boolean[n][m];

        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                canvas[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pictureCount = 0;  // 그림의 개수
        int maxArea = 0;       // 가장 넓은 그림의 넓이

         //BFS로 그림 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //1이면서 방문하지 않은 곳
                if (canvas[i][j] == 1 && !visited[i][j]) {
                    pictureCount++; // 새로운 그림 발견
                    int area = bfs(i, j); // 해당 그림의 넓이 계산
                    maxArea = Math.max(maxArea, area); // 가장 넓은 그림의 넓이 갱신
                }
            }
        }

        // 결과 출력
        System.out.println(pictureCount); // 그림의 개수
        System.out.println(maxArea);      // 가장 넓은 그림의 넓이
    }

    //그림의 넓이를 계산하는 함수
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        int area = 0; // 현재 그림의 넓이
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            area++; // 그림의 넓이 증가

            // 상, 하, 좌, 우로 인접한 칸 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 내에 있고, 1이면서 아직 방문하지 않은 칸일 경우
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && canvas[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return area; // 그림의 넓이 반환
    }
}
