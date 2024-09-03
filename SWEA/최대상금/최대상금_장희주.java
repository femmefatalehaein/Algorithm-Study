package 최대상금;
import java.util.*;

public class 최대상금_HashSet_장희주 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();  // 테스트 케이스 개수 입력
        
        for (int test_case = 1; test_case <= T; test_case++) {
            String num = sc.next();  // 숫자 문자열 입력
            int cnt = sc.nextInt();  // 교환 횟수 입력
            
            // 현재 가능한 숫자 조합들을 저장하는 Set
            Set<String> now = new HashSet<>();
            now.add(num);  // 초기 숫자를 Set에 추가
            
            // 다음 단계에서 가능한 숫자 조합들을 저장할 Set
            Set<String> next = new HashSet<>();
            
            // 주어진 교환 횟수만큼 반복
            for (int k = 0; k < cnt; k++) {
                // 현재 Set에 있는 모든 숫자 조합에 대해 교환 시도
                while (!now.isEmpty()) {
                    String s = now.iterator().next();  // Set에서 하나의 숫자 조합을 가져옴
                    now.remove(s);  // 가져온 숫자 조합을 Set에서 제거
                    
                    char[] sArr = s.toCharArray();  // 문자열을 문자 배열로 변환
                    
                    // 모든 가능한 두 자리 쌍에 대해 교환 시도
                    for (int i = 0; i < sArr.length - 1; i++) {
                        for (int j = i + 1; j < sArr.length; j++) {
                            // i와 j 위치의 문자 교환
                            char temp = sArr[i];
                            sArr[i] = sArr[j];
                            sArr[j] = temp;
                            
                            // 교환 결과를 next Set에 추가
                            next.add(new String(sArr));
                            
                            // 원래 상태로 복구
                            temp = sArr[i];
                            sArr[i] = sArr[j];
                            sArr[j] = temp;
                        }
                    }
                }
                // 다음 단계로 이동하기 위해 now와 next를 교체
                now = next;
                next = new HashSet<>();
            }
            
            // 최종적으로 가능한 숫자 조합 중 가장 큰 값을 찾음
            int answer = Integer.parseInt(Collections.max(now));
            // 결과 출력
            System.out.println("#" + test_case + " " + answer);
        }
        
        sc.close();  // Scanner 객체 닫기
    }
}
