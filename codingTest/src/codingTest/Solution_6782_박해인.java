package codingTest;

import java.io.*;
import java.util.*;

public class Solution_6782_박해인 {
	
	static int cnt;
	
/*
4
2
3
4
99
*/

	static boolean isInteger(double N) {
		return N == (long)N;
	}

	static void root (long N) {
		
		if(N==2) {
			return;
		}
		//1. 제곱근을 할 수 있을 때 
		if(isInteger(Math.sqrt(N))) {
			//System.out.println("제곱근을 할 수 있을때"+N);
			cnt++;
			N = (long)Math.sqrt(N);
			root(N);
		//2. 제곱근을 할 수 없을때->5라고 생각해보자:제곱근을 만들어야해 !!-> 가장 가까운 제곱근이 되는법?
		}else {
			
			//System.out.println("제곱근을 할 수 없을때"+N);
			long a = (long)(Math.sqrt(N)+1);
			long b = a*a;
			//b가 될때 가장 가까운 제곱근이 되는거야...
			cnt += (int)(b-N);
			//그리고 N에 b 넣어줘,
			N = b;
			root(N);
			
		}

	}
	
	

	
	public static void main(String [] args) throws IOException {
		
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int t=0; t<T; t++) {
			
			long N = Long.parseLong(sc.nextLine());
			cnt = 0;
			root(N);
			System.out.println("#"+(t+1)+" "+cnt);
		}
		
		
		
	}
	
	
	
}
