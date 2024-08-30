package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2382_김가연 {
	
	// 이동방향 반대로 돌리는 함수
	static int swap(int a) {
		if (a == 1) {
			return 2;
		} else if (a == 2) {
			return 1;
		} else if (a == 3) {
			return 4;
		} else {
			return 3;
		}
	}
	
	// 배열 내부인지 확인하는 함수
	static boolean iflab(int x, int y, int n) {
		if ((x == 0) | (x == (n - 1)) | (y == 0) | (y == (n - 1))) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1 ; test_case <= tc ; test_case ++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			// 미생물의 현재 위치
			int n = Integer.parseInt(st.nextToken());
			int [][] lab = new int [n][n];
			
			// 소요 시간
			int m = Integer.parseInt(st.nextToken());
			
			// 미생물 정보 담을 배열 생성 [][0] : 미생물 수 , [][1] : 이동 방향
			int k = Integer.parseInt(st.nextToken());
			int [][] info = new int[k][2];
			
			// 방향배열 설정
			int [][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			Queue<int[]> inlab = new LinkedList<>();
			
			
			for (int i = 0 ; i < k ; i++) {
				StringTokenizer st1 = new StringTokenizer(bf.readLine());
				
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st1.nextToken());
				// 미생물 좌표정보와 미생물 구분할 숫자 넣어주기
				inlab.add(new int[] {x, y, i+1});
				
				// 미생물 수와 이동방향 넣어주기
				info[i][0] = Integer.parseInt(st1.nextToken());
				info[i][1] = Integer.parseInt(st1.nextToken());
			}
			
			int sum = 0;

			for (int i = 0 ; i < m ; i++) {
				
				lab = new int [n][n];
				// 만약 미생물이 모인다면 모인 미생물들의 합을 저장해줄 배열 선언
				int [][] max = new int [n][n];
				sum = 0;
				
				while (!inlab.isEmpty()) {
					int [] mi = inlab.poll();
					
					// 이동방향 1부터 넣어줬으므로 -1해서 배열 인덱스와 맞춰주기
					int d = info[mi[2] - 1][1] - 1;
					
					// 이동방향으로 한칸 이동시키기
					int mx = mi[0] + dir[d][0];
					int my = mi[1] + dir[d][1];
					
					// 만약 이미 이동한 미생물이 있다면 
					if (lab[mx][my] != 0) {
						
						// 처음 이 칸에서 모인거라면
						if (max[mx][my] == 0) {
							// 두 미생물 번호의 미생물 수를 더해주기
							max[mx][my] = info[lab[mx][my] - 1][0] + info[mi[2] - 1][0];
						
						// 이미 모여져 있었다면
						} else {
							// 이미 모여져 있던 미생물 합에 새로 들어온 미생물 합을 구해준다
							max[mx][my] += info[mi[2] - 1][0];
						}
						
						// 미생물 중 가장 많은 미생물을 가지고 있는 미생물의 번호를 LAB 배열에 저장
						if (info[lab[mx][my] - 1][0] < info[mi[2] - 1][0]) {
							lab[mx][my] = mi[2];
						}
						
					// 없다면 그냥 미생물 번호 저장하기	
					} else {
						lab[mx][my] = mi[2];
					}
				}
				
				// 저장된 미생물 한바퀴 돌기
				for (int x = 0 ; x < n ; x++) {
					for (int j = 0 ; j < n ; j++) {
						// 만약 이칸에 미생물이 저장되어 있고
						if (lab[x][j] != 0) {
							// 외곽 (약품) 지역이 아니라면
							if (iflab(x, j, n)) {
								// MAX배열에 값이 저장되어 있으면 -> 합쳐진 미생물이라면
								if (max[x][j] != 0) {
									// 가장 큰 미생물 번호에 MAX배열에 저장되어 있던 미생물 수를 담아주기
									info[lab[x][j] - 1][0] = max[x][j];
									// 다시 큐에 좌표, 번호넣기
									inlab.add(new int[] {x, j, lab[x][j]});
									
									// 정답 구하기 위해 저장된 미생물 수를 더해주기
									sum += info[lab[x][j] - 1][0];
									
								// 합쳐진 미생물이 아니라면
								} else {
									// 큐에 좌표 , 번호 넣어주기
									inlab.add(new int[] {x, j, lab[x][j]});
									sum += info[lab[x][j] - 1][0];
								}
							// 만약 약품처리된 지역에 들어갔다면
							} else {
								if (max[x][j] != 0) {
									// 저장된 미생물 수 합을 2로 나눠 저장해주기
									info[lab[x][j] - 1][0] = max[x][j] / 2;
									inlab.add(new int[] {x, j, lab[x][j]});
									sum += info[lab[x][j] - 1][0];
								} else {
									info[lab[x][j] - 1][1] = swap(info[lab[x][j] - 1][1]);
									info[lab[x][j] - 1][0] /= 2;
									inlab.add(new int[] {x, j, lab[x][j]});
									sum += info[lab[x][j] - 1][0];
								}
							}
							
						}
					}
				}
			}
			// 테스트 케이스와 합 출력하기
			System.out.printf("#%d %d \n", test_case, sum);

				
		}
		
	}

}
