package 정렬된배열에서특정수의개수구하기;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정렬된배열에서특정수의개수구하기_장희주 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		//n개의 원소
		int n=Integer.parseInt(st.nextToken());
		//등장하는 원소
		int x=Integer.parseInt(st.nextToken());
		//숫자 배열에 저장
		st=new StringTokenizer(br.readLine());
		int[] num=new int[n];
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		//결과값
		int left=binary_search(0,n-1,num,x,false);
		int right=binary_search(0,n-1,num,x,true);
		if(left==-1) {
			System.out.println(-1);
		}else {
			System.out.println(right-left+1);
		}
		//중간값
		
	}
	static int binary_search(int start, int end, int[]arr, int x,boolean greater) {
		//시작점과 끝점이 같을때
		if(start>=end) {
			if(start < 0 || end >= arr.length || arr[start] != x)
				return -1;
			else
				return start;
		}
		//중간값 2개일경우도 대비해 +1해주기!
		//안그러면 스택오버플로우남...
		int mid=(end + start)/2;
		//왼쪽
		if(arr[mid]>x) {
			end=mid-1;
			
		//오른쪽
		}else if(arr[mid]<x) {
			start=mid+1;
		
		//값이 같을때
		}else {
			//오른쪽일때
			if(greater) {
				//시작인덱스에 중간값넣기
				if(mid+1>end || arr[mid]!=arr[mid+1]) return mid;
				else start = mid + 1;
			//왼쪽일때
			}else {
				//끝 인덱스에 중간값 삽입
				if(mid-1 < start || arr[mid]!=arr[mid-1]) return mid;
				else end = mid - 1;
			}
		}
		
		return binary_search(start,end,arr,x,greater);
	}


}
