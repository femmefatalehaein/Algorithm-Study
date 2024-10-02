package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나이트의이동 {
    //상하좌우 대각선
    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    // 체스판 크기
    static int l;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); 

      
        for (int i = 0; i < t; i++) {
            // 체스판의 한 변의 길이 l 
            l = Integer.parseInt(br.readLine());

           
            visited = new boolean[l][l];

            // 현재 나이트의 위치 
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            // 이동하려는 목표 위치 
            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            // 결과 출력
            System.out.println(bfs(startX, startY, endX, endY));
        }
    }

    // BFS를 이용
    static int bfs(int startX, int startY, int endX, int endY) {
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startX, startY, 0}); 
      
        visited[startX][startY] = true;

      
        while (!queue.isEmpty()) {
           
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int count = cur[2];  

            
            if (x == endX && y == endY) {
                return count;
            }

           
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];  
                int ny = y + dy[i]; 

               
                if (nx >= 0 && nx < l && ny >= 0 && ny < l && !visited[nx][ny]) {
                    visited[nx][ny] = true;  // 방문 체크
                    queue.offer(new int[] {nx, ny, count + 1});  
                }
            }
        }

        return -1;  // 도달할 수 없으면 -1 
    }
}
