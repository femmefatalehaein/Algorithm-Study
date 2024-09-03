package 프로세서연결하기;
/*
 * SWEA 1767. 프로세서 연결하기
 * + DFS를 사용하여 가능한 모든 코어 연결 조합 탐색
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기_곽승미 {

    static int N;
    static int[][] map;
    static int[] dy = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dx = {1, 0, -1, 0}; // 우, 하, 좌, 상
    static List<int[]> core; // 코어 위치
    static int max_core, min_length;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/s_1767_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            core = new ArrayList<>();
            max_core = 0;
            min_length = Integer.MAX_VALUE;

            // 배열 초기화 및 코어 위치 파악
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                	map[i][j] = Integer.parseInt(st.nextToken());
                	// 위치가 가장자리가 아닌 코어 저장
                	if (i > 0 && i < N - 1 && j > 0 && j < N - 1 && map[i][j] == 1) {
                        core.add(new int[]{i, j});
                    }
                }
            }

            // 코어의 수에 대한 모든 가능한 조합을 시도
            dfs(0, 0, 0);

            System.out.println("#" + tc + " " + min_length);
        }
    }

    // DFS를 통해 모든 가능한 코어 연결 조합을 탐색
    static void dfs(int idx, int cores, int wireLength) {
    	// 모든 코어 처리했을 때
        if (idx == core.size()) {
        	// 최대 코어, 최소 길이 업데이트
            if (cores > max_core) {
            	max_core = cores;
                min_length = wireLength;
            } else if (cores == max_core) {
            	min_length = Math.min(min_length, wireLength);
            }
            return;
        }
        
        // 현재 처리 중인 코어의 위치
        int x = core.get(idx)[0];
        int y = core.get(idx)[1];

        // 모든 방향에 대해 시도
        for (int d = 0; d < 4; d++) {
            int length = canConnect(x, y, d); // 해당 방향으로 전선 연결할 수 있는지 확인
            if (length != -1) { // 연결 가능하다면
                connect(x, y, d, length, true);  // 전선 연결
                dfs(idx + 1, cores + 1, wireLength + length);  // 다음 코어로 이동
                connect(x, y, d, length, false); // 전선 해제
            }
        }

        // 연결하지 않고 다음 코어로 이동
        dfs(idx + 1, cores, wireLength);
    }

    // 주어진 방향으로 선을 연결할 수 있는지 확인
    static int canConnect(int x, int y, int d) {
        int length = 0;
        int nowX = x;
        int nowY = y;

        while (true) {
            nowX += dx[d];
            nowY += dy[d];
            
            // 범위 체크
            if (nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) {
                return length;
            }
            
            // 다른 코어나 전선이 이미 있는 경우
            if (map[nowX][nowY] != 0) {
                return -1;  // 연결 불가능
            }

            length++;
        }
    }

    // 주어진 방향으로 전선을 연결하거나 해제
    static void connect(int x, int y, int d, int length, boolean isConnecting) {
        int nowX = x;
        int nowY = y;

        for (int i = 0; i < length; i++) {
            nowX += dx[d];
            nowY += dy[d];
            map[nowX][nowY] = isConnecting ? 2 : 0;  // 2: 선 연결, 0: 연결 해제
        }
    }
}
