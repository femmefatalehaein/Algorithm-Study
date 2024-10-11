import java.io.*;
import java.util.*;
 
public class Main {
 
    public static int max = Integer.MIN_VALUE;    // 최댓값 
    public static int min = Integer.MAX_VALUE;    // 최솟값 
    public static int[] operator = new int[4];    // 연산자 개수 
    public static int[] number;                   // 숫자 배열
    public static int N;                          // 숫자 개수 
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(br.readLine());
        number = new int[N];
 
        // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
 
        // 연산자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
 
        // DFS 탐색 시작
        dfs(number[0], 1);
 
        // 결과 출력
        System.out.println(max);
        System.out.println(min);
 
    }
 
    // DFS 탐색
    public static void dfs(int num, int idx) {
        // 모든 숫자를 다 사용한 경우, 최댓값과 최솟값 갱신
        if (idx == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
 
        // 각 연산자를 사용하여 DFS 재귀 호출
        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) { // 사용할 연산자가 있는 경우
                operator[i]--; // 연산자 사용
                
                // if문을 이용한 각 연산 처리
                if (i == 0) { // 더하기
                    dfs(num + number[idx], idx + 1);
                } else if (i == 1) { // 빼기
                    dfs(num - number[idx], idx + 1);
                } else if (i == 2) { // 곱하기
                    dfs(num * number[idx], idx + 1);
                } else if (i == 3) { // 나누기
                    dfs(num / number[idx], idx + 1);
                }
                
                // 연산자 복구
                operator[i]++;
            }
        }
    }
}
