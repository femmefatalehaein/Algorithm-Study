package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Score {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		String [][] score = new String[n][4];
		
		for (int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0 ; j < 4 ; j++) {
				score[i][j] = st.nextToken();
			}
		}
		// [0] 이름 [1] 국어 [2]영어 [3] 수학
		Arrays.sort(score, new Comparator<String[]>() {
			public int compare(String[] o1, String[] o2) {
				if (Integer.valueOf(o1[1]) == Integer.valueOf(o2[1])) { // 만약 국어 점수가 같으면 영어점수 비교
					if (Integer.valueOf(o1[2]) == Integer.valueOf(o2[2])) { // 만약 국어 , 영어 점수 같으면
						if (Integer.valueOf(o1[3]) == Integer.valueOf(o2[3])) { // 다 같으면
							return o1[0].compareTo(o2[0]); // 이름 비교
						}
						else { // 국어, 영어 같으면 수학점수 내림차순 비교
							return Integer.valueOf(o2[3]) - Integer.valueOf(o1[3]);
						}
					}
					else { // 국어점수 같으면 영어점수 오름차순
						return Integer.valueOf(o1[2]) - Integer.valueOf(o2[2]);
					}
					
				}
				else { // 국어점수 같지 않으면 내림차순
					return Integer.valueOf(o2[1]) - Integer.valueOf(o1[1]);
				}
			}
		});
		
		// 이름만 출력하기
		for (int i = 0 ; i < n ; i++) {
			System.out.println(score[i][0]);
		}

	}

}
