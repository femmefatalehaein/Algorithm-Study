package 캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;

public class 캐슬디펜스_장희주 {

	static class Target {
		int x, y, dist;

		public Target(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}

	static int N, M, D;
	static int[][] castle;
	static int[] numbers;
	
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		castle = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}

		int result = 0;
		numbers=new int[3];
		result=bruteforce(0, 0);
		System.out.println(result);

	}

	static int shoot(int[] postion, int[][] array) {
		// 각 궁수마다
		int cnt = 0;
		List<Target> targets = new ArrayList<>();
		for (int y : postion) {
			// 젤 가까운애 찾음
			Target target = new Target(-1, -1, Integer.MAX_VALUE);
			for (int j = 0; j < M; j++) {
				for (int i = 0; i < N; i++) {
					int value = Math.abs(i - N) + Math.abs(j - y);
					if (array[i][j] == 1 && value <= D) {
						if (target.dist > value) {
							target.dist = value;
							target.x = i;
							target.y = j;
						}
					}
				}
			}
			targets.add(target);
		}
		for (Target t : targets) {
			if (t.dist != Integer.MAX_VALUE) {
				if (array[t.x][t.y] == 1) {
					cnt++;
					array[t.x][t.y] = 0;
				}
			}
		}
		return cnt;
	}

	static int movedown(int[][] array) {
		int cnt = 0;

		for (int j = 0; j < M; j++) {
			for (int i = N - 1; i > 0; i--) {
				array[i][j] = array[i - 1][j];
				if (array[i][j] == 1) {
					cnt++;
				}
			}
			array[0][j] = 0;
		}
		return cnt;
	}

	static int bruteforce(int cnt, int idx) {
		if (cnt == 3) {
			int[][] array = new int[N][M];
			array = copyArray(array);
			// 끝날때까지 반복
			int result=0; // 총 몇명 죽였니?
			while (true) {
				result+=shoot(numbers, array);
				if(movedown(array)==0) {
					return result;
				}	
			}
			// 그래서 몇킬? 최대값은?
		}
		int maxresult = 0;
		for (int i = idx; i < M; i++) {
			numbers[cnt] = i;
			maxresult = Math.max(maxresult, bruteforce(cnt + 1, i + 1));
		}
		return maxresult;
	}

	static int[][] copyArray(int[][] array) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				array[i][j] = castle[i][j];
			}
		}
		return array;
	}
}
