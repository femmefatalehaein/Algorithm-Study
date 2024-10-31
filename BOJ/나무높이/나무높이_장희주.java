package 나무높이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무높이_장희주 {

	static int N;
	static int high;
	static int mincnt;
	static int[] trees;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			trees = new int[N];
			StringTokenizer st = null;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
			}
			mincnt = 0;
			Arrays.sort(trees);
			high = trees[N - 1];
			trees();
			System.out.printf("#%d %d\n", test_case, mincnt - 1);
		}

	}

	static void trees() {
		boolean flag = false;
		int cnt = 0;
		
		while (N > cnt) {
			mincnt++;
			flag = true;
			cnt = 0;
			for (int i = 0; i < N; i++) {
				// 1. 지금홀수날 -홀수있으면 +1
				// 2. 지금 홀수날인데 -홀수없음 제일 작은애 준다..?
				// 3. 짝수날- 짝수가있으면 제일 큰애줌..
				// 4. 짝수날-짝수가없으면 -줄수없으면 패스 있으면 줌
				if (trees[i] == high) {
					cnt++;
					continue;
				}
				// 짝수 일 일 때 -> +2
				if (mincnt % 2 == 0) {
					// 짝수가 있을 때
					if ((high - trees[i]) % 2 == 0) {
						trees[i] += 2;
						flag = false;
						break;
					}

					// 홀수 일 일 때 -> +1
				} else {
					// 홀수가 있을 때
					if ((high - trees[i]) % 2 == 1) {
						trees[i] += 1;
						flag = false;
						break;
					}
				}

			}
			if (flag) {
				// 짝수 날인데 짝수가 없었을 때
				if (mincnt % 2 == 0) {
					for (int i = 0; i < N; i++) {
						if ((high - trees[i]) >= 2) {
							trees[i] += 2;
							break;
						}
					}
				}

				// 홀수 날인데 홀수가 없었을 때
				else {
					for (int i = 0; i < N; i++) {
						if(high - trees[i] == 2 && cnt == N - 1) {
							break;
						}
						
						else if ((high - trees[i]) >= 1) {
							trees[i] += 1;
							break;
						} else {

						}
					}
				}
			}
			
		}
	}

}
