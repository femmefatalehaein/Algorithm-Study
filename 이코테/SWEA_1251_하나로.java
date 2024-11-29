import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {
	static class Node implements Comparable<Node>{
		
		int to;
		long weight;
		
		public Node(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
        public int compareTo(Node o) {
            return Long.compare(this.weight, o.weight);
        }

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/s_input_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			long[] x = new long[N];
			long[] y = new long[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				x[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				y[i] = Long.parseLong(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());
			

            ArrayList<Node>[] list = new ArrayList[N];
            
            for (int i = 0; i < N; i++) {
            	list[i] = new ArrayList<>();
                
                for (int j = 0; j < N; j++) {
                    if(i == j) continue;
                    long dx = Math.abs(x[i] - x[j]);
                    long dy = Math.abs(y[i]- y[j]); 
                    list[i].add(new Node(j, dx * dx + dy * dy));
                }
                
            }
			boolean visited[] = new boolean[N];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0));

            long ans = 0;
            int cnt = 0;

            while (!pq.isEmpty() && cnt < N) {
                Node node = pq.poll();
                int v = node.to;
                double cost = node.weight;

                if (visited[v]) continue;

                visited[v] = true;
                ans += cost;
                if(++cnt == N) break;

                for (Node n : list[v]) {
                    if (!visited[n.to]) {
                        pq.add(n);
                    }
                }
            }

            System.out.println("#" + tc + " " + Math.round(ans * E));
		}
		
	}
}