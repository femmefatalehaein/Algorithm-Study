import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 물 옮기는 방법
 * (A -> B) ( A -> C) ( B -> A) (B -> C) (C -> A) (C -> B)
 */

public class 백준_2251_물통_곽승미 {
    static int A, B, C; // 물통 A, B, C

    // 방문 여부를 저장하는 3차원 배열 (A, B, C 상태)
    static boolean[][][] visited;

    // 가능한 C 물통의 값들을 저장할 배열
    static boolean[] answer;
    
    // 물을 옮기는 6가지 이동 방법
    static int[] from = {0, 0, 1, 1, 2, 2};
    static int[] to = {1, 2, 0, 2, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[A + 1][B + 1][C + 1];
        answer = new boolean[C + 1];

        bfs();

        // 결과 출력
        for (int i = 0; i <= C; i++) {
            if (answer[i]) {
                System.out.print(i + " ");
            }
        }
    }

    // bfs 탐색
    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        // 시작 상태는 (0, 0, C) - A와 B는 비어 있고, C는 가득 찬 상태
        queue.offer(new int[]{0, 0, C});
        visited[0][0][C] = true; // 시작 방문 처리

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();  // 현재 상태 가져오기
            int a = cur[0], b = cur[1], c = cur[2];

            // A가 비어있을 때, C의 양을 기록 (정답 후보)
            if (a == 0) answer[c] = true;

            // 6가지 물 옮기는 경우를 반복
            for (int i = 0; i < 6; i++) {
                // 현재 상태에서 물을 옮긴 후의 상태를 복사
                int[] next = {a, b, c};

                // from에서 to로 물을 모두 옮김
                next[to[i]] += next[from[i]];
                next[from[i]] = 0;

                // 옮겼을 때 to 물통이 넘치면 넘친만큼 다시 from으로 돌려줌
                if (next[to[i]] > new int[]{A, B, C}[to[i]]) {
                    next[from[i]] = next[to[i]] - new int[]{A, B, C}[to[i]];
                    next[to[i]] = new int[]{A, B, C}[to[i]];
                }

                // 아직 방문하지 않은 상태라면 큐에 넣고 방문 처리
                if (!visited[next[0]][next[1]][next[2]]) {
                    visited[next[0]][next[1]][next[2]] = true;
                    queue.offer(next);
                }
            }
        }
    }
}