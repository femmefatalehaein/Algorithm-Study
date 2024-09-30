package 백준;
import java.io.*;
import java.util.*;

public class 영역구하기 {
    static int M, N, K;  
    static int[][] grid;  // 모눈종이 배열, 직사각형 내부는 1
    static boolean[][] visited;  
    static int[] dx = {1, -1, 0, 0};  
    static int[] dy = {0, 0, 1, -1};  

    public static void main(String[] args) throws IOException {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  // 세로 크기
        N = Integer.parseInt(st.nextToken());  // 가로 크기
        K = Integer.parseInt(st.nextToken());  // 직사각형의 개수

        grid = new int[M][N];  
        visited = new boolean[M][N];  

        // K개의 직사각형을 입력받아 모눈종이 위에 표시
        for (int i = 0; i < K; i++) {
           
            st = new StringTokenizer(br.readLine());
            // 왼쪽 아래 x좌표
            int x1 = Integer.parseInt(st.nextToken()); 
            // 왼쪽 아래 y좌표
            int y1 = Integer.parseInt(st.nextToken()); 
            // 오른쪽 위 x좌표
            int x2 = Integer.parseInt(st.nextToken()); 
         // 오른쪽 위 y좌표
            int y2 = Integer.parseInt(st.nextToken());  

            // 직사각형의 좌표 영역을 1로 채움 
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    grid[y][x] = 1;  
                }
            }
        }

        // 분리된 영역들의 넓이를 저장할 리스트
        List<Integer> areas = new ArrayList<>();

        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 직사각형 내부가 아니고 방문하지 않은 영역(0)을 찾으면 BFS를 시작
                if (grid[i][j] == 0 && !visited[i][j]) {
                    // 새로운 영역을 찾으면 BFS를 통해 넓이를 계산
                    int areaSize = bfs(i, j);  
                    areas.add(areaSize);  
                }
            }
        }

       
        Collections.sort(areas);  // 넓이를 오름차순으로 정렬
        StringBuilder sb = new StringBuilder();
        sb.append(areas.size()).append("\n");  // 영역의 개수
        for (int area : areas) {
            sb.append(area).append(" ");  // 각 영역의 넓이
        }
        System.out.println(sb.toString().trim());
    }

    // BFS로 영역의 넓이를 계산하는 메서드
    static int bfs(int x, int y) {
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y}); 
        visited[x][y] = true;  
        int areaSize = 0;  

        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();  
            int cx = cur[0];  // 현재 x좌표
            int cy = cur[1];  // 현재 y좌표
            areaSize++;  // 현재 칸을 포함하므로 넓이 +1

            // 상하좌우로 이동하면서 새로운 영역을 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];  
                int ny = cy + dy[i]; 

                // 새 좌표가 모눈종이 범위 내에 있고, 아직 방문하지 않았으며, 직사각형 내부(1)가 아닌 경우
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[nx][ny] && grid[nx][ny] == 0) {
                    visited[nx][ny] = true;  
                    queue.offer(new int[] {nx, ny});  
                }
            }
        }

        return areaSize;  // 계산된 넓이를 반환
    }
}
