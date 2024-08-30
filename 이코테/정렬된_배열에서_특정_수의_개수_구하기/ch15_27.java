package algo_Java;

import java.util.*;
import java.io.*;

// 입력되는 수열이 오름차순이고, 완탐으로 몇 개인지 셀 수 있지만, 이는 O(N)의 시간복잡도를 가짐.
// 이진탐색을 사용하면 O(logN)의 시간복잡도로 풀 수 있다.

public class ch15_27 {
	static int N, X; // N개의 수열, 개수를 세야 하는 수 X
	static int[] arr; // 수열

	// leftIndex는 X와 같거나 큰 수의 인덱스를 탐색
	private static int leftIndex(int start, int end) { 
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] >= X)
				end = mid;
			else
				start = mid + 1;
		}
		return end;
	}

	// rightIndex는 X보다 큰 수의 인덱스를 탐색
	private static int rightIndex(int start, int end) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (arr[mid] > X)
				end = mid;
			else
				start = mid + 1;
		}
		return end;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 입력 끝

		int left = leftIndex(0, N);
		int right = rightIndex(0, N);
		int result = right - left;

		System.out.println(result == 0 ? -1 : result); // X가 없으면 -1반환, 그렇지 않으면 result 반환 

	}

}
