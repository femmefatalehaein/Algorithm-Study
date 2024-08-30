package 정배특;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_0827_박해인 {
	
	
	
    public static int N, x;
    public static int[] arr;
    
    
    public static int leftIndex(int start, int end){
        while(start < end){
            int mid = (start+end)/2;
            if (arr[mid] >= x) end = mid;
            else start = mid+1;
        }
        return end;
    }

    public static int rightIndex(int start, int end){
        while (start < end){
            int mid = (start+end)/2;
            if(arr[mid] > x) end = mid;
            else start = mid+1;
        }
        return end;
    }
    

    public static void main(String args[]) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        
        //여기까지 입력
        
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = leftIndex(0, N);
        int right = rightIndex(0, N);

        System.out.println(right-left == 0 ? -1 : right-left);
    }


}