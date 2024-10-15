package 단지번호붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class 단지번호붙이기_장희주 {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        array = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                array[i][j] = str.charAt(j) - '0';
            }
        }
     // 각 단지의 집 수를 저장할 리스트
        List<Integer> homeCounts = new ArrayList<>();  

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (array[i][j] == 1 && !visited[i][j]) {
                    // 단지를 찾으면 해당 단지의 집의 수를 리스트에 추가
                    int hCnt = bfs(i, j);
                    homeCounts.add(hCnt);
                }
            }
        }

        // 오름차순 정렬
        Collections.sort(homeCounts);

        // 총 단지 수 출력
        System.out.println(homeCounts.size());

        // 각 단지 내 집의 수를 출력
        for (int count : homeCounts) {
            System.out.println(count);
        }
    }
    //bfs수행
    static int bfs(int x, int y) {
        int homeCnt = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        //큐가비어질때까지
        while (!q.isEmpty()) {
            Node current = q.poll();
         // 집의 수
            homeCnt++;  

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                //범위에 벗어나거나 방문했을때
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || array[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny));
            }
        }
        return homeCnt;
    }
}
