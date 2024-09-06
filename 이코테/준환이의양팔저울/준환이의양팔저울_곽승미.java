package 준환이의양팔저울;
/*
 * SWEA 3234. 준환이의 양팔 저울
 * 순열, 조합 문제
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 준환이의양팔저울_곽승미 {

	static int N,result,w[],num[];
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/s_3234_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			w = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			num = new int[N];
			result = 0;
			perm(0);
			
			System.out.println("#"+tc+" "+result);
		}
	}
	static void perm(int cnt) {
		if(cnt == N) { // 순열 생성
			subset(0,0,0);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				num[cnt] = w[i];
				perm(cnt+1);
				visited[i] = false;
			}
		}
	}
	static void subset(int cnt, int left, int right) {
		if(left < right) return;  //왼쪽보다 오른쪽 무게가 더 크면 탐색x
		if(cnt == N) { // 모든 원소가 처리되었다면
			result++;
			return;
		}
		// 부분집합 생성 - 각 추를 저울의 왼쪽 또는 오른쪽에 올리는 모든 경우 계산
		subset(cnt+1, left+num[cnt], right); //왼쪽에 무게 추가
		subset(cnt+1, left, right+num[cnt]); // 오른쪽에 무게 추가

	}
}