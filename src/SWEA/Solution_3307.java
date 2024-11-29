import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] numbers = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer> lis = new ArrayList<>(); // LIS를 유지할 리스트

            for (int num : numbers) {
                if (lis.isEmpty() || lis.get(lis.size() - 1) < num) {
                    // 리스트가 비어있거나, 현재 수가 LIS 마지막 원소보다 크면 그냥 추가
                    lis.add(num);
                } else {
                    // 그렇지 않다면 현재 수가 들어갈 자리를 찾아서 대체 (이분 탐색 사용)
                    int idx = lowerBound(lis, num);
                    lis.set(idx, num); // 해당 위치에 num을 대체
                }
            }

            sb.append("#").append(tc).append(" ").append(lis.size()).append("\n");
        }
        System.out.println(sb);
    }

    // lowerBound 메서드 구현 (이분 탐색)
    private static int lowerBound(ArrayList<Integer> list, int key) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // key가 들어갈 위치 반환
    }
}
