package 미생물격리;
/*
 * SWEA_2382. 미생물 격리
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미생물격리_곽승미 {
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/s_2382_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 셀 개수
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수

            List<int[]> micro = new ArrayList<>(); // 미생물 정보 저장 리스트

            for (int i = 0; i < K; i++) {
                int[] info = new int[4];
                st = new StringTokenizer(br.readLine());

                info[0] = Integer.parseInt(st.nextToken()); // 세로 위치
                info[1] = Integer.parseInt(st.nextToken()); // 가로 위치
                info[2] = Integer.parseInt(st.nextToken()); // 미생물 수
                info[3] = Integer.parseInt(st.nextToken()); // 이동 방향

                micro.add(info);
            }

            int result = 0;

            for (int m = 0; m < M; m++) { // 시간이 지날때마다
                List<int[]> list = new ArrayList<>(); // 부딪히는 미생물 좌표 리스트

                for (int idx = 0; idx < micro.size(); idx++) {
                    int[] mc = micro.get(idx); // 미생물 하나 가져오기

                    // 미생물 이동
                    move(mc);

                    // 미생물이 약품(가장 자리)에 닿은 경우
                    if (mc[0] == 0 || mc[0] == N - 1 || mc[1] == 0 || mc[1] == N - 1) {
                    	mc[2] /= 2;  // 미생물 수를 절반으로 줄이기
                    	mc[3] = changeDir(mc[3]);  // 방향 바꾸기
                    }

                    // 좌표 확인(현재 위치)
                    int[] location = {mc[0], mc[1]};
                    
                    // 같은 위치에 위치하는 미생물 확인
                    // 좌표를 확인해서 location 좌표가 list에 존재하지 않으면 추가
                    boolean flag = false;
                    for (int[] loc : list) {
                        if (loc[0] == location[0] && loc[1] == location[1]) {
                        	flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                    	list.add(location);
                    }
                }

                // 좌표가 같은 미생물 군집들 연산
                for (int[] loc : list) { // 리스트에서 하나씩 꺼내오기
                    int maxValue = 0;
                    int maxIndex = -1;
                    int sum = 0;
                    List<Integer> remove = new ArrayList<>();
                    
                    // 가장 큰 미생물 수인 미생물의 인덱스 찾기
                    for (int j = 0; j < micro.size(); j++) {
                        int[] cur = micro.get(j);
                        if (cur[0] == loc[0] && cur[1] == loc[1]) {
                            if (cur[2] > maxValue) {
                            	maxValue = cur[2]; // 가장 큰 미생물 수
                                maxIndex = j; // 해당 인덱스
                            }
                            sum += cur[2];
                            remove.add(j); // 나머지 인덱스 리스트에 저장
                        }
                    }

                    // 큰 미생물 군집을 유지하며 나머지 제거
                    if (maxIndex != -1) {
                        int[] merge = micro.get(maxIndex);
                        merge[2] = sum;  // 미생물 합치기
                        merge[3] = micro.get(maxIndex)[3];  // 방향 유지
                    }

                    // 인덱스 내림차순으로 정렬하고, maxIndex가 아닌 경우 리스트에서 삭제
                    remove.sort((a, b) -> Integer.compare(b, a));

                    for (int k : remove) {
                        if (k != maxIndex) {
                            micro.remove(k);
                        }
                    }
                }
            }
            
            // 시간 이후 남은 미생물 수 계산
            for (int[] last : micro) {
            	result += last[2];
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    // 미생물 이동 처리
    static void move(int[] m) {
        if (m[3] == 1) {
            m[0] -= 1;  // 상
        } else if (m[3] == 2) {
            m[0] += 1;  // 하
        } else if (m[3] == 3) {
            m[1] -= 1;  // 좌
        } else if (m[3] == 4) {
            m[1] += 1;  // 우
        }
    }

    // 이동 방향을 반대로 바꾸기(상<->하, 좌<->우)
    static int changeDir(int dir) {
        if (dir == 1) {
            return 2;  // 상 -> 하
        } else if (dir == 2) {
            return 1;  // 하 -> 상
        } else if (dir == 3) {
            return 4;  // 좌 -> 우
        } else if (dir == 4) {
            return 3;  // 우 -> 좌
        }
        return dir;
    }
}
