import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 백준 14567. 선수과목
 * 위상 정렬
 */
public class 백준_14657_선수과목_곽승미 {
    
    static int N; // 과목의 수
    static int M; // 선수 조건의 수
    static int[] arr; // 학기 수 저장 배열
    static List<Integer>[] list; // 필수 과목을 저장 - 인접 리스트

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        list = new ArrayList[N + 1]; 
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>(); // 각 과목에 대해 빈 리스트 생성
        }

        arr = new int[N + 1]; // 각 과목마다의 필요한 학기 수를 저장할 배열

        // 필수 과목 입력 받기
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken()); // 선행 과목
            int cur = Integer.parseInt(st.nextToken()); // 현재 과목
            list[cur].add(before); // 현재 과목의 선행 과목 추가
        }

        arr[1] = 1; // 첫 번째 과목은 첫 학기에 들을 수 있음

        // 필요 학기 수 계산
        for (int i = 2; i <= N; i++) {
            if (list[i].isEmpty()) { // 필수 과목이 없다면
                arr[i] = 1; // 첫 학기에 들을 수 있음
            } else {
                int max = 0; //최대 학기 수 초기화
                for (int cnt : list[i]) { // 각 선행 과목에 대해
                    if (max <= arr[cnt]) {
                        max = arr[cnt] + 1; // 선행 과목의 학기 수 +1
                    }
                }
                arr[i] = max; // 계산된 학기 수 저장
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb.toString()); // 결과 출력
    }
}