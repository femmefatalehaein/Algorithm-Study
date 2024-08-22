package codingTest;

import java.util.Scanner;

public class squareNumberTest {
	
	
	
	static long callCnt1, callCnt2;
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		long y = sc.nextLong();
		
		System.out.println(exp2(x,y));
		System.out.println("O(logN) : "+callCnt2);
		System.out.println(exp1(x,y));
		System.out.println("O(N) : "+callCnt1);
		
	}
	
	static long exp1(long x, long n) { //O(N)
		callCnt1++;
		if(n==1) return x;
		return x * exp1(x, n-1);
	}
	
	static long exp2(long x, long n) {
		
		long r = exp2(x,n/2);
		//Â¦¼ö¸é --- È¦¼ö¸é x¸¦ ´õ °öÇØ¶ó; ;
		return n%2==0? r*r:r*r*x;
		
	}
	
	
}
