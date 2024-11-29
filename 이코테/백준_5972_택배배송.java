import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 5972. 택배배송
 */
public class 백준_5972_택배배송 {
    static class Node implements Comparable<Node> {
        int v, weight;
        Node next;

        public Node(int v, int weight, Node next) {
            this.v = v;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight; // 가중치 기준 오름차순 정렬
        }
    }

    static int N, M, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1-based index 처리 위해 N+1 크기로 설정
        Node[] list = new Node[N + 1];

        // 간선 정보 입력 (양방향 그래프)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 가중치

            list[a] = new Node(b, c, list[a]);
            list[b] = new Node(a, c, list[b]); // 양방향 연결
        }

        result = getMin(list, 1, N); // 1번 농장에서 N번 농장으로 가는 최소 비용
        System.out.println(result);
    }

    static int getMin(Node[] list, int start, int end) {
        final int INF = Integer.MAX_VALUE;
        int[] minDistance = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        Arrays.fill(minDistance, INF);
        minDistance[start] = 0; 

        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        pQueue.offer(new Node(start, 0, null)); // 시작 노드

        while (!pQueue.isEmpty()) {
            Node current = pQueue.poll();
            int curV = current.v;

            if (visited[curV]) continue; // 이미 방문한 노드라면 스킵
            visited[curV] = true;

            // 도착 노드에 도달하면 최소 거리를 반환
            if (curV == end) return minDistance[end];

            // 인접 노드 탐색
            for (Node temp = list[curV]; temp != null; temp = temp.next) {
                if (!visited[temp.v] && minDistance[temp.v] > minDistance[curV] + temp.weight) {
                    minDistance[temp.v] = minDistance[curV] + temp.weight;
                    pQueue.offer(new Node(temp.v, minDistance[temp.v], null));
                }
            }
        }

        return -1; // 경로를 찾지 못한 경우
    }
}
