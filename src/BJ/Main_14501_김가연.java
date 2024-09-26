package D0925;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_김가연 {
	
	static int arr [][];
	static int max;
	
	static void next(int cnt, int idx, int n) {
		
		if (idx > n) {
			return;
		}
		
		for (int i = 0 ; i < n - idx ; i ++) {
			
	        int ccnt = 0;
	        int cidx = 0;
	        
	        cidx = idx + (arr[idx][0] + i);

	        if (cidx < n) {
	        	if (arr[cidx][0] + cidx <= n) {
	        		
	        		ccnt = cnt + arr[cidx][1];
		            
		            if (ccnt > max) {
		                max = ccnt;
		            }
		            
		            next(ccnt, cidx, n);

	        	}
	            
	        }
	    }
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	    int n = Integer.parseInt(bf.readLine());
	
	    arr = new int [n][2];
	    
	    max = Integer.MIN_VALUE;
	
	    for (int i = 0 ; i < n ; i++) {
	        StringTokenizer st = new StringTokenizer(bf.readLine());
	
	        arr[i][0] = Integer.parseInt(st.nextToken());
	        arr[i][1] = Integer.parseInt(st.nextToken());
	        
	        if ((i + arr[i][0] <= n) && (arr[i][1] > max)) {
	        	max = arr[i][1];
	        }
 	    }
	    
	    for (int i = 0 ; i < n ; i ++) {
	    	for (int j = 0 ; j < n - i ; j ++) {
	    		
	    		int cnt = 0;
		        int idx = i + arr[i][0] + j;
		        if (idx < n) {
		        	if (arr[idx][0] + idx <= n) {
		        		
		        		cnt = arr[i][1] + arr[idx][1];
			            
			            if (cnt > max) {
			                max = cnt;
			            }
			            
			            next(cnt, idx, n);
			            
		        	}
		            
		        }
	    	}
	    }
	    
	    if (max == Integer.MIN_VALUE) {
	    	max = 0;
	    }
	    
	    System.out.println(max);
	}

}
