import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_1107_리모컨_곽승미 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10]; // 고장난 버튼을 표시할 배열

        if (m != 0) {
            String[] str = br.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                int button = Integer.parseInt(str[i]);
                broken[button] = true; // 고장난 버튼을 true로 표시
            }
        }

        int min = Math.abs(100 - n); // 현재 채널 100에서 목표 채널까지 이동하는 횟수

        for (int i = 0; i <= 999999; i++) {
            String num = Integer.toString(i);
            boolean check = true;

            for (int j = 0; j < num.length(); j++) {
                int d = num.charAt(j) - '0'; // 각 자릿수를 구함
                if (broken[d]) { // 고장난 버튼을 포함하는 경우
                	check = false;
                    break;
                }
            }

            if (check) {
                min = Math.min(min, Math.abs(n - i) + num.length()); // 숫자 버튼 누르는 횟수 + 이동 횟수
            }
        }

        System.out.println(min);
    }
}