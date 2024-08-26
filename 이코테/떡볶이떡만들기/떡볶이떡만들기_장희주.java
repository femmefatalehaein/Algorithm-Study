package 이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 떡볶이떡만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		//떡의 갯수
		int n=Integer.parseInt(st.nextToken());
		//요청한 떡의 길이
		int m=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		//떡을 배열에 저장
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		//오름차순정렬하기
		Arrays.sort(arr);
		int H=0;
		//시작값
		int start=0;
		//제일 큰 값
		int end=arr[arr.length-1];
		
		while(start<=end) {
			//자른떡 총 길이 합
			//오버픞로우나지않도록 long으로 설정잘하기..!!
			long total=0;
			//중간값
			int mid=(start+end)/2;
			for(int i=0;i<n;i++) {
				//중간값보다 크면
				//잘린부분만 더함
				if(arr[i]>mid) {
					total+=arr[i]-mid;
				}
			}
			//더한값이 m 보다 작으면
			//절단기 높이를 낮춰야함
			if(total<m) {
				end=mid-1;
			//더한값이 m보다 크거나 작다면
			//더한값 H반환,더큰 절단기 높이 시도
			}else {
				H=mid;
				start=mid+1;
			}
		}
		System.out.println(H);
		
	}

}
