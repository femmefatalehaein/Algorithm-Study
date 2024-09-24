package D0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1932_김가연 {
	
	/*
	 * 맨 아래에서부터 현재 위치에서 양쪽 대각선 값을 더하기
	 * 만약 값이 저장되어 있다면 현재 더한값과 비교해 최대값 저장해두기
	 */
	
	
	// 현재 위치가 배열 안쪽인지 학인하기
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
		// 정수 삼각형의 값 저장할 배열 선언
		int [][] ori = new int [n][n];
		
		// 최대값을 찾기 위한 정수 삼각형 배열 선언
		int [][] copy = new int [n][n];
		
		// 일단 최대값 배열을 정수의 최소값으로 초기화 시켜주기
		for (int i = 0 ; i < n ; i ++) {
			Arrays.fill(copy[i], Integer.MIN_VALUE);
		}
		
		for (int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j <= i ; j ++) {
				// 값 받아오기
				ori[i][j] = Integer.parseInt(st.nextToken());
				
				// 최대값 저장 배열의 맨 아랫줄만 초기화 시켜주기
				if (i == n - 1) {
					copy[i][j] = ori[i][j];
				}
			}
		}
		
		int idx = n;
		
		for (int i = n - 1 ; i > 0 ; i--) {
			for (int j = 0 ; j < idx ; j++) {
				
				// 왼쪽 대각선 값과 더해 최대값이면 copy배열에 저장
				// 값이 저장되어 있다면 더 큰값을 알아내 큰 값 저장
				if (ifmap(i - 1, j - 1, n)) {
					if (copy[i][j] + ori[i - 1][j - 1] > copy[i - 1][j - 1]) {
						copy[i - 1][j - 1] = copy[i][j] + ori[i - 1][j - 1];
					}
				}
				
				// 오른쪽 대각선 값과 더해 최대값이면 copy배열에 저장
				// 값이 저장되어 있다면 더 큰값을 알아내 큰 값 저장
				if (ifmap(i - 1, j, n)) {
					if (copy[i][j] + ori[i - 1][j] > copy[i - 1][j]) {
						copy[i - 1][j] = copy[i][j] + ori[i - 1][j];
					}
				}
			}
			idx --;
		}

		System.out.println(copy[0][0]);
	}

}
