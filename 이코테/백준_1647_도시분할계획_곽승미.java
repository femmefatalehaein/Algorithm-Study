import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1647_도시분할계획_곽승미 {

    static class Node implements Comparable<Node> {
        int idx, weight; // 노드 번호, 가중치

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node e) {
            return this.weight - e.weight;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수

        ArrayList<Node>[] nodes = new ArrayList[N + 1]; // 인접 리스트
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            // a: 시작 도시, b: 도착 도시, c: 간선 가중치
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 무방향 그래프
            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }

        boolean[] visited = new boolean[N + 1]; // 방문 확인 배열

        // cnt: 연결된 정점 수, res: 총 가중치 합, max: 가장 큰 가중치 저장
        int cnt = 0, res = 0, max = 0;

        // 우선순위 큐 사용
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        pq.add(new Node(1, 0)); // 1번 도시 큐에 삽입

        // 프림 알고리즘
        while (true) {
            Node cur = pq.poll();

            // 방문했던 노드라면 continue
            if (visited[cur.idx]) {
                continue;
            }

            visited[cur.idx] = true; // 현재 노드를 방문 처리
            res += cur.weight;  // 현재 간선의 가중치를 결과값에 추가
            max = Math.max(max, cur.weight);  // 가장 큰 간선 가중치 갱신
            cnt++;  // 연결된 정점 수 증가

            // 연결된 정점 수가 N개면 트리 완성
            if (cnt == N) break;

            // 현재 노드에 인접한 노드들 중 방문하지 않은 노드들을 우선순위 큐에 추가
            for (Node v : nodes[cur.idx]) {
                if (!visited[v.idx]) {
                    pq.add(new Node(v.idx, v.weight));
                }
            }
        }

        // 최소 신장 트리의 총 가중치에서 가장 큰 간선의 가중치를 뺌
        System.out.println(res - max);
    }
}