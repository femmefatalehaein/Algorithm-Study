package 공유기;

import java.io.*;
import java.util.*;

public class Main_2110_박해인 {
	
	static int N, M, Max;
	static int[] map;
	static boolean[] visited;
	static List<Integer> output;
	
	static void combi(int start, int r) {
		
		if(r == 0) {
			output.clear();
			//System.out.println();
			for (int i = 0; i < N; i++) {
				if(visited[i]) { output.add(map[i]);}
			}
			//System.out.println(Arrays.deepToString(output.toArray()));
			//getMax();
			Max = Math.max(Max, getMax());
			return;
		}
		
		for(int i=start; i<N; i++) {
			visited[i] = true;
			combi(i+1,r-1);
			visited[i] = false;
			
		}	
	}
	
	//각 조합마다의 최댓값을 알려주는데
	public static int getMax() {
		int min = Integer.MAX_VALUE;
		int diff = 0;
		for(int i=0; i<M; i++) {
			
			if(i+1<M) {
				int x = output.get(i);
				int y = output.get(i+1);
				diff = Math.abs(y-x);
			}
			min = Math.min(diff, min);
		}
		return min;
	}

	public static void main(String [] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		//N은 집의 수
		//M은 공유기 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//System.out.println(N);
		//System.out.println(M);
		
		map = new int[N];
		
		visited = new boolean[N];
		
		
		for(int n=0; n<N; n++) {
			map[n] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(map);
		//System.out.println(Arrays.toString(map));
		
		output = new ArrayList<Integer>();
		Max = Integer.MIN_VALUE;
		combi(0,M);
		System.out.println(Max);
		//차례대로 3개를 선택할까?
//		
//		
	}
}
