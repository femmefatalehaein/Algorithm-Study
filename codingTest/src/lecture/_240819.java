package lecture;

import java.util.Arrays;
import java.util.Scanner;

public class _240819 {
	
	static int N,R;
	static int[] input, numbers;
	
	static void perm(int cnt, int flag) {
		
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		
		/*유도 파트*/
		for(int  i =0; i<N; i++) {
			//0이 아니고 1이다.
			if( (flag & 1<<i) != 0 ) continue;
			numbers[cnt] = input[i];
			//다음 수 뽑으로 가 
			flag |= 1<<i;
			perm(cnt+1, flag | 1<<i );
			//원복하기
			//flag &= ~(1<<i);

		}
	}
	

	public static void main(String [] args) {
	
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		input = new int[N];
		numbers = new int[R];
		
		for(int i=0; i<N; i++) {
			input[i] = sc.nextInt();
		}
	
		perm(0, 0);
		

		
	}
}
