
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baechu {
	
	static boolean ifbc(int x, int y, int row, int col) {
		if (((0 <= x) && (x < row)) && ((0 <= y) && (y < col))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		
		for (int t = 0 ; t < tc ; t++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			Queue<int[]> zirung = new LinkedList<>();
			
			// 가로, 세로 순으로 주어지기 때문에 가로, 세로 순으로 변수 설정 후 초기화
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			
			// 배추밭 배열, 방문했는지 체크할 배열, 사방탐색할 배열 설정
			int [][] baechu = new int [row][col];
			int [][] visit = new int [row][col];
			int [][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			
			// 배추 있는 좌표 갯수
			int n = Integer.parseInt(st.nextToken());
			
			// 배추 좌표 1로 설정
			for (int i = 0 ; i < n ; i++) {
				StringTokenizer st1 = new StringTokenizer(bf.readLine());
				int c = Integer.parseInt(st1.nextToken());
				int r = Integer.parseInt(st1.nextToken());
				baechu[r][c] = 1;
			}
			
			int sum = 0;
			
			// 배열 돌면서 배추 있는곳 사방탐색
			for (int i = 0 ; i < row ; i ++) {
				for (int j = 0 ; j < col ; j++) {
					if ((baechu[i][j] != 0) && (visit[i][j] != 1)) {
						// visit 처리 큐에서 해줬더니 메모리 초과떠서 배열에 넣기 전에 visit했더니 초과 안남,,
						visit[i][j] = 1;
						zirung.add(new int[] {i, j});
						
						// 큐 돌리기
						while(!zirung.isEmpty()) {
							int [] rung = zirung.poll();
							// 사방탐색
							for (int [] d : dir) {
								int dx = rung[0] + d[0];
								int dy = rung[1] + d[1];
								// 만약 배추밭에서 벗어나지 않는다면
								if (ifbc(dx, dy, row, col)) {
									// 그리고 배추가 심어져 있고 방문한 곳이 아니라면 큐에 넣기
									if ((baechu[dx][dy] != 0) && (visit[dx][dy] != 1)) {
										visit[dx][dy] = 1;
										zirung.add(new int[] {dx, dy});
									}
								}
							}
						}
						// 지렁이 수 count
						sum += 1;
					}
				}
			}
			// 지렁이 수 출력
			System.out.println(sum);
			
		}

	}

}
