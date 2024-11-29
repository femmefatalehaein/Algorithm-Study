import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_10026_적록색약_곽승미 {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};  // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 지도 크기
        map = new char[N][N];  // 색상 정보를 저장하는 배열

        for (int i = 0; i < N; i++) {
        	map[i] = br.readLine().toCharArray();
        }

        // 적록색약 아닌 경우 dfs로 구역 수 계산
        visited = new boolean[N][N];
        int cnt1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {  // 방문하지 않은 좌표이면 dfs 시작
                    dfs(i, j, map[i][j]);
                    cnt1++;
                }
            }
        }

        // 적록색약인 경우 (G를 R로 변환)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') {
                    map[i][j] = 'R';
                }
            }
        }

        // 방문 기록 초기화
        visited = new boolean[N][N];
        int cnt2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {  // 방문하지 않은 좌표이면 DFS 시작
                    dfs(i, j, map[i][j]);
                    cnt2++;
                }
            }
        }

        System.out.println(cnt1 + " " + cnt2);
    }
    
    // dfs로 풀이
    static void dfs(int x, int y, char c) {
        visited[x][y] = true;  // 현재 좌표 방문 체크

        // 상, 하, 좌, 우 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 내에 있고, 같은 색상이며, 방문하지 않은 경우
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && map[nx][ny] == c) {
            	dfs(nx, ny, c);  // 재귀 호출
            }
        }
    }
}