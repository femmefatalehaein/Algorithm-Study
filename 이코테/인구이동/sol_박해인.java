package 인구이동;

import java.util.*;

public class sol_박해인{

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};

// 특정 위치에서 출발하여 모든 연합을 체크한 뒤에 데이터 갱신
public static void process(int x, int y, int index, int[][] union, int[][] graph, int n, int l, int r) {
    // (x, y)의 위치와 연결된 나라(연합) 정보를 담는 리스트
    ArrayList<int[]> united = new ArrayList<>();
    united.add(new int[]{x, y});
    // 너비 우선 탐색 (BFS)을 위한 큐 라이브러리 사용
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{x, y});
    union[x][y] = index; // 현재 연합의 번호 할당
    int summary = graph[x][y]; // 현재 연합의 전체 인구 수
    int count = 1; // 현재 연합의 국가 수

    // 큐가 빌 때까지 반복(BFS)
    while (!q.isEmpty()) {
        int[] pos = q.poll();
        int curX = pos[0];
        int curY = pos[1];
        // 현재 위치에서 4가지 방향을 확인하며
        for (int i = 0; i < 4; i++) {
            int nx = curX + dx[i];
            int ny = curY + dy[i];
            // 바로 옆에 있는 나라를 확인하여
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && union[nx][ny] == -1) {
                // 옆에 있는 나라와 인구 차이가 L명 이상, R명 이하라면
                if (l <= Math.abs(graph[nx][ny] - graph[curX][curY]) && Math.abs(graph[nx][ny] - graph[curX][curY]) <= r) {
                    q.offer(new int[]{nx, ny});
                    // 연합에 추가하기
                    union[nx][ny] = index;
                    summary += graph[nx][ny];
                    count++;
                    united.add(new int[]{nx, ny});
                }
            }
        }
    }

    // 연합 국가끼리 인구를 분배
    for (int[] pos : united) {
        int i = pos[0];
        int j = pos[1];
        graph[i][j] = summary / count;
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

  
    int n = sc.nextInt();
    int l = sc.nextInt();
    int r = sc.nextInt();

  
    int[][] graph = new int[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            graph[i][j] = sc.nextInt();
        }
    }

    int total_count = 0;

  
    while (true) {
        int[][] union = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(union[i], -1);
        }
        int index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (union[i][j] == -1) { 
                    process(i, j, index, union, graph, n, l, r);
                    index++;
                }
            }
        }

        // 모든 인구 이동이 끝난 경우
        if (index == n * n) {
            break;
        }
        total_count++;
    }

    // 인구 이동 횟수 출력
    System.out.println(total_count);
}
}