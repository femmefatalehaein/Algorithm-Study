package algo_Java;

import java.util.*;
import java.io.*;

public class SWEA_6109 {
	static int N;
	static String S;
	static int[][] in_arr;
	static int[][] out_arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			in_arr = new int[N][N];
			out_arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					in_arr[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝

			switch (S) {
			case "up":
				for (int j = 0; j < N; j++) { // 열 우선 탐색
					for (int i = 0; i < N - 1; i++) { // 위부터 아래로
						if (in_arr[i][j] == 0) // 현재 값이 0이면 패스
							continue;

						int idx = i + 1;

						while (in_arr[idx][j] == 0) { // 비교할 값이 0이면 다음값
							if (idx == N - 1) // 끝까지 탐색
								break;
							idx++;
						}

						if (in_arr[idx][j] == 0) // 비교할 값이 0이면 패스
							continue;

						if (in_arr[i][j] == in_arr[idx][j]) { // 합치고 지우고
							in_arr[i][j] += in_arr[i][j];
							in_arr[idx][j] = 0;
							i = idx;
						}
					}
					int cur = 0;
					for (int i = 0; i < N; i++) { // 결과 입력
						if (in_arr[i][j] != 0) {
							out_arr[cur++][j] = in_arr[i][j];
						}
					}
				}
				break;

			case "down":
				for (int j = 0; j < N; j++) { // 열 우선 탐색
					for (int i = N - 1; i > 0; i--) { // 아래부터 위로
						if (in_arr[i][j] == 0) // 현재 값이 0이면 패스
							continue;

						int idx = i - 1;

						while (in_arr[idx][j] == 0) { // 비교할 값이 0이면 다음값
							if (idx == 0) // 끝까지 탐색
								break;
							idx--;
						}

						if (in_arr[idx][j] == 0) // 비교할 값이 0이면 패스
							continue;

						if (in_arr[i][j] == in_arr[idx][j]) { // 합치고 지우고
							in_arr[i][j] += in_arr[i][j];
							in_arr[idx][j] = 0;
							i = idx;
						}
					}
					int cur = N - 1; // ??
					for (int i = N - 1; i > 0; i--) { // 결과 입력
						if (in_arr[i][j] != 0) {
							out_arr[cur--][j] = in_arr[i][j];
						}
					}
				}
				break;

			case "left":
				for (int i = 0; i < N; i++) { // 행 우선 탐색
					for (int j = 0; j < N - 1; j++) { // 위부터 아래로
						if (in_arr[i][j] == 0) // 현재 값이 0이면 패스
							continue;

						int idx = j + 1;

						while (in_arr[i][idx] == 0) { // 비교할 값이 0이면 다음값
							if (idx == N - 1) // 끝까지 탐색
								break;
							idx++;
						}

						if (in_arr[i][idx] == 0) // 비교할 값이 0이면 패스
							continue;

						if (in_arr[i][j] == in_arr[i][idx]) { // 합치고 지우고
							in_arr[i][j] += in_arr[i][j];
							in_arr[i][idx] = 0;
							j = idx;
						}
					}
					int cur = 0;
					for (int j = 0; j < N; j++) { // 결과 입력
						if (in_arr[i][j] != 0) {
							out_arr[i][cur++] = in_arr[i][j];
						}
					}
				}
				break;

			case "right":
				for (int i = 0; i < N; i++) { // 행 우선 탐색
					for (int j = N - 1; j > 0; j--) { // 오른쪽부터 왼쪽으로
						if (in_arr[i][j] == 0) // 현재 값이 0이면 패스
							continue;

						int idx = j - 1;

						while (in_arr[i][idx] == 0) { // 비교할 값이 0이면 다음값
							if (idx == 0) // 끝까지 탐색
								break;
							idx--;
						}

						if (in_arr[idx][j] == 0) // 비교할 값이 0이면 패스
							continue;

						if (in_arr[i][j] == in_arr[i][idx]) { // 합치고 지우고
							in_arr[i][j] += in_arr[i][j];
							in_arr[i][idx] = 0;
							j = idx;
						}
					}
					int cur = N - 1; // ??
					for (int j = N - 1; j > 0; j--) { // 결과 입력
						if (in_arr[i][j] != 0) {
							out_arr[i][cur--] = in_arr[i][j];
						}
					}
				}
				break;

			}

			System.out.println("#" + tc);
			for (int[] is : out_arr) {
				for (int i : is) {
					System.out.print(i + " ");
				}
				System.out.println();
			}

		}

	}
}
