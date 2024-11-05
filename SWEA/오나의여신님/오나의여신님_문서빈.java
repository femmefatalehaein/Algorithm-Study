	/*
	 * S : 수연이 위치
	 * D : 여신의 공간
	 * X : 돌의 위치
	 * '*' : 악마
	 * 
	 * 악마의 손아귀 : 악마 위치 기준으로 1초마다 상하좌우로 퍼져나감
	 * 수연 : 1초마다 상하좌우로 이동 가능
	 * 악마의 손아귀를 피해 최소 시간에 여신에게 가야함
	 */


import java.io.*;
import java.util.*;

public class 오나의여신님_문서빈 {
    static int N, M;
    static char[][] map;
    static int[][] visit; // 수연이 지나간 위치를 표시
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1}; // 상하좌우

    static boolean range(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> devil = new LinkedList<>();

        // 초기 위치 설정
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    q.add(new int[]{i, j, 0});
                    visit[i][j] = 1;
                } else if (map[i][j] == '*') {
                    devil.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int devilSize = devil.size();
            for (int i = 0; i < devilSize; i++) {
                int[] nowDevil = devil.poll();
                int nowX = nowDevil[0];
                int nowY = nowDevil[1];
                
                for (int d = 0; d < 4; d++) {
                    int X = nowX + dx[d];
                    int Y = nowY + dy[d];
                    
                    if (range(X, Y) && map[X][Y] == '.') {
                        map[X][Y] = '*';
                        devil.add(new int[]{X, Y});
                    }
                }
            }

            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int[] now = q.poll();
                int nowX = now[0];
                int nowY = now[1];
                int time = now[2];
                
                for (int d = 0; d < 4; d++) {
                    int X = nowX + dx[d];
                    int Y = nowY + dy[d];
                    
                    if (range(X, Y) && visit[X][Y] == 0 && (map[X][Y] == '.' || map[X][Y] == 'D')) {
                        if (map[X][Y] == 'D') {
                            System.out.println(time + 1);
                            return;
                        }
                        
                        visit[X][Y] = 1;
                        q.add(new int[]{X, Y, time + 1});
                    }
                }
            }
        }
        System.out.println("GAME OVER");
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            visit = new int[N][M];
            
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            
            System.out.print("#" + tc + " ");
            bfs();
        }
    }
}
