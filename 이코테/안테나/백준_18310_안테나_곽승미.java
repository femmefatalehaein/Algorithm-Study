package 안테나;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 18310. 안테나
 * 정렬 문제
 */
public class 백준_18310_안테나_곽승미 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] house = new int[N];
		for(int n=0; n<N; n++) {
			house[n] = Integer.parseInt(st.nextToken()); 
		}
		Arrays.sort(house); // 집 정렬

	  	if(house.length %2 == 0) {//짝수일 때
	  		System.out.println(house[(int)house.length/2 -1]);
	  	}else {// 홀수 일때
	  		System.out.println(house[(int)house.length/2]);
	  	}

	}

}
