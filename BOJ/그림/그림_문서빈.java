import java.util.*;
import java.io.*;

public class Main_1926 {
	static int n, m; // 도화지의 세로, 가로(행, 열) 크기
	static int[][] pictureArr = new int[n + 2][m + 2];  // 그림 배열
	static int[][] visited = new int[n + 2][m + 2];  // 처리 여부 저장 배열
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				pictureArr[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		Queue<Pair> queue = new LinkedList<>(); // 큐 생성
		int count = 0; // 그림의 개수
		int maxSize = 0; // 그림의 최대 크기

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				if (pictureArr[i][j] == 1 && visited[i][j] == 0) { // 시작 좌표가 그림이고, 아직 방문하지 않았으면
					int size = 0; // 그림의 크기

					queue.add(new Pair(i, j)); // 큐에 삽입
					visited[i][j] = 1; // 방문 완료 갱신

					while (!queue.isEmpty()) { // 큐가 비어있지 않으면 (큐가 빌 때까지 반복)
						Pair pair = queue.remove(); // 큐에서 front 좌표를 꺼내고
						size++; // 해당 그림의 크기 갱신

						for (int d = 0; d < 4; d++) { // 해당 좌표의 주변 좌표를 우상좌하 순으로 탐색
							int tmpX = pair.X + dx[d]; // 주변 좌표 : (tmpX, tmpY)
							int tmpY = pair.Y + dy[d];

							if (pictureArr[tmpX][tmpY] == 1 && visited[tmpX][tmpY] == 0) { // 그림이고, 아직 방문하지 않았으면
								queue.add(new Pair(tmpX, tmpY)); // 큐에 해당 좌표 삽입
								visited[tmpX][tmpY] = 1; // 방문 완료 갱신
							}
						}
					}
					count++; // 그림의 개수 갱신
					if (size > maxSize)
						maxSize = size; // 그림의 최대 크기 갱신
				}
			}
		}

		System.out.println(count + "\n" + maxSize);

	}

}

class Pair { // 좌표 값을 저장할 자료구조 Pair
	int X;
	int Y;

	Pair(int x, int y) {
		this.X = x;
		this.Y = y;
	}

}
