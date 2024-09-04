package 보물상자비밀번호;
/*
 * SWEA 5658. 보물상자 비밀번호
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 보물상자비밀번호_곽승미 {
	static int N,K;
	static Set<String> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			char[] line = br.readLine().toCharArray();
			
			list = new LinkedHashSet<>(); // 순서있는 Set
			for(int i=0; i<4; i++) {
				char[] c = new char[N/4];
				for(int j = i * (N/4); j < (i + 1) * (N/4); j++) {
			        c[j - i * (N/4)] = line[j]; // 내부 배열 인덱스는 j - i * (N/4)
			    }
				list.add(String.valueOf(c));
			}
			// 1칸씩 이동, 중복은 포함하지 않고 list에 생성 가능한 수 저장
			for(int i=0; i<N/4-1; i++) {
				char last = line[line.length-1];
				for(int j=N-1; j>0; j--) {
					line[j] = line[j-1];
				}
				line[0] = last;
				
				for(int c=0; c<4; c++) {
					char[] pwd = new char[N/4];
					for(int j = c * (N/4); j < (c + 1) * (N/4); j++) {
						pwd[j - c * (N/4)] = line[j]; // 내부 배열 인덱스는 j - c * (N/4)
				    }
					if(!list.contains(pwd)) {
						list.add(String.valueOf(pwd));
					}
				}
			}
			ArrayList<Integer> nlist = new ArrayList<>();
			// 16진수 -> 10진수 변환
			for(String s : list) {
				nlist.add(Integer.parseInt(s,16));
			}
			Collections.sort(nlist, Collections.reverseOrder());
			
			System.out.println("#"+tc+" "+nlist.get(K-1));
		}
	}
}