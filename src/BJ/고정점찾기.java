package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 고정점찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = -1;
        int start =0;
        int end =N-1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (mid == arr[mid]) {
                answer = mid;
                break;
            } else if (mid < arr[mid]) {
                end = mid - 1;
            } else start = mid + 1;
        }

        System.out.println(answer);
    }
}
