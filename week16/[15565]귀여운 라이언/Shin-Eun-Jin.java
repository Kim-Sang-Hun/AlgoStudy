import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15565_귀여운라이언 {
    static int N, K;
    static int[] dolls;
    static ArrayList<Integer> lion;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dolls = new int[N];
        lion = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());

            if (dolls[i] == 1) {
                lion.add(i);
            }
        }

        if (lion.size() < K) {
            System.out.println(-1);
            return;
        }

        int ans = Integer.MAX_VALUE;

        int sp = 0;
        int ep = K - 1;
        while (true) {
            if (ep == lion.size()) {
                break;
            }

            ans = Math.min(ans, lion.get(ep) - lion.get(sp) + 1);
            sp++;
            ep++;
        }

        System.out.println(ans);
    }
}
