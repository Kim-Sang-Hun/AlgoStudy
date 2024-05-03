import java.util.*;
import java.io.*;
/*
  Title: 부분수열의 합 2
  Tier: Gold 1
  Algorithm: Data Structure
  Constraint: 1 Second, 256MB
  Comment: 다른 사람들은 이분 탐색, 투 포인터로 푸는데, 조금 단순하게 2^20 2개로 나눠서 시간 초과가 발생하지 않도록 풀어봤습니다.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, s;
    static long answer;
    static int[] a;
    static Map<Integer, Integer> map = new HashMap<>();

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        answer = 0;
    }

    static void left(int idx, int sum) {
        if (idx == (n >> 1)) {
            if(map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else{
                map.put(sum, 1);
            }
            return;
        }
        left(idx + 1, sum);
        left(idx + 1, sum + a[idx]);
    }

    static void right(int idx, int sum) {
        if (idx == n) {
            if(!map.containsKey(s - sum)) return;
            answer += map.get(s - sum);
            return;
        }
        right(idx + 1, sum);
        right(idx + 1, sum + a[idx]);
    }

    static void solution() {
        left(0, 0);
        right(n >> 1, 0);
        if(s == 0) --answer;
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
