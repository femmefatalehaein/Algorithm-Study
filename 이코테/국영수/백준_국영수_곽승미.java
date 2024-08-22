package 국영수;

/*
 * 백준 10825. 국영수
 * 정렬 문제
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class 백준_국영수_곽승미 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] students = new String[n][4]; // 학생별로 이름,국어,영어,수학 점수 저장하는 2차원 배열

        for (int i = 0; i < n; i++) {
            students[i] = br.readLine().split(" ");
        }

        Arrays.sort(students, (a, b) -> {
            int korean1 = Integer.parseInt(a[1]);
            int korean2 = Integer.parseInt(b[1]);
            int english1 = Integer.parseInt(a[2]);
            int english2 = Integer.parseInt(b[2]);
            int math1 = Integer.parseInt(a[3]);
            int math2 = Integer.parseInt(b[3]);

            // 국어 점수가 다르면 감소하는 순서로
            if (korean1 != korean2) {
                return korean2 - korean1;
            }
            // 국어 점수가 같으면 영어 점수가 증가하는 순서로
            if (english1 != english2) {
                return english1 - english2;
            }
            // 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
            if (math1 != math2) {
                return math2 - math1;
            }
            // 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로
            return a[0].compareTo(b[0]);
        });

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(students[i][0]).append('\n');
        }
        System.out.print(sb.toString());
    }
}