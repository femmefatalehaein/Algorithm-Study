package codingTest;
import java.io.*;
import java.util.*;


public class Main_5215_박해인 {
	
	public static int N;
	
	public static boolean np(int []  arr){
		
		 int n = arr.length;
		 int i = n-1;
		 while( i>0 && arr[i-1]>=arr[i]) i--;
		 
		 if(i == 0 ) return false;
		//자 이제 i를 찾았겠지.....
		// 이제 j를 찾자!!
		 int j = n-1;
		 
		 //여기 --j랑 --i 체크ㅠㅐ보기
		 while(arr[j]<=arr[i-1]) --j;
		 
		 //빠져나와따
		 swap(i-1,arr,j);
		 
		 int k = n-1;
		 //i가 k
		 while(i<k) {
			 swap(i++, arr, k--);
		 }
		 return true;
	}
		
	public static void swap(int i, int[] arr, int j) {
		
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	
	}
	

	public static void main(String [] args) throws IOException{
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken()); 
		int [] ans = new int[N];
		int [] tmp = new int[N];
		
		int tastes[] = new int[N];  
		int ingdts[] = new int[N];
		
		for( int i=0; i<N; i++) {
			
			st = new StringTokenizer(br.readLine());
			tastes[i] = Integer.parseInt(st.nextToken());
			ingdts[i] = Integer.parseInt(st.nextToken());
			
		}
		
	
		
		int sumIng;
		int sumTaste;
		int max = 0;
		
		int idx = N-1;
		
		while(idx>=0) {
			System.out.println(Arrays.toString(ans));
			tmp = Arrays.copyOf(ans, N); 
			do {
				sumIng = 0;
				sumTaste = 0;
				for(int i=0; i<N; i++) {
					if(tmp[i]==1) {
						sumIng += ingdts[i];
						sumTaste += tastes[i];
					}
				}
				if(sumIng<limit&&max<sumTaste) {
					max = sumTaste;
				}
				
			}while(np(tmp));
			
			ans[idx--] = 1;
		}
				
		System.out.println(max);

	}
	
}
