package D_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3190_김가연 {
	
	static int n;
	static int [][] game;
	
	static boolean ifalive(int x, int y) {
		if (((0 <= x) && (x < n)) && ((0 <= y) && (y < n))) {
			if ((game[x][y] != 2)) {
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bf.readLine());
		
		game = new int [n][n];
		
		int rp = Integer.parseInt(bf.readLine());
		for (int i = 0 ; i < rp ; i++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			game[x][y] = 1;
			
		}
		
		rp = Integer.parseInt(bf.readLine());
		Queue<String[]> list = new LinkedList<>();
		for (int i = 0 ; i < rp ; i++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			String sc = st.nextToken();
			String dir = st.nextToken();
			
			list.add(new String [] {sc, dir});
			
		}
		
		int [][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		Deque<int[]> sn = new ArrayDeque<>();
		sn.add(new int[] {0, 0});
		
		// 머리 좌표
		int hx = 0, hy = 0;
		game[hx][hy] = 3;
		
		// 머리 방향
		int hd = 0;
		
		int cnt = 1;
		
		while(true) {
				
			if (!ifalive(hx + dir[hd][0], hy + dir[hd][1])) {
				break;
			}
			
			// 사과 먹을때
			if (game[hx + dir[hd][0]][hy + dir[hd][1]] == 1) {
				
				// 새로운 머리 생성
				hx = hx + dir[hd][0];
				hy = hy + dir[hd][1];
				sn.addFirst(new int[] {hx, hy});
				game[hx][hy] = 2;
			} else {

				// 새로운 머리 생성
				hx = hx + dir[hd][0];
				hy = hy + dir[hd][1];
				sn.addFirst(new int[] {hx, hy});
				
				// 꼬리 한칸 삭제
				int [] tail = sn.pollLast();
				
				// 원래 꼬리 0으로 만들기
				game[tail[0]][tail[1]] = 0;
				// 새로운 머리 만들기
				game[hx][hy] = 2;
			}
			
			if (list.size() > 0) {
				if (Integer.parseInt(list.peek()[0]) == cnt) {
					String [] l = list.poll();
					
					if (l[1].equals("D")) {
						hd = (hd + 1) % 4;
					} else {
						hd = (hd - 1) % 4;
						if (hd < 0) {
							hd += 4;
						}
					}
				}
			}
			
			cnt += 1;

		}
		
		System.out.println(cnt);

	}

}
