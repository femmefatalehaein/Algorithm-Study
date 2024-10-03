package 미로탐색;
import java.io.*;
import java.util.*;

public class 미로탐색_장희주 {
    
    // 방향 벡터 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 미로의 행 크기
        int M = Integer.parseInt(st.nextToken()); // 미로의 열 크기
        
        int[][] maze = new int[N][M]; 
        boolean[][] visited = new boolean[N][M]; 
        
      
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0'; 
            }
        }
        
        // BFS 탐색 시작
        System.out.println(bfs(maze, visited, N, M));
    }
    
    // BFS 탐색 함수
    static int bfs(int[][] maze, boolean[][] visited, int N, int M) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0}); 
        visited[0][0] = true; 
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0]; 
            int y = current[1]; 
            
            // 도착 지점에 도달했을 경우
            if (x == N - 1 && y == M - 1) {
                return maze[x][y]; // 최소 칸 수 반환
            }
            
            // 네 방향으로 이동 (상, 하, 좌, 우)
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 미로의 범위를 벗어나지 않고, 이동 가능한 칸이며, 아직 방문하지 않은 경우
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && maze[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[] {nx, ny}); // 큐에 새로운 위치 추가
                    visited[nx][ny] = true; // 방문 처리
                    maze[nx][ny] = maze[x][y] + 1; // 이동한 칸까지의 거리를 기록
                }
            }
        }
        
        return -1; // 도착 지점에 도달할 수 없는 경우
    }
}
