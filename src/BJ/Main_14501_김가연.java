import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_김가연 {

    static int [][] arr;
    static int max;

	static void next(int cnt, int idx, int n) {
	    
	    int ccnt = 0 ;
	    
	    for (int i = 1 ; i < n - idx ; i++) {
	        if (idx + arr[idx + i][0] < n) {
	        	if (arr[idx + i][0] + idx + i <= n) {
	        		ccnt = cnt + arr[idx + i][1];
		            
		            idx = idx + arr[idx + i][0];
		            
		            if (ccnt > max) {
		                max = ccnt;
		            }
		            
		            next(ccnt, idx, n);
	        	}
	        }
	    }
	}
	 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
	    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	    int n = Integer.parseInt(bf.readLine());
	
	    arr = new int [n][2];
	
	    for (int i = 0 ; i < n ; i++) {
	        StringTokenizer st = new StringTokenizer(bf.readLine());
	
	        arr[i][0] = Integer.parseInt(st.nextToken());
	        arr[i][1] = Integer.parseInt(st.nextToken());
	    }
	
	    int [] save = new int [n];
	    max = Integer.MIN_VALUE;
	
	    for (int i = 0 ; i < n ; i ++) {
	        int cnt = 0;
	        int idx = 0;
	        if (i + arr[i][0] < n) {
	        	if (arr[i + arr[i][0]][0] + i + arr[i][0] <= n) {
	        		cnt = arr[i][1] + arr[i + arr[i][0]][1];
		            
		            idx = i + arr[i][0];
		            
		            if (cnt > max) {
		                max = cnt;
		            }
		            
		            System.out.println(cnt);
		            System.out.println(idx);
		            
		            next(cnt, idx, n);
	        	}
	            
	        }
	    }
	    
	    System.out.println(max);
	}
}
