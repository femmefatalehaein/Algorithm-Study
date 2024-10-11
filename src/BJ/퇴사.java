import java.util.*;
import java.io.*;

public class 퇴사 {

	static int N;
	static int[][] schedule;
	static int result;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
        // 스케쥴 : 상담일과 돈 한번에 넣어주기
		schedule = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken()); 
			schedule[i][1] = Integer.parseInt(st.nextToken()); 
		}
		
		result = 0;
		// 0일 부터 시작함
	    findMax(0, 0);
		
		System.out.println(result);
	}
	
	static void findMax(int idx, int pay) {
		if(idx >= N) {    // 백트래킹 갈겨버리기
            // 종료조건 : 퇴사일보다 일 더시키면 냅다 그만두기 (사유 : 엠지세대 이슈)
			result = Math.max(pay, result);
			return;
		}
		
		if(idx + schedule[idx][0] <= N) { // 퇴사날짜 전에 일 할 수 있음 -> 일하고 돈받기
			findMax(idx + schedule[idx][0], pay + schedule[idx][1]);
		} else { // 퇴사날짜 이후에 일한다? -> 일단 더해보고 안할 결심으로 넘겨줌
			findMax(idx + schedule[idx][0], pay);
		}
	    // 중요 : 다 훑어야 되니까 다음날도 넘겨줘보기
		findMax(idx + 1, pay);
	}
}
