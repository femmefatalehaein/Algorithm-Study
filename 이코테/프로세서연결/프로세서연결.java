package D_0827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Solution_1767_김가연 {
	
	// 프로세서 정보 저장할 ArrayList선언
	static ArrayList<int []> incore;
	// 탐색을 돌려야 할 프로세서가 몇개인지 저장할 변수 선언
	static int end;
	// 사방탐색 
	static int [][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	// 전선의 최솟값 저장할 변수 선언
	static int min;
	// 연결된 프로세서의 최대 갯수를 저장할 변수 선언
	static int minidx;
	
	// 셀 배열 내부에 있나 확인하는 함수
	static boolean ifmap(int x, int y, int n) {
		if (((0 <= x) && (x < n)) && ((0 <= y) && (y < n))) {
			return true;
		} else {
			return false;
		}
	}
	
	// 방문배열, 탐색한 프로세서 갯수, 배열 길이(이건 필요 없는데 왜 넣었지,,), 현재까지의 전선 총 합, 현재까지 연결된 프로세서 수
	static void processor(int [][] visit, int depth, int n, int sum, int connect) {
		
		// 만약 탐색을 끝까지 완료하였고
		if (depth == end) {
			
			// 아직 최솟값과 연결된 프로세서 갯수가 설정되지 않았다면 값 저장
			if (minidx == -1) {
				minidx = connect;
				min = sum;
			}
			
			// 만약 현재 저장된 연결 프로세서 갯수가 현재 구한 연결 프로세서 갯수보다 작다면
			// 현재 구한 값으로 초기화
			if (minidx < connect) {
				minidx = connect;
				min = sum;
			}
			
			// 만약 프로세서 갯수는 같은데 전선 길이가 더 작다면
			// 현재 구한 값으로 초기화
			if ((minidx == connect) && (min > sum)) {
				min = sum;
			}
			return;
		}
		
		// 만약 지금까지 연결된 프로세서와 남은 프로세서 갯수를 다 더해도 현재 저장된 연결 프로세서 갯수보다 작다면
		// 그냥 리턴해서 종료
		if (((end - depth) + connect) < minidx) {
			return;
		}
		
		// 저장된 프로세서 좌표 꺼내주기
		int [] pro = incore.get(depth);
		depth += 1;
		
		// 사방탐색 돌려주기 -> for문 현재 탐색 방향 설정
		for (int d = 0 ; d < 4 ; d++) {
			
			// 연결 전선 길이 셀 변수 선언
			int cnt = 0;
			
			// 좌표 구해주기
			int dx = pro[0] + dir[d][0];
			int dy = pro[1] + dir[d][1];
			
			// 연결되지 않을 때 visit 배열 오염되는것을 막기 위해 visit를 복사해 vclone을 만들어준다
			int [][] vclone = new int[n][n];
			for (int i = 0 ; i < visit.length ; i++) {
				for (int j = 0 ; j < visit[0].length ; j++) 
					vclone[i][j] = visit[i][j];
			}
			
			// 설정한 방향대로 계속 돌려주기
			while(ifmap(dx, dy, n)) {
				
				// 만약 가다가 프로세서를 만나면 바로 종료
				if (visit[dx][dy] == -1) {
					cnt = 0;
					break;
				} 
				
				// 만약 가다가 길이 막혀있으면 바로 종료
				if (visit[dx][dy] == 1) {
					cnt = 0;
					break;
				}
				
				// 아니라면 가는 길 방문 표시 해주고 전설 길이 + 1
				vclone[dx][dy] = 1;
				cnt += 1;
				
				// 방향 계속 탐색
				dx += dir[d][0];
				dy += dir[d][1];
			}
			// 중간에 막혔다면 방문표시하지 않은 배열로 다음 재귀 돌려주기
			if (cnt == 0){
				processor(visit, depth, n, sum, connect);
			// 중간에 막히지 않았다면 새로 방문표시 한 배열로 다음 재귀 돌려주기
			} else {
				processor(vclone, depth, n, sum + cnt, connect + 1);
			}
		}
		
		return;

		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(bf.readLine());

		for (int test_case = 1 ; test_case <= tc ; test_case++) {
			
			int n = Integer.parseInt(bf.readLine());
			int [][] core = new int [n][n];
			incore = new ArrayList<>();
			
			
			int [][] visit = new int[n][n];
			// 전선 연결 수, 프로세서 연결 수 -1로 초기화
			min = -1;
			minidx = -1;
			
			for(int i = 0 ; i < n ; i++) {
				StringTokenizer st1 = new StringTokenizer(bf.readLine());
				for (int j = 0 ; j < n ; j++) {
					core[i][j] = Integer.parseInt(st1.nextToken());
					
					// 만약  제일 끝쪽에 있는 프로세서가 아니라면 프로세서의 좌표 배열에 삽입 + 방문배열에는 -1로 표시해주기
					if (((i != 0) && (i != n - 1) && (j != 0) && (j != n - 1)) && (core[i][j] != 0)) {
						incore.add(new int[] {i, j});
						visit[i][j] = -1;
					// 만약 끝쪽에 있는 프로세서라면 방문배열에 -1로 표시해주기
					} else if (((i == 0) || (i == n - 1) || (j == 0) || (j == n - 1)) && (core[i][j] != 0)) {
						visit[i][j] = -1;
					}
				}
			}
			
			// 배열 끝쪽에 있는 프로세서를 제외한 탐색해야 할 프로세서의 갯수
			end = incore.size();
			
			// 방문배열, 현재 탐색한 프로세서 갯수, 배열 길이, 전선 총 합, 연결된 프로세서 갯수 파라미터로 재귀 호출
			processor(visit, 0, n, 0, 0);
			
			// 저장된 테스트케이스, 전선 연결 수 출력
			System.out.printf("#%d %d \n", test_case, min);
			
		}

	}

}
