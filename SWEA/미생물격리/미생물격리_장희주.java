package 미생물격리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미생물격리_장희주 {

	static class Node {
		int x; // 세로
		int y; // 가로
		int miCnt; // 미생물갯수
		int direction; // 방향

		public Node(int x, int y, int miCnt, int direction) {
			this.x = x;
			this.y = y;
			this.miCnt = miCnt;
			this.direction = direction;
		}

	}

	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int sum;
	static int n, m, k;
	static List<Node> mi;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		int T;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			// 셀의 개수
			n = Integer.parseInt(st.nextToken());
			// 격리 시간
			m = Integer.parseInt(st.nextToken());
			// 미생물 군집의 개수
			k = Integer.parseInt(st.nextToken());
			// 미생물의 군집정보저장하는 리스트 초기화
			mi = new ArrayList<>();
			//리스트에 저장
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())-1; //방향을 0부터
				mi.add(new Node(x, y, cnt, d));
			}
			// 결과값
			sum = 0;
			//m시간동안 이동 및 합치는 작업 수행
			for(int i=m;i>0;i--) {
				move(i);
			}
			//남아있는 미생물 수를 모두 더함
			for(Node node : mi) {
				sum+=node.miCnt;
			}
			// 결과출력
			System.out.println("#" + test_case + " " + sum);
		}

	}
	//미생물 군집 이동 및 합치는 함수
	static void move(int Time) {
		//시간이 0이하이면 종료
		if(Time<=0) {
			return;
		}
		//미생물 군집 이동 처리
		for(Node m : mi) {
			int x=m.x;
			int y=m.y;
			int cnt=m.miCnt;
			int d=m.direction;
			int nx=x+dx[d]; //다음위치
			int ny=y+dy[d];
			m.x=nx;//이동후 위치 업데이트
			m.y=ny;
			//경계에 도달하면
			if(nx==0 || ny==0 || nx==(n-1) || ny==(n-1)) {
				//수가 절반 
				m.miCnt=cnt/2;
				//방향 반대로 바뀜
				switch(d) {
				case 0 : 
					m.direction=1;
					break;
				case 1:
					m.direction=0;
					break;
				case 2:
					m.direction=3;
					break;
				case 3:
					m.direction=2;
					break;
				}	
			}
		}
		//같은 위치에 있는 미생물 군집 합치는 처리
		boolean[] visited=new boolean[mi.size()]; //방문처리
		
		for(int i=0;i<mi.size();i++) {
			//미생물 수가 0이면 건너뜀
			if(mi.get(i).miCnt==0) continue;
			//이미 합쳐진 군집이면 건너뜀
			if(visited[i]) continue;
			//해당 위치에서의 총 미생물 수
			int total=mi.get(i).miCnt;
			//가장 큰 미생물 수
			int maxValue=mi.get(i).miCnt;
			//방향 유지
			int dir=mi.get(i).direction;
			
			for(int j=i+1;j<mi.size();j++) {
				//같은 위치에 있는 미생물 군집 찾기
				if((mi.get(i).x==mi.get(j).x) && (mi.get(i).y==mi.get(j).y)){
					visited[j]=true; //방문체크
					//미생물 수가 0이면
					if(mi.get(j).miCnt==0) continue;
					//미생물 수 합산
					total+=mi.get(j).miCnt;
					if((mi.get(j).miCnt)>maxValue) {
						//가장 큰 미생물 수 업데이트
						maxValue=mi.get(j).miCnt;
						//방향 업데이트
						dir=mi.get(j).direction;
					}
					//합쳐진 군집의 미생물 수를 0으로 만듬
					mi.get(j).miCnt=0;
				}
			}
			//i번째 군집에 합산된 값을 반영
			mi.get(i).miCnt=total;
			mi.get(i).direction=dir;
		}
		
	}
	
	

}
