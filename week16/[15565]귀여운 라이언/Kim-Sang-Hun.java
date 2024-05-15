import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*
Deque를 이용한다.
숫자가 1이면 deque에 넣고 size가 k일 경우만 집합의 크기를 계산한다.
메모리 84000kb, 시간 496ms
 */
public class JUN15565_귀여운라이언 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        Deque<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur == 1) {
                qu.add(i);
                if (qu.size() > k) {
                    qu.pollFirst();
                }
                if (qu.size() == k) {
                    min = Math.min(qu.getLast() - qu.getFirst() + 1, min);
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
