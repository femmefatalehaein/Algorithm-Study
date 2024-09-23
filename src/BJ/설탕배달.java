package BJ;

import java.util.Scanner;

public class 설탕배달 {
    public static void main(String[] args) {
        int w1 = 3;
        int w2 = 5;

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int answer = -1;

        int min = Integer.MAX_VALUE;
        int maxI = 0;
        int no = 0;

        if (N%5 == 0) answer = N/5;

        else{
            maxI  = N/w2;
            for (int i = 0; i <= maxI; i++) {
                no =N-w2*i;
                if (no> 0) {
                    if (no%w1 == 0) {
                        int cnt  = i+(no/w1);
                        min = Math.min(min, cnt);
                        answer = min;
                    }
                }
            }

        }






        System.out.println(answer);

    }
}
