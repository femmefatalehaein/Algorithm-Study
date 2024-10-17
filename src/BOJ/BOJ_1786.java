package BOJ;

import java.io.*;
import java.util.*;

import SWEA.Solution;
import javaPSdebugger.Debug;

public class BOJ_1786 {
	public static boolean isValid;
/*
ABC ABCDAB ABCDABCDABDE
ABCDABD
 */	
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
