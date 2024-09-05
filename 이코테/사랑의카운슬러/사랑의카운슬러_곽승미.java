package 사랑의카운슬러;

/*
 * SWEA 7227. 사랑의 카운슬러
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사랑의카운슬러_곽승미 {

	public static int T, N;
    public static long answer;
    public static int[][] worms = new int[20][2];
    public static int[] selected = new int[20];
    
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    T = Integer.parseInt(br.readLine());  // 테스트 케이스의 수
	    for (int tc = 1; tc <= T; tc++) {
	        N = Integer.parseInt(br.readLine());  // 벌레의 수 (항상 짝수)
	        answer = Long.MAX_VALUE;  // 최소 벡터 크기를 저장할 변수 초기화

	        // 벌레들의 좌표 입력
	        for (int i = 0; i < N; i++) {
	            st = new StringTokenizer(br.readLine());
	            worms[i][0] = Integer.parseInt(st.nextToken());  // x 좌표
	            worms[i][1] = Integer.parseInt(st.nextToken());  // y 좌표
	        }

	        match(0, 0);  // 백트래킹을 이용하여 조합 찾기

	        // 테스트 케이스 결과 출력
	        System.out.println("#" + tc + " " + answer);
	    }
	}

	
	private static void match(int idx, int depth) {
	    if (depth == N / 2) {
	        long rowSum = 0;
	        long colSum = 0;

	        // 각 그룹의 벡터 합을 계산
	        for (int i = 0; i < N; i++) {
	            if (selected[i] == 1) {
	                rowSum += worms[i][0];  // 첫 번째 그룹의 x좌표 합
	                colSum += worms[i][1];  // 첫 번째 그룹의 y좌표 합
	            } else {
	                rowSum -= worms[i][0];  // 두 번째 그룹의 x좌표 합
	                colSum -= worms[i][1];  // 두 번째 그룹의 y좌표 합
	            }
	        }

	        // 벡터의 크기 계산: (rowSum^2 + colSum^2)
	        long vectorSize = rowSum * rowSum + colSum * colSum;

	        // 최소값 갱신
	        if (answer > vectorSize)
	            answer = vectorSize;

	        return;
	    }

	    // 백트래킹을 이용해 벌레를 그룹에 나누는 과정
	    for (int i = idx; i < N; i++) {
	        selected[i] = 1;  // 현재 벌레를 첫 번째 그룹에 포함
	        match(i + 1, depth + 1);  // 다음 벌레 선택
	        selected[i] = 0;  // 백트래킹: 다시 원상태로 돌리기
	    }
	}
}