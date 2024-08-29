package 고정점찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고정점찾기_장희주 {

	static int n;
	static int[] nums;
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		//수열 n개
		n=Integer.parseInt(br.readLine());
		//모든 원소
		nums=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		//결과값
		//인덱스값과 값이 없을때 -1 반환
		int fixed=-1;
		//시작점
		int start=0;
		//끝점
		int end=nums.length;
		
		while(start<=end) {
			//중간값
			int mid=(start+end)/2;
			//왼쪽
			if(mid<nums[mid]) {
					
					end=mid-1;
			//오른쪽
			}else if(mid>nums[mid]) {
				start=mid+1;
				continue;
			//인덱스값과 값이 같을때
			}else if(mid==nums[mid]){
				//값 넣어주고 끝내기
				fixed=mid;
				break;
			}
			
		}
		//-1일때=> 값이 없을때
		if(fixed<0) {
			System.out.println(-1);
		}
		//값이 있을경우
		else {
		System.out.println(fixed);
		}
	}
	
	
}
