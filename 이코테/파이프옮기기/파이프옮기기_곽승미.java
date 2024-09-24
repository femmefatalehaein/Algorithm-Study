package 파이프옮기기;

/*
 * 백준 17070. 파이프 옮기기 1
 * DFS로 풀이
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기_곽승미 {
    static int count = 0; // 결과 저장
    static int N; // 집 크기
    static int[][] home; // 집 상태 2차원 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        home = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS 호출(0,1 좌표와 초기상태 전달)
        dfs(0, 1, 0); 

        // 결과값 출력
        System.out.println(count);
    }
    
    static void dfs(int x, int y, int state) {
        // 도착점에 도달한 경우 count 증가
        if (x == N - 1 && y == N - 1) {
            count++;
            return;
        }

        // 가로 상태일때
        if (state == 0) {
            // 가로로 이동할 수 있는지 확인
            if (y + 1 < N && home[x][y + 1] == 0) {
                dfs(x, y + 1, 0);
            }
            // 대각선으로 이동할 수 있는지 확인
            if (x + 1 < N && y + 1 < N && home[x][y + 1] == 0 && home[x + 1][y] == 0 && home[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 2);
            }
        }

        // 세로 상태일때
        else if (state == 1) {
            // 세로로 이동할 수 있는지 확인
            if (x + 1 < N && home[x + 1][y] == 0) {
                dfs(x + 1, y, 1);
            }
            // 대각선으로 이동할 수 있는지 확인
            if (x + 1 < N && y + 1 < N && home[x][y + 1] == 0 && home[x + 1][y] == 0 && home[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 2);
            }
        }

        // 대각선 상태일때
        else if (state == 2) {
            // 가로로 이동할 수 있는지 확인
            if (y + 1 < N && home[x][y + 1] == 0) {
                dfs(x, y + 1, 0);
            }
            // 세로로 이동할 수 있는지 확인
            if (x + 1 < N && home[x + 1][y] == 0) {
                dfs(x + 1, y, 1);
            }
            // 대각선으로 이동할 수 있는지 확인
            if (x + 1 < N && y + 1 < N && home[x][y + 1] == 0 && home[x + 1][y] == 0 && home[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 2);
            }
        }
    }
}