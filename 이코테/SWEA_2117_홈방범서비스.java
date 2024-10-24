import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_2117_홈방범서비스 {
    
    static int N, M, map[][]; 
    static ArrayList<int[]> home;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지도 크기
            M = Integer.parseInt(st.nextToken()); // 집이 지불할 수 있는 비용
            map = new int[N][N]; // 지도 배열
            home = new ArrayList<>();  // 집 좌표 저장 리스트
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) { // 집 위치를 저장
                        home.add(new int[] {i, j});
                    }
                }
            }
            int result = 0;
            if(N * N == home.size()) { // 집이 모든 칸에 있을 경우
                result = N * N; // 전체 칸이 서비스 범위에 포함됨
            } else {
                result = cal(); // 서비스 범위 계산
            }
            System.out.println("#" + tc + " " + result);
        }
    }
  
    public static int cal() {
        int K = N; // 서비스 범위 크기
        int homeCnt = Integer.MIN_VALUE; // 서비스 가능한 최대 집 개수
        while(K > 0) {
            if(K == 0) break; // 범위가 0이 되면 종료
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int cnt = 0;
                    for(int[] h : home) {
                        int x = h[0]; 
                        int y = h[1]; 
                        
                        int d = Math.abs(i - x) + Math.abs(j - y); // 현재 위치에서 집까지의 거리
                        
                        if(K - 1 >= d) { // 현재 서비스 범위 내에 집이 있으면
                            cnt++;
                        }
                    }
                    int total = cnt * M; // 서비스 범위 내 집들이 지불할 수 있는 총 비용
                    int val = K * K + (K - 1) * (K - 1); // 서비스 운영 비용
                    if(val <= total && homeCnt < cnt) { // 비용 감당되면서 더 많은 집을 서비스할 수 있다면
                        homeCnt = cnt; // 서비스 가능한 집 개수를 갱신
                    }
                }
            }
            K--; // 서비스 범위 축소
        }
        return homeCnt;
    }
}