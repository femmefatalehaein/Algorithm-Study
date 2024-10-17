package algo_Java;

import java.util.*;
import java.io.*;

public class ������ã�� {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int N = in.nextInt(); // ������ ����

		int[] arr = new int[N]; // N���� ���� ���� �� �ʱ�ȭ

		for (int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		} // �Է� ��

		// �̺� Ž�� (upper bound)
		int fixedPoint = findFixedPoint(arr, 0, N - 1);
		System.out.println(fixedPoint);

	}

	public static int findFixedPoint(int[] arr, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;

			if (arr[mid] == mid) {
				return mid; // �������� ã��
			} else if (arr[mid] < mid) {
				start = mid + 1; // ���� ���� �ε��� ������ ������, ������ �κи� Ž��
			} else {
				end = mid - 1; // ���� ���� �ε��� ������ ũ��, ���� �κи� Ž��
			}
		}
		return -1; // �������� ������ -1 ���
	}

}

/*
 * ���� ���� arr[mid]�� �ε��� ���� mid���� ���� ���, �������� �߰� �ε������� �����ʿ� ���� ���ɼ��� ũ��. 
 * ���� start�� mid+1�� ������Ʈ
 * ���� ���� arr[mid]�� �ε��� ���� mid���� ū ���, �������� �߰� �ε������� ���ʿ� ���� ���ɼ��� ũ��. 
 * ���� end�� mid-1�� ������Ʈ
 */