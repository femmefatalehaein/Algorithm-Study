package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 국영수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 값 세팅 - map
        //        Map<String, int []> map = new HashMap<>();  //  점수 담기위한 맵
//        String name;
//        int[] scores = new int[3];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            name = st.nextToken();
//            for (int j = 0; j < 3; j++) {
//                scores[j] = Integer.parseInt(st.nextToken());
//            }
//            map.put(name, scores);
//        }

        // map은 정렬이 안되는데?
        // 그냥 배열로 묶어서 관리하고 인덱스로 연결할까?
        // 값 세팅 - array
        String[] names = new String[N];
        int[][] scores = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();    // 다 소문자로 변경 > 하면 출력도 수정되니까 비교떄만 바꿔주기
            for (int j = 0; j < 3; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] tmpInt;
        String tmpName;
        // for문 돌려도 안터져 - 야생으로 일단 해보자
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                if (scores[i][0] < scores[j][0]) { // 작으면 뒤로 가야됨, 크면 제자리, 같으면 영어점수 순
                    tmpInt = scores[i];
                    tmpName = names[i];
                    // 점수 순서 바꾸기
                    scores[i] = scores[j];
                    scores[j] = tmpInt;
                    // 이름 순서 바꾸기
                    names[i] = names[j];
                    names[j] = tmpName;

                } else if (scores[i][0] == scores[j][0]) {   // 국어 점수가 같다면?
                    if (scores[i][1] > scores[j][1]) { // 영어점수 큰 순
                        tmpInt = scores[i];
                        tmpName = names[i];
                        // 점수 순서 바꾸기
                        scores[i] = scores[j];
                        scores[j] = tmpInt;
                        // 이름 순서 바꾸기
                        names[i] = names[j];
                        names[j] = tmpName;


                    } else if (scores[i][1] == scores[j][1]) {  // 영어 같으면?
                        if (scores[i][2] < scores[j][2]) {
                            tmpInt = scores[i];
                            tmpName = names[i];
                            // 점수 순서 바꾸기
                            scores[i] = scores[j];
                            scores[j] = tmpInt;
                            // 이름 순서 바꾸기
                            names[i] = names[j];
                            names[j] = tmpName;


                        } else if (scores[i][2] == scores[j][2]) {
                            if (names[i].toLowerCase().compareTo(names[j].toLowerCase()) > 0) {  // 이름 사전순, 비교대상이 더 크면 양수가 나옴
                                tmpInt = scores[i];
                                tmpName = names[i];
                                // 점수 순서 바꾸기
                                scores[i] = scores[j];
                                scores[j] = tmpInt;
                                // 이름 순서 바꾸기
                                names[i] = names[j];
                                names[j] = tmpName;


                            }
                        }
                    }
                }
            }


        }
        for (String name : names) {
            System.out.println(name);
        }

    }
}
