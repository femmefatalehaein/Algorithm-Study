package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Move {
	
	static boolean ifcoun(int x, int y, int n) {
		if (((0 <= x) && (x < n)) &&((0 <= y) &&(y < n))) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		// 국경선 열릴 수 있는지 체크하는 큐
		Queue<int[]> line = new LinkedList<>();
		// 인구 나눠주기 위해 인구 나눠줄 수 있는 좌표 체크하는 큐
		Queue<int[]> one = new LinkedList<>();
		
		// 국가 사방탐색하는 사방탐색 배열
		int [][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		// 국가 인원수 채워주기
		int [][] coun = new int [n][n];
		for (int i = 0 ; i < n ; i ++) {
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < n ; j ++) {
				coun[i][j] = Integer.parseInt(st1.nextToken());
			}
		}
		
		int b = 0;
		int cnt = 0;
		int day = 0;
		int [][] visit;
		
		// 이동이 끝날때까지 돌려주기
		while (true) {
			int ss  = 0;
			// 다음날에도 이동이 계속되어야 하므로 방문배열 큐 시작할때마다 초기화시켜주기
			visit = new int [n][n];
			for (int i = 0 ; i < n ; i ++) {
				for (int j = 0 ; j < n ; j ++) {
					// 큐에 들어간 국가 몇개인지 체크할 변수
					b = 0;
					// 인원수 다 합쳐서 경계선 열린 국가 갯수로 나눠줘 저장할 변수
					cnt = 0; 
	
					if (visit[i][j] != 1) {
						// 인원수 나눠주는 큐는 for문 돌릴때마다 초기화		
						one = new LinkedList<>();
						// 방문 표시
						visit[i][j] = 1;
						// 경계 국가 확인할 큐에 넣어줌
						line.add(new int[] {i, j});
						
						while(!line.isEmpty()) {
							int [] arr = line.poll();
							// 경계 국가 인원수 합쳐주기 위해 설정한 큐에 좌표 넣기
							one.add(new int[] {arr[0], arr[1]});
							// 큐에 들어간 국가의 인원수 합
							cnt += coun[arr[0]][arr[1]];
							for (int [] a : dir) { // 사방탐색
								int dx = arr[0] + a[0];
								int dy = arr[1] + a[1];
								if (ifcoun(dx, dy, n)) {
									// 국가 인원 차이 구해주고
									int minus = Math.abs(coun[arr[0]][arr[1]] - coun[dx][dy]);
									// 그 국가 방문하지 않고 차이가 지정된 값 보다 적다면
									if ((visit[dx][dy] != 1) &&((l <= minus) && (minus <= r))) {
										// 방문 처리 후 큐에 넣어주기
										visit[dx][dy] = 1;
										line.add(new int[] {dx, dy});
									}
								}
							}
						}
					}
					
					// 경계가 열릴 수 있을만큼 (2개이상) 큐에 들어갔다면
					if (one.size() > 1) {
						// 나누기 할 값 구하기
						b = one.size();
						// 더했던 인구수 합 나눠주기
						cnt = cnt / b; 
						// 큐에 들어간 좌표값에 나눠준 값 설정
						while(!one.isEmpty()) {
							int [] arr = one.poll();
							coun[arr[0]][arr[1]] = cnt;
						}
						// 이동 일어났다고 체크
						ss++;
					}
				}
				
			}
			// 만약 이동이 일어나지 않았다면 인구이동이 끝난것이므로 종료
			if (ss == 0) {
				break;
			// 이동이 일어났다면 일자를 하루 늘려준다
			} else {
				day++;
			}
		}
		System.out.println(day);

	}

}