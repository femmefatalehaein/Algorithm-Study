import java.io.*;
import java.util.*;

public class Main_2178 {
	static int N, M;
	static int[][] miro;
	static boolean[][] visited; // 방문 처리
	// static Queue<int[]> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 }; // 상하좌우 탐색

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		miro = new int[N + 1][M + 1]; // 시작점이 1,1이기 때문에 +1
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken(); 
			for (int j = 1; j <= M; j++) {
				miro[i][j] = Integer.parseInt(line.substring(j-1, j)); // j가 증가하면서 data를 앞에서 한칸씩 substring으로 끊고, int형으로 대입해줌
				 // miro[i][j] = line.charAt(j-1) - '0'; // j=1일 때, line.charAt(j-1) -> line.charAt(0) : 첫번째 문자를 가져옴
				 // j-1 -> 1부터 시작하는 인덱스를 0부터 시작하는 문자열 인덱스와 일치시키기 위해.
			}
		}
		// 입력끝

		BFS(1, 1); // 시작점이 1,1부터 시작
		System.out.println(miro[N][M]); // miro의 원소값에 현재 깊이?가 들어가 있으니 최단 경로 == miro의 끝점인 miro[N][M]의 원소값
	}

	/*
	 * BFS 동작 원리
	 * 1. 방문할 노드를 방문 처리
	 * 2. 1의 좌표를 큐에 넣기
	 * 3. 현재 큐의 front를 pop (poll 사용)
	 * 4. pop한 좌표의 상하좌우 (범위 내)를 살피며 방문하지 않은 좌표를 큐에 넣기(offer 이용)
	 */
	
	private static void BFS(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		visited[i][j] = true; // 방문 처리
		q.offer(new int[] { i, j }); // 큐에 시작 좌표 넣기 // offer은 큐가 꽉 찰 경우 false반환 (add는 에러 발생)
		// new int[] {i, j}는 x, y 좌표를 한꺼번에 배열로 저장하는 방식
		// (i, j)는 각각 미로의 x와 y 좌표를 의미
		
		while (!q.isEmpty()) { // 큐가 빌 때까지 현재 큐의 front를 pop
			int[] now = q.poll(); // poll은 큐의 front를 pop(삭제 및 반환)
			
			for (int k = 0; k < 4; k++) { // 해당 좌표의 상하좌우 탐색
				int nx = now[0] + dx[k]; // now[0]은 x 좌표
				int ny = now[1] + dy[k]; // now[1]은 y 좌표
				if (nx >= 1 && ny >= 1 && nx <= N && ny <= M ) { // 범위 체크(1~N, 1~M)
					if (miro[nx][ny] != 0 && !visited[nx][ny]) { // (miro의 값이 1일때만 갈 수 있기에) miro 원소값이 0이 아니면서 방문하지 않은 곳이라면
						visited[nx][ny] = true; // 해당 좌표를 방문 처리 후
						miro[nx][ny] = miro[now[0]][now[1]] + 1; // miro 배열에 depth를 현재 노드의 depth+1로 업데이트
						// 현재 좌표에서 한 칸 이동한 것이므로 miro 배열에 '해당좌표 값 + 1'을 저장하여 이동한 거리를 기록!
						q.offer(new int[] { nx, ny }); // 새로운 좌표를 큐에 넣어 다음 탐색 대상으로 추가
					}
				}
			}
		}

	}

}
