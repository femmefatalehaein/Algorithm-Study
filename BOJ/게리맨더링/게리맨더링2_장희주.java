package 게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링2_장희주 {

	static int N;
	static int[] personCnt;
	static List<Integer>[] list;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		personCnt = new int[N + 1];
		list = new ArrayList[N + 1];
		visited=new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			personCnt[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		result = Integer.MAX_VALUE;
		subset(1, new boolean[N + 1], 0, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	// true : 1 false: 2
	static void subset(int cnt, boolean[] check, int sum1, int sum2) {

		if (cnt > N) {

			int cnt1 = 0;
			int cnt2 = 0;
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				// 1구역
				if (check[i] && !visited[i]) {

					bfs(check, i);
					cnt1++;
				}
				// 2구역
				if (!check[i] && !visited[i]) {

					bfs(check, i);
					cnt2++;
				}
			}
			if (cnt1 == 1 && cnt2 == 1) {
				result = Math.min(result, Math.abs(sum1 - sum2));
			}
			return;

		}
		// 1구역
		check[cnt] = true;
		subset(cnt + 1, check, sum1 + personCnt[cnt], sum2);
		// 2구역
		check[cnt] = false;
		subset(cnt + 1, check, sum1, sum2 + personCnt[cnt]);

	}

	// idx 도시에서 시작해서 같은 구역 다돌기
	static void bfs(boolean[] check, int idx) {
		// idx번 도시가 1번도시인지 2번도시인지? (첫도시가)
		boolean flag = check[idx];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(idx);
		while (!q.isEmpty()) {
			int num = q.poll();
			for (int n : list[num]) {
				if (!visited[n] && flag==check[n]) {
					visited[n] = true;
					q.offer(n);
				}

			}
		}
		
	}
}