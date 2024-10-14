import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 백준 2056. 작업
 * 위상 정렬
 */
public class 백준_2056_작업_곽승미 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 작업의 수
		ArrayList<ArrayList<Integer>> work = new ArrayList<>(); // 선행 작업들을 저장할 2차원 리스트

		for (int i = 0; i <= N; i++) {
			work.add(new ArrayList<>());
		}

		int[] cnt = new int[N + 1]; // 선행 작업 개수 저장 배열
		int[] time = new int[N + 1]; // 작업을 완료할때 필요한 시간 저장 배열

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken()); // 작업의 소요 시간

			int num = Integer.parseInt(st.nextToken()); // 작업의 선행 작업 개수
		
			for (int j = 0; j < num; j++) {
				int pre = Integer.parseInt(st.nextToken()); // 선행 작업 번호
				work.get(pre).add(i); // 선행 작업 후 i 작업을 수행할 수 있기 때문에 연결 관계 저장

				cnt[i]++; // i번 작업의 선행 작업 개수 증가
			}
		}

		// 결과 출력
		System.out.println(cal(N, work, cnt, time));
	}

	// 위상 정렬 - 작업 최소 시간 계산
	public static int cal(int N, ArrayList<ArrayList<Integer>> arr, int[] cnt, int[] time) {
		Queue<Integer> q = new LinkedList<>();

		int[] result = new int[N + 1]; // 작업 완료 시 걸리는 시간을 저장할 배열
		
		for (int i = 1; i <= N; i++) {
			result[i] = time[i]; // 초기값은 해당 작업의 수행 시간
			
			// 선행 작업이 없는 경우 큐에 삽입
			if (cnt[i] == 0) {
				q.offer(i);
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll(); // 현재 작업 큐에서 꺼내기

			// 현재 작업을 선행 작업으로 하는 다음 작업 확인
			for (int next : arr.get(cur)) {
				cnt[next]--; // 선행 작업 개수 감소

				// 더 오래 걸리는 시간 갱신
				result[next] = Math.max(result[next], result[cur] + time[next]);
				
				// 선행 작업이 모두 완료된 경우 큐에 삽입
				if (cnt[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		// 모든 작업을 완료하는 데 걸리는 최대 시간 계산
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, result[i]);
		}
		return ans;
	}
}