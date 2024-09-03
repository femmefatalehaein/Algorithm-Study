package Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Antenna {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		int [] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0 ; i < n ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 가장 가운데 있는 집이 모든집까지의 거리가 최소가 되므로 중간 인덱스 구해주기
		if (n % 2 == 0) {
			n = (n /2) - 1;
		} else {
			n = n / 2;
		}
		
		// 배열 정렬해주기
		Arrays.sort(arr);
		
		
		System.out.println(arr[n]);

	}

}
