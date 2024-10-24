import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1916_최소비용구하기 {
    // Node 클래스
    static class Node {
        int v;   // 정점
        int cost;   // 비용
        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 인접 리스트
    static boolean visited[]; // 방문 저장 배열
    static int dist[]; // 시작점부터 각 정점까지 최단 거리 저장 배열
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 정점 수
        int M = Integer.parseInt(br.readLine()); // 간선 수

        // 인접 리스트 생성
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());  // 출발 정점
            int b = Integer.parseInt(st.nextToken());  // 도착 정점
            int c = Integer.parseInt(st.nextToken());  // 비용
            graph.get(a).add(new Node(b, c));  // 출발 정점 a에서 도착 정점 b로 가는 비용 c
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());  // 시작 정점
        int d = Integer.parseInt(st.nextToken());  // 도착 정점

        sol(s);
        System.out.println(dist[d]);
    }

    // 다익스트라 알고리즘
    public static void sol(int start) {
        // 최단 거리 배열을 무한대로 초기화
        Arrays.fill(dist, INF);
        
        // 우선순위 큐 사용
        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
        queue.add(new Node(start, 0));  // 시작 정점을 큐에 추가
        dist[start] = 0;  // 시작 정점까지의 거리는 0

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Node now = queue.poll();  // 가장 비용이 작은 정점을 꺼냄

            // 현재 정점이 이미 방문된 적이 없다면
            if (!visited[now.v]) {
                visited[now.v] = true;  // 현재 정점을 방문 처리

                // 현재 정점에서 연결된 다른 정점들을 확인
                for (Node next : graph.get(now.v)) {
                    // 현재 정점을 거쳐서 다음 정점으로 가는 비용이 더 작다면
                    if (dist[next.v] > dist[now.v] + next.cost) {
                        dist[next.v] = dist[now.v] + next.cost;  // 최단 거리 갱신
                        queue.add(new Node(next.v, dist[next.v]));  // 갱신된 정점을 큐에 추가
                    }
                }
            }
        }
    }
}