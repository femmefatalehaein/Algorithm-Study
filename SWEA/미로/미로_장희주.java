package 미로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 미로_장희주 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] maze; 
    static boolean[][] visited; 
    static int startX, startY, finalX, finalY;
    // 상, 하, 좌, 우 방향
    static int[] dx = {0, 0, -1, 1}; 
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            br.readLine();
            maze = new int[16][16];
            for (int i = 0; i < 16; i++) {
                String line = br.readLine();
                for (int j = 0; j < 16; j++) {
                    maze[i][j] = line.charAt(j) - '0';
                    if (maze[i][j] == 2) { // 출발
                        startX = i;
                        startY = j;
                    }
                    if (maze[i][j] == 3) { // 도착
                        finalX = i;
                        finalY = j;
                    }
                }
            }

                     visited = new boolean[16][16];

            // BFS 탐색 시작
            if (bfs(startX, startY)) {
                System.out.println("#" + test_case + " 1");
            } else {
                System.out.println("#" + test_case + " 0");
            }
        }
    }

    // BFS 탐색 함수
    static boolean bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>(); // BFS 탐색을 위한 큐 생성
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node current = queue.poll(); 
            int cx = current.x;
            int cy = current.y;

            // 도착지점에 도달하면 true 반환
            if (cx == finalX && cy == finalY) { // 도착지점에 도달하면 종료
                return true;
            }

            // 네 방향으로 이동 (상, 하, 좌, 우)
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i]; 
                int ny = cy + dy[i]; 

                // 미로 범위 안에 있고, 이동할 수 있는 길(1)이며, 아직 방문하지 않은 경우
                if (nx >= 0 && ny >= 0 && nx < 16 && ny < 16 && !visited[nx][ny] && maze[nx][ny] != 1) {
                    queue.offer(new Node(nx, ny)); 
                    visited[nx][ny] = true; 
                }
            }
        }

        return false; // 도착지점에 도달할 수 없는 경우
    }
}
