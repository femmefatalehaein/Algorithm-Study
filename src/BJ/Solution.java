package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int N, x;
    public static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 개수 카운트 하면 터져서 끝인덱스 첫 인덱스 찾아서  구해줌
        int first = firstIdx(0, N);
        int last = lastIdx(0, N);

        System.out.println(first-last == 0 ? -1 : first-last);
    }

    public static int firstIdx(int start, int end){
        while(start < end){
            int mid = (start+end)/2;
            if (arr[mid] >= x) end = mid;
            else start = mid+1;
        }
        return end;
    }

    public static int lastIdx(int start, int end){
        while (start < end){
            int mid = (start+end)/2;
            if(arr[mid] > x) end = mid;
            else start = mid+1;
        }
        return end;
    }
}