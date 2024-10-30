package 벽돌깨기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽돌깨기3_장희주 {

	static class Node {
		int x, y, value;

		public Node(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N, W, H;
	static int[][] walls, copyWalls;
	static int result;
	static int[] array;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			walls = new int[H][W];
			copyWalls = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					walls[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			array = new int[N];
			// 입력끝
			result = Integer.MAX_VALUE;

			bruteforce(0);
			// 결과 출력
			System.out.println("#" + test_case + " " + result);
		}

	}

	// 전부의 경우의 수
	static void bruteforce(int cnt) {
		if (cnt == N) {
			copy(copyWalls, walls);
			start(array);
			minCnt();
			return;
		}
		for (int i = 0; i < W; i++) {
			array[cnt] = i;
			bruteforce(cnt + 1);

		}
	}

	// 탐색
	static void start(int[] array) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < H; j++) {
				if (copyWalls[j][array[i]] != 0) {
					bfs(j, array[i], copyWalls[j][array[i]]);
					move();
					break;
				}
			}
		}
	}

	// 확산해주는 함수
	static void bfs(int x, int y, int value) {

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y, value));
		boolean[][] visited = new boolean[H][W];
		while (!q.isEmpty()) {
			Node node = q.poll();
			x = node.x;
			y = node.y;
			value = node.value;
			copyWalls[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < value; j++) {
					int nx = x + dx[i] * j;
					int ny = y + dy[i] * j;
					if (nx >= H || ny >= W || nx < 0 || ny < 0 || copyWalls[nx][ny] == 0 ||visited[nx][ny]) {
						continue;
					}
					visited[nx][ny]=true;
					q.add(new Node(nx, ny, copyWalls[nx][ny]));

				}
			}
		}

	}

	static void move() {
		for (int i = 0; i < W; i++) {
			int idx = H - 1;
			for (int j = H - 1; j >= 0; j--) {
				if (copyWalls[j][i] != 0) {
					if (idx != j) {
						copyWalls[idx][i] = copyWalls[j][i];
						copyWalls[j][i] = 0;
					}
					idx--;
				}
			}
		}
	}

	// 최소값 갱신
	static void minCnt() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copyWalls[i][j] != 0) {
					cnt++;
				}
			}
		}
		result = Math.min(cnt, result);

	}

	static void copy(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				arr1[i][j] = arr2[i][j];
			}
		}
	}

	static void print(int[][] array) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
}
