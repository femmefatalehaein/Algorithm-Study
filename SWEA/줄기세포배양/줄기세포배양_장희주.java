package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//줄기세포배양
public class 줄기세포배양_장희주 {

static class Node {
    // 위치
    int x, y;
    // 생명력
    int life;
    // 시간흐름
    int time;
    // 상태정보
    int status;

    public Node(int x, int y, int life) {

        this.x = x;
        this.y = y;
        this.life = life;
        this.time = life;
    }

    // 시간마다 상태정보 변경하는 함수
    void step() {
        switch (status) {
        case 0:
            // 생명력이 0이면 활성화
            if (--time == 0) {
                status = 1;
            }
            break;
        case 1:
            // 생명력과 같아지면 죽은세포
            if (++time == life) {
                status = 2;
            }
            break;
        }
    }

}

static int N, M, K;
static boolean[][] visited;
static Queue<Node> q;
static PriorityQueue<Node> pq;
static int[] dx = { -1, 1, 0, 0 };
static int[] dy = { 0, 0, -1, 1 };

public static void main(String[] args) throws NumberFormatException, IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int test_case = 1; test_case <= T; test_case++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 세로크기
        N = Integer.parseInt(st.nextToken());
        // 가로크기
        M = Integer.parseInt(st.nextToken());
        // 배양시간
        K = Integer.parseInt(st.nextToken());
        // 번식여부
        visited = new boolean[N + K + 1][M + K + 1];
        // 생명력 높은 기준으로 저장
        pq = new PriorityQueue<>(new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                // TODO Auto-generated method stub
                return o2.life - o1.life;
            }
        });
        // 관리할 세포 저장
        q = new LinkedList<>();
        // 가운데로 배치
        for (int i = K / 2 + 1; i < N + K / 2 + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = K / 2 + 1; j < M + K / 2 + 1; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp != 0) {
                    visited[i][j]=true;
                    q.add(new Node(i, j, temp));
                }
            }
        }
        bfs();
        System.out.println("#" + test_case + " " + q.size());
    }

}

// bfs탐색
private static void bfs() {

    while (K-- > 0) {
        // 이번시간에 확인할 세포수
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Node c = q.poll();
            // 활성화일때
            if (c.status == 1) {
                pq.add(c);
            }
            // 세포상태변화
            c.step();
            // 죽은세포일때
            if (c.status == 2) {
                continue;
            }
            // 활성화, 비활성세포 저장->다음시간을 위해
            q.add(c);
        }
        // 우선순위가 비어질때까지
        while (!pq.isEmpty()) {
            Node c = pq.poll();
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                // 전에 배양된 자리 or 더 생명력이 높은 세포
                if (visited[nx][ny])
                    continue;
                visited[nx][ny] = true;
                // 다음시간에 배양할수있도록 저장
                q.add(new Node(nx, ny, c.life));
            }
        }
    }
}
}