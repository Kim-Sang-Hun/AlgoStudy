import java.util.*;
import java.io.*;
/*
  Title: 여행 가자
  Tier: Gold 4
  Algorithm: Disjoint Set
  Constraint: 2 Second, 128MB
  Comment: Programmers에도 비슷한 문제가 있었고, 코테에서 자주 볼 지는 모르겠으나 은근 비슷한 레퍼토리로 많은 문제에 적용된 바 있습니다.
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[] par;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        par = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            par[i] = i;
        }
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < i; ++j) {
                if(st.nextToken().equals("1")){
                    union(i, j);
                }
            }
            for (int j = i; j <= n; ++j) {
                st.nextToken();
            }
        }
    }

    static int find(int x){
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;
        par[x] = y;
    }

    static void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        start = find(start);
        for (int i = 1; i < m; ++i) {
            int next = Integer.parseInt(st.nextToken());
            if (start != find(next)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
