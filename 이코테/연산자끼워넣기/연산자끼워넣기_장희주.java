package 연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 연산자끼워넣기_장희주 {
	
	//수의 개수
	static int n;
	//수열들
    static int[] data;
    //덧셈, 뺄섬, 곱하기, 나누기의 갯수
    static int add, sub, mul, div;
    //최소값
    static int minValue = Integer.MAX_VALUE;
    //최대값
    static int maxValue = Integer.MIN_VALUE;
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		n=Integer.parseInt(br.readLine());
		data=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			data[i]=Integer.parseInt(st.nextToken());
		}
		//갯수 입력
		st=new StringTokenizer(br.readLine());
		add=Integer.parseInt(st.nextToken());
		sub=Integer.parseInt(st.nextToken());
		mul=Integer.parseInt(st.nextToken());
		div=Integer.parseInt(st.nextToken());
		//함수 실행
		dfs(1,data[0]);
		System.out.println(minValue);
	    System.out.println(maxValue);
		
	}
	static void dfs(int i, int now) {
		//갯수가 n개가 되었을때
		if(i==n) {
			 minValue = Math.min(minValue, now);
	         maxValue = Math.max(maxValue, now);
		}else {
			//더하기 
			 if (add > 0) {
	                add--;
	                dfs(i + 1, now + data[i]);
	                add++;
	            }
			//빼기
            if (sub > 0) {
                sub--;
                dfs(i + 1, now - data[i]);
                sub++;
            }
            //곱하기
            if (mul > 0) {
                mul--;
                dfs(i + 1, now * data[i]);
                mul++;
            }
            //나누기
            if (div > 0) {
                div--;
                dfs(i + 1, now / data[i]);
                div++;
            }
		}
	}

}
