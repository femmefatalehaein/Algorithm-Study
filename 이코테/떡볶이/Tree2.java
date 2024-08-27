package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Tree2 {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int [] tree = new int[n];
		
		long all = 0;
		StringTokenizer tr = new StringTokenizer(bf.readLine());
		for (int i = 0 ; i < n ; i++) {
			tree[i] = Integer.parseInt(tr.nextToken());
			all += tree[i];
		}
		
		Arrays.sort(tree);
		if (all <= m) {
			System.out.println(0);
		} else {
			int end = tree[n-1];
			int start = 0;
			int mid = 0;
			
			 while (start <= end) { // start가 end를 초과하기 전 까지 이분탐색
		            mid = (start + end) / 2;
		            long sum = 0;
		            for (int i = 0; i < n; i++) {
		                if (tree[i] > mid) {
		                    sum += tree[i] - mid;
		                }
		            }
		            if (sum >= m) { // 합이 m을 넘어가면
		                start = mid + 1; // start mid로 재설정
		            } else if (sum < m) { // 합이 m보다 작아으면
		                end = mid - 1; // end mid로 재설정
		            }
		        }
		        System.out.println(end);

		}

	}

}

