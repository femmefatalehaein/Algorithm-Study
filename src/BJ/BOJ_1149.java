import java.io.*;
import java.util.*;

public class RGB거리_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 1][3];
        int[] values = new int[3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                values[j] = Integer.parseInt(st.nextToken());
            }

            arr [i][0] = Math.min(arr[i-1][1]+values[0], arr[i-1][2]+values[0]);
            arr [i][1] = Math.min(arr[i-1][0]+values[1], arr[i-1][2]+values[1]);
            arr [i][2] = Math.min(arr[i-1][0]+values[2], arr[i-1][1]+values[2]);

        }
        int min = Integer.MAX_VALUE;
        for (int val : arr[N]){
            min = Math.min(min, val);
        }
        System.out.println(min);
    }
}
