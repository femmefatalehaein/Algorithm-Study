package 벽돌깨기;


import java.io.*;
import java.util.*;

//벽돌을 깨자..!!
public class Solution_5656_박해인 {
    static int N,W,H,T,cnt;
    static int map[][];
    static int output[];
    static int dx [] = {-1,0,0,1};
    static int dy [] = {0,1,-1,0};


    public static void perm(int depth, int r) {

        if(depth == N) {
            for(int i=0; i<N; i++) {
                getResult(output[i]);
                System.out.println(cnt);
            }

            return;
        }

        for(int i=0; i<W; i++) {
            output[depth] = i;
            perm(depth+1,r);
        }
    }

    //tgt을 얻었을 때 벽돌이 어떻게 되는지 보자 !
    public static void getResult(int tgt) {
        int idx = 0;
        while(true) {
            if(map[idx][tgt]!=0) {
                break;
            }
            idx++;
        }
        //System.out.println(idx);

        bomb(idx,tgt);
        //현재 위치는 map[idx][tgt] 이다...

    }

    public static void bomb(int idx, int tgt) {
        int d = map[idx][tgt];
        //System.out.println("d->"+d);
        //이렇게 돌거야
        int posX = idx;
        int posY = tgt;

        for(int k = 1; k<=d-1; k++) {
            if(map[posX][posY]!=0) {
                map[posX][posY] = 0;
                cnt++;
            }

            for(int t=0; t<4; t++) {
                posX = posX+dx[t]*k;
                posY = posY+dy[t]*k;
                if(posX>=0&&posX<H&&posY>=0&&posY<W) {
                    //벽돌깨기
                    //lastone일때..->재귀!
                    //종료조건인데..
                    if(k==d-1) {
                        if(map[posX][posY]==0||map[posX][posY]==1) {
                            //return;
                        }else {
                            //여기서 다시 시작이다..
                            bomb(posX,posY);
                        }
                    }
                    if(map[posX][posY]!=0) {
                        map[posX][posY] = 0;
                        cnt++;
                    }
                }
                //원복해주는 코드 ^^..필요했다
                posX =idx;
                posY = tgt;
            }
        }
        //여기까지 1회 벽돌을 깼습니다.
        moveToDown();
        //System.out.println("cnt->"+cnt);
    }

    static void moveToDown() {
        //열마다 큐 생성하기
          for (int col = 0; col < W; col++) {
                Queue<Integer> queue = new LinkedList<>();

            // 열의 모든 값을 큐에 넣기
            for (int row = 0; row < H; row++) {
                if (map[row][col] != 0) {
                    queue.add(map[row][col]);
                }
            }

            // 열의 값을 아래로 이동시키기
            for (int row = H - 1; row >= 0; row--) {
                if (queue.isEmpty()) {
                    map[row][col] = 0;
                } else {
                    map[row][col] = queue.poll();
                }
            }
        }
}


public static void main(String [] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    T = Integer.parseInt(br.readLine());
    for(int t=0; t<T; t++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        map = new int[H][W];
        output = new int[N];
        
        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //n개를 뽑는 중복 순열.
        //중복 순열 문제인것 같다.        
        //System.out.println(Arrays.deepToString(map));
        cnt = 0;
        perm(0,N);
        
        
        
        
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        
        System.out.println(cnt);
    }
    
}
}

