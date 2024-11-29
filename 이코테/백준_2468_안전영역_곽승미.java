import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class 백준_2468_안전영역_곽승미 {
 
    static int N; // 지도 크기
    static int[][] map; // 지도 배열
    static boolean[][] arr; // 잠긴 영역 표시 배열
    static int[] dx = {-1, 1, 0, 0}; // 상,하,좌,우
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 지도 크기 입력
        map = new int[N][N];

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
            	map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 최대 물 높이 찾기
        int max = 0;
        for(int[] row : map) {
            for(int r : row) {
                if(r > max) {
                	max = r; // 최대 물 높이 업데이트
                }
            }
        }

        // 각 물 높이에 대해 안전한 영역 개수 세기
        int maxcnt = 0; // 최대 안전한 영역 수
        for (int val = 0; val <= max; val++) {
            int cnt = 0; // 안전한 영역 수 초기화
            arr = new boolean[N][N]; // 잠긴 부분 초기화

            // 잠긴 영역 표시
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] <= val) {
                    	arr[i][j] = true; // 물에 잠긴 부분 표시
                    }
                }
            }

            // 안전 영역 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!arr[i][j]) { // 잠기지 않은 영역일 경우
                        bfs(i, j); // BFS 실행
                        cnt++; // 안전영역 개수 증가
                    }
                }
            }

            maxcnt = Math.max(maxcnt, cnt); // 최대 업데이트
        }

        System.out.println(maxcnt);
    }

    // BFS로 영역 탐색
    static void bfs(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j}); // 시작 위치 큐에 넣기
        arr[i][j] = true; // 현재 위치를 잠김 표시

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // 큐에서 위치 꺼내기
            int x = cur[0];
            int y = cur[1];

            // 상하좌우 탐색
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k]; 

                // 범위를 벗어나면 무시
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (!arr[nx][ny]) { // 잠기지 않은 영역일 경우
                    arr[nx][ny] = true; // 잠김 표시
                    q.add(new int[]{nx, ny}); // 위치를 큐에 추가
                }
            }
        }
    }
}

