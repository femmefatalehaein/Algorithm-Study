import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 홈방범서비스 {
    private static int N, M, total, max;
    private static int[][] map;
    private static ArrayList<int[]> homes;

    public static void main(String[] args) throws IOException {
        System.setIn(Files.newInputStream(Paths.get("./src/res/input_홈방범.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 배열 크기
            M = Integer.parseInt(st.nextToken()); // 비용 크기
            homes = new ArrayList<>();
            map = new int[N][N];
            int n;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    n = Integer.parseInt(st.nextToken());
                    if (n == 1) {
                        homes.add(new int[]{i, j});
                        map[i][j] = n;
                    } else {
                        map[i][j] = n;
                    }
                }
            }
//            System.out.println(Arrays.deepToString(homes.toArray()));
            max = 0;
            for (int k = N + 1; k >= 1; k--) { // k먼저 정한다음 진행하는데 -> K가 충분히 클 경우 = map의 크기를 모두 감쌀 경우
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        total = 0; // 현재 위치에서 집까지의 멘하튼거리
                        // 여기서부터 하나씩 거리 구해주기
                        for (int[] point : homes) {
                            int distance = Math.abs(point[0] - i) + Math.abs(point[1] - j);
                            if (distance < k) { // k범위 이내에서 거리
                                total++;
                            }
                        }
                        // 아 한칸 돌릴 떄 마다 총비용이랑 계산해야되는데 for문 다돌리고 마지막에 비교해서
                        // 마지막 부분만 계산되서 틀린거임 ㅜ
                        // 총 비용이랑 비교
                        int totalCost = k * k + (k - 1) * (k - 1);
                        if (M * total >= totalCost) { // 손해만 아니면 되서 같아도 노상관
                            max = Math.max(total, max);
                        }
                    }
                }

            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}
