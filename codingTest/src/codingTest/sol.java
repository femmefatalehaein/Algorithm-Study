package codingTest;


import java.io.*;
import java.util.*;

public class sol {

public static int input [][];

public static void main(String []  args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int T = Integer.parseInt(br.readLine());
    
    for(int t=0; t<T; t++) {
        
        int N = Integer.parseInt(br.readLine());
        input = new int[N][N];
        StringTokenizer st;
        
        for(int n=0; n<N; n++) {
        
            st = new StringTokenizer(br.readLine());
            
            for(int c =0; c<N; c++) {
            	
                int x = Integer.parseInt(st.nextToken());
                input[n][c] = x;
            }
            
            
        }
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
}