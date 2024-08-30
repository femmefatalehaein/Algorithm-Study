package algo_Java;

import java.util.*;
import java.io.*;

public class 고정점찾기 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int N = in.nextInt(); // 수열의 개수

		int[] arr = new int[N]; // N개의 수열 생성 및 초기화

		for (int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		} // 입력 끝

		// 이분 탐색 (upper bound)
		int fixedPoint = findFixedPoint(arr, 0, N - 1);
		System.out.println(fixedPoint);

	}

	public static int findFixedPoint(int[] arr, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;

			if (arr[mid] == mid) {
				return mid; // 고정점을 찾음
			} else if (arr[mid] < mid) {
				start = mid + 1; // 원소 값이 인덱스 값보다 작으면, 오른쪽 부분만 탐색
			} else {
				end = mid - 1; // 원소 값이 인덱스 값보다 크면, 왼쪽 부분만 탐색
			}
		}
		return -1; // 고정점이 없으면 -1 출력
	}

}

/*
 * 원소 값인 arr[mid]가 인덱스 값인 mid보다 작은 경우, 고정점은 중간 인덱스보다 오른쪽에 있을 가능성이 크다. 
 * 따라서 start를 mid+1로 업데이트
 * 원소 값인 arr[mid]가 인덱스 값인 mid보다 큰 경우, 고정점은 중간 인덱스보다 왼쪽에 있을 가능성이 크다. 
 * 따라서 end를 mid-1로 업데이트
 */