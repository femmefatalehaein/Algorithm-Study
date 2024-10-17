package algo_Java;

import java.util.*;
import java.io.*;

public class SWEA_1486 {
	static int ans; // ��� ��
	static int N, B; // ������ ��, ž�� ����(B�̻��̾�� ��)
	static int[] H; // �� ������ Ű
	static boolean[] select;

	// ž�� ���̴� ������ 1���� ��� �� ������ Ű�� ����, 2�� �̻��� ��� ž�� ���� ��� ������ Ű�� �հ� ����.
	// ���̰� B �̻��� ž �߿��� ���̰� ���� ���� ž�� �˾Ƴ���, "�ش� ���� - B"���� ���
	// �� �������� Ű H�� ���Ҹ� �����Ͽ� B�̻��� �� �� �ּڰ� ���ϱ�
	// => �� �κ������� ���� ���ϰ�, ���� B �̻��� �� �߿����� �����Ͽ� �ּڰ� Ȯ�� �� "�ش� ���� - B"���� ���

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

			H = new int[N]; // ũ�� ���� �� �ʱ�ȭ�� ���� ������ NullPointerException �߻���!!!

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}

			ans = Integer.MAX_VALUE; // ������ �ִ밪�� ����
			select = new boolean[N];
			subset(0); // �κ����� ���� ����
			sb.append("#").append(tc).append(" ").append(ans - B).append("\n");
		}
		bw.write(sb.toString()); // �Է�
		bw.flush(); // ���ۿ� �ִ� ������ ���
		bw.close(); // BufferedWriter �ݱ�
	}

	// ��͸� �� ��������.. ��� ȣ���ϴ� ���� �޸𸮿��� �Ͼ�� ���� ���� �׷����� ���� !!!
	// �κ������� �����ϴ� ��� �Լ�
	public static void subset(int cnt) {
		// ���� ����: �迭�� ���� �������� ��
		if (cnt == N) {
			int sum = 0;
			// �κ������� ���
			for (int i = 0; i < N; i++) {
				if (select[i])
					sum += H[i]; // �κ����� ���
//				System.out.println(sum);
			}
			if (sum >= B)
				ans = Math.min(sum, ans); // ������ �ʿ���� ans�� sum�� �ּڰ��� ����
			return; // ���� ȣ���� �Լ��� ����
		}

		// ����1
		select[cnt] = true; // ���� �ε����� ���Ҹ� �κ����տ� ���Խ�Ű�� ���
		subset(cnt + 1); // ���� ���ҷ� �Ѿ

		// ����2
		select[cnt] = false; // ���� �ε����� ���Ҹ� �κ����տ� ���Խ�Ű�� �ʴ� ���
		subset(cnt + 1); // ���� ���ҷ� �Ѿ
	}
	// ��� ���ҿ� ���� ���� �Ǵ� �������� �ʴ� �� ���� ��츦 Ž���ϱ� ������ 2^N���� �κ������� ������

}
