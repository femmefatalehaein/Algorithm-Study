package 연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연구소_장희주 {

    static int n, m; // 배열의 크기
    static int[][] array; // 원본 맵
    static int[][] temp; // 작업 중 사용할 맵
    static int result = 0; // 최대 안전 영역 크기

    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        temp = new int[n][m];

        // 배열 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 울타리 설치와 최대 안전 영역 크기 계산
        dfs(0);

        // 결과 출력
        System.out.println(result);
    }

    // 바이러스가 사방으로 퍼지는 경우
    static void virus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (temp[nx][ny] == 0) {
                    temp[nx][ny] = 2;
                    virus(nx, ny);
                }
            }
        }
    }

    // 현재 맵에서 안전영역의 크기 계산
    static int getScore() {
        int score = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    score++;
                }
            }
        }
        return score;
    }

    // 울타리를 설치하면서 매번 안전영역의 크기 계산
    static void dfs(int count) {
        if (count == 3) {
            // 현재 배열을 temp에 복사
            for (int i = 0; i < n; i++) {
                System.arraycopy(array[i], 0, temp[i], 0, m);
            }

            // 바이러스 전파
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }

            // 안정영역 최대값 계산
            result = Math.max(result, getScore());
            return;
        }

        // 빈공간에 울타리 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (array[i][j] == 0) {
                    array[i][j] = 1;
                    dfs(count + 1);
                    array[i][j] = 0;
                }
            }
        }
    }
}
