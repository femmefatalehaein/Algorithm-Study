import java.io.*;
import java.util.*;

public class Main_14888 {
	static int N;
	static int[] A;
	static int[] operator;
	static int maxVal = Integer.MIN_VALUE; // 최댓값 (Integer의 가장 최솟값을 넣어줌)
	static int minVal = Integer.MAX_VALUE; // 최솟값 (Integer의 가장 최댓값을 넣어줌)

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		operator = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < 4; j++) {
			operator[j] = Integer.parseInt(st.nextToken());
		}
		// 입력 끝

		// 연산자의 개수 n-1개끼리 자리를 바꾸는 경우의 수 -> 순열?
		// - 연산자 우선순위 무시, 앞에서부터 진행
		// - 나눗셈은 몫만 취하기
		// - 음수를 양수로 나눌 땐 양수로 바꾼 후 몫을 음수로 바꾸기
		// 그 중 최대 최소는 어떻게 구하지? -> 브루트포스? 다 해보기? 2~11까지 10개니깐 괜찮을지도?
		// 시간복잡도 잘 모르겠다

		backtracking(A[0], 1);
		
		System.out.println(maxVal);
		System.out.println(minVal);

	}

	public static void backtracking(int num, int idx) {
		if (idx == N) {
			maxVal = Math.max(maxVal, num);
			minVal = Math.min(minVal, num);
			return;
		} // ??

		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) { // 연산자 개수가 1개 이상인 경우
				operator[i]--; // 해당 연산자를 1 감소시킴

				switch (i) {
				case 0: // 더하기
					backtracking(num + A[idx], idx + 1);
					break;

				case 1: // 빼기
					backtracking(num - A[idx], idx + 1);
					break;
					
				case 2: // 곱하기
					backtracking(num * A[idx], idx + 1);
					break;
					
				case 3: // 나누기
					backtracking(num / A[idx], idx + 1);
					break;
				}
				
				operator[i]++; // 재귀호출이 종료되면 다시 해당 연산자 개수를 복구
			}
		}
	}

}
