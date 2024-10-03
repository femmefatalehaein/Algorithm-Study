import java.io.*;
import java.util.*;
// down은 항상 up보다 횟수가 하나 작다. -> 이게 왜 중요?
/*
 * 이로써 up-down 의 차이 값보다 작은 나머지가 존재하면 다음날 up 때 올라가야하는 경우가 발생한다.
 * 결과적으로 down 을 뺌으로서 up 과 down 의 차이를 나눈 몫은 최소한의 일(日) 수가 된다.
 */
public class Main_2869 {
	static int A, B, V;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		// 입력 끝

		int day = (V - B) / (A - B); // ?? 이해 안됨
		if ((V - B) % ( A - B) != 0) {
			day ++;
		}
		System.out.println(day);
	}

}
