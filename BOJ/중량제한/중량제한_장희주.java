package 중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중량제한_장희주 {

	static class Node {
		int now, weight;

		public Node(int now, int weight) {
			super();
			this.now = now;
			this.weight = weight;
		}

	}

	static int N, M;
	static List<Node>[] list;
	static int n1, n2;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, c));
			list[b].add(new Node(a,c));

		}
		st = new StringTokenizer(br.readLine());
		n1 = Integer.parseInt(st.nextToken())-1;
		n2 = Integer.parseInt(st.nextToken())-1;
		
		int result=dikstra();
		System.out.println(result);

	}

	static int dikstra(){
		
		boolean visited[]=new boolean [N];
		
		PriorityQueue<Node>pq=new PriorityQueue<>((a,b)->Integer.compare(b.weight, a.weight));
		pq.add(new Node(n1,Integer.MAX_VALUE));
		while(!pq.isEmpty()) {
			Node c=pq.poll();
			int now=c.now;
			int weight=c.weight;
			
			visited[now]=true;
			if(now==n2) {
				return weight;
			}
			for(Node edge : list[now]) {
					int next=edge.now;
					int nWeight=Math.min(edge.weight,weight);
					if(!visited[next]) {
						pq.add(new Node(next,nWeight));
					}
			
				
			}
		}
		return 0;
		
	}

}