package 고정점찾기;

import java.io.*;
import java.util.*;

public class Main_0828_박해인 {
	
	
	static int [] arr;
	
	static int binarySearch(int low, int high) {
		
		int mid;
		//low high가 역전되는 순간을 찾아라
		if(low<=high) {
			mid = (low+high)/2;
			//key는 찾고하 하는 것
			if(mid == arr[mid]) {
				return mid;
			}else if(mid < arr[mid]) {
				
				return binarySearch(low,mid-1);
			}else if(mid > arr[mid]) {
				
				return binarySearch(mid+1,high);
			}
		}
		
		//탐색 실패했을 때
		return -1;
	
	}
	
	
	public static void main(String []  args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
			
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
	
		System.out.println(binarySearch(0,N));
		
		
	}

}
