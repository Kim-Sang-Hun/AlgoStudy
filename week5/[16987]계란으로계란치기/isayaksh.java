import java.io.*;
import java.util.*;

public class isayaksh {

    private static int N;
    private static int answer = 0;
    
    private static Egg[] eggInfo;
    private static Egg[] taskSet;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        eggInfo = new Egg[N];
        taskSet = new Egg[N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            eggInfo[i] = new Egg(S, W);
            taskSet[i] = new Egg(S, W);
        }

        beatingEggs(0);
        System.out.println(answer);

    }

    private static void beatingEggs(int current) {

        answer = Math.max(answer, countBorkenEgg());

        // 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료
        if(current == N) {
            return;
        }

        // 현재 계란(egg)이 부서진 계란일 경우
        if(isBorken(current)) {
            beatingEggs(current+1);
            return;
        }

        for(int other = 0; other < N; other++) {
            
            if(isBorken(other) || current == other) continue;

            taskSet[current].S -= eggInfo[other].W;
            taskSet[other].S -= eggInfo[current].W;

            beatingEggs(current + 1);

            taskSet[current].S += eggInfo[other].W;
            taskSet[other].S += eggInfo[current].W;

        }

    }

    private static int countBorkenEgg() {
        int brokenEgg = 0;
        for(int i = 0; i < N; i++) {
            if(isBorken(i)) brokenEgg++;
        }
        return brokenEgg;
    }

    private static boolean isBorken(int i) {
        return taskSet[i].S <= 0;
    }

    static class Egg {
        int S;
        int W;

        Egg(int S, int W) {
            this.S = S;
            this.W = W;
        }
        
    }
}
