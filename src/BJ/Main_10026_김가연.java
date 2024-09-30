package SWEA.D_0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_10026_김가연 {
	
	static boolean ifmap(int x, int y, int n) {
		
		if (((0 <= x) && (x < n)) && ((0 <= y) && (y < n))) {
			return true;
		} else {
			return false;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		
		char [][] rgb = new char [n][n];
		
		for (int i = 0 ; i < n ; i ++) {
			String line = bf.readLine();
			for (int j = 0 ; j < n ; j++) {
				rgb[i][j] = line.charAt(j);
			}
		}
		
		// 일반 사람이 볼 수 있는 구역
		Queue<int[]> normal = new LinkedList<>();
		// 적록색약이 볼 수 있는 구역
		Queue<int[]> eye = new LinkedList<>();
		
		// 일반사람이 볼 수 있는 구역 셀 변수 선언
		int nor = 0;
		// 적록색약이 볼 수 있는 구역 셀 변수
		int nnor = 0;
		
		// 일반 방문 배열 선언
		boolean [][] visit = new boolean [n][n];
		// 적록색약 방문 배열 선언
		boolean [][] rvisit = new boolean [n][n];
		
		// 사방탐색하기 위한 배열 설정
		int [][] dir = new int [][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		
		for (int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < n ; j++) {
				
				// 일반사람 구역 체크
				// 만약 방문하지 않았다면
				if (!visit[i][j]) {
					
					// 방문처리 후 일반 큐에 좌표 넣기
					visit[i][j] = true;
					normal.add(new int [] {i, j});
					
					while(!normal.isEmpty()) {
						
						int [] nm = normal.poll();
						
						// 현재 위치에서 사방탐색
						for (int [] d : dir) {
							
							int dx = d[0] + nm[0];
							int dy = d[1] + nm[1];
							
							// 만약 배열 안쪽이고
							if (ifmap(dx, dy, n)) {
								
								// 방문하지 않고 현재 색과 같은 색이라면 방문처리 후 큐에 삽입
								if ((!visit[dx][dy]) && (rgb[nm[0]][nm[1]] == rgb[dx][dy])) {
									visit[dx][dy] = true;
									normal.add(new int [] {dx, dy});
								}
							}
						}
					}
					// 일반 구역 +1
					nor += 1;
				}
				
				// 적록색약 구역 체크
				// 만약 방문하지 않았다면
				if (!rvisit[i][j]) {
					
					// 방문처리 후 적록색약 큐에 삽입
					rvisit[i][j] = true;
					eye.add(new int [] {i, j});
					
					while(!eye.isEmpty()) {
						
						int [] nm = eye.poll();
						
						// 사방탐색
						for (int [] d : dir) {
							
							int dx = d[0] + nm[0];
							int dy = d[1] + nm[1];
							
							// 만약 배열 내부이고
							if (ifmap(dx, dy, n)) {
								// 방문하지 않은 곳이며
								if (!rvisit[dx][dy]) {
									// 현재 색이 R OR G 이고 다음 탐색 구역 색이 R OR G라면 적록색약 구분 X -> 방문 처리 후 큐에 삽입
									// 현재 색과 다음 탐색구역 색이 같다면 같은 구역이므로 방문처리 후 큐에 넣기
									if (((rgb[nm[0]][nm[1]] == 'R' || rgb[nm[0]][nm[1]] == 'G') && (rgb[dx][dy] == 'R' || rgb[dx][dy] == 'G'))
											|| (rgb[nm[0]][nm[1]] == rgb[dx][dy])) {
										rvisit[dx][dy] = true;
										eye.add(new int [] {dx, dy});
									}
								}
							}
						}
					}
					
					// 적록색약 구역 +1
					nnor += 1;
				}
			}
		}
		
		// 일반 구역 cnt와 적록 구역 cnt 출력
		System.out.printf("%d %d", nor, nnor);
		
	}
	
}
