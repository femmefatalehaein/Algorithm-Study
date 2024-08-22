package algo_Java;

import java.util.*;
import java.io.*;

//1. 집과 치킨집의 좌표를 각각 list에 저장해 둔다.
//2. 치킨집이 open한 개수가 M과 같다면, 모든 집에 대하여 M개의 치킨집 중 최단거리를 계산한다.
//3. 탐색을 시작하는 지점이 치킨집 list의 사이즈가 벗어나게 되면 탐색을 종료한다.

class Point {
    int x;
    int y;
 
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class BOJ_15686 {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> person;
    static ArrayList<Point> chicken;
    static int ans;
    static boolean[] open;
 
    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("src/input/BOJ_15686.txt"));
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();
 
        // 미리 집과 치킨집에 해당하는 좌표를 ArrayList에 넣어 둠.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
 
                if (map[i][j] == 1) {
                    person.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
 
        ans = Integer.MAX_VALUE;
        open = new boolean[chicken.size()];
 
        DFS(0, 0);
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
    public static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;
 
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;
 
                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                // 그 중, 최소 거리를 구한다.
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);
 
                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }
 
        // 백트래킹
        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            DFS(i + 1, cnt + 1);
            open[i] = false;
        }
    }
 
}

//
//public class BOJ_15686 {
//
//	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("src/input/BOJ_15686.txt"));
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		int[][] arr = new int[N+1][N+1]; // 입력 배열
//		int[] dist2to1 = new int[13];
//
//		for (int i = 1; i <= N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for (int j = 1; j <= N; j++) {
//				arr[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		
//		List<int[]> coordinates = new ArrayList<>(); // 좌표를 저장할 리스트
//		
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				// 2에서 1 사이의 거리 구하고, 이를 dist2to1[]에 저장
//				if (arr[i][j] == 2) { // 좌표i, j를 구해야 함 
//					coordinates.add(new int[] {i, j});
//					for (int[] coord : coordinates) {
//						System.out.println("(" + coord[0] +", " + coord[1] + ")");
//					}
//				}
//				
////				if (arr[i][j] == 1) { // 좌표i, j를 구해야 함 
////					coordinates.add(new int[] {i, j});
////					
////				}
//				//dist2to1[] =  
//			}
//		}
//	
//	}
//
//}
