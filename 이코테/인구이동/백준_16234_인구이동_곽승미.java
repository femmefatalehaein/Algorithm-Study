package 인구이동;
/*
 * 백준 16234. 인구이동
 * BFS로 풀이
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16234_인구이동_곽승미 {

    static int N, L, R;
    static int[][] graph; // 나라의 인구 정보 저장
    static int[][] union; // 연합 번호 저장

    // 상, 좌, 하, 우
    static int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 땅의 크기(N)
        L = Integer.parseInt(st.nextToken()); // 최소 인구 차이(L)
        R = Integer.parseInt(st.nextToken()); // 최대 인구 차이(R)

        graph = new int[N][N];

        // 전체 나라의 인구 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int total_count = 0;

        // 더 이상 인구 이동이 없을 때까지 반복
        while (true) {
            union = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    union[i][j] = -1; // 초기에는 모든 나라가 연합에 속하지 않음
                }
            }

            int index = 0; // 연합 번호

            // 모든 나라에 대해 처리 시작
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (union[i][j] == -1) { // 아직 처리되지 않은 나라라면
                    	bfs(i, j, index);
                        index++;
                    }
                }
            }

            // 모든 인구 이동이 끝난 경우 반복문 종료
            if (index == N * N) {
                break;
            }

            total_count++;
        }

        System.out.println(total_count);
    }

    // 특정 위치에서 출발하여 모든 연합을 체크한 뒤에 데이터를 갱신
    static void bfs(int x, int y, int index) {
        Queue<int[]> q = new LinkedList<>();
        LinkedList<int[]> united = new LinkedList<>(); // 연합에 포함된 나라들의 좌표 리스트
        q.offer(new int[]{x, y});
        united.add(new int[]{x, y});
        union[x][y] = index; // 현재 연합의 번호 할당
        int summary = graph[x][y]; // 현재 연합의 전체 인구 수
        int count = 1; // 현재 연합의 국가 수

        // BFS 수행
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int curX = node[0];
            int curY = node[1];

            // 현재 위치에서 4가지 방향을 확인
            for (int[] delta : deltas) {
                int nx = curX + delta[0];
                int ny = curY + delta[1];

                // 옆에 있는 나라를 확인하여 조건에 맞으면 연합에 추가
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && union[nx][ny] == -1) {
                    int diff = Math.abs(graph[curX][curY] - graph[nx][ny]);
                    if (L <= diff && diff <= R) {
                        q.offer(new int[]{nx, ny});
                        united.add(new int[]{nx, ny});
                        union[nx][ny] = index;
                        summary += graph[nx][ny];
                        count++;
                    }
                }
            }
        }

        // 연합 국가끼리 인구를 분배
        int newpeole = summary / count;
        for (int[] country : united) {
            graph[country[0]][country[1]] = newpeole;
        }
    }
}