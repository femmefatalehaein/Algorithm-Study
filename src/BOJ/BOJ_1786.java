package BOJ;

import java.io.*;
import java.util.*;

import SWEA.Solution;
import javaPSdebugger.Debug;

// github
public class BOJ_1786 {
	public static boolean isValid;
/*
ABC ABCDAB ABCDABCDABDE
ABCDABD
 */	
	static void KMP(String parent, String pattern){
	    int[] table = makeTable(pattern);

	    int n1 = parent.length(), n2 = pattern.length();
	    int begin =0, matched=0;
	    int cnt=0;
	    StringBuilder sb = new StringBuilder();
	    while(begin <= n1-n2) {
	        // 만약 짚더미의 해당 글자가 바늘의 해당 글자가 같다면
	        if(matched < n2 && parent.charAt(begin+matched) == pattern.charAt(matched)) {
	            ++matched;
	            // 결과적으로 m글자가 모두 일치했다면 답에 추가한다.
	            if(matched == n2) {
	                sb.append((begin+1)+" ");
	                cnt++;
	            }
	        }else{
	            // 예외 : matched가 0인 경우에는 다음 칸에서부터 계속 
	            if(matched ==0) {
	                ++begin;
	            }else {
	                // begin을 옮겼다고 처음부터 다시 비교할 필요가 없다.
	                // 옮긴 후에도 table[matched-1]만큼은 항상 일치하기 때문이다.
	                begin += matched - table[matched-1];
	                matched = table[matched-1];
	            }
	        }
	    }
	    System.out.println(cnt);
	    System.out.println(sb.toString());
	}
	
	public static void main(String [] args) throws IOException{
		
		Debug.start(new BOJ_1786());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String t = br.readLine();
		String p = br.readLine();
		
		System.out.println(t);
		System.out.println(p);
		
		int tl = t.length();
		int pl = p.length();
	

		
		for(int i=0; i<tl-pl; i++) {
			for(int j=0; j<pl; j++) {
				isValid = true;
				if(t.charAt(i+j)!=p.charAt(j)) {				
					//System.out.println(t.charAt(i+j));
					//System.out.println(p.charAt(i+j));
					isValid = false;
					continue;
				}
			}
			if(isValid) {
	
				System.out.println(t.charAt(i));
				
			}
		
		}
		
		
	
		System.out.println('a'=='a');
		
		
	}
}
