package 떡볶이떡만들기;

/*
 * 이코테. 떡볶이떡 만들기
 * 이진 탐색 문제
 * 절단기의 높이의 범위를 보고 이진 탐색을 떠올려야함(1부터 10억 사이)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 이진탐색_떡볶이떡만들기_곽승미 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 떡의 개수(N)와 요청한 떡의 길이(M)을 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //떡의 개수
        int m = Integer.parseInt(st.nextToken()); // 떡의 길이

        // 떡의 높이 입력
        int[] array = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            if (array[i] > max) {
                max = array[i]; // 가장 높은 떡의 길이 저장
            }
        }
        
        int start = 0;
        int end = max;
        int result = 0;

        // 이진 탐색 수행
        //start가 end보다 작거나 같을 때까지 반복적으로 중간점 계산
        while (start <= end) {
            int total = 0;
            int mid = (start + end) / 2; 
            
            
            // 잘랐을 때의 떡볶이 떡 양 계산
            for (int i = 0; i < n; i++) {
                if (array[i] > mid) {
                    total += array[i] - mid;
                }
            }

            // 자른 떡의 길이가 부족하다면, end를 줄임
            if (total < m) {
                end = mid - 1;
            } 
            // 떡의 길이가 충분하다면, 덜 자르기 위해 start를 늘림
            else {
                result = mid; // 가능한 최댓값 구하기(떡을 자를 때 얻을 수 있는 최대 높이)
                //System.out.println(result);
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}