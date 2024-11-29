
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_12869_뮤탈리스크_곽승미 {
    public static void main(String[] args) throws IOException {
        int[] scv = new int[3]; // SCV 체력 배열
        int N; // SCV 수

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        // 최소 공격 횟수 구하기
        int res = bfs(scv, N);
        
        System.out.println(res);
    }

    private static int bfs(int[] scv, int N) {
        // SCV 체력 체크 배열
    	boolean[][][] visited = new boolean[61][61][61];
        // 6가지 공격 방법
        int[][] deal = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{scv[0], scv[1], scv[2], 0});  // SCV1 체력, SCV2 체력, SCV3 체력, 공격 횟수

        visited[scv[0]][scv[1]][scv[2]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int s1 = current[0]; 
            int s2 = current[1]; 
            int s3 = current[2];
            int cnt = current[3];

            // 만약 모든 SCV의 체력이 0 이하라면 현재까지의 공격 횟수를 반환
            if (s1 == 0 && s2 == 0 && s3 == 0) {
                return cnt;
            }

            // 6가지 공격 패턴에 대해 새로운 체력 상태를 계산
            for (int i = 0; i < 6; i++) {
                int ns1 = Math.max(s1 - deal[i][0], 0);  // 체력은 0 이하로 감소하지 않음
                int ns2 = Math.max(s2 - deal[i][1], 0);
                int ns3 = Math.max(s3 - deal[i][2], 0);

                // 아직 방문하지 않은 상태라면 방문 처리 후 큐에 추가
                if (!visited[ns1][ns2][ns3]) {
                    visited[ns1][ns2][ns3] = true;
                    queue.offer(new int[]{ns1, ns2, ns3, cnt + 1});
                }
            }
        }

        return -1;
    }
}