package algo_Java;

import java.util.*;
import java.io.*;

public class 나무자르기_문서빈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Scanner in = new Scanner(System.in);

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] tree = new int[N];

		int min = 0, max = 0;

		for (int i = 0; i < N; i++) {
			// tree[i] = Integer.parseInt(st.nextToken());
			tree[i] = in.nextInt();

			if (max < tree[i]) { // 입력받은 나무가 max보다 클 경우 max값을 해당 나무 높이로 갱신
				max = tree[i];
			}
		}

		// 이분 탐색
		while (min < max) {
			int mid = (min + max) / 2;
			long sum = 0;
			for (int treeH : tree) { // tree 잘린 길이 = tree 높이 - 자르는 위치(mid)
			// 0 이하의 수는 합산하지 않는다.
				if (treeH - mid > 0) {
					sum += (treeH - mid);
				}
			}

			if (sum < M) {
				max = mid;
			}

			else {
				min = mid + 1;
			}
		}

		System.out.println(min - 1);
	}

}
