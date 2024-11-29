import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취 {
	
	static int N,M,C, map[][], cost1, cost2, answer;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/s_2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken()); // 맵 크기(벌통 크기)
			M = Integer.parseInt(st.nextToken()); // 선택 가능한 벌통 개수
			C = Integer.parseInt(st.nextToken()); // 벌꿀 채취 최대 양
			
			map = new int[N][N];
			visited = new boolean[M];
			
			answer = Integer.MIN_VALUE;
			cost1 = Integer.MIN_VALUE;
			cost2 = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sol();
			System.out.println("#"+tc+" "+answer);
			
		}
		

	}
	static void sol() {
		boolean checked[][] = new boolean[N][N];;
		boolean flag = true;
		// 두 일꾼이 꿀통 선택
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N-M+1 ; j++) {
				// 1번 일꾼 선택
				for (int k = j; k < j+M; k++) {
					checked[i][k] = true;
				}
				// 2번 일꾼 선택
				for (int a = 0; a < N; a++) {
					for (int b = 0; b < N-M+1 ; b++) {
						flag = true;
						for (int c = 0; c < M; c++) {
							if(checked[a][b+c]) {
								flag = false;
								break;
							}
						}
						if(!flag) continue;
						
						comb(i,j,a,b,0);
						answer = Math.max(answer, cost1+cost2);
						cost1 = 0;
						cost2 = 0;
						
					}
				}
				for (int k = j; k < j+M; k++) {
					checked[i][k] = false;
				}
			}
		}
	}
	private static void comb(int i, int j, int a, int b, int d) {
		
		if(d == M) { // 부분집합 생성 시
			int c1 = 0, c2 = 0, total1 = 0, total2 = 0;
			for (int k = 0; k < M; k++) {
				if(visited[k]) {
					c1 += map[i][j+k];
					total1 += (int) Math.pow(map[i][j+k],2);
					c2 += map[a][b+k];
					total2 += (int) Math.pow(map[a][b+k],2);
				}
			}
			if(c1 <= C) cost1 = Math.max(cost1, total1);
			if(c2 <= C) cost2 = Math.max(cost2, total2);
			return;
			
		}
		
		visited[d] = true;
		comb(i,j,a,b,d+1);
		visited[d] = false;
		comb(i,j,a,b,d+1);
		
		
	}

}