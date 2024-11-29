package 영역구하기;

import java.io.*;
import java.util.*;

public class 백준_2583_영역구하기_곽승미 {
    static int[][] map;
    static boolean[][] visited;
    static int M, N, K;
    static List<Integer> areas = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken()); // 행
        N = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 사각형 개수
        
        map = new int[M][N]; // 맵 배열
        visited = new boolean[M][N]; // 방문 처리 배열

        // 맵에 사각형 채우기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()); // 왼쪽 위 x
            int y1 = Integer.parseInt(st.nextToken()); // 왼쪽 위 y
            int x2 = Integer.parseInt(st.nextToken()); // 오른쪽 아래 x
            int y2 = Integer.parseInt(st.nextToken()); // 오른쪽 아래 y
            
            // 맵에 사각형 채우기
            for (int x = y1; x < y2; x++) { // y 방향
                for (int y = x1; y < x2; y++) { // x 방향
                    map[x][y] = 1; // 채워진 영역 1로 표시
                }
            }
        }

        // BFS를 사용 - 영역 구하기
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) { // 값이 0이면서 방문하지 않았으면 bfs 실행
                    areas.add(bfs(i, j));
                }
            }
        }

        Collections.sort(areas); // 오름차순 정렬
        System.out.println(areas.size());
        for (int area : areas) {
            System.out.print(area + " ");
        }
    }

    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        int areaSize = 1; 
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                
                // 맵 영역을 벗어나지 않고, 방문하지 않았고, 사각형에 포함되지 않았다면
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true; // 방문 처리
                    queue.offer(new int[]{nx, ny}); // 큐에 넣기
                    areaSize++; // 영역 크기 증가
                }
            }
        }

        return areaSize;
    }
}
