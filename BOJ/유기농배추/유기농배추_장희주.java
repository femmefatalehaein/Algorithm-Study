import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추_장희주 {

    // 좌표를 저장
    static class Node {
        int x, y;

        // 생성자: 좌표 (x, y) 초기화
        public Node(int x, int y) {
           
            this.x = x;
            this.y = y;
        }
    }

    
    static int N, M, K; 
    //상하좌우
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1}; 
    static int[][] array; 
    static boolean[][] visited; 

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int T = Integer.parseInt(br.readLine()); 

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 배추밭 세로 길이 
            M = Integer.parseInt(st.nextToken()); // 배추밭 가로 길이 
            K = Integer.parseInt(st.nextToken()); // 배추가 심어진 위치 개수

            array = new int[N][M]; 

            // 배추 위치 
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); // 배추의 x좌표
                int y = Integer.parseInt(st.nextToken()); // 배추의 y좌표
                array[x][y] = 1; 
            }

            int result = 0; // 지렁이 개수
            visited = new boolean[N][M]; // 방문 여부

            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 배추가 있고 아직 방문하지 않은 곳이면 BFS 탐색 시작
                    if (array[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        result++; // 지렁이 수 증가
                    }
                }
            }

            // 결과 출력
            System.out.println(result);
        }
    }

    // BFS
    static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>(); 
        q.add(new Node(x, y)); 
        visited[x][y] = true; 

        while (!q.isEmpty()) {
            Node c = q.poll(); 

            // 4방향탐색
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i]; 
                int ny = c.y + dy[i]; 

                // 새 좌표가 배열 경계를 벗어나지 않았는지, 방문하지 않은 배추인지, 그리고 배추가 있는지 
                if (nx >= N || ny >= M || nx < 0 || ny < 0 || visited[nx][ny] || array[nx][ny] == 0) {
                    continue; 
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny)); 
            }
        }
    }
}
