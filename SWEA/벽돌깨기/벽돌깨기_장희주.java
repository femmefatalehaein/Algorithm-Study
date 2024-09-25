package 벽돌깨기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽돌깨기_장희주 {

	static class Node {
		int x, y, value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

	}

	static int N, W, H;
	static int[][] walls;
	static int brickMin;
	// 좌우 상하
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] numbers;
	static int[][] originWalls;
	static int minCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			// 구슬 쏘는 횟수
			N = Integer.parseInt(st.nextToken());
			// 배열의 크기
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			// 벽돌의 정보
			walls = new int[H][W];
			originWalls = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					walls[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			copyArray(walls, originWalls);
			brickMin = Integer.MAX_VALUE;
			numbers = new int[N];
			minCnt=Integer.MAX_VALUE;
			// W만큼 첫번째 구슬 쏘기 -완전 탐색
			permutation(0);
			System.out.printf("#%d %d\n", test_case, minCnt);
		}
	}

	// 완전 탐색
	static void permutation(int cnt) {
		if (cnt == N) {
			copyArray(originWalls, walls);
			start(numbers);
			brickMinCnt();
			return;
		}
		for (int i = 0; i < W; i++) {
			numbers[cnt] = i;
			permutation(cnt + 1);
		}

	}
	
	static void print(int arr[][]) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		System.out.println();
	}

	// 탐색하기
	static void start(int[] arr) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < H; j++) {
				if (walls[j][arr[i]] != 0) {
					remove(j, arr[i], walls[j][arr[i]]);
					pushdown();
					break;
				}
			}
		}
	}

	// 쏘면 연쇄적으로 제거되는 함수
	static void remove(int x, int y, int value) {
		boolean[][] visited = new boolean[H][W];
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y, value));
		walls[x][y] = 0;
		while (!q.isEmpty()) {
			
			Node node = q.poll();
			int cx = node.x;
			int cy = node.y;
			int cv = node.value;
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < cv; j++) {
					int nx = cx + dx[i] * j;
					int ny = cy + dy[i] * j;
					if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
						break;
					}
					if (!visited[nx][ny]) {
						q.add(new Node(nx, ny, walls[nx][ny]));
						walls[nx][ny] = 0;
						visited[nx][ny] = true;
					}
				}
			}
		}

	}

	// 빈공간 있을경우 밑으로 밀어주는 함수
	static void pushdown() {
		for (int i = 0; i < W; i++) {
			int insert = H - 1;
			for (int j = H - 1; j >= 0; j--) {
				if (walls[j][i] > 0) {
					walls[insert][i] = walls[j][i];
					if(insert != j) {
						walls[j][i] = 0;
					}
					insert--;
				}

			}
		}
	}

	// 남은 벽돌개수 구해주는 함수
	// 최소값 갱신
	static void brickMinCnt() {
		int cnt=0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (walls[i][j] != 0) {
					cnt++;
				}
			}
		}
		minCnt=Math.min(cnt, minCnt);

	}

	static void copyArray(int[][] from, int[][] to) {

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				to[i][j] = from[i][j];
			}
		}
	}
}
