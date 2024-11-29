import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기_곽승미 {
    static int N, W, H, min;  
    static int[][] map, copy;
    static int[] num; 
    static int[] dx = {-1, 1, 0, 0};  // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for (int tc = 1; tc <= T; tc++) {
            // 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 구슬 개수
            W = Integer.parseInt(st.nextToken());  // 열 
            H = Integer.parseInt(st.nextToken());  // 행

            map = new int[H][W];
            copy = new int[H][W];
            num = new int[N];  // 구슬을 떨어뜨릴 열 선택 저장 배열
            min = Integer.MAX_VALUE; 

            // 맵 입력
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copy[i][j] = map[i][j];
                }
            }

            perm(0); 
            System.out.println("#" + tc + " " + min);
        }
    }

    // 구슬을 떨어뜨릴 열을 중복 순열로 생성하는 메서드
    static void perm(int cnt) {
        if (cnt == N) {
            start(num);  // 구슬 떨어뜨리기 시작
            min = Math.min(min, countMap());  // 최소값 갱신
            mapCopy();  // 맵을 초기 상태로 복구
            return;
        }

        // 구슬을 떨어뜨릴 열을 선택
        for (int i = 0; i < W; i++) {
            num[cnt] = i;
            perm(cnt + 1);  // 재귀적으로 중복 순열 생성
        }
    }

    // 구슬을 떨어뜨려 벽돌을 처리하는 메서드
    static void start(int[] number) {
        for (int i = 0; i < N; i++) {
            int x = 0, y = number[i];

            // 해당 열에서 벽돌을 찾음
            for (int j = 0; j < H; j++) {
                if (map[j][y] != 0) {
                    x = j;
                    break;
                }
            }

            // 벽돌이 있으면 BFS로 처리
            if (map[x][y] != 0) {
                bfs(x, y);
                down();  // 벽돌 아래로
            }
        }
    }

    // BFS로 벽돌을 깨뜨리는 메서드
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, map[x][y]});  // 초기 벽돌 추가
        map[x][y] = 0;  // 벽돌 제거

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int power = cur[2];  // 벽돌의 파괴 범위

            // 벽돌의 파워만큼 상하좌우로 퍼짐
            for (int i = 1; i < power; i++) {
                for (int j = 0; j < 4; j++) {
                    int nx = curX + dx[j] * i;
                    int ny = curY + dy[j] * i;

                    // 맵을 벗어나거나 벽돌이 없으면 무시
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 0) {
                        continue;
                    }

                    // 벽돌이 있으면 큐에 넣고 제거
                    if (map[nx][ny] != 0) {
                        q.add(new int[]{nx, ny, map[nx][ny]});
                        map[nx][ny] = 0;  // 벽돌 제거
                    }
                }
            }
        }
    }

    // 벽돌을 아래로 떨어뜨리는 메서드
    static void down() {
        for (int i = 0; i < W; i++) {
            Stack<Integer> s = new Stack<>();
            for (int j = 0; j < H; j++) {
                if (map[j][i] != 0) {
                    s.push(map[j][i]);  // 벽돌을 스택에 저장
                }
            }

            // 벽돌 아래로 내리기
            for (int j = H - 1; j >= 0; j--) {
                if (!s.isEmpty()) {
                    map[j][i] = s.pop();
                } else {
                    map[j][i] = 0;  // 스택이 비었으면 0
                }
            }
        }
    }

    // 벽돌 세는 메서드
    static int countMap() {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 맵 상태 복구 메서드
    static void mapCopy() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = copy[i][j];  // 초기 상태로 복구
            }
        }
    }
}