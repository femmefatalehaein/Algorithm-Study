package codingTest;
import java.io.*;
import java.util.*;

public class Solution_4796_박해인 {
	


	public static int [] input;
	

	public static void main(String [] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());	
			
			int N = Integer.parseInt(st.nextToken());
			
			input = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int n = 0 ; n < N; n++) {
				int x = Integer.parseInt(st.nextToken());
				input[n] = x;
				//input에 넣었어 !!	
			}
	
			ArrayList<Integer> idxs = new ArrayList<Integer>();
			for(int i=1; i<=N-2; i++) {
				
				if(input[i-1]<input[i]&&input[i]>input[i+1]) {
					//인덱스를 넣었어.
					idxs.add(i);
	
				}	
			}
			
		
			
			
			
			int cnt = 0;

			
			for(int i=0; i<idxs.size(); i++) {
				
				int left = 0;
				int right = 0;
				
				int index = idxs.get(i);
				
				//왼쪽 방향으로 이동해
				for(int j=index; j>0; j--) {
					
					if(input[j-1]<input[j])left ++;
					else {
						break;
					}
				}
				//오른쪽 방향으로 이동해
				for(int k=index; k<N-1; k++) {

					if(input[k]>input[k+1])right ++;
					else {
						break;
					}
					
				}
				cnt+=left*right;
				
				if(cnt==0) cnt = 1;
			}

			System.out.println("#"+(t+1)+" "+cnt);
	
		}
	}
}
