import java.util.*;

/*
메모리 17600, 시간 208
그냥 간단하게 두 가지 조건 모두 진행해본다
*/
public class Main {
    
    static int min = Integer.MAX_VALUE;
    static int A, B;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        A = s.nextInt();
        B = s.nextInt();
        s.close();
        findMin(A,0);
        System.out.println(min == Integer.MAX_VALUE? -1 : min + 1);
    }

    static void findMin(long cur, int cnt) {
        if (cur > B) {
            return;
        }
        if (cur == B) {
            min = Math.min(min, cnt);
            return;
        }
        findMin(cur * 10 + 1, cnt + 1);
        findMin(cur * 2, cnt + 1);
    }
}
