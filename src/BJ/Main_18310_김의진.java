package BJ;
import java.io.*;
import java.util.*;

public class Main_18310_김의진 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int [] arr = new int[N];

        for (int n = 0; n < N; n++) {
            arr[n] = sc.nextInt();
        }
        // sorting
        Arrays.sort(arr);
        // print mid index
        System.out.println(arr[(N - 1) / 2]);

    }


}
