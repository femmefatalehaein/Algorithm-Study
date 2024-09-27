package 게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 게리맨더링_장희주 {

	static int N;
	static int[] sections;
	static List<Integer>[] adjSections;
	static int min;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 구역의 개수
		N = Integer.parseInt(br.readLine());
		// 구역의 인구수
		sections = new int[N + 1];
		//인접구역정보
		adjSections = new ArrayList[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			sections[i] = Integer.parseInt(st.nextToken()); // 인구 수
			adjSections[i] = new ArrayList<>();
		}
		// 인접한 구역의 갯수 + 인접한 구역의 번호
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				adjSections[i].add(Integer.parseInt(st.nextToken()));
			}

		}
		// 최소 차이 초기화
		min = Integer.MAX_VALUE;
		//방문처리
		visited=new boolean[N + 1];
		//부분집합으로 1구역, 2구역나눠주기
		subset(1, new boolean[N + 1], 0, 0);
		
		// 결과출력
		System.out.println(min<Integer.MAX_VALUE?min:-1);

	}

	static void dfs(int now,boolean check[]) {
		//지금 위치 방문 처리
		visited[now]=true;
		//다음 위치로 방문하기
		for(int next : adjSections[now]) {
			//방문하지않았다면
			if(!visited[next]) {
				//같은 구역이라면
				if(check[next]==check[now]) {
					dfs(next,check);
				}
			}
		}
	}
	
	// 무엇을 부분집합으로 선택하는가 ?
	// 방법 1) 구역 1을 어떤걸 선택했는지'만' 저장해주고, 나머지는 전부 구역 2로 치기
	// 방법 2) 선택 했을때는 1에 담아주고, 선택 안했을때는 2에 담아주기->이방식으로 품

	static boolean checking(boolean[] check) {
		//false로 초기화
		Arrays.fill(visited, false);
		
		// 1번도시 한개'만' 찍어서 거길 dfs 돌아봄
		for (int i = 1; i < N + 1; i++) {
			if (check[i]) {
				dfs(i,check);
				break;
			}
		}

		// 2번도시 한개'만' 찍어서 거길 dfs 돌아봄
		for (int i = 1; i < N + 1; i++) {
			if (!check[i]) {
				dfs(i,check);
				break;
			}
		}
		// 아직 방문 안한 곳이 있다? -> false
		for(int i=1;i<=N;i++) {
			if(!visited[i]) return false;
		}
		// 그렇지 않으면 true 반환
		return true;
	}

	// check란 ? true: 1구역임, false : 2구역임
	// sum1 : 바로 직전까지 도시들 중에 1번 구역에 속하는 인구수 합
	// sum2 : " 2번에 속하는 합
	static void subset(int cnt, boolean[] check, int sum1, int sum2) {
		if (cnt > N) {
			// DFS를 통해서 올바르게 연결됐는지를 체크합니다.
			// 만약 올바르다면 SUM1 SUM2로 차 최솟값 갱신합니다.
			if (checking(check)) {
				min = Math.min(min, Math.abs(sum1 - sum2));
			}

			return;
		}

		// 부분집합 구하면서 1구역과 2구역의 인구수 합을 그때그때에 맞게 넘겨줍니다
		// 선택안했을때
		//2구역 합더하기 
		subset(cnt + 1, check, sum1, sum2 + sections[cnt]);
		// 선택했을때
		check[cnt] = true;
		//1구역합 더하기
		subset(cnt + 1, check, sum1 + sections[cnt], sum2);
		check[cnt] = false;
	}

}
