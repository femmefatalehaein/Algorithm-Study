package 안테나;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 안테나_장희주 {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		//집의수
		int n=sc.nextInt();
		//리스트로 저장
		List<Integer> homes=new ArrayList<>();
		for(int i=0;i<n;i++) {
			int home=sc.nextInt();
			homes.add(home);
		}
		//정렬
		Collections.sort(homes);
		//중간값찾기
		System.out.println(homes.get((n-1)/2));
		

	}

}
