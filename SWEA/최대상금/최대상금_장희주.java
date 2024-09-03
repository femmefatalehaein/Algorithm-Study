package 최대상금;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대상금_장희주 {
	
	static int maxValue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		T=Integer.parseInt(br.readLine());
		for(int test_case=1;test_case<=T;test_case++) {
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			String num=st.nextToken();
			int n=Integer.parseInt(st.nextToken());
			maxValue=0;
			char[] ch=new char[num.length()];
			for(int i=0;i<num.length();i++) {
				ch[i]=num.charAt(i);
			}
			combination(0,0,n,ch);
			
			
			//결과값
			System.out.println("#"+test_case+" "+maxValue);	
			
		}
	}
	static void combination(int start, int cnt,int n, char[] ch) {
		
		if(cnt==n) {
			maxValue=Math.max(maxValue, Integer.parseInt(String.valueOf(ch)));
			return;
		}
		if(start>=ch.length - 1) {
			return;
		}
		//선택안했을때
		combination(start+1, cnt, n, ch);
		
		//선택했을때
		for(int i=start+1;i<ch.length;i++) {
			swap(start,i,ch);
			combination(start, cnt+1, n, ch);
			swap(start,i,ch);
		}
	}
	//값을 교환해주는 함수
	static void swap(int idx, int idx2, char[] ch) {
		char temp=ch[idx];
		ch[idx]=ch[idx2];
		ch[idx2]=temp;
	}
	
		
		
		
	

}
