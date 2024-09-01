package SWEA.D_0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Pipe {
	
	// 파이프를 돌릴 수 있는 방향 배열
	static int [][] dir = new int[][] {{0, 1}, {1, 1}, {1, 0}};
	// 벽, 파이프 배열을 받을 배열 선언
	static int [][] pipe;
	// 가로, 세로길이 받을 변수 선언
	static int n;
	// [n-1][n-1]번째 자리에 파이프가 도착할 수 있는 수 세기 위한 변수 선언
	static long cnt;
	
	// 만약 배열 내부이고 벽이 있는 자리가 아니라면 true, 나머지 경우는 false 반환
	static boolean ifmap(int x, int y) {
		if (((0 <= x) && (x < n)) && ((0 <= y) && (y < n))) {
			if (pipe[x][y] != 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	// 파이프를 대각선으로 돌릴 때 위, 왼쪽, 그 자리에 벽이 없는지 확인하는 함수
	// 만약 벽이 있다면 false, 없다면 true 리턴
	static boolean slash(int x, int y) {
		for (int i = 0 ; i < 3 ; i++) {
			if (pipe[x + dir[i][0]][y + dir[i][1]] == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	// 다음 파이프 돌릴 위치 찾는 함수
	static void dpipe(int x, int y, int d) {
		
		// 파이프가 배열을 벗어났다면 return -> 이거 왜햇징
		if ((x >= n) || (y >= n)) {
			return;
		}
		
		// 만약 마지막칸에 도착했다면 정답변수 1 올리고 return
		if ((x == n - 1) && (y == n - 1)) {
			cnt ++;
			return;
		}
		
		// 다음 좌표 저장할 변수 선언
		int dx = 0, dy = 0;
		
		// 만약 가로로 이루어진 파이프라면 -> 가로, 대각선으로 이어질 수 있음
		if (d == 0) {
			// 가로, 대각선으로 돌려주기
			for (int i = 0 ; i < 2 ; i++) {
				dx = x + dir[i][0];
				dy = y + dir[i][1];
				if (dy != 0) {
					// 가로로 돌릴때는 다음 좌표에만 벽있는지 확인하면 되니 그냥 ifmap 함수에 돌려줌
					if (i != 1) {
						if (ifmap(dx, dy)) {
							dpipe(dx, dy, i);
						}
					// 대각선으로 돌릴때는 ifmap함수 + 대각선 위치에 벽 있는지 확인할 함수 slash함수 돌려주기
					} else {
						if (ifmap(dx, dy) && slash(x, y)) {
							dpipe(dx, dy, i);
						}
					}
				}
			}
		// 만약 대각선으로 이루어진 파이프라면 -> 가로, 세로, 대각선으로 이어질 수 있음
		} else if (d == 1){
			for (int i = 0 ; i < 3 ; i++) {
				dx = x + dir[i][0];
				dy = y + dir[i][1];
				if (dy != 0) {
					// 대각선 아닐때 
					if (i != 1) {
						if (ifmap(dx, dy)) {
							dpipe(dx, dy, i);
						}
					// 대각선일때
					} else {
						if (ifmap(dx, dy) && slash(x, y)) {
							dpipe(dx, dy, i);
						}
					}
				}
			}
		// 만약 세로로 이루어진 파이프라면 -> 세로, 대각선으로 이어질 수 있음
		} else {
			for (int i = 1 ; i < 3 ; i++) {
				dx = x + dir[i][0];
				dy = y + dir[i][1];
				if (dy != 0) {
					// 대각선 아닐 때
					if (i != 1) {
						if (ifmap(dx, dy)) {
							dpipe(dx, dy, i);
						}
					// 대각선일때
					} else {
						if (ifmap(dx, dy) && slash(x, y)) {
							dpipe(dx, dy, i);
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bf.readLine());
		pipe = new int[n][n];
		
		// 파이프 정보 받아주기
		for (int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < n ; j++) {
				pipe[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 정답 변수 초기화
		cnt = 0;
		
		// [0][1] 좌표에서 가로로 이어진 파이프부터 시작이라고 함수 호출
		dpipe(0, 1, 0);
		
		// 정답 변수 출력
		System.out.println(cnt);
	}

}
