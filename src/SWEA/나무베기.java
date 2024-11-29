package swea.battleSSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node1 {
    int x, y, dir, cnt, treesCut;
    Node1(int x, int y, int dir, int cnt, int treesCut) {
        this.x = x;
        this.y = y;
        this.dir = dir; // 방향 (0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽)
        this.cnt = cnt; // 현재까지 조작 횟수
        this.treesCut = treesCut; // 벤 나무 수
    }
}

public class 나무베기 {
    static int N, K;
    static char[][] field;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상, 우, 하, 좌
    static boolean[][][][] visited; // [x][y][dir][treesCut] 방문 여부 체크
// 방향과 베어낸 나무 수를 동시에 고려한 상태를 추적하기 위한 배열
// 각 상태를 중복해서 방문하지 않도록 체크하기 위해서 4차원 배열 사용.

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/res/input_22683.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 맵 크기
            K = Integer.parseInt(st.nextToken());   // 나무를 벨 수 있는 최대 횟수
            field = new char[N][N];

            int startX = 0, startY = 0, targetX = 0, targetY = 0;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    field[i][j] = line.charAt(j);
                    if (field[i][j] == 'X') {
                        startX = i;
                        startY = j;
                    } else if (field[i][j] == 'Y') {
                        targetX = i;
                        targetY = j;
                    }
                }
            }

            // BFS로 최소 조작 횟수를 찾음
            int result = bfs(startX, startY, targetX, targetY);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(int startX, int startY, int targetX, int targetY) {
        Queue<Node1> queue = new LinkedList<>();
        visited = new boolean[N][N][4][K + 1]; // 방향별로 방문 체크

        // 시작점에서 상(0) 방향으로 시작
        queue.offer(new Node1(startX, startY, 0, 0, 0));
        visited[startX][startY][0][0] = true;

        while (!queue.isEmpty()) {
            Node1 cur = queue.poll();

            // 목적지 도달
            if (cur.x == targetX && cur.y == targetY) {
                return cur.cnt;
            }

            // 1. 전진
            int nx = cur.x + directions[cur.dir][0];
            int ny = cur.y + directions[cur.dir][1];

            if (isInBounds(nx, ny)) {
                if (field[nx][ny] == 'G' || field[nx][ny] == 'Y') {
                    // 이동할 수 있는 경우
                    if (!visited[nx][ny][cur.dir][cur.treesCut]) {
                        visited[nx][ny][cur.dir][cur.treesCut] = true;
                        queue.offer(new Node1(nx, ny, cur.dir, cur.cnt + 1, cur.treesCut));
                    }
                } else if (field[nx][ny] == 'T' && cur.treesCut < K) {
                    // 나무를 베고 이동할 수 있는 경우
                    if (!visited[nx][ny][cur.dir][cur.treesCut + 1]) {
                        visited[nx][ny][cur.dir][cur.treesCut + 1] = true;
                        queue.offer(new Node1(nx, ny, cur.dir, cur.cnt + 1, cur.treesCut + 1));
                    }
                }
            }

            // 2. 좌회전
            int leftDir = (cur.dir + 3) % 4;
            if (!visited[cur.x][cur.y][leftDir][cur.treesCut]) {
                visited[cur.x][cur.y][leftDir][cur.treesCut] = true;
                queue.offer(new Node1(cur.x, cur.y, leftDir, cur.cnt + 1, cur.treesCut));
            }

            // 3. 우회전
            int rightDir = (cur.dir + 1) % 4;
            if (!visited[cur.x][cur.y][rightDir][cur.treesCut]) {
                visited[cur.x][cur.y][rightDir][cur.treesCut] = true;
                queue.offer(new Node1(cur.x, cur.y, rightDir, cur.cnt + 1, cur.treesCut));
            }
        }

        // 도달할 수 없는 경우
        return -1;
    }

    public static boolean isInBounds(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

