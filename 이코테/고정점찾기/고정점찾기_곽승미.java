package 고정점찾기;
/*
 * 이코테. 고정점찾기
 * 이진탐색(Binary Search)
 */
import java.util.*;

import java.io.*;

public class 고정점찾기_곽승미 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이진 탐색을 위한 변수 설정
        int start = 0;
        int end = N - 1;
        int res = -1; // 고정점을 찾지 못했을 때 값

        // 이진 탐색
        while (start <= end) {
            int mid = (start + end) / 2;

            // 고정점을 찾은 경우 중간점 반환
            if (arr[mid] == mid) {
            	res = mid;
                break;
            }
            
            // 중간점의 값보다 작은 경우 왼쪽 확인
            else if (arr[mid] > mid) {
                end = mid - 1;
            }
            // 중간점의 값보다 큰 경우 오른쪽 확인
            else {
                start = mid + 1;
            }
        }

        System.out.println(res);
    }
}
