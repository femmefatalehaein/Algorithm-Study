import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1987_알파벳_곽승미 {

	static int R,C;
	static char[][] board;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int max;
	static boolean visited[];
	
    public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		visited = new boolean[26]; // 알파벳 방문 여부
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		// 좌측 상단부터 시작
		visited[board[0][0] - 'A'] = true;
		dfs(0,0,1);
		System.out.println(max);
		
	}
    public static void dfs(int x, int y, int cnt) {
    	max = Math.max(max, cnt); // 이동 가능한 최대 칸 수 갱신
    	
    	// 상하좌우로 이동
    	for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위를 벗어나지 않고, 방문하지 않았다면
			if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
				int alpha = board[nx][ny] - 'A'; // 이동할 칸의 알파벳 인덱스
				
				if(!visited[alpha]) {
					visited[alpha] = true; // 방문처리
					dfs(nx, ny, cnt+1); // dfs 진행
					visited[alpha] = false; // 방문해제(백트래킹)
				}
			}	
		}
    }
}