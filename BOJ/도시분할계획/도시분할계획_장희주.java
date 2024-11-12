package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 도시분할계획_장희주 {
	
	static class Node{
		int a,b,weight;

		public Node(int a, int b, int weight) {
		
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		
		
	}
	
	static int N, M;
	static List<Node> list;
	static int result;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		parent=new int[N];
		list=new ArrayList<>();
		for(int i=0;i<M;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken())-1;
			int end=Integer.parseInt(st.nextToken())-1;
			int weight=Integer.parseInt(st.nextToken());
			list.add(new Node(start,end,weight));
		}
		result=0;
		int cnt=0;
		Arrays.fill(parent, -1);
		Collections.sort(list,(a,b)->Integer.compare(a.weight, b.weight));
		for(int i=0;i<list.size();i++) {
			
			if(cnt==N-2) {
				break;
			}
			if(union(list.get(i).a,list.get(i).b)) {
				result+=list.get(i).weight;
				cnt++;
			}
		}
		System.out.println(result);

	}
	
	static int findset(int a) {
		if(parent[a]<0) {
			return a;
		}
		return parent[a]=findset(parent[a]);
	}
	static boolean union(int a,int b) {
		int aRoot=findset(a);
		int bRoot=findset(b);
		if(aRoot==bRoot) {
			return false;
			
		}
		parent[bRoot]=aRoot;
		return true;
	}
}
