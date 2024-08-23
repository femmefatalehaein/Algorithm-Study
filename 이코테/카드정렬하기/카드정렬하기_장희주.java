package 카드정렬하기;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 카드정렬하기_장희주 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// 숫자 갯수
		int n = sc.nextInt();
		// 최소횟수
		int sum = 0;
		// 우선순위 큐
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		//값 우선순위 큐에 저장
		for (int i = 0; i < n; i++) {
			pq.add(sc.nextInt());
		}
		//사이즈가 2이상 일때
		while (pq.size()>1) {
			
			//값 두개 빼기
			int num1 = pq.poll();
			int num2 = pq.poll();
			//더한값 넣어주기
			pq.add(num1+num2);
			//총합에 더해주기
			sum+=num1+num2;
		}

		// 결과출력
		System.out.println(sum);
	}

}
