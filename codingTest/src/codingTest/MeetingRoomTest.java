package codingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MeetingRoomTest {
	
	
	static class Meeting implements Comparable<Meeting>{
		
		int start, end;
		
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			
			//종료시간이 빠른 순, 같다면 시작시간이 빠른 수 
			return this.end != o.end ? this.end-o.end: this.start - o.start;
		}
		
		
	}

	
	public static void main(String [] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Meeting[] meetings = new Meeting[N];
		
		for(int i = 0; i<N; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		// sort 했고
		Arrays.sort(meetings);
		ArrayList<Meeting> result = new ArrayList<>();
		
		// 가장 일찍 끝나는 애를 넣는다.
		result.add(meetings[0]); //첫회의 스케쥴링에 넣기
		
		for(int i = 0; i<N; i++) {
			
			// 스케줄링에 넣어져 있는 가장 마지막애의 종료시간이  넣을까 말까 고민하는 미팅 시간의 시작시간 전에 
			// 넣자.
			if(result.get(result.size()-1).end <= meetings[i].start) {
				result.add(meetings[i]);
			}
			
		}
		
		System.out.println(result.size());
		
		for(Meeting meeting : result) {
			System.out.println(meeting.start+"-"+meeting.end);
		}
		
		
	}

}
