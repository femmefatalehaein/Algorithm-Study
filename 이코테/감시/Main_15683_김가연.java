package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15683_김가연 {
	
	// cctv가 방에 몇개있는지 확인하는 변수 선언
	static int end;
	
	// cctv 좌표 넣을 어레이리스트 선언
	static ArrayList<int []> cctv;
	
	// 배열의 가로길이, 세로길이 저장할 변수 선언
	static int row, col;
	
	// 어느방향으로 감시해야하는지 방향 배열 선언
	static int [][] dir = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	// 처음 방 정보 저장할 배열 선언
	static int [][] monit;
	
	// 남은 안전구역의 최솟값 저장할 변수 선언
	static int min;
	
	// 이동한 좌표가 방 내부인지 확인하는 함수
	static boolean ifctv(int x, int y) {
		if (((0 <= x) && (x < row)) && ((0 <= y) && (y < col))) {
			return true;
		} else {
			return false;
		}
	}
	
	// 파라미터로 방문체크배열, 현재 몇번째 cctv를 돌고있는지, 안전구역의 갯수 받아주기
	static void room(int [][] visit, int depth, int hide) {
		
		// 만약 cctv를 다 순회했다면
		if (depth == end) {
			
			// 만약 최솟값이 저장되지 않은 상태라면
			if (min == -1) {
				// 안전구역의 갯수 최솟값으로 설정
				min = hide;
			}
			
			// 만약 최솟값이 설정되었고 현재 안전구역의 갯수가 설정된 최솟값보다 작은 경우
			if (hide < min) {
				// 현재 안전구역 갯수 최솟값으로 설정
				min = hide;
			}
			return;
		}
	
		// 현재 돌아야 할 cctv 설정
		int [] cc = cctv.get(depth);
		// cctv 감시구역 수 저장할 변수 선언
		int cnt = 0;
		
		// 1번 cctv일 때
		if (cc[2] == 1) {	
			
			// 위, 아래, 좌, 우 따로따로 돌기
			for (int i = 0 ; i < 4 ; i++) {
				
				// cctv 감시구역 수 초기화
				cnt = 0;
				// 원본 방문배열이 훼손되지 않게 하기 위해 vclone 이라는 배열로 visit 배열 복사
				int [][] vclone = new int[row][col];
				for (int j = 0 ; j < row ; j++) {
					for (int k = 0 ; k < col ; k++) {
						vclone[j][k] = visit[j][k];
					}
				}
				
				// 돌아야할 방향으로 좌표 설정
				int dx = cc[0] + dir[i][0];
				int dy = cc[1] + dir[i][1];
				
				// 방 끝에 닿을때까지 반복하기
				while(ifctv(dx, dy)) {
					
					// 만약 벽에 닿았다면 종료
					if (vclone[dx][dy] == -2) {
						break;
					}
					
					// 만약 다른 cctv에 닿거나 다른 cctv의 감시구역에 닿았다면 진행방향으로 한칸 뛰어넘어주기
					if ((vclone[dx][dy] == -1) || (vclone[dx][dy] == 1)) {
						dx += dir[i][0];
						dy += dir[i][1];
					
					// 그냥 빈칸이고 아무도 방문하지 않았다면 방문 체크해주고 감시구역 + 1
					// 진행방향으로 좌표 한칸 이동
					} else {
						vclone[dx][dy] = 1;
						cnt += 1;
						dx += dir[i][0];
						dy += dir[i][1];
					}
				}
				
				// 저장된 visit 복사 배열, 다음 cctv 좌표를 불러오기위해 현재 depth에서 +1
				// 안전구역 수에서 현재 탐색에서 포착된 감시구역 수 빼주고 다음 재귀로 넘기기
				room(vclone, depth + 1, hide - cnt);
			}
		// 2번 cctv일 때	
		} else if (cc[2] == 2) {
			
			// 가로, 세로 방향 따로 탐색을 해야하므로
			// dir배열의 (0, 1), (0, -1) 방향으로 탐색 한번 / (1, 0), (-1, 0) 방향으로 탐색을 한번 돌린다
			int [][] dir2 = {{0, 1}, {2, 3}};
			for (int [] d : dir2) {
				
				// cctv 감시구역 수 초기화
				cnt = 0;
				// 원본 방문배열이 훼손되지 않게 하기 위해 vclone 이라는 배열로 visit 배열 복사
				int [][] vclone = new int[row][col];
				for (int j = 0 ; j < row ; j++) {
					for (int k = 0 ; k < col ; k++) {
						vclone[j][k] = visit[j][k];
					}
				}
				
				// 1차원 배열 반복문 한번 더 돌려주기
				for (int d2 : d) {
					
					int dx = cc[0] + dir[d2][0];
					int dy = cc[1] + dir[d2][1];
					
					// 방 끝에 닿을때까지 반복하기
					while(ifctv(dx, dy)) {
						// 만약 벽에 닿았다면 종료
						if (vclone[dx][dy] == -2) {
							break;
						}
						// 만약 다른 cctv에 닿거나 다른 cctv의 감시구역에 닿았다면 진행방향으로 한칸 뛰어넘어주기
						if ((vclone[dx][dy] == -1) || (vclone[dx][dy] == 1)) {
							dx += dir[d2][0];
							dy += dir[d2][1];
						// 그냥 빈칸이고 아무도 방문하지 않았다면 방문 체크해주고 감시구역 + 1
						// 진행방향으로 좌표 한칸 이동
						} else {
							vclone[dx][dy] = 1;
							cnt += 1;
							dx += dir[d2][0];
							dy += dir[d2][1];
						}
					}
				}
				// 저장된 visit 복사 배열, 다음 cctv 좌표를 불러오기위해 현재 depth에서 +1
				// 안전구역 수에서 현재 탐색에서 포착된 감시구역 수 빼주고 다음 재귀로 넘기기
				room(vclone, depth + 1, hide - cnt);
			}
		// 3번 cctv일 때	
		} else if (cc[2] == 3)  {
			
			// 가로 탐색 + 세로 탐색이 쌍을 이루어하므로 dir배열의 가로 탐색, 세로 탐색 인덱스를 하나씩 넣어 배열 만들기
			int [][] dir3 = {{3, 0}, {0, 2}, {2, 1}, {1, 3}};
			
			// 저장한 가로, 세로 탐색 인덱스 배열 반복문 돌려주기
			for (int [] d : dir3) {
				
				cnt = 0;
				int [][] vclone = new int[row][col];
				for (int j = 0 ; j < row ; j++) {
					for (int k = 0 ; k < col ; k++) {
						vclone[j][k] = visit[j][k];
					}
				}
				
				for (int d3 : d) {
					
					int dx = cc[0] + dir[d3][0];
					int dy = cc[1] + dir[d3][1];
					
					while(ifctv(dx, dy)) {
						if (vclone[dx][dy] == -2) {
							break;
						}
						if ((vclone[dx][dy] == -1) || (vclone[dx][dy] == 1)) {
							dx += dir[d3][0];
							dy += dir[d3][1];
						} else {
							vclone[dx][dy] = 1;
							cnt += 1;
							dx += dir[d3][0];
							dy += dir[d3][1];
						}
					}
				}
				room(vclone, depth + 1, hide - cnt);
			}
		// 4번 cctv일 때
		} else if (cc[2] == 4) {
			
			// 3방향 탐색이 이루어져야 하므로 한방향만 빼고 dir배열의 인덱스번호 이용하여 배열 만들어주기
			int [][] dir4 = new int[][] {{0, 1, 3}, {0, 2, 3}, {0, 2, 1}, {2, 1, 3}};
			
			// 저장한 dir인덱스 배열로 반복문 돌려주기
			for (int [] d : dir4) {
				
				cnt = 0;
				int [][] vclone = new int[row][col];
				for (int j = 0 ; j < row ; j++) {
					for (int k = 0 ; k < col ; k++) {
						vclone[j][k] = visit[j][k];
					}
				}
				
				for (int d4 : d) {
					int dx = cc[0] + dir[d4][0];
					int dy = cc[1] + dir[d4][1];
					
					while(ifctv(dx, dy)) {
						if (vclone[dx][dy] == -2) {
							break;
						}
						if ((vclone[dx][dy] == -1) || (vclone[dx][dy] == 1)) {
							dx += dir[d4][0];
							dy += dir[d4][1];
						} else {
							vclone[dx][dy] = 1;
							cnt += 1;
							dx += dir[d4][0];
							dy += dir[d4][1];
						}
					}
				}
				
				room(vclone, depth + 1, hide - cnt);
			}
		
		// 5번 cctv일 때
		} else {
			
			// 한번에 사방탐색이 이루어져야 하므로 초기화문을 반복문 바깥에 써주고
			// 이미 사방탐색 dir 배열이 있기 때문에 따로 인덱스 배열을 만들어 주지 않고 진행
			cnt = 0;
			int [][] vclone = new int[row][col];
			for (int j = 0 ; j < row ; j++) {
				for (int k = 0 ; k < col ; k++) {
					vclone[j][k] = visit[j][k];
				}
			}
			
			for (int i = 0 ; i < 4 ; i++) {
				int dx = cc[0] + dir[i][0];
				int dy = cc[1] + dir[i][1];
				
				while(ifctv(dx, dy)) {
					if (vclone[dx][dy] == -2) {
						break;
					}
					if ((vclone[dx][dy] == -1) || (vclone[dx][dy] == 1)) {
						dx += dir[i][0];
						dy += dir[i][1];
					} else {
						vclone[dx][dy] = 1;
						cnt += 1;
						dx += dir[i][0];
						dy += dir[i][1];
					}
				}
			}
			// 저장된 visit 복사 배열, 다음 cctv 좌표를 불러오기위해 현재 depth에서 +1
			// 안전구역 수에서 현재 탐색에서 포착된 감시구역 수 빼주고 다음 재귀로 넘기기
			room(vclone, depth + 1, hide - cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		// 가로길이, 세로길이 초기화 시켜주기
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		// 방 정보 저장할 배열 크기 초기화 시켜주기
		monit = new int [row][col];
		
		// 방문배열 생셩하기
		int [][] visit = new int [row][col];
		
		// 전체 안전구역의 수 일단 가로 * 세로로 설정하기
		int hide = row * col;
		
		// cctv 좌표 저장할 리스트 초기화시키기
		cctv = new ArrayList<>();
		
		for (int i = 0 ; i < row ; i++) {
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < col ; j ++) {
				monit[i][j] = Integer.parseInt(st1.nextToken());
				
				// 만약 벽이 아니고 cctv가 저장되어 있다면
				if ((monit[i][j] != 0) && (monit[i][j] != 6)) {
					
					// 리스트에 x, y좌표와 몇번 cctv가 저장되어 있는지 정보 저장
					cctv.add(new int[] {i, j , monit[i][j]});
					
					// 방문배열에 cctv는 -1로 설정하기
					visit[i][j] = -1;
					
					// cctv가 존재하는 구역은 안전구역에 속하지 않으므로 -1
					hide -= 1;
				
				// 만약 벽이 저장된 좌표라면
				} else if (monit[i][j] == 6) {
					
					// 방문 배열에 -2로 저장하기
					visit[i][j] = -2;
					
					// 벽이 존재하는 구역은 안전구역에 속하지 않으므로 -1
					hide -= 1;
				}
			}
		}
		
		// 최솟값 초기화
		min = -1;
		
		// cctv의 갯수 초기화 시켜주기
		end = cctv.size();
		
		// 초기 방문배열, 깊이 0, 벽과 cctv 위치를 뺀 안전구역 수 파라미터로 넣고 함수 호출
		room(visit, 0, hide);
		
		// 저장된 안전구역 최솟값 출력해주기
		System.out.println(min);

	}

}
