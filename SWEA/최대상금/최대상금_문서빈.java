import java.util.*;
import java.io.*;

// 재귀  + 반복문을 이용한 DFS(완전탐색) & 최적화 
public class 최대상금 {
	static String[] arr;
	static int max, swap_num; // 최대 금액, 교환 횟수 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.setIn(new FileInputStream("src/input/input_1244.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			/*
			 * 숫자 문자열(123456)을 개별 문자(숫자)로 분리(빈 문자열 ""를 기준으로)하여 arr 배열에 저장
			 * "123456"에 .split("")를 적용하면 ["1", "2", "3", "4", "5", "6"]
			 * 즉 문자열의 각 문자들이 배열의 원소로 하나씩 저장됨 
			 */
			arr = sc.next().split(""); 
			swap_num = sc.nextInt(); 
			// 입력 끝 
			
			max = 0;
			// 시간초과 최적화
			if (arr.length < swap_num) { // swap 횟수가 자릿수보다 클 때
				swap_num = arr.length; // 자릿수만큼만 옮겨도 전부 옮길 수 있음
			}
			dfs(0, 0);
			System.out.println("#" + tc + " " + max);
		}
	}

	static void dfs(int start, int cnt) { // dfs를 통해 모든 경우의 수 탐색 
		if (cnt == swap_num) { // 교환횟수만큼 교환했다면 (종료 조건)
			String result = ""; // 현재 arr에 저장된 문자열들을 합쳐서 하나의 숫자 문자열로 만듦 
			for (String s : arr) // arr에 저장된 각 문자를 하나의 문자열(result)로 결합
				result += s; //  ["3", "2", "1"] -> "321"
			max = Math.max(max, Integer.parseInt(result)); 
			return;
		}
		for (int i = start; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				// swap
				String temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;

				dfs(i, cnt + 1); // 깊이 +1
				// 각 단계에서 두 자리를 교환한 후, 다시 swap 해서 원래 자리로 복구(백트래킹).
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}
}