package SWEA.D_0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14891_김가연 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int [][] magnet = new int [4][8];
		
		for (int i = 0 ; i < 4 ; i++) {
			String m = bf.readLine();
			for (int j = 0 ; j < 8 ; j ++) {
				magnet[i][j] = (int)(m.charAt(j) - 48);
			}
		}
		
		int [][] dir = new int [][] {{2, 6}, {2, 6}, {2, 6}, {2, 6}};
		
		int rp = Integer.parseInt(bf.readLine());
		
		for (int i = 0 ; i < rp ; i ++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int wheel = Integer.parseInt(st.nextToken()) - 1;
			int turn = Integer.parseInt(st.nextToken());
			
			// [0] : 맞닿는 자석 부분 체크해서 서로 다른극이면 1 / 0번, 1번 자석이 맞닿아있는 부분은 1번 인덱스[0]에 저장
			// [1] : 회전방향 체크 / 1이면 시계 -1이면 반시계
			int [][] reach = new int [4][2];
			
			for (int j = 0 ; j < 3 ; j ++) {
				if (magnet[j][dir[j][0]] != magnet[j + 1][dir[j + 1][1]]) {
					reach[j + 1][0] = 1;
				} else {
					reach[j + 1][0] = 0;
				}
			}
			
			// 회전방향 저장하기
			reach[wheel][1] = turn;
			
			for (int j = wheel - 1 ; j >= 0 ; j--) {
				if(reach[j + 1][0] != 0) {
					reach[j][1] = reach[j + 1][1] * (-1);
				}
			}
			
			for (int j = wheel + 1 ; j < 4 ; j++) {
				if(reach[j][0] != 0) {
					reach[j][1] = reach[j - 1][1] * (-1);
				}
			}
			
			for (int j = 0 ; j < 4 ; j++) {
				// 회전방향이 저장되어 있을 때
				if (reach[j][1] != 0) {
					// 시계방향으로 돌리기
					if (reach[j][1] == 1) {
					
						dir[j][0] -= 1;
						dir[j][1] -= 1;
						
						if (dir[j][0] < 0) {
							dir[j][0] = 7;
						}
						
						if (dir[j][1] < 0) {
							dir[j][1] = 7;
						}
						
						
					// 반시계방향으로 돌리기
					} else if (reach[j][1] == -1) {
						
						dir[j][0] += 1;
						dir[j][1] += 1;
						
						if (dir[j][0] > 7) {
							dir[j][0] %= 8;
						}
						if (dir[j][1] > 7) {
							dir[j][1] %= 8;
						}
						
					}
					
				}
			}
			
		}
		
		int answer = 0;
		
		for (int i = 0 ; i < 4 ; i++) {
			
			int d = dir[i][1] + 2;
			if (d > 7) {
				d = d % 8;
			}
			
			if ((i == 0) && (magnet[i][d] == 0)) {
				answer += 0;
			} else {
				answer += Math.pow(magnet[i][d] * 2, i);
			}
			
		}
		
		System.out.println(answer);
		
	}

}
