
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_장희주 {

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int n;
	static int[][] process;
	static int minTotal, maxTotal;
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { -0, 0, -1, 1 };

	static List<Node> core;

	public static void main(String[] args) throws NumberFormatException, IOException {

		int T;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 셀의 크기
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			// 빈셀 : 0 core : 1
			// 프로세서정보 배열에 저장
			process = new int[n][n];
			// 코어 갯수
			core = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					process[i][j] = Integer.parseInt(st.nextToken());
					if (process[i][j] == 1) {
						core.add(new Node(i, j));
					}
				}
			}
			// 입력끝
			// 결과값
			minTotal = Integer.MAX_VALUE;
			//연결할수있는 셀의 개수
			maxTotal = Integer.MIN_VALUE;
			
			process(0, 0, 0);

			System.out.println("#"+test_case+" "+minTotal);

		}
	}

	static void process(int cnt, int minlength, int connectCnt) {
		
		
		if (cnt == core.size()) {
			// 최대 core 수 갱신
			if (maxTotal < connectCnt) {
				maxTotal = connectCnt;
				minTotal = minlength;
			}
			// 연결된 개수가 같을경우
			else if (maxTotal == connectCnt) {
				// 전선 길이의 합 최소 갱신
				if (minTotal > minlength) {
					minTotal = minlength;
				}
			}
			return;
		}
		// 현재 프로세서를 고르지않았을때
		process(cnt + 1, minlength, connectCnt);

		// 현재 코어 위치
		int curX = core.get(cnt).x;
		int curY = core.get(cnt).y;
		//상하좌우 방향 돌기
		for (int i = 0; i < 4; i++) {
			//상하좌우로 움직이는 좌표
			int nx = curX;
			int ny = curY;
			//전선갯수
			int elecLength=0;
			// 한칸씩 지금 방향으로 전진 and 전선을 깔수있는지 확인하기위해 전진
			while (true) {
				nx += dx[i];
				ny += dy[i];
				// 범위가 벗어나지않았을때
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					// core를 만났을때 and 전선을 만났을때
					if (process[nx][ny] == 1) {
						break;
					}

				} else { // 범위가 벗어날 때
					//전선 움직이는 초기화
					nx = curX;
					ny = curY;
					elecLength=0;
					// 전선을 깔아주기 위한 전진
					while (true) {
						nx += dx[i];
						ny += dy[i];
						// 벗어나지않았다면
						if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
							// 전선 깔아주기
							process[nx][ny] = 1;
							// 전선길이 카운트증가
							elecLength++;
							// 벗어났다면
						} else {
							break;
						}
					}
					// 다음 프로세서 보러가기 위한 체크
					process(cnt + 1, minlength+elecLength, connectCnt + 1);

					// 백트래킹(전선 다 빼줌)
					for (int j = elecLength; j > 0; j--) {
						//다시 원상태로 되돌아감
						nx -= dx[i];
						ny -= dy[i];
						
						process[nx][ny] = 0;
						elecLength--;
					}
					break;
				}
			}
		}

	}

}
