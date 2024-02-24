import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* idx를 늘려가면서 왼쪽부터 하나씩 들고 랜덤한 계란을 치면 된다.
* dfs로 해결하였다.
* idx의 계란의 durability가 0 이하이거나 칠 계란이 없어 치지 않았다면 idx를 늘려주었다.
*/
public class JUN16987_계란으로계란치기 {

    static class Egg {
        int durability;
        int weight;
        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static int N, maxBrokenEgg;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(durability, weight);
        }
        dfs(0);
        System.out.println(maxBrokenEgg);
    }

    public static void dfs(int idx) {
        if (idx == N) {
            int countBrokenEgg = 0;
            for (int i = 0; i < eggs.length; i++) {
                if (eggs[i].durability <= 0) {
                    ++countBrokenEgg;
                }
            }
            maxBrokenEgg = Math.max(maxBrokenEgg, countBrokenEgg);
            return;
        }
        Egg left = eggs[idx];
        if (left.durability <= 0) {
            dfs(idx + 1);
            return;
        }
        boolean hit = false;
        for (int i = 0; i < N; i++) {
            if (i == idx) continue;
            Egg right = eggs[i];
            if (right.durability <= 0)  {
                continue;
            }
            hit = true;
            right.durability -= left.weight;
            left.durability -= right.weight;
            dfs(idx + 1);
            right.durability += left.weight;
            left.durability += right.weight;
        }
        if (!hit) dfs(idx + 1);
    }
}
