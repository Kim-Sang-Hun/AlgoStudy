import java.util.*;
import java.io.*;
/*
  Title: 귀여운 라이언
  Tier: Silver 1
  Algorithm: List(Data Structure)
  Constraint: 1 Second, 256MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, k, answer;
    static int[] a;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        answer = (int)1e9;
    }

    static void solution() {
        List<Integer> lion = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if(a[i] == 1) lion.add(i);
        }
        if(lion.size() < k) {
            answer = -1;
            return;
        }
        int len = lion.size() - k + 1;
        for (int i = 0; i < len; ++i) {
            int s = lion.get(i);
            int e = lion.get(i + k - 1);
            answer = Math.min(answer, e - s + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(answer);
    }
}
