import java.io.*;
import java.util.*;

public class Main_9020 {
	/*
	 * false : �Ҽ� range : 0 ~ 10000
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
			// �Է� ��

			while (true) {
				// �� ��Ƽ���� ��� �Ҽ��� ���
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

// �����佺�׳׽��� ü
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
//				if (n[i]/2 == �Ҽ�) {
//					Prime[i] = n[i]/2;
//				} else if (n[i]/2 > �Ҽ�) {
//					
//				}
//			}
//		
//		// �Ҽ� �Ǻ� �޼ҵ�
//			
