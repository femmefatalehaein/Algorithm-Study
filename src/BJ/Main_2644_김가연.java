package D_1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2644_김가연 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int front = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		int rp = Integer.parseInt(bf.readLine());
		
		ArrayList[] family = new ArrayList[n];
		
		for (int i = 0 ; i < n ; i++) {
			family[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0 ; i < rp ; i ++) {
			 st = new StringTokenizer(bf.readLine());
			 
			 int a = Integer.parseInt(st.nextToken()) - 1;
			 int b = Integer.parseInt(st.nextToken()) - 1;
			 
			 family[b].add(a);
		}
		
		ArrayList<int []> afamily = new ArrayList<>();
		ArrayList<int []> bfamily = new ArrayList<>();
		
		afamily.add(new int[] {front, 0});
		bfamily.add(new int[] {end, 0});
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(front);
		
		if (family[front].size() > 0) {
			
			int depth = 1;
			
			while(!q.isEmpty()) {
				
				int qparent = q.poll();
				if (family[qparent].size() > 0) {
					
					int p = (int) family[qparent].get(0);
					afamily.add(new int[] {p, depth});
					depth ++;
					q.add(p);
					
				}
				
			}
			
		}
		
		q = new LinkedList<>();
		
		q.add(end);
		
		if (family[end].size() > 0) {
			
			int depth = 1;
			
			while(!q.isEmpty()) {
				
				int qparent = q.poll();
				if (family[qparent].size() > 0) {
					
					int p = (int)family[qparent].get(0);
					bfamily.add(new int[] {p, depth});
					depth ++;
					q.add(p);
				}
				
			}
			
		}
		
//		for (int i = 0 ; i < afamily.size() ; i++) {
//			System.out.println(Arrays.toString(afamily.get(i)));
//		}
//		
//		System.out.println("*****************");
//		
//		for (int i = 0 ; i < bfamily.size() ; i++) {
//			System.out.println(Arrays.toString(bfamily.get(i)));
//		}
//		
//		System.out.println("-----------------");
		
		int answer = 0;
		
		if (afamily.get(afamily.size() - 1)[0] != bfamily.get(bfamily.size() - 1)[0]) {
			System.out.println(-1);
		} else {
			for (int i = 0 ; i < afamily.size() ; i++) {
				for (int j = 0 ; j < bfamily.size() ; j++) {
					if (afamily.get(i)[0] == bfamily.get(j)[0]) {
						answer = afamily.get(i)[1] +  bfamily.get(j)[1];
						System.out.println(answer);
						break;
					}
				}
				
				if (answer != 0) {
					break;
				}
			}
		}
		
		
	}

}
