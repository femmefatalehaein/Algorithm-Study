package 이코테;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 떡볶이떡만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());    // 떡 개수
        int M = Integer.parseInt(br.readLine());    // 요청한 떡 길이

        int[] ricecake = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ricecake[i] = Integer.parseInt(st.nextToken());
        }   // 값 초기화

        Arrays.sort(ricecake);  // 이진은 반드시 정렬

        // 절단기 최대 높이? -> 절단기 일단 잘라보고 체크
        // 떡 M과 일치 -> 그때 높이 반환
        // 작음 -> mid 키우기
        // 큼 -> 여긴 어떻게 해볼까?
        int max = 0;    // 절단기 길이 최대값
        int start = 0;  // 시작 인덱스
        int end = ricecake[ricecake.length - 1];    // 가장 긴 떡 길이
        while (start <= end) {
            int mid = (start + end) / 2;    // 떡 길이 중간값
            int sum = 0; // 자른 떡 길이 합

            // 떡 잘라서 더해보기
            for (int r : ricecake) {
                if (r > mid) {  // 떡이 더 길떄만 계산
                    sum += r - mid; // 떡 잘라보고 총 길이 구해보기
                }
            }
            if (sum == M) { // 딱 좋은 길이
                max = mid;
                break;
            } else if (sum < M) {
                end = mid - 1;  // 너무 기니까 절단기 길이 줄여보기
            } else {
                start = mid + 1;
            }
        }

        System.out.println(max);

    }

}
