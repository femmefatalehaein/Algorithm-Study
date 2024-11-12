package 햄스터;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄스터_장희주 {

	static int N, X, M;
	static int l, r, s;
	static int[][] records;
	static int[] cages;
	static int[] bCages;
	static int maxHamsters;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			X=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			records=new int[M][3];
			cages=new int[N];
			bCages=new int[N];
			maxHamsters=-1;
			for(int i=0;i<M;i++) {
				st=new StringTokenizer(br.readLine());
				records[i][0]=Integer.parseInt(st.nextToken())-1;
				records[i][1]=Integer.parseInt(st.nextToken())-1;
				records[i][2]=Integer.parseInt(st.nextToken());
				
			}
			backtracking(0,0);
			System.out.print("#"+test_case+" ");
			if(maxHamsters==-1) {
				System.out.println("-1");
			}else {
				for(int i=0;i<N;i++) {
					System.out.print(bCages[i]+" ");
				}
				System.out.println();
			}
		}

	}

	static void backtracking(int idx, int totalHamsters) {
		if(idx==N) {
			
			if(isValid()) {
				if(maxHamsters<totalHamsters || (maxHamsters==totalHamsters && dicSmaller())) {
					maxHamsters=totalHamsters;
					
					for(int i=0;i<N;i++) {
						bCages[i]=cages[i];
					}
				}
			}
			return;
		}
		for(int i=0;i<=X;i++) {
			cages[idx]=i;
			backtracking(idx+1,totalHamsters+i);
			
		}
	}

	// 기록이 유효한지 검사하는 함수
	static boolean isValid() {
		
		for(int[] record : records) {
			int start=record[0];
			int end=record[1];
			int expectedSum=record[2];
			int sum=0;
			for(int i=start;i<=end;i++) {
				sum+=cages[i];
			}
			if(sum!=expectedSum) {
				return false;
			}
		}
		return true;
	}

	// 사전순으로 더 작은지 확인하는 함수
	static boolean dicSmaller() {

		for(int i=0;i<N;i++) {
			if(cages[i]<bCages[i]) {
				return true;
			}else if(cages[i]>bCages[i]) {
				return false;
			}
		}
		return false;
	}
}
