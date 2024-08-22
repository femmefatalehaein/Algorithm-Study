package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 국영수 {
	
	static class Student{
		String name;
		int koreanScore;
		int engScore;
		int mathScore;
		public Student(String name, int koreanScore, int engScore, int mathScore) {
			this.name = name;
			this.koreanScore = koreanScore;
			this.engScore = engScore;
			this.mathScore = mathScore;
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//학생수
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st=null;
		//리스트로 저장
		List<Student>list=new ArrayList<>();
		for (int i = 0; i < n; i++) {
              st = new StringTokenizer(br.readLine());
              String name=st.nextToken();
              int kor=Integer.parseInt(st.nextToken());
              int eng=Integer.parseInt(st.nextToken());
              int math=Integer.parseInt(st.nextToken());
              //리스트에 넣어주기
              list.add(new Student(name,kor,eng,math));
            }
		//재정렬하기
              list.sort(new Comparator<Student>() {

				@Override
				public int compare(Student o1, Student o2) {
					//국어점수가 같다면
					if(o1.koreanScore==o2.koreanScore) {
						//영어점수가 같다면
						if(o1.engScore==o2.engScore) {
							//수학점수가 같다면
							if(o1.mathScore==o2.mathScore) {
								//이름 사전순으로 재정렬하기
								return o1.name.compareTo(o2.name);
							}
							//수학점수내림차순
							return o2.mathScore-o1.mathScore;
						}
						//영어 오름차순
						return o1.engScore-o2.engScore;
					}//국어내림차순
					return o2.koreanScore-o1.koreanScore;
				}
            	  
              });
              //결과출력
              for(Student s : list) {
            	System.out.println(s.name);
              }
              
		

	}

}
