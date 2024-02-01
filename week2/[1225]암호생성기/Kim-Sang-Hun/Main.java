import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static Deque<Integer> deque;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            s.nextInt();
            //Deque를 이용합니다.
            deque = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                deque.add(s.nextInt());
            }
            //무한반복문을 돌며 deque에 계속 뺀 값을 넣어줍니다.
            //만약 넣은 값이 0보다 작거나 같다면 0을 넣어주고 loop를 끝냅니다.
            loop:
            while (true) {
                for (int i = 1; i <= 5; i++) {
                    int tmp = deque.pollFirst() - i;
                    deque.addLast(tmp);
                    if (tmp <= 0) {
                        deque.pollLast();
                        deque.addLast(0);
                        break loop;
                    }
                }
            }

            System.out.printf("#%d ", tc);
            for (int j = 0; j < 8; j++) {
                System.out.print(deque.pollFirst() + " ");
            }
            System.out.println();
        }
    }
}
