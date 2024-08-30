package algo_Java;

import java.util.*;
import java.io.*;

public class SWEA_1486 {
	static int ans; // 출력 값
	static int N, B; // 점원의 수, 탑의 높이(B이상이어야 함)
	static int[] H; // 각 점원의 키
	static boolean[] select;

	// 탑의 높이는 점원이 1명일 경우 그 점원의 키와 같고, 2명 이상일 경우 탑을 만든 모든 점원의 키의 합과 같다.
	// 높이가 B 이상인 탑 중에서 높이가 가장 낮은 탑을 알아내고, "해당 높이 - B"값을 출력
	// 각 점원들의 키 H의 원소를 조합하여 B이상인 값 중 최솟값 구하기
	// => 각 부분집합의 합을 구하고, 합이 B 이상인 것 중에서만 정렬하여 최솟값 확인 후 "해당 높이 - B"값을 출력

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			H = new int[N]; // 크기 지정 및 초기화를 하지 않으면 NullPointerException 발생함!!!

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}

			ans = Integer.MAX_VALUE; // 정수의 최대값을 지정
			select = new boolean[N];
			subset(0); // 부분집합 생성 시작
			sb.append("#").append(tc).append(" ").append(ans - B).append("\n");
		}
		bw.write(sb.toString()); // 입력
		bw.flush(); // 버퍼에 있는 내용을 출력
		bw.close(); // BufferedWriter 닫기
	}

	// 재귀를 잘 이해하자.. 재귀 호출하는 동안 메모리에서 일어나는 동작 직접 그려보는 연습 !!!
	// 부분집합을 생성하는 재귀 함수
	public static void subset(int cnt) {
		// 종료 조건: 배열의 끝에 도달했을 때
		if (cnt == N) {
			int sum = 0;
			// 부분집합을 출력
			for (int i = 0; i < N; i++) {
				if (select[i])
					sum += H[i]; // 부분집합 출력
//				System.out.println(sum);
			}
			if (sum >= B)
				ans = Math.min(sum, ans); // 정렬할 필요없이 ans에 sum의 최솟값을 대입
			return; // 현재 호출한 함수만 끝냄
		}

		// 동작1
		select[cnt] = true; // 현재 인덱스의 원소를 부분집합에 포함시키는 경우
		subset(cnt + 1); // 다음 원소로 넘어감

		// 동작2
		select[cnt] = false; // 현재 인덱스의 원소를 부분집합에 포함시키지 않는 경우
		subset(cnt + 1); // 다음 원소로 넘어감
	}
	// 모든 원소에 대해 포함 또는 포함하지 않는 두 가지 경우를 탐색하기 때문에 2^N개의 부분집합이 생성됨

}
