package BaekJoon;

import java.util.Scanner;

/*
combi와 비슷하지만 더했을때 중복되는 값을 제거해줘야 한다.
그래서 visited배열을 만들고(최대 1000까지니까 1001)
dfs방식으로 진행한다.

 */
public class JUN16922_로마숫자만들기 {

    static int N, cnt;
    static int[] roman = {1, 5, 10, 50};
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[1001];
        sub(0, 0, 0);
        System.out.println(cnt);
    }

    private static void sub(int n, int idx, int sum) {
        if (n == N) {
            if (!visited[sum]) {
                visited[sum] = true;
                cnt++;
            }
            return;
        }
        // idx부터 시작하는 이유는 중복해서 넣는 것을 방지하기 위함.
        for (int i = idx; i < 4; i++) {
            sub(n+1, i, sum + roman[i]);
        }
    }
}
