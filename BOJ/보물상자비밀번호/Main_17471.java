import java.util.*;
import java.io.*;

public class Main_17471 {
	static int N, K; // N : 수의 개수, K번째로 큰 수 찾기
	static String hex; // 16진수 문자열
	static int decimal, shift;
//	static String[] hex; // 16진수 문자열을 쪼갠 것을 담을 배열 -> 굳이 안해도 됨

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // StringTokenizer은 기본적으로 공백으로 구분된 토큰을 읽는다.
		// 공백이 없는 문자열을 처리할 땐 필요X
		// StringTokenizer 대신 BufferedReader로 바로 문자열을 읽고 split("")을 사용하면 된다!!

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 16진수 자리수
			K = Integer.parseInt(st.nextToken()); // K번째 큰 수

			// 16진수 문자열 입력 받기(공백 없이)
			hex = br.readLine();

			// 16진수 문자열을 한 글자씩 처리
			for (int i = 0; i < (N/4); i++) {
				// 16진수 문자열 -> 10진수 정수로 변환(shift 연산은 정수형 데이터에만 적용 가능)
				decimal = Integer.parseInt(Character.toString(hex.charAt(i)), 16);

				// 오른쪽으로 한 칸 비트 shift
				shift = decimal >> 1;

				// 결과를 16진수로 출력
				System.out.print(Integer.toHexString(shift));
				System.out.println("==========");
			}

		}

	}

}
