package lecture;

import java.util.Arrays;
import java.util.Scanner;

public class NextPerm {
	
	static int N,R;
	static int[] input, numbers;
	
	
	//boolean : true(다음 순열 존재) ,false(다음 순열 없음)
	static boolean np(int[] p) {
		
			int N = p.length;
			//맨 마지막 i임
			int i = N-1;
			
			//내 앞에 있는 녀석이 나보다 같거나 크다? 
			while(i>0 && p[i-1]>=p[i]) --i;
			
			//이제 앞에는 없다.
			if(i==0) return false;
			
			//step 2 ) 꼭대기 앞 교환위치에 교환할 값(i-1 위치 값보다 큰 값중 가장 작은 값) 자리 찾기
			// i 찾았으면 -> 뒤에서부터 j를 찾아가야지.
			int j = N-1;
			while(p[i-1]>=p[j]) --j;
			
			//step 3) 두 위치의 수 교환
			swap(p, i-1, j);
			
			//step 4) 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦.
			int k = N-1;
			while(i<k) {
				swap(p, i++,k--);
			}
			
			return true;
	}
	
	static void swap(int [] p, int i, int j) {
		
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
		
	}
	

	public static void main(String [] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		//R = sc.nextInt();
		input = new int[N];
		//numbers = new int[R];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
	
		//1. 제읾 먼저 해야하는 거 // 오름차순 정렬을 햐기
		long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		Arrays.sort(input); //오름차순 정렬\
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
	        
		long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("시간차이(m) : "+secDiffTime);

		
	}
}
