import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_17471_게리맨더링_곽승미 {
	
	static int N, people[],result;
	static boolean isSelected[], visited[];
	static List<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		people = new int[N];
		isSelected = new boolean[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int to = Integer.parseInt(st.nextToken());
				graph.get(i).add(to-1);
			}
		}
		result = Integer.MAX_VALUE;
		subset(0);
		
		if(result == Integer.MAX_VALUE) {
			result = -1;
		}
		System.out.println(result);
	}
	static void subset(int idx) {
		if(idx == N) {
			List<Integer> a = new ArrayList<>();
			List<Integer> b = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					a.add(i);
				}
				else {
					b.add(i);
				}
			}
			if(a.size() == 0 || b.size() == 0) {
				return;
			}
			if(check(a) && check(b)) {
				getDiff();
			}
			return;
			
		}
		isSelected[idx] = true;
		subset(idx+1);
		isSelected[idx] = false;
		subset(idx+1);
	}
	static boolean check(List<Integer> list) {  
		Queue<Integer> q = new LinkedList<>();
		visited = new boolean[N];
		visited[list.get(0)] = true;
		q.offer(list.get(0));
		
		int cnt = 1;
		while(!q.isEmpty()) {
			int v = q.poll();
			
			for (int i = 0; i < graph.get(v).size(); i++) {
				int next = graph.get(v).get(i);
				
				if(list.contains(next) && !visited[next]) {
					q.offer(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		if(cnt == list.size()) {
			return true;
		}else {
			return false;
		}
	}
	static void getDiff() {
		int cntA = 0, cntB = 0;
		for (int i = 0; i < N ; i++) {
			if(isSelected[i]) {
				cntA += people[i];
			}else {
				cntB += people[i];
			}
		}
		int min = Math.abs(cntA-cntB);
		result = Math.min(result, min);
	}
	
}