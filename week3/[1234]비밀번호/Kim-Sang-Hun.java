import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= 10; tc++) {
            int N = s.nextInt();
            String str = s.next();
          // stack처럼 마지막 원소 확인하고 queue처럼 순서대로 출력하기 위해 deque 사용
            Deque<Character> deque = new ArrayDeque<>();
          // stack처럼 마지막 원소가 현재 보고 있는 원소와 같을 경우 deque에서 마지막 원소를 제거
          // 스택이 비어있거나 원소가 다른 경우 deque에 현재 보고 있는 원소를 추가
            for (int i = 0; i < N; i++) {
                if (deque.isEmpty()) {
                    deque.addLast(str.charAt(i));
                } else if (deque.peekLast() == str.charAt(i)) {
                    deque.pollLast();
                } else {
                    deque.addLast(str.charAt(i));
                }
            }
            sb.append("#" + tc + " ");
            int dequeSize = deque.size();
            for (int i = 0; i < dequeSize; i++) {
                sb.append(deque.pollFirst());
            }
            sb.append(System.lineSeparator());
        }
        System.out.print(sb);
    }
}
