import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇청소기_곽승미 {

    // 북, 동, 남, 서 (시계 방향 순서)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()); // 행
        int c = Integer.parseInt(st.nextToken()); // 열
        int d = Integer.parseInt(st.nextToken()); // 방향

        // 맵 입력
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방문 여부 확인 배열
        int[][] visited = new int[N][M];

        visited[r][c] = 1; // 시작 위치
        int cnt = 1; // 청소 카운트

        while (true) {
            int flag = 0;  // 청소 여부 체크

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;  // 왼쪽으로 회전(북,서,남,동)
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위 내에 있고, 청소 가능한 경우
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && arr[nr][nc] == 0 && visited[nr][nc] == 0) {
                    visited[nr][nc] = 1;  // 방문 처리
                    cnt++;  // 청소 카운트 증가
                    r = nr;  // 위치 업데이트
                    c = nc;
                    flag = 1;  // 청소 완료
                    break;
                }
            }

            // 네 방향 모두 청소할 곳이 없을 때
            if (flag == 0) {
                // 후진 좌표 계산
                int bR = r - dr[d];
                int bC = c - dc[d];

                // 후진할 곳이 벽일 경우 종료
                if (arr[bR][bC] == 1) {
                    System.out.println(cnt);  // 청소한 칸 수 출력
                    break;
                } else {
                    // 후진할 수 있으면 후진
                    r = bR;
                    c = bC;
                }
            }
        }
    }
}