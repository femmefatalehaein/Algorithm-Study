import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 백준_15686_치킨배달_곽승미 {
	static int N, M, result; // 도시 크기, 선택할 치킨집 수, 최소 치킨 거리 결과
	static int[][] map; // 도시 지도
	static ArrayList<int[]> home, chicken, checked; // 집 위치, 치킨집 위치, 선택된 치킨집 저장 리스트
	static boolean[] visited; //방문 체크 배열
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
	
		map = new int[N][N]; 
		home = new ArrayList<>(); 
		chicken = new ArrayList<>(); 
		checked = new ArrayList<>(); 
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { // 집이라면
					home.add(new int[] {i, j}); // 집 위치 저장
				}
				else if(map[i][j] == 2) // 치킨집이라면
					chicken.add(new int[] {i, j}); // 치킨집 위치 저장
			}
		}
		visited = new boolean[chicken.size()];
		result = Integer.MAX_VALUE; 
		back(0, 0);
		System.out.println(result);
	}
	
	// 백트래킹 함수
	static void back(int cnt, int idx) {
		// M개의 치킨집을 선택했을 때
		if(cnt == M) {
			int total = 0; // 전체 치킨 거리
			// 각 집에 대해 최소 치킨 거리를 계산
			for(int[] h : home) {
				int min = Integer.MAX_VALUE;
				for(int[] c : checked) { // 선택된 치킨집과의 거리 계산
					int d = Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]); // 치킨 거리 계산 (맨해튼 거리)
					min = Math.min(d, min); // 최소 거리 갱신
				}
				total += min; // 최소 치킨 거리를 전체 합에 더함
			}
			result = Math.min(result, total); // 현재까지의 최소 치킨 거리와 비교하여 갱신
			return;
		}
		
		// 치킨집 선택
		for(int i = idx; i < chicken.size(); i++) {
			if(!visited[i]) { // 아직 방문하지 않은 치킨집이라면
				checked.add(chicken.get(i)); // 선택된 치킨집 리스트에 추가
				visited[i] = true; // 방문 표시
				back(cnt+1, i+1); // 다음 치킨집 선택
				checked.remove(checked.size()-1); // 선택 취소
				visited[i] = false; // 방문 표시 해제
			}
		}
	}
}