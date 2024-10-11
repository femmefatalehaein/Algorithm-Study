import java.io.*;
import java.util.*;

public class Main_9020 {
	/*
	 * false : 소수 range : 0 ~ 10000
	 */

	public static boolean[] isPrime = new boolean[10001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		getPrime();

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			int firstPartiton = n / 2;
			int secondPartition = n / 2;
			// 입력 끝

			while (true) {
				// 두 파티션이 모두 소수일 경우
				if (!isPrime[firstPartiton] && !isPrime[secondPartition]) {
					sb.append(firstPartiton).append(' ').append(secondPartition).append('\n');
					break;
				}
				firstPartiton--;
				secondPartition++;
			}
		}
		System.out.println(sb);
	}

// 에라토스테네스의 체
	public static void getPrime() {
		isPrime[0] = isPrime[1] = true;

		for (int i = 2; i <= Math.sqrt(isPrime.length); i++) {
			if (isPrime[i])
				continue;
			for (int j = i * i; j < isPrime.length; j += i) {
				isPrime[j] = true;
			}
		}
	}
}

//			for (int i = 0; i < T; i++) {
//				if (n[i]/2 == 소수) {
//					Prime[i] = n[i]/2;
//				} else if (n[i]/2 > 소수) {
//					
//				}
//			}
//		
//		// 소수 판별 메소드
//			
