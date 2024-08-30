package 국영수;



import java.io.*;
import java.util.*;

public class Main_10825_박해인 {

	//클래스 접근이였다니...!!..ㅠ
	 static class Student{
	        String name;
	        int kor;
	        int eng;
	        int math;

	        public Student(String name, int kor, int eng, int math) {
	            this.name = name;
	            this.kor = kor;
	            this.eng = eng;
	            this.math = math;
	        }
	    }
	
	public static void main(String [] args) throws IOException {
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		

		ArrayList<Student> students = new ArrayList<Student>();
		for(int n=0; n<N; n++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			students.add( new Student(st.nextToken(),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		
		//Comparator 재정의 하는법...... 외우자..!!!
	    Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.kor == o2.kor) {
                    if (o1.eng == o2.eng) {
                        if (o1.math == o2.math) {
                            return o1.name.compareTo(o2.name);
                        }
                        return o2.math - o1.math;
                    }
                    return o1.eng - o2.eng;
                }
                return o2.kor - o1.kor;
            }
        });
		
	    for(int i=0; i<students.size(); i++) {
	    	System.out.println(students.get(i).name);
	    }
		
		
		
	}
}
