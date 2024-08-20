package bfs_dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 인구이동 {
    
    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, l, r; // n: 격자의 크기, l: 최소 인구 차이, r: 최대 인구 차이
    static int[][] map; // 각 국가의 인구 수를 저장할 배열
    static int[][] union; // 각 셀의 연합 그룹을 기록할 배열
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동을 위한 x 방향 변화량
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 이동을 위한 y 방향 변화량

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        //map 배열에 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalCount = 0; // 인구 이동 횟수를 저장할 변수
        
        while (true) {
            union = new int[n][n]; // 연합 그룹을 기록할 배열 초기화
            for (int[] row : union) {
                Arrays.fill(row, -1); // -1로 초기화하여 방문하지 않은 상태를 표시
            }

            int index = 0; // 연합의 인덱스
            boolean hasMovement = false; // 인구 이동 여부를 확인하는 플래그

            // 각 셀에 대해 연합을 형성
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (union[i][j] == -1) { // 방문하지 않은 경우
                        if (process(i, j, index)) { // 연합을 형성하고 인구 이동 처리
                            hasMovement = true; // 인구 이동이 발생했음을 표시
                        }
                        index++; // 다음 연합의 인덱스로 이동
                    }
                }
            }

            // 더 이상 인구 이동이 없으면 종료
            if (!hasMovement) {
                break;
            }
            totalCount++; // 인구 이동 횟수 증가
        }

        System.out.println(totalCount); // 인구 이동 횟수를 출력
        br.close(); // BufferedReader를 닫아 리소스를 해제
    }

    // 특정 위치에서 출발하여 모든 연합을 체크한 뒤에 데이터 갱신
    static boolean process(int x, int y, int index) {
        List<Node> united = new ArrayList<>(); // 연합에 속한 국가 리스트
        Queue<Node> q = new LinkedList<>(); // BFS를 위한 큐
        q.add(new Node(x, y)); // 시작 위치를 큐에 추가
        union[x][y] = index; // 현재 위치의 연합 인덱스 설정
        int summary = map[x][y]; // 현재 연합의 총 인구수
        int count = 1; // 현재 연합의 국가 수

        united.add(new Node(x, y)); // 연합에 시작 위치 추가

        while (!q.isEmpty()) {
            Node node = q.poll(); // 큐에서 노드 제거
            int cx = node.x;
            int cy = node.y;

            // 상하좌우 방향으로 인접 국가 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i]; // 이동할 x 좌표
                int ny = cy + dy[i]; // 이동할 y 좌표

                // 이동할 위치가 격자 내에 있고, 아직 방문하지 않은 경우
                if (0 <= nx && nx < n && 0 <= ny && ny < n && union[nx][ny] == -1) {
                    // 인구 차이가 범위 내에 있는 경우
                    if (l <= Math.abs(map[nx][ny] - map[cx][cy]) && Math.abs(map[nx][ny] - map[cx][cy]) <= r) {
                        q.add(new Node(nx, ny)); // 큐에 인접 국가 추가
                        union[nx][ny] = index; // 연합 인덱스 설정
                        summary += map[nx][ny]; // 총 인구수 업데이트
                        count++; // 국가 수 증가
                        united.add(new Node(nx, ny)); // 연합에 국가 추가
                    }
                }
            }
        }

        // 연합을 형성한 경우, 인구수 갱신
        if (count > 1) {

            for (Node pos : united) {
                map[pos.x][pos.y] = summary / count; // 연합에 속한 국가들의 인구수를 평균으로 업데이트
            }
            return true; // 연합이 형성되었음을 반환
        }
        return false; // 연합이 형성되지 않았음을 반환
    }
}
